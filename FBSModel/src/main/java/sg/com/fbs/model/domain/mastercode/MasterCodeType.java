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

	private DateTime effectiveDate;
	
	private DateTime expiryDate;
	
	private ValueLabelPair categoryType = new ValueLabelPair();
	
	private String code;
	
	private String name;
	
	private int version;

	private int sortOrder;
	
	private boolean deletable = false;
	
	private String dataSourceValueType;
	
	private String dataSourceDisplayType;

	private Set<MasterCode> masterCodes = new HashSet<MasterCode>(0);

	private List<Long> activeMasterCodesId = new ArrayList<Long>(0);
	
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

	public ValueLabelPair getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(ValueLabelPair categoryType) {
		this.categoryType = categoryType;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public int getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(int sortOrder) {
		this.sortOrder = sortOrder;
	}

	public boolean isDeletable() {
		return deletable;
	}

	public void setDeletable(boolean deletable) {
		this.deletable = deletable;
	}

	public String getDataSourceValueType() {
		return dataSourceValueType;
	}

	public void setDataSourceValueType(String dataSourceValueType) {
		this.dataSourceValueType = dataSourceValueType;
	}

	public String getDataSourceDisplayType() {
		return dataSourceDisplayType;
	}

	public void setDataSourceDisplayType(String dataSourceDisplayType) {
		this.dataSourceDisplayType = dataSourceDisplayType;
	}

	public Set<MasterCode> getMasterCodes() {
		return masterCodes;
	}

	public void setMasterCodes(Set<MasterCode> masterCodes) {
		this.masterCodes = masterCodes;
	}

	public List<Long> getActiveMasterCodesId() {
		return activeMasterCodesId;
	}

	public void setActiveMasterCodesId(List<Long> activeMasterCodesId) {
		this.activeMasterCodesId = activeMasterCodesId;
	}

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

















