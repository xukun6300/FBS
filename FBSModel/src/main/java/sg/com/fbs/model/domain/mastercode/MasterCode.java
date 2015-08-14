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
    
    @Getter
    @Setter
    protected ValueLabelPair categoryType = new ValueLabelPair();
    @Getter
    @Setter   
    protected MasterCodeType masterCodeType;
    @Getter
    @Setter
    protected String codeValue;
    @Getter
    @Setter
    protected String description;
    @Getter
    @Setter
    protected String remarks;
    @Getter
    @Setter
    protected int sequenceNo;
    @Getter
    @Setter
    protected DateTime effectiveDate;  //effective start date
    @Getter
    @Setter
    protected DateTime expiryDate;
    @Setter
    protected boolean deletable;
    
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
































