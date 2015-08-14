package sg.com.fbs.validator.core;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @Author Frank Xu $
 * @Created 9:47:24 am 6 Jul, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public class ValidationConstants {

	private static Properties props = new Properties();
	
	static {
		InputStream resourceAsStream = null;
		try {
			resourceAsStream = ValidationConstants.class.getResourceAsStream("fbsvalidation.properties");
			if (resourceAsStream != null) {
				props.load(resourceAsStream);
			}
		} catch (IOException e) {

		} finally {
			safeClose(resourceAsStream);
		}

	}
	
	private static void safeClose(Closeable closeable){
		if(closeable!=null){
			try {
				closeable.close();
			} catch (IOException e) {
			}
		}
	}
	
	public static final String FRAMEWORK_PREFIX = props.getProperty("FRAMEWORK_PREFIX", "fbs");
	public static final String FRAMEWORK_DELIM = props.getProperty("FRAMEWORK_DELIM", "/");
	public static final String OBJECT_REGISTRY = props.getProperty("OBJECT_REGISTRY", 
			"sg.com.fbs.validator.core.spring.support.SpringApplicationContextObjectRegistry");
    public static final String LOG = props.getProperty("LOG", "sg.com.fbs.validator.core.Log");
	
}











































