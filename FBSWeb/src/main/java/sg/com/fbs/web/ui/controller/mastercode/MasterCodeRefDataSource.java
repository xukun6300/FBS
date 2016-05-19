package sg.com.fbs.web.ui.controller.mastercode;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.web.servlet.ModelAndView;

import sg.com.fbs.core.techinfra.exception.ApplicationCoreException;
import sg.com.fbs.core.techinfra.web.WebDropDownListIF;
import sg.com.fbs.core.techinfra.web.WebRefDataSourceIF;
import sg.com.fbs.core.techinfra.web.WebRefDataSourceImpl;
import sg.com.fbs.services.controlsource.CodeKeyControlSource;

/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu Kun $
 * @Created May 19, 2016 $
 * 
 */
public class MasterCodeRefDataSource extends WebRefDataSourceImpl implements WebRefDataSourceIF{

	@Override
	public void populateReferenceData(WebDropDownListIF[] enums, ModelAndView modelView, Map extraParams) throws ApplicationCoreException {
		for (WebDropDownListIF dropdown : enums) {
			populateDataFromManager(new MasterCodeRefDataSource(), dropdown, modelView, extraParams);
		}
	}

	public Map<String, String> getCodeKeys(String listName, ModelAndView modelView, Map extraParams) throws ApplicationCoreException {
		Map<String, String> codeKeys = new LinkedHashMap<String, String>();
		codeKeys.put("-1", "[Any Code Key]");
		CodeKeyControlSource codeKeyControlSource = new CodeKeyControlSource();
		codeKeys.putAll(codeKeyControlSource.getControlSourceValues(codeKeys));
		return codeKeys;
	}
}
