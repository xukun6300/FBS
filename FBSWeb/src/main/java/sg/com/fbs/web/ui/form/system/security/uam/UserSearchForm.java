package sg.com.fbs.web.ui.form.system.security.uam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;

import lombok.Getter;
import lombok.Setter;
import sg.com.fbs.common.form.BusinessQueryWebForm;
import sg.com.fbs.core.techinfra.util.DateUtil;
import sg.com.fbs.model.business.pojo.BasePojoRequest;
import sg.com.fbs.model.system.persistence.query.Criteria;
import sg.com.fbs.model.system.persistence.query.CriteriaIF;
import sg.com.fbs.model.system.persistence.query.Criterion;
import sg.com.fbs.model.system.persistence.query.CriterionIF;
import sg.com.fbs.model.system.persistence.query.Order;
import sg.com.fbs.model.system.persistence.query.Projection;
import sg.com.fbs.model.system.security.User;
import sg.com.fbs.model.system.security.uam.AccountStatusEnum;
import sg.com.fbs.model.user.UserRequest;
import sg.com.fbs.services.mastercode.exception.MasterCodeException;
import sg.com.fbs.services.mastercode.mgr.MasterCodeMgrBD;
import sg.com.fbs.web.ui.form.mapper.ResponseCrudQueryResultMapper;

/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu $
 * @Created 5:11:01 pm 8 Sep, 2015 $
 * 
 */
public class UserSearchForm extends BusinessQueryWebForm{

	private static final long serialVersionUID = 1L;
	
	public static final String ACCOUNT_STATUS = "accountStatus";
	
	@Setter
	@Getter
	private ResponseCrudQueryResultMapper queryResultMapper = null;
	
	@Setter
	@Getter
	private String name;
	
	@Setter
	@Getter
	private String email;
	
	@Setter
	@Getter
	private String accountStatus;
	
	@Setter
	@Getter
	private String userRole;
	
	
	public ResponseCrudQueryResultMapper getCrudQueryResultMapper(){
		if(queryResultMapper==null){
			queryResultMapper = new UserSearchFormQueryResultMapper();
		}
		return queryResultMapper;
	}
	
	private class UserSearchFormQueryResultMapper implements ResponseCrudQueryResultMapper{

		private static final long serialVersionUID = 1L;

		@SuppressWarnings("rawtypes")
		@Override
		public Collection<BasePojoRequest> mapQueryResult(Object results) {
			
			//pojo request will be set to response query result
			List<BasePojoRequest> content = new ArrayList<BasePojoRequest>();
			
			MasterCodeMgrBD masterCodeMgrBD = new MasterCodeMgrBD();
			
			for (Object result : (Collection)results) {
				Object[] _result = (Object[])result;
				UserRequest userRequest = new UserRequest();
				
				if(_result[0]!=null){
					userRequest.setId(Long.parseLong(_result[0].toString()));
				}
				
				if(_result[1]!=null){
					userRequest.setName(_result[1].toString());
				}
				if(_result[2]!=null){
					try {
						Long codeId = Long.parseLong(_result[2].toString()); 
						userRequest.setSalutation(masterCodeMgrBD.getMasterCodeValue(codeId));
					} catch (MasterCodeException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(_result[3]!=null){
					userRequest.setEmail(_result[3].toString());
				}
				if(_result[4]!=null){
					AccountStatusEnum accountStatusEnum = AccountStatusEnum.getEnumFromValue(_result[4].toString());
					if(accountStatusEnum!=null){
						userRequest.setAccountStatus(accountStatusEnum.getDescription());
					}	
				}
				if(_result[5]!=null){
					String lastSuccessLoginDate = DateUtil.convertDateToDateString((DateTime)_result[5], DateUtil.DEFAULT_DATETIME_FORMAT);
					userRequest.setLastSuccessLoginDate(lastSuccessLoginDate);
				}
				if(_result[6]!=null){
					String lastFailedLoginDate = DateUtil.convertDateToDateString((DateTime)_result[6], DateUtil.DEFAULT_DATETIME_FORMAT);
					userRequest.setLastFailedLoginDate(lastFailedLoginDate);
				}
				
				content.add(userRequest);
			}
					
			return content;
		}
		
	}
	
	
	
	
	@Override
	public CriteriaIF getSearchCriteria(HttpServletRequest request) {
		CriteriaIF criteria = new Criteria();
		criteria.setCriterion(getCriterion(request));
		
		Order[] orders = super.getOrder();
		if(request.getParameter(IS_ASCENDING)!=null && request.getParameter(IS_ASCENDING).trim().length()>0 && request.getParameter(SORTING_PROPERTY)!=null && request.getParameter(SORTING_PROPERTY).trim().length()>0){
			Order newOrder = new Order(request.getParameter(SORTING_PROPERTY), request.getParameter(IS_ASCENDING).equalsIgnoreCase("true") ? true : false);
			Order[] newOrders = {newOrder};
			orders = newOrders;
		}else{
			Order newOrder = new Order(User.NAME, true);
			Order[] newOrders = {newOrder};
			orders = newOrders;
		}
			
		criteria.setOrder(orders);		
		criteria.setProjection(getProjection());
		criteria.setFetchAll(super.isFetchAll());
		criteria.setRequestedPage(super.getRequestedPage());
		return criteria;
	}
	
	public CriterionIF[] getCriterion(HttpServletRequest request){
		CriterionIF[] criterion = super.getCriterion();
		
		if(criterion == null){
			List<CriterionIF> criterionList = new ArrayList<CriterionIF>();
			if(!StringUtils.isEmpty(name)){
				criterionList.add(new Criterion(User.NAME, name));
			}
			
			if(!StringUtils.isEmpty(email)){
				criterionList.add(new Criterion(User.LOGINID, email));
			}
			
			if(accountStatus!=null && !accountStatus.equals("-1")){
				criterionList.add(new Criterion(User.STATUS, accountStatus));
			}
			criterionList.add(new Criterion(User.ACT_IND, "Y"));
			criterion = criterionList.toArray(new CriterionIF[criterionList.size()]);
		}
		
		return criterion;
 	}
	
	public Projection[] getProjection(){
		Projection[] projections = {
				new Projection(Projection.SELECT_PROPERTY, User.ID),
				new Projection(Projection.SELECT_PROPERTY, User.NAME),
				new Projection(Projection.SELECT_PROPERTY, User.SALUTATION),
				new Projection(Projection.SELECT_PROPERTY, User.LOGINID),
				new Projection(Projection.SELECT_PROPERTY, User.STATUS),
				new Projection(Projection.SELECT_PROPERTY, User.LAST_SUCCESS_LOGIN_DATE),
				new Projection(Projection.SELECT_PROPERTY, User.LAST_FAILED_LOGIN_DATE)
		};
		return projections;
	}

}













