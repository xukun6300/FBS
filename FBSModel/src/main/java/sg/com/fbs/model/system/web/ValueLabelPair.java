package sg.com.fbs.model.system.web;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author Frank Xu $
 * @Created 3:51:48 pm 9 Jul, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public class ValueLabelPair implements Serializable{

	private static final long serialVersionUID = 4288431882834502431L;

	private String label;

	private String value;

	private long valueId = -1;
	
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public long getValueId() {
		return valueId;
	}

	public void setValueId(long valueId) {
		this.valueId = valueId;
	}

	public String getDescription(){
		return this.label;
	}
	
	public boolean isValidRequired(){
		return valueId>0 || StringUtils.isNotEmpty(value);
	}
	
}












































