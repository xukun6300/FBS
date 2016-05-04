package sg.com.fbs.model.system.activity;

import sg.com.fbs.model.business.pojo.BasePojo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * @Author Frank Xu
 * @Created 4:13:37 pm 24 Jun, 2015
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
@ToString
public class Activity extends BasePojo{
	private static final long serialVersionUID = -2102109603714502173L;

	public static final String NAME = "name";
	
	public static final String CATEGORY = "category";

	private String name;
	
	private String category;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	
}
