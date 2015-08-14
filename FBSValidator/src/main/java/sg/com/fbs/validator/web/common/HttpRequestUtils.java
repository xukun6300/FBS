package sg.com.fbs.validator.web.common;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author Frank Xu $
 * @Created 5:31:09 pm 13 Jul, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public class HttpRequestUtils {

	private static ThreadLocal<HttpServletRequest> threadLocal = new ThreadLocal<HttpServletRequest>();
	
	public static void setHttpRequest(final HttpServletRequest httpServletRequest){
		threadLocal.set(httpServletRequest);
	}
	
	public static HttpServletRequest request(){
		return threadLocal.get();
	}
}
