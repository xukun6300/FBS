package sg.com.fbs.validator.validation;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author Frank Xu $
 * @Created 3:27:26 pm 6 Jul, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public class ValidatorMessage implements Serializable, ValidatorMessageHolder{

	private static final long serialVersionUID = -6862791213495240088L;
	
	@Setter
	@Getter
	private String detail;
	
	@Setter
	@Getter
	private String summary;
	
	@Setter
	@Getter
	private String labelFields;
	
	@Setter
	@Getter
	private String dynaData;
	
	@Setter
	@Getter
	private boolean hasError = false;
	
	public ValidatorMessage(String summary, String detail){
		this.summary = summary;
		this.detail = detail;
		this.hasError = true;
	}

	public ValidatorMessage(String message) {
		this.summary = message;
		this.detail = message;
		this.hasError = true;
	}
	 
	public ValidatorMessage() {
    	this.summary = "Message not setup!";
    	this.detail = "Message not setup!";
    	hasError = false;
    }
}









































