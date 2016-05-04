package sg.com.fbs.model.domain.mastercode;

import lombok.Getter;
import lombok.Setter;

import org.joda.time.DateTime;

import sg.com.fbs.model.business.pojo.BasePojoRequest;
import sg.com.fbs.model.system.web.ValueLabelPair;

/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu $
 * @Created 3:16:27 pm 16 Sep, 2015 $
 * 
 */
public class MasterCodeTypeRequest extends BasePojoRequest{

	private static final long serialVersionUID = -1730401896478916303L;

	private ValueLabelPair categoryType = new ValueLabelPair();

	private String codeKey;

	private String remarks;
	
	private DateTime effectiveDate;

	private int sortOrder = 1;

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

	public DateTime getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(DateTime effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public int getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(int sortOrder) {
		this.sortOrder = sortOrder;
	}
	
	
	
}
