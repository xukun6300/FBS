package sg.com.fbs.model.domain.mastercode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

import org.joda.time.DateTime;

import sg.com.fbs.model.business.pojo.BaseLogPojo;
import sg.com.fbs.model.system.web.ValueLabelPair;

/**
 * @Author Frank Xu $
 * @Created 3:21:40 pm 3 Aug, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public class MasterCodeType extends BaseLogPojo{

	private static final long serialVersionUID = 6391199588299049480L;

	public static final String REMARKS="name";
	
	public static final String CATEGORY_TYPE="code";
	
    public static final String NAME="name";
	
	public static final String CODE="code";
	
    public static final String EFFECTIVE_DATE="effectiveDate";
	
	public static final String EXPIRY_DATE="expiryDate";
	
	@Setter
	@Getter
	private DateTime effectiveDate;
	@Setter
	@Getter
	private DateTime expiryDate;
	@Setter
	@Getter
	private ValueLabelPair categoryType = new ValueLabelPair();
	@Setter
	@Getter
	private String code;
	@Setter
	@Getter
	private String name;
	@Setter
	@Getter
	private int version;
	@Setter
	@Getter
	private int sortOrder;
	@Setter
	@Getter
	private boolean deletable = false;
	@Setter
	@Getter
	private String dataSourceValueType;
	@Setter
	@Getter
	private String dataSourceDisplayType;
	
	@Setter
	@Getter
	private Set<MasterCode> masterCodes = new HashSet<MasterCode>(0);
	@Setter
	@Getter
	private List<Long> activeMasterCodesId = new ArrayList<Long>(0);
	
	public String getRemarks(){
		return this.name;
	}
	
	public void setRemarks(String remarks){
		this.name = remarks;
	}
	
	public String getCodeKey(){
		return this.code;
	}
	
	public void setCodeKey(String codeKey){
		this.code = codeKey;
	}
	
	public void upperCaseCodeNameFirstLetter(){
		this.setName(Character.toUpperCase(this.getName().charAt(0))+this.getName().substring(1).toLowerCase());
	}
	
	public int getMasterCodesSize(){
		return (this.masterCodes == null) ? 0 : this.masterCodes.size();
	}
	
	public int getActiveMasterCodesSize(){
		return (this.activeMasterCodesId == null) ? 0 : this.activeMasterCodesId.size();
	}
	

	
	
	
}

















