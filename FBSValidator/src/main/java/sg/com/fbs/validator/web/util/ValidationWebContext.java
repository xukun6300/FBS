package sg.com.fbs.validator.web.util;

import java.util.Map;

/**
 * @Author Frank Xu $
 * @Created 9:29:25 am 6 Jul, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public class ValidationWebContext {

	private Map sessionScope;
	
	private Map requestScope;
	
	private Map requestParameters;
	
	private Map cookieMap;

	private static ThreadLocal<ValidationWebContext> threadLocal = new ThreadLocal<ValidationWebContext>();
	
	public Map getSessionScope() {
		return sessionScope;
	}

	public Map getRequestScope() {
		return requestScope;
	}

	public Map getRequestParameters() {
		return requestParameters;
	}

	public Map getCookieMap() {
		return cookieMap;
	}
	//instantiate via ValidationWebContextFilter
	public ValidationWebContext(final Map aRequestParameters, final Map aRequestScope, final Map aSessionScope, final Map aCookieMap){
		this.requestParameters = aRequestParameters;
		this.requestScope = aRequestScope;
		this.sessionScope = aSessionScope;
		this.cookieMap = aCookieMap;
		threadLocal.set(this);
	}
	
	public static ValidationWebContext getInstance(){
		return threadLocal.get();
	}
	
	public static void clearValidationWebContext(){
		threadLocal.set(null);
	}
	
	
	
}











