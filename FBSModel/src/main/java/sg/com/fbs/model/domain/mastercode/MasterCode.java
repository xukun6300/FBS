package sg.com.fbs.model.domain.mastercode;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

import org.joda.time.DateTime;

import sg.com.fbs.model.business.pojo.BaseLogPojo;
import sg.com.fbs.model.domain.enumeration.ActiveStatusEnum;
import sg.com.fbs.model.system.web.ValueLabelPair;

/**
 * @Author Frank Xu $
 * @Created 3:28:34 pm 3 Aug, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public class MasterCode extends BaseLogPojo{

	private static final long serialVersionUID = 379252953506047105L;

	public static final String MASTERCODE_TYPE = "masterCodeType";
	
	public static final String MASTERCODE_TYPE_ID = "masterCodeType.id";
	
	public static final String MASTERCODE_TYPE_KEY = "masterCodeType.code";
	
	public static final String MASTERCODE_TYPE_DESCRIPTION = "description";
	
    public static final String CODE_VALUE = "codeValue";
    
    public static final String SEQUENCE_NO = "sequenceNo";
    
    public static final String EFFECTIVE_DATE = "effectiveDate";
    
    public static final String EXPIRY_DATE = "expiryDate";

    protected ValueLabelPair categoryType = new ValueLabelPair();
    
    protected MasterCodeType masterCodeType;
  
    protected String codeValue;
    
    protected String description;
    
    protected String remarks;
    
    protected int sequenceNo;
    
    protected DateTime effectiveDate;  //effective start date
    
    protected DateTime expiryDate;
  
    protected boolean deletable;

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

	public void setDeletable(boolean deletable) {
		this.deletable = deletable;
	}

	public MasterCode(){
    	super();
    }
    
    public MasterCode(MasterCodeType masterCodeType, String code, String description){
    	this();
    	this.masterCodeType = masterCodeType;
    	this.codeValue = code;
    	this.description = description;
    }
    
    public MasterCode(MasterCodeType masterCodeType, String code, String description, String properties, int sequenceNo, DateTime effectiveDate, DateTime expiryDate){
    	this();
    	this.masterCodeType = masterCodeType;
    	this.codeValue = code;
    	this.description = description;
    	this.sequenceNo = sequenceNo;
    	this.effectiveDate = effectiveDate;
    	this.expiryDate = expiryDate;
    }
    
    public boolean getDeletable(){
    	boolean deletable = false;
    	if(activeStatus.equalsIgnoreCase(ActiveStatusEnum.YES.toString())){
    		deletable = true;
    	}
    	
    	return deletable;
    }
    
    public String getMasterCodeTypeValue(){
    	String value = categoryType.getValue();
    	return value;
    }
    
    
}
































