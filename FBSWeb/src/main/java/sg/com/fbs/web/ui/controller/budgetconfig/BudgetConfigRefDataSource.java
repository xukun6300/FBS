package sg.com.fbs.web.ui.controller.budgetconfig;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.web.servlet.ModelAndView;

import sg.com.fbs.core.techinfra.exception.ApplicationCoreException;
import sg.com.fbs.core.techinfra.web.WebDropDownListIF;
import sg.com.fbs.core.techinfra.web.WebRefDataSourceIF;
import sg.com.fbs.core.techinfra.web.WebRefDataSourceImpl;
import sg.com.fbs.services.controlsource.BudgetForFYControlSource;

/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu Kun $
 * @Created Jan 16, 2017 $
 * 
 */
public class BudgetConfigRefDataSource extends WebRefDataSourceImpl implements WebRefDataSourceIF{

	@Override
	public void populateReferenceData(WebDropDownListIF[] enums, ModelAndView modelView, Map extraParams) throws ApplicationCoreException {
		for (WebDropDownListIF dropdown : enums) {
			populateDataFromManager(new BudgetConfigRefDataSource(), dropdown, modelView, extraParams);
		}	
		
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, String> getBudgetForFYs(String listName, ModelAndView modelview, Map extraParams) throws ApplicationCoreException{
		Map<String, String> budgetForFy = new LinkedHashMap<String, String>();
		BudgetForFYControlSource budgetForFYControlSource = new BudgetForFYControlSource();
		budgetForFy.putAll(budgetForFYControlSource.getControlSourceValues(budgetForFy));
		return budgetForFy;
	}

}
