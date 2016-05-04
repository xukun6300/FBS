package sg.com.fbs.model.system.datalog;

import lombok.Getter;
import lombok.Setter;
import sg.com.fbs.model.business.pojo.BasePojo;
import sg.com.fbs.model.system.activity.Activity;

/**
 * @Author Frank Xu $
 * @Created 5:31:06 pm 24 Jun, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public class DataLog extends BasePojo{

	private static final long serialVersionUID = -2237129072496966386L;
	
	public static final String ACTIVITY = "activity";
	
	public static final String ACTIVITY_ID = "activity.id";
	
	public static final String ACTIVITY_CATEGORY = "activity.category";
	
	private Activity activity;
	
	private String objType;

	private long objId;

	private String oldValue;

	private String newValue;

	private String convertedOldValue;

	private String convertedNewValue;

	private String auditTransaction;
	
	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public String getObjType() {
		return objType;
	}

	public void setObjType(String objType) {
		this.objType = objType;
	}

	public long getObjId() {
		return objId;
	}

	public void setObjId(long objId) {
		this.objId = objId;
	}

	public String getOldValue() {
		return oldValue;
	}

	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}

	public String getNewValue() {
		return newValue;
	}

	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}

	public String getConvertedOldValue() {
		return convertedOldValue;
	}

	public void setConvertedOldValue(String convertedOldValue) {
		this.convertedOldValue = convertedOldValue;
	}

	public String getConvertedNewValue() {
		return convertedNewValue;
	}

	public void setConvertedNewValue(String convertedNewValue) {
		this.convertedNewValue = convertedNewValue;
	}

	public String getAuditTransaction() {
		return auditTransaction;
	}

	public void setAuditTransaction(String auditTransaction) {
		this.auditTransaction = auditTransaction;
	}

	public DataLog(){
		
	}
	
	public DataLog(Activity activity, String objType, long objId, String oldValue, String newValue) {
		this.activity = activity;
		this.objType = objType;
		this.objId = objId;
		this.oldValue = oldValue;
		this.newValue = newValue;
	}
	
}













