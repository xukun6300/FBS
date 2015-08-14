package sg.com.fbs.validator.web.util;

import javax.servlet.ServletContext;

/**
 * @Author Frank Xu $
 * @Created 10:10:38 am 6 Jul, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 * 
 * Stores the ServletContext (application scope) so that is available to anyone on the request thread. 
 */

public class ServletContextUtils {
	/** Holds the ServletContext in the current thread. */ 
	private static ThreadLocal<ServletContext> threadLocal = new ThreadLocal<ServletContext>();
	
	/** Set the ServletContext in the thread. 
	 */
	public static void setServletContext(ServletContext servletContext){
		threadLocal.set(servletContext);
	}
	
	/** Get the Servlet context from the thread.
     */
	public static ServletContext context(){
		return threadLocal.get();
	}
}
