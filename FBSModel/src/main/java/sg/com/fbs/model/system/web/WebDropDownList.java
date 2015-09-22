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

	@Setter
	@Getter
	@Expose
	private String value;
	
	@Setter
	@Getter
	@Expose
	private String description;
	
	@Setter
	@Getter
	@Expose
	private String name;
}
