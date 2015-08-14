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

	@Setter
	@Getter
	private String label;
	
	@Setter
	@Getter
	private String value;
	
	@Setter
	@Getter
	private long valueId = -1;
	
	public String getDescription(){
		return this.label;
	}
	
	public boolean isValidRequired(){
		return valueId>0 || StringUtils.isNotEmpty(value);
	}
	
}












































