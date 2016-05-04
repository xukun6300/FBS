package sg.com.fbs.common.form;

import lombok.Getter;
import lombok.Setter;
import sg.com.fbs.core.techinfra.web.BaseQueryWebForm;

/**
 * @Author Frank Xu $
 * @Created 4:56:44 pm 30 Jul, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public abstract class BusinessQueryWebForm extends BaseQueryWebForm{

	private static final long serialVersionUID = -8798607554314461473L;

	public static final String CREATED_BY = "createdby";

	protected Long createdby;

	public Long getCreatedby() {
		return createdby;
	}

	public void setCreatedby(Long createdby) {
		this.createdby = createdby;
	}

	@Override
	public String[] getDefaultOrders() {
		String[] orders = {ID};
		return orders;
	}
	
	protected String getDefaultCodeValue(String key, String codeValue) {
		return "";
	}
}



























