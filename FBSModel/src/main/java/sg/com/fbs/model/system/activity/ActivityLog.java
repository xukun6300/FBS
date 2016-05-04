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

	private Activity activity; 

	private String controller;

	private String action;

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public String getController() {
		return controller;
	}

	public void setController(String controller) {
		this.controller = controller;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

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
