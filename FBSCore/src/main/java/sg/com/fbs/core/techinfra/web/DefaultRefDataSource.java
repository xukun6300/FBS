package sg.com.fbs.core.techinfra.web;

import java.util.Map;

import org.springframework.web.servlet.ModelAndView;

import sg.com.fbs.core.techinfra.exception.ApplicationCoreException;

/**
 * @Author Frank Xu $
 * @Created 10:40:00 am 3 Jul, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public class DefaultRefDataSource implements WebRefDataSourceIF{

	public static final String WEB_LIST_DATA_SOURCE = "webDataSource";
	
	@Override
	public void populateReferenceData(WebDropDownListIF[] enums, ModelAndView modelView, Map extraParams) throws ApplicationCoreException {

	}

}
