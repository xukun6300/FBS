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
	
	@Setter
	@Getter
	private Activity activity;
	
	@Setter
	@Getter
	private String objType;
	
	@Setter
	@Getter
	private long objId;
	
	@Setter
	@Getter
	private String oldValue;
	
	@Setter
	@Getter
	private String newValue;
	
	@Setter
	@Getter
	private String convertedOldValue;
	
	@Setter
	@Getter
	private String convertedNewValue;
	
	@Setter
	@Getter
	private String auditTransaction;
	
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













