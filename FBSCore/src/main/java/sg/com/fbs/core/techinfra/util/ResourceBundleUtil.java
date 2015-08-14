package sg.com.fbs.core.techinfra.util;

import java.util.Locale;

import org.apache.log4j.Logger;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * @Author Frank Xu $
 * @Created 9:13:39 am 25 Jun, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public class ResourceBundleUtil {  //this bean is configured in spring-fbs-validator.xml

	private static Logger logger = Logger.getLogger(ResourceBundleUtil.class);
	
	private static ReloadableResourceBundleMessageSource vCoreMessageSource;  //autowired 
	
	private static ReloadableResourceBundleMessageSource exceptionMessageSource; //autowired
	
	public void setvCoreMessageSource(ReloadableResourceBundleMessageSource vCoreMessageSource){
		ResourceBundleUtil.vCoreMessageSource = vCoreMessageSource;
	}
	
	public void setExceptionMessageSource(ReloadableResourceBundleMessageSource exceptionMessageSource){
		ResourceBundleUtil.exceptionMessageSource = exceptionMessageSource;
	}
	
	public static String getMessage(String code, Object[] args){
		Locale locale = Locale.getDefault();
		logger.debug(vCoreMessageSource.toString());
		String msg = null;
		try {
			msg= vCoreMessageSource.getMessage(code, args, locale);
		} catch (Exception e) {		
		}
		
		return msg;
	}
	
	public static String getExceptionMessage(String code, Object[] args){
		Locale locale = Locale.getDefault();
		logger.debug(exceptionMessageSource.toString());
		String msg = null;
		try {
			msg = exceptionMessageSource.getMessage(code, args, locale);
		} catch (Exception e) {
			
		}
		return msg;
	}
	
	public static String getMessage(String code){
		return getMessage(code, null);
	}
	
	public static String getExceptionMessage(String code){
		return getExceptionMessage(code, null);
	}
}
