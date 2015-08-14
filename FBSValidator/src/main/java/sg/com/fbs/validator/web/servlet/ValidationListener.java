package sg.com.fbs.validator.web.servlet;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

import sg.com.fbs.validator.web.common.HttpRequestUtils;
import sg.com.fbs.validator.web.util.ServletContextUtils;

/**
 * @Author Frank Xu $
 * @Created 5:28:49 pm 13 Jul, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public class ValidationListener implements ServletRequestListener{

	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		ServletContextUtils.setServletContext(sre.getServletContext());
		HttpRequestUtils.setHttpRequest((HttpServletRequest)sre.getServletRequest());
	}
	
	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
		ServletContextUtils.setServletContext(null);
		HttpRequestUtils.setHttpRequest(null);
	}
	
}

































