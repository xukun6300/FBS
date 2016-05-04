package sg.com.fbs.validator.validation;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;


/**
 * @Author Frank Xu $
 * @Created 11:21:22 am 3 Jul, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 * 
 * store the validator name and a list of name value pairs for the validator.
 */
public class ValidatorMetaData {

	private String name = null;

	private Map<String, Object> properties = new HashMap<String, Object>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, Object> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, Object> properties) {
		this.properties = properties;
	}

	
}
