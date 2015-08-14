package sg.com.fbs.validator.core;

/**
 * @Author Frank Xu $
 * @Created 3:33:21 pm 6 Jul, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public interface NameAware {

	void setName(String name);
	
	String getName();
	
	void init();
}
