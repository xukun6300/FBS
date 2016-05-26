package sg.com.fbs.web.ui.form.mastercode;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import sg.com.fbs.common.form.BusinessQueryWebForm;
import sg.com.fbs.model.domain.mastercode.MasterCode;
import sg.com.fbs.model.domain.mastercode.MasterCodeType;
import sg.com.fbs.model.system.persistence.query.Criteria;
import sg.com.fbs.model.system.persistence.query.CriteriaIF;
import sg.com.fbs.model.system.persistence.query.Criterion;
import sg.com.fbs.model.system.persistence.query.CriterionIF;
import sg.com.fbs.model.system.persistence.query.Order;
import sg.com.fbs.model.system.web.ValueLabelPair;
import sg.com.fbs.web.ui.controller.mastercode.MasterCodeWebEnum;

/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu Kun $
 * @Created May 19, 2016 $
 * 
 */
public class MasterCodeTypeListForm extends BusinessQueryWebForm{

	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(MasterCodeTypeListForm.class);
	
	private ValueLabelPair categoryType = new ValueLabelPair();
	
	private String codeKey;
	
	private String remarks;
	
	private String sequenceJson;
	
	private long codeTypeId;
	
	private String txnType;
	
	protected boolean searchInactiveMasterCodes = false;
	
	private long deleteCodeId;
	
	public void setDeleteCodeId(long deleteCodeId) {
		this.deleteCodeId = deleteCodeId;
	}
	
	public long getDeleteCodeId() {
		return deleteCodeId;
	}

	public ValueLabelPair getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(ValueLabelPair categoryType) {
		this.categoryType = categoryType;
	}

	public String getCodeKey() {
		return codeKey;
	}

	public void setCodeKey(String codeKey) {
		this.codeKey = codeKey;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getSequenceJson() {
		return sequenceJson;
	}

	public void setSequenceJson(String sequenceJson) {
		this.sequenceJson = sequenceJson;
	}

	public long getCodeTypeId() {
		return codeTypeId;
	}

	public void setCodeTypeId(long codeTypeId) {
		this.codeTypeId = codeTypeId;
	}

	public String getTxnType() {
		return txnType;
	}

	public void setTxnType(String txnType) {
		this.txnType = txnType;
	}

	public boolean isSearchInactiveMasterCodes() {
		return searchInactiveMasterCodes;
	}

	public void setSearchInactiveMasterCodes(boolean searchInactiveMasterCodes) {
		this.searchInactiveMasterCodes = searchInactiveMasterCodes;
	}


	@Override
	public String[] getDefaultOrders() {
		String[] orders = {MasterCode.SEQUENCE_NO};
		return orders;
	}

	@Override
	public CriteriaIF getSearchCriteria(HttpServletRequest request) {
		CriteriaIF searchCriteria = new Criteria();
		CriterionIF[] criterion = getCriterion();
		
		if(criterion==null){
			CriterionIF[] defaultCriterion = {
					new Criterion(MasterCodeType.ID, this.getId())  //this is for search MasterCodeType Obj
			};
			
			criterion = defaultCriterion;
		}
		
		Order[] orders = getOrder(); //default order --> SequenceNO
				
		if(!StringUtils.isEmpty(request.getParameter(IS_ASCENDING)) && !StringUtils.isEmpty(request.getParameter(SORTING_PROPERTY))){
			Order newOrder = new Order(request.getParameter(SORTING_PROPERTY), request.getParameter(IS_ASCENDING).equals("true")?true:false); 
			Order[] newOrders = {newOrder};
			orders = newOrders;
		}
		
		searchCriteria.setCriterion(criterion);
		searchCriteria.setOrder(orders);
		
		if(this.getTxnType().equalsIgnoreCase(MasterCodeWebEnum.LIST_CODE_VALUES_TXN_TYPE.toString())){
			searchCriteria.setRequestedPage(super.getRequestedPage());
			searchCriteria.setFetchAll(super.isFetchAll());
		}else if (this.getTxnType().equalsIgnoreCase(MasterCodeWebEnum.UPDATE_CODE_VALUE_SEQUENCE_TXN_TYPE.toString())) {
			searchCriteria.setRequestedPage(1);
			searchCriteria.setFetchAll(true);
		}
		
		return searchCriteria;
	}

}


















