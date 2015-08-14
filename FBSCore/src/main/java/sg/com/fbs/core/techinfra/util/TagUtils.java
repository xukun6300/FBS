package sg.com.fbs.core.techinfra.util;

import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

import org.apache.commons.beanutils.PropertyUtils;
import org.joda.time.DateTime;

import com.sun.swing.internal.plaf.basic.resources.basic;

/**
 * @Author Frank Xu $
 * @Created 2:02:42 pm 25 Jun, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public class TagUtils {

	private static final String BACKSLASH = "/";
	private static final String QUESTION_MARK = "?";
	private static final String EQUAL_MARK = "=";
	private static final String AND_MARK = "&";
	private static final int PATH_LEVEL = 2;
	private static final String REQUEST_PAGE_PARAMETER = "requestedPage";
	
	
	public static Object lookup(PageContext pageContext, String name, String scopeName) throws JspException{
		return pageContext.findAttribute(name);
	}
	
	public static Object lookup(PageContext pageContext, String name, String property, String scope) throws JspException{
		
		Object bean = lookup(pageContext, name, scope);
		
		if(bean == null){
			if(scope==null){
				throw new JspException("Scope is null");
			}else {
				throw new JspException("Bean is null");
			}
		}
		
		if(property == null){
			return bean;
		}
		
		// Locate and return the specified property
		try {
			return PropertyUtils.getProperty(bean, property);
		} catch (Exception e) {
			throw new JspException(e.getMessage());
		}
	}
	
	public static String createActionURL(ServletRequest request, String action, long requestPage, Map searchCriterion){
		return createActionURL(request, action, requestPage, searchCriterion, false);
	}
	
    public static String createActionURL(ServletRequest request, String action, long requestPage, Map searchCriterion, Map orderMap){
		return createActionURL(request, action, requestPage, searchCriterion, false) + addOrder(orderMap);
	}
	
    public static String createActionURL(ServletRequest request, String action, long requestPage, Map searchCriterion, boolean fromDocBase){
	/*	HttpServletRequest servletRequest = (HttpServletRequest) request;
		String pageUri = servletRequest.getRequestURI();*/
    	String pageUri = "./";  //??
		String actionUrl = createActionURL(pageUri, action, requestPage, searchCriterion, fromDocBase);
		return actionUrl;
	}

	public static String createActionURL(String pageURI, String action, long requestPage, Map searchCriterion, boolean fromDocBase){
		StringBuffer value = new StringBuffer();
		
		int lastSlash = pageURI.lastIndexOf(BACKSLASH);
		
		if(lastSlash<0){
			throw new IllegalArgumentException("Illegal URI [" + pageURI +"]");
		}
			
		int docBaseSlash = lastSlash;
		//??
		if(fromDocBase){
			for (int i = 0; i < PATH_LEVEL; i++) {
				docBaseSlash = pageURI.lastIndexOf(BACKSLASH, docBaseSlash - BACKSLASH.length());
				if(docBaseSlash<BACKSLASH.length()){
					throw new IllegalArgumentException("Illegal URI [" + pageURI +"]");
				}
			}
		}
		
		value.append(pageURI.substring(0, docBaseSlash));
		
		if(!action.startsWith(BACKSLASH)){
			value.append(BACKSLASH);
		}
		value.append(action);
		
		int lastQuestionMark = pageURI.lastIndexOf(QUESTION_MARK);
		
		if(action.indexOf(QUESTION_MARK)>-1){ // qn mark exists in action
			value.append(AND_MARK);
		}else{    // qn mark not exist in action
			if(pageURI.indexOf(QUESTION_MARK)!=-1){		 //qn mark exist in pageURI, not exist in action 
				
				if (lastSlash > lastQuestionMark) {
					throw new IllegalArgumentException("Illegal URL");
				}			
				value.append(pageURI.substring(lastQuestionMark) + AND_MARK);
				
			}else {                   //qn mark exist in pageURI and action  
				value.append(QUESTION_MARK);
			}
		}
		
		value.append(REQUEST_PAGE_PARAMETER+EQUAL_MARK+requestPage);
		
		return value.toString()+addSearchCriterion(searchCriterion);
	}


	protected static String addSearchCriterion(Map searchCriterion){
		StringBuffer bf = new StringBuffer();
		
		Iterator iterator = searchCriterion.keySet().iterator();
		while(iterator.hasNext()){
			String key = (String)iterator.next();
			Object value;
			
			try {
				Object obj = searchCriterion.get(key);
				if(obj instanceof DateTime){
					value = DateUtil.convertDateToDateString((DateTime)obj);
				}else {
					if(obj instanceof Long){
						value = String.valueOf(((Long)obj).longValue());
					}else {
						if(obj instanceof Object[]){
							continue;
						}
						if(obj == null){
							continue;
						}
						value = obj.toString();
					}
				}
				
			} catch (Exception e) {
				continue;
			}
			String para = key;
			bf.append(AND_MARK+key).append(EQUAL_MARK).append(URLEncoder.encode(value==null?"":(String)value));
		}
		
		return bf.toString();
	}
	
	protected static String addOrder(Map orderMap){
		StringBuffer bf = new StringBuffer();
		Iterator iterator = orderMap.keySet().iterator();
		while(iterator.hasNext()){
			String key = (String)iterator.next();
			Object value = orderMap.get(key);
			bf.append(AND_MARK).append(key).append(EQUAL_MARK).append(URLEncoder.encode(value == null ? "" : (String)value));
		}
		
		return bf.toString();
	}
	
}







