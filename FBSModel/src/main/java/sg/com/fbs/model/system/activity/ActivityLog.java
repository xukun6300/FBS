package sg.com.fbs.model.system.activity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import sg.com.fbs.model.business.pojo.BasePojo;

/**
 * @Author Frank Xu
 * @Created 4:16:43 pm 24 Jun, 2015
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
@ToString
public class ActivityLog extends BasePojo{

	private static final long serialVersionUID = 257537187767336040L;
	
	public static final String ACTIVITY = "activity";

	public static final String ACTIVITY_ID = "activity.id";
	
	@Setter
	@Getter
	private Activity activity; 
	
	@Setter
	@Getter
	private String controller;
	
	@Setter
	@Getter
	private String action;
	
	public ActivityLog(){
		
	}
	
	public ActivityLog(Activity activity){
		this.activity = activity;
	}
	
	public ActivityLog(Activity activity, String details, Long refId){
		this.activity = activity;
		this.details = details;
		this.refId = refId;
	}
	
	public ActivityLog(String controller, String action){
		this.controller = controller;
		this.action = action;
	}
}
