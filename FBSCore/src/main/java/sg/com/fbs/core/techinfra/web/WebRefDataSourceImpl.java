package sg.com.fbs.core.techinfra.web;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.apache.commons.beanutils.MethodUtils;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import sg.com.fbs.core.techinfra.exception.ApplicationCoreException;
import sg.com.fbs.core.techinfra.util.StringUtil;

public class WebRefDataSourceImpl {

	protected Logger logger = Logger.getLogger(WebRefDataSourceImpl.class);
	
	private static final String REF_DATA_PREFIX = "get";
	
	protected void populateDataFromManager(Object executionClass, WebDropDownListIF methodName, ModelAndView modelview, Map extraParams) throws ApplicationCoreException {
		
		StringBuilder methodNameStr = new StringBuilder();
		methodNameStr.append(REF_DATA_PREFIX);
		methodNameStr.append(StringUtil.makeFirstLetterUpperCase(methodName.toString()));
		
		Object[] params = {methodName.toString(), modelview, extraParams};
		
		try {
			Map returnValues = (Map)MethodUtils.invokeMethod(executionClass, methodNameStr.toString(), params);
			
			modelview.addObject(methodName.toString(), returnValues);			
		} catch (NoSuchMethodException e) {
			logger.warn("Invalid method on the web data source during population.");
		} catch (IllegalAccessException e) {
			throw new ApplicationCoreException(e);
		} catch (InvocationTargetException e) {
			logger.warn("Invalid method on the web data source during population.");
		}
 		
	}
}
