package sg.com.fbs.core.techinfra.web;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.apache.commons.beanutils.MethodUtils;

import sg.com.fbs.core.techinfra.exception.ApplicationCoreException;
import sg.com.fbs.core.techinfra.util.StringUtil;
import sg.com.fbs.model.system.web.WebDropDownList;

/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu $
 * @Created 4:56:52 pm 22 Sep, 2015 $
 * 
 */
public class WebUtil {

	private static void createWebDropdownObject(Map listResult, String keyDropdown, String[] listNames, BaseWebFormIF commandForm, Boolean isError) throws ApplicationCoreException{
		for (String value : listNames) {
			if(keyDropdown.equals(value)){
				String capsValue = StringUtil.makeFirstLetterUpperCase(value);
				
				try {
					Object valueFromObj = MethodUtils.invokeMethod(commandForm, "get"+capsValue, null);
					
					if(valueFromObj!=null){
						if(!(valueFromObj instanceof String)){
							valueFromObj = String.valueOf(valueFromObj);
						}
						
						if(listResult.containsKey(valueFromObj)){
							String descValue = (String) listResult.get(valueFromObj);
							if(descValue!=null){
								WebDropDownList displayValue = new WebDropDownList();
								displayValue.setValue(String.valueOf(valueFromObj));
								displayValue.setDescription(descValue);
								Object[] params = new Object[1];
								params[0] = valueFromObj;
								if(isError == null){
									params[0] = displayValue;
								}
								
								Object returnMethod = MethodUtils.invokeMethod(commandForm, "set"+capsValue, params);
							}
							
							break;
						}
					}
					
					
				} catch (NoSuchMethodException e) {
					throw new ApplicationCoreException(e);
				} catch (IllegalAccessException e) {
					throw new ApplicationCoreException(e);
				} catch (InvocationTargetException e) {
					throw new ApplicationCoreException(e);
				}
			}
		}
	}
}
