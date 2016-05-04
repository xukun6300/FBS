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

	private String detail;

	private String summary;

	private String labelFields;
	
	private String dynaData;
	
	private boolean hasError = false;

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getLabelFields() {
		return labelFields;
	}

	public void setLabelFields(String labelFields) {
		this.labelFields = labelFields;
	}

	public String getDynaData() {
		return dynaData;
	}

	public void setDynaData(String dynaData) {
		this.dynaData = dynaData;
	}

	public boolean isHasError() {
		return hasError;
	}

	public void setHasError(boolean hasError) {
		this.hasError = hasError;
	}

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









































