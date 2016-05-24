package sg.com.fbs.web.ui.form.mastercode;

import javax.servlet.http.HttpServletRequest;

import lombok.Getter;
import lombok.Setter;

import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

import sg.com.fbs.common.form.BusinessQueryWebForm;
import sg.com.fbs.model.domain.mastercode.MasterCode;
import sg.com.fbs.model.domain.mastercode.MasterCodeType;
import sg.com.fbs.model.system.persistence.query.Criteria;
import sg.com.fbs.model.system.persistence.query.CriteriaIF;
import sg.com.fbs.model.system.persistence.query.Criterion;
import sg.com.fbs.model.system.persistence.query.Order;
import sg.com.fbs.model.system.web.ValueLabelPair;
import sg.com.fbs.validator.annotations.validation.Required;

/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu $
 * @Created 3:09:10 pm 21 Sep, 2015 $
 * 
 */
public class MasterCodeForm extends BusinessQueryWebForm{

	private static final long serialVersionUID = 1L;

	public static final String EFFECTIVE_DATE = "effectiveDate";
	
	public static final String EXPIRY_DATE = "expiryDate";
	
	public static final String CATEGORY_TYPE = "categoryType";
	
	private ValueLabelPair categoryType = new ValueLabelPair();
	
	private MasterCodeType masterCodeType;
	
	private String codeValue;
	
	private String description;
	
	private String remarks;
	
	private int sequenceNo;
	
	private DateTime effectiveDate;
	
	private DateTime expiryDate;
	
	private boolean alwaysAvailable;

	private boolean deleteMode;

	public boolean isAlwaysAvailable() {
		return alwaysAvailable;
	}

	public void setAlwaysAvailable(boolean alwaysAvailable) {
		this.alwaysAvailable = alwaysAvailable;
	}

	public boolean isDeleteMode() {
		return deleteMode;
	}

	public void setDeleteMode(boolean deleteMode) {
		this.deleteMode = deleteMode;
	}

	protected boolean searchInactiveMasterCodes = false;
	
	public void setSearchInactiveMasterCodes(boolean searchInactiveMasterCodes) {
		this.searchInactiveMasterCodes = searchInactiveMasterCodes;
	}
	
	public boolean isSearchInactiveMasterCodes() {
		return searchInactiveMasterCodes;
	}
	
	@Required(detailMessage="{fbs.common.errors.required}",
			  summaryMessage="{fbs.common.errors.required}",
			  labelFields="fbs.common.codemaintenance.ui.label.code.key")
	public void setCategoryType(ValueLabelPair categoryType) {
		this.categoryType = categoryType;
	}
	
	public ValueLabelPair getCategoryType() {
		return categoryType;
	}
	
	@Required(detailMessage="{fbs.common.errors.required}",
			  summaryMessage="{fbs.common.errors.required}",
			  labelFields="fbs.common.codemaintenance.ui.label.code.value")
	public void setCodeValue(String codeValue) {
		this.codeValue = codeValue;
	}
	
	public String getCodeValue() {
		return codeValue;
	}
	
	@Required(detailMessage="{fbs.common.errors.required}",
			  summaryMessage="{fbs.common.errors.required}",
			  labelFields="fbs.common.codemaintenance.ui.label.description")
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	public String getRemarks() {
		return remarks;
	}
	
	public void setSequenceNo(int sequenceNo) {
		this.sequenceNo = sequenceNo;
	}
	
	public int getSequenceNo() {
		return sequenceNo;
	}
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	public void setEffectiveDate(DateTime effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	
	public DateTime getEffectiveDate() {
		return effectiveDate;
	}
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	public void setExpiryDate(DateTime expiryDate) {
		this.expiryDate = expiryDate;
	}
	
	public DateTime getExpiryDate() {
		return expiryDate;
	}
	
	public void setMasterCodeType(MasterCodeType masterCodeType) {
		this.masterCodeType = masterCodeType;
	}
	
	public MasterCodeType getMasterCodeType() {
		return masterCodeType;
	}
	
	@Override
	public CriteriaIF getSearchCriteria(HttpServletRequest request) {
		CriteriaIF criteria = new Criteria();
		criteria.appendCriterion(new Criterion(MasterCode.ID, this.getId()));
		Order order = new Order(MasterCode.ID, true);
		Order[] orders = {order};
		criteria.setOrder(orders);
		criteria.setFetchAll(true);
		return criteria;
	}

}



















