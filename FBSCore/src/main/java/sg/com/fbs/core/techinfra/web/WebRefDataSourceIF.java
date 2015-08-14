package sg.com.fbs.core.techinfra.web;

import java.util.Map;

import org.springframework.web.servlet.ModelAndView;

import sg.com.fbs.core.techinfra.exception.ApplicationCoreException;

/**
 * @Author Frank Xu $
 * @Created 10:32:34 am 3 Jul, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public interface WebRefDataSourceIF {

	public void populateReferenceData(WebDropDownListIF[] enums, ModelAndView modelView, Map extraParams)
			throws ApplicationCoreException;
}
