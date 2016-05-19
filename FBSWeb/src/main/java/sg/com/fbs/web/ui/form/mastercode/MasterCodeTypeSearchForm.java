package sg.com.fbs.web.ui.form.mastercode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;

import sg.com.fbs.common.form.BusinessQueryWebForm;
import sg.com.fbs.model.business.pojo.BasePojo;
import sg.com.fbs.model.domain.enumeration.ActiveStatusEnum;
import sg.com.fbs.model.domain.mastercode.MasterCodeType;
import sg.com.fbs.model.system.persistence.query.Criteria;
import sg.com.fbs.model.system.persistence.query.CriteriaIF;
import sg.com.fbs.model.system.persistence.query.Criterion;
import sg.com.fbs.model.system.persistence.query.CriterionIF;
import sg.com.fbs.model.system.persistence.query.Order;
import sg.com.fbs.model.system.persistence.query.RestrictionType;

/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu Kun $
 * @Created May 17, 2016 $
 * 
 */
public class MasterCodeTypeSearchForm extends BusinessQueryWebForm{

	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(MasterCodeTypeSearchForm.class);
	
	protected boolean searchInactiveMasterCodes = false;
	
	private String remarks;
	
	private String remarksCode;
	
	private DateTime effectiveDateFrom;
	
	private DateTime effectiveDateTo;
	
    private DateTime expiryDateFrom;
	
	private DateTime expiryDateTo;
			
	public boolean isSearchInactiveMasterCodes() {
		return searchInactiveMasterCodes;
	}

	public void setSearchInactiveMasterCodes(boolean searchInactiveMasterCodes) {
		this.searchInactiveMasterCodes = searchInactiveMasterCodes;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getRemarksCode() {
		return remarksCode;
	}

	public void setRemarksCode(String remarksCode) {
		this.remarksCode = remarksCode;
	}

	public DateTime getEffectiveDateFrom() {
		return effectiveDateFrom;
	}

	public void setEffectiveDateFrom(DateTime effectiveDateFrom) {
		this.effectiveDateFrom = effectiveDateFrom;
	}

	public DateTime getEffectiveDateTo() {
		return effectiveDateTo;
	}

	public void setEffectiveDateTo(DateTime effectiveDateTo) {
		this.effectiveDateTo = effectiveDateTo;
	}

	public DateTime getExpiryDateFrom() {
		return expiryDateFrom;
	}

	public void setExpiryDateFrom(DateTime expiryDateFrom) {
		this.expiryDateFrom = expiryDateFrom;
	}

	public DateTime getExpiryDateTo() {
		return expiryDateTo;
	}

	public void setExpiryDateTo(DateTime expiryDateTo) {
		this.expiryDateTo = expiryDateTo;
	}

    public MasterCodeTypeSearchForm() {
		setCrudResponse(null);
	}
    
    @Override
    public String[] getDefaultOrders() {
    	String[] orders = {MasterCodeType.CREATED_DATE};
    	return orders;
    }

	@Override
	public CriteriaIF getSearchCriteria(HttpServletRequest request) {
		
		CriteriaIF searchCriteria = new Criteria();
		CriterionIF[] criterion = getCriterion();
		
		List<CriterionIF> criterionList = new ArrayList<CriterionIF>();
		if(criterion==null){
			logger.debug("in MasterCodeTypeSearchForm");
			
			//Name
			if(this.getRemarks()!=null){
				criterionList.add(new Criterion(MasterCodeType.REMARKS, this.getRemarks()));
			}
			
			//CodeKey
			if(this.getRemarksCode()!=null){
				criterionList.add(new Criterion(MasterCodeType.CATEGORY_TYPE, this.getRemarksCode()));
			}
			
			if(effectiveDateFrom!=null){
				criterionList.add(new Criterion(MasterCodeType.EFFECTIVE_DATE, RestrictionType.GREATER_OR_EQUAL, effectiveDateFrom.toDateTime()));
			}
			
			if(effectiveDateTo!=null){
				criterionList.add(new Criterion(MasterCodeType.EFFECTIVE_DATE, RestrictionType.LESS_OR_EQUAL, effectiveDateTo.toDateTime()));
			}
			
			if(expiryDateFrom!=null){
				criterionList.add(new Criterion(MasterCodeType.EXPIRY_DATE, RestrictionType.GREATER_OR_EQUAL, expiryDateFrom.toDateTime()));
			}
			
			if(expiryDateTo!=null){
				criterionList.add(new Criterion(MasterCodeType.EXPIRY_DATE, RestrictionType.GREATER_OR_EQUAL, expiryDateTo.toDateTime()));
			}
			
			criterion = criterionList.toArray(new CriterionIF[criterionList.size()]);
		}
		
		Order[] orders = getOrder();
		
		if(!StringUtils.isEmpty(request.getParameter(IS_ASCENDING)) && !StringUtils.isEmpty(request.getParameter(SORTING_PROPERTY))){
			Order newOrder = new Order(request.getParameter(SORTING_PROPERTY), request.getParameter(IS_ASCENDING).equals("true")?true:false);
			Order[] newOrders = {newOrder};
			orders = newOrders;
		}
		searchCriteria.setCriterion(criterion);
		searchCriteria.setOrder(orders);
		searchCriteria.setFetchAll(super.isFetchAll());
		searchCriteria.setRequestedPage(super.getRequestedPage());
		return searchCriteria;
	}
	
}

























