package sg.com.fbs.model.system.transactionlog;

import lombok.Getter;
import lombok.Setter;
import sg.com.fbs.model.business.pojo.BasePojo;
import sg.com.fbs.model.system.activity.Activity;

/**
 * @Author Frank Xu $
 * @Created 5:04:40 pm 24 Jun, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public class TransactionLog extends BasePojo{

	private static final long serialVersionUID = 2190650116617029508L;
	
	public static final String ACTIVITY = "activity";
	public static final String ACTIVITY_ID = "activity.id";
	
	@Setter
	@Getter
	private Activity activity;
	
	@Setter
	@Getter
	private String message;
	
	public TransactionLog() {
		
	}

    public TransactionLog(Activity activity, String message) {
		this.activity = activity;
		this.message = message;
	}
}
