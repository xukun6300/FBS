package sg.com.fbs.model.system.web;

import com.google.gson.annotations.Expose;

import lombok.Getter;
import lombok.Setter;
import sg.com.fbs.model.business.pojo.BasePojo;

/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu $
 * @Created 5:29:26 pm 22 Sep, 2015 $
 * 
 */
public class WebDropDownList extends BasePojo{

	private static final long serialVersionUID = -8668117314242017535L;

	@Expose
	private String value;

	@Expose
	private String description;

	@Expose
	private String name;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
