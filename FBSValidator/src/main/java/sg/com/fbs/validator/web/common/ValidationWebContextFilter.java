package sg.com.fbs.validator.web.common;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sg.com.fbs.validator.web.util.ValidationWebContext;

/**
 * @Author Frank Xu $
 * @Created 10:42:02 am 13 Jul, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public class ValidationWebContextFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		new ValidationWebContext(request.getParameterMap(), //For HTTP servlets, parameters are contained in the query string or posted form data.
				new RequestMap((HttpServletRequest)request),
				new SessionMap(((HttpServletRequest)request).getSession()), 
				new CookieMap(((HttpServletRequest)request).getCookies(), (HttpServletResponse)response));
		
		chain.doFilter(request, response);
		ValidationWebContext.clearValidationWebContext();
	}

	@Override
	public void destroy() {
		
	}

}























