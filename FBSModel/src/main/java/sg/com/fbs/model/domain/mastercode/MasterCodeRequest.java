package sg.com.fbs.model.domain.mastercode;

import org.joda.time.DateTime;

import lombok.Getter;
import lombok.Setter;
import sg.com.fbs.model.business.pojo.BasePojoRequest;
import sg.com.fbs.model.system.web.ValueLabelPair;

/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu $
 * @Created 11:52:58 am 16 Sep, 2015 $
 * 
 */
public class MasterCodeRequest extends BasePojoRequest{
	
	private static final long serialVersionUID = -7795980426670039436L;

	protected ValueLabelPair categoryType = new ValueLabelPair();//this for what

	protected MasterCodeType masterCodeType;

	protected String codeValue;

	protected String description;

	protected String remarks;

	protected int sequenceNo;

	protected DateTime effectiveDate;

	protected DateTime expiryDate;

	public ValueLabelPair getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(ValueLabelPair categoryType) {
		this.categoryType = categoryType;
	}

	public MasterCodeType getMasterCodeType() {
		return masterCodeType;
	}

	public void setMasterCodeType(MasterCodeType masterCodeType) {
		this.masterCodeType = masterCodeType;
	}

	public String getCodeValue() {
		return codeValue;
	}

	public void setCodeValue(String codeValue) {
		this.codeValue = codeValue;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public int getSequenceNo() {
		return sequenceNo;
	}

	public void setSequenceNo(int sequenceNo) {
		this.sequenceNo = sequenceNo;
	}

	public DateTime getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(DateTime effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public DateTime getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(DateTime expiryDate) {
		this.expiryDate = expiryDate;
	}
	
	
}
