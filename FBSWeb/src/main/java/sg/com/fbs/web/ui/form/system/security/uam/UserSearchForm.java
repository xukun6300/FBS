package sg.com.fbs.web.ui.form.system.security.uam;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import lombok.Getter;
import lombok.Setter;
import sg.com.fbs.common.form.BusinessQueryWebForm;
import sg.com.fbs.model.business.pojo.BasePojoRequest;
import sg.com.fbs.model.system.persistence.query.Criteria;
import sg.com.fbs.model.system.persistence.query.CriteriaIF;
import sg.com.fbs.model.system.persistence.query.Criterion;
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

		@SuppressWarnings("rawtypes")
		@Override
		public Collection<BasePojoRequest> mapQueryResult(Object results) {
			List<BasePojoRequest> content = new ArrayList<BasePojoRequest>();
			
			MasterCodeMgrBD masterCodeMgrBD = new MasterCodeMgrBD();
			
			for (Object result : (Collection)results) {
				Object[] _result = (Object[])result;
				UserRequest userRequest = new UserRequest();
				if(_result[0]!=null){
					userRequest.setName(_result[0].toString());
				}
				if(_result[1]!=null){
					try {
						Long codeId = Long.parseLong(_result[1].toString()); 
						userRequest.setSalutation(masterCodeMgrBD.getMasterCodeValue(codeId));
					} catch (MasterCodeException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(_result[2]!=null){
					userRequest.setEmail(_result[2].toString());
				}
				if(_result[3]!=null){
					AccountStatusEnum accountStatusEnum = AccountStatusEnum.getEnumFromValue(_result[3].toString());
					if(accountStatusEnum!=null){
						userRequest.setAccountStatus(accountStatusEnum.getDescription());
					}	
				}
				if(_result[4]!=null){
					userRequest.setLastSuccessLoginDate(_result[4].toString());
				}
				if(_result[5]!=null){
					userRequest.setLastFailedLoginDate(_result[5].toString());
				}
				
				content.add(userRequest);
			}
					
			return content;
		}
		
	}
	
	
	
	
	@Override
	public CriteriaIF getSearchCriteria(HttpServletRequest request) {
		CriteriaIF criteria = new Criteria();
		criteria.appendCriterion(new Criterion(User.ACT_IND, "Y"));
		criteria.setProjection(getProjection());
		criteria.setFetchAll(super.isFetchAll());
		criteria.setRequestedPage(super.getRequestedPage());
		return criteria;
	}
	
	public Projection[] getProjection(){
		Projection[] projections = {
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













