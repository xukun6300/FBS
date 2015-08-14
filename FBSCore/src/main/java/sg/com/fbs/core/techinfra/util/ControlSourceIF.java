package sg.com.fbs.core.techinfra.util;

import java.util.Map;

import sg.com.fbs.core.techinfra.exception.ApplicationCoreException;

/**
 * @Author Frank Xu $
 * @Created 11:35:42 am 4 Aug, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public interface ControlSourceIF {

	public static final String CODE_KEY = "CODE_KEY";
	
	public static final String FILTEREFFECTIVEDATE = "EFFECTIVEDATE";
	
	public static final String TYPE = "TYPE";
	
	public static final String CRITERION_LIST_KEY = "CRITERION_LIST_KEY";
	
	public static final String CODE = "CODE";
	
	public static final String ID = "ID";
	
	public static final String STR_Y = "Y";
	
    public Map getControlSourceValues(Map params) throws ApplicationCoreException;
}
