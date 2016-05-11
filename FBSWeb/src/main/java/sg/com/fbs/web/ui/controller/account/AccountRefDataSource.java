package sg.com.fbs.web.ui.controller.account;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.web.servlet.ModelAndView;

import sg.com.fbs.core.techinfra.exception.ApplicationCoreException;
import sg.com.fbs.core.techinfra.web.WebDropDownListIF;
import sg.com.fbs.core.techinfra.web.WebRefDataSourceIF;
import sg.com.fbs.core.techinfra.web.WebRefDataSourceImpl;
import sg.com.fbs.services.controlsource.FinancialYearControlSource;

/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu Kun $
 * @Created May 11, 2016 $
 * 
 */
public class AccountRefDataSource extends WebRefDataSourceImpl implements WebRefDataSourceIF{

	@Override
	public void populateReferenceData(WebDropDownListIF[] enums, ModelAndView modelView, Map extraParams)
			throws ApplicationCoreException {
		for (WebDropDownListIF dropdown : enums) {
			populateDataFromManager(new AccountRefDataSource(), dropdown, modelView, extraParams);
		}
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, String> getFinancialYears(String listName, ModelAndView modelView, Map extraParams) throws ApplicationCoreException {
		Map<String, String> financialYears = new LinkedHashMap<String, String>();
		financialYears.put(AccountWebEnum.DEFAULT_VALUE.toString(), AccountWebEnum.PLEASE_SELECT.toString());
		FinancialYearControlSource financialYearControlSource = new FinancialYearControlSource();
		financialYears.putAll(financialYearControlSource.getControlSourceValues(financialYears));
		return financialYears;
	}

}
