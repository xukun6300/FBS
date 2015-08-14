package sg.com.fbs.web.ui.controller.system.security.uam;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.web.servlet.ModelAndView;

import sg.com.fbs.core.techinfra.exception.ApplicationCoreException;
import sg.com.fbs.core.techinfra.web.WebDropDownListIF;
import sg.com.fbs.core.techinfra.web.WebRefDataSourceIF;
import sg.com.fbs.core.techinfra.web.WebRefDataSourceImpl;
import sg.com.fbs.services.controlsource.GenderTypeControlSource;
import sg.com.fbs.services.controlsource.SalutationControlSource;

/**
 * @Author Frank Xu $
 * @Created 3:29:30 pm 5 Aug, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public class UserAccountManagementRefDataSource extends WebRefDataSourceImpl implements WebRefDataSourceIF{

	@Override
	public void populateReferenceData(WebDropDownListIF[] enums,ModelAndView modelView, Map extraParams)throws ApplicationCoreException {
		
		for (WebDropDownListIF dropdown : enums) {
			populateDataFromManager(new UserAccountManagementRefDataSource(), dropdown, modelView, extraParams);
		}	
	}
	
	public Map<String, String> getSalutationType(String listName, ModelAndView modelview, Map extraParams) throws ApplicationCoreException{
		Map<String, String> salutations = new LinkedHashMap<String, String>();
		salutations.put(UserAccountManagementWebEnum.DEFAULT_VALUE.toString(), UserAccountManagementWebEnum.PLEASE_SELECT.toString());
		SalutationControlSource salutationControlSource = new SalutationControlSource();
		salutations.putAll(salutationControlSource.getControlSourceValues(salutations));
		return salutations;
	}
	
	public Map<String, String> getGenderType(String listName, ModelAndView modelview, Map extraParams) throws ApplicationCoreException{
		Map<String, String> genderTypes = new LinkedHashMap<String, String>();
	    genderTypes.put(UserAccountManagementWebEnum.DEFAULT_VALUE.toString(), UserAccountManagementWebEnum.PLEASE_SELECT.toString());
	    GenderTypeControlSource genderTypeControlSource = new GenderTypeControlSource();
	    genderTypes.putAll(genderTypeControlSource.getControlSourceValues(genderTypes));
	    return genderTypes;
	}

}


























