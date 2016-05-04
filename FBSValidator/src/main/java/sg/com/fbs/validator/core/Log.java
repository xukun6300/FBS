package sg.com.fbs.validator.core;

import java.util.HashMap;
import java.util.Map;

import lombok.Setter;

/**
 * @Author Frank Xu $
 * @Created 11:41:27 am 6 Jul, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public class Log {

	private static Map<Class<?>, Log> logMap = new HashMap<Class<?>, Log>();
	
	protected Class<?> clazz;
	
	public void setClazz(Class<?> clazz) {
		this.clazz = clazz;
	}
	
	public void info(String message){
		System.out.println(message);
	}
	
	public void debug(String message){
		System.out.println(message);
	}
	
	public void warn(String message){
		System.out.println(message);
	}
	
	public void error(String message) {
        System.err.println(message);
    }
	
    public void fatal(String message) {
        System.err.println(message);
    }
    
	public static Log getLog(Class<?> clazz) {
		Log log = logMap.get(clazz);
		if (log != null) {
			return log;
		}
		try {
			Class<?> logClass = Class.forName(ValidationConstants.LOG);
			log = (Log) logClass.newInstance();
			log.setClazz(clazz);
			logMap.put(clazz, log);
			return log;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
	}
    
	protected void init() {
		
	}
	
	public void handleExceptionError(String message, Exception ioe) {
		System.err.println(" AN EXCEPTION OCCURRED: " + ioe.getMessage());
		ioe.printStackTrace(System.err);
	}

	public void handleExceptionFatal(String message, Exception ioe) {
		System.err.println(" AN EXCEPTION OCCURRED: " + ioe.getMessage());
		ioe.printStackTrace(System.err);
	}

	public void handleExceptionWarn(String message, Exception ioe) {
		System.out.println(" AN EXCEPTION OCCURRED: " + ioe.getMessage());
		ioe.printStackTrace(System.out);
	}

	public void handleExceptionInfo(String message, Exception ioe) {
		System.out.println(" AN EXCEPTION OCCURRED: " + ioe.getMessage());
		ioe.printStackTrace(System.out);
	}

}
























