package sg.com.fbs.core.techinfra.web;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.aop.framework.Advised;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.util.WebUtils;

import sg.com.fbs.core.techinfra.exception.ApplicationCoreException;

/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu $
 * @Created 11:54:05 am 22 Sep, 2015 $
 * 
 */
public class WebMappingExceptionResolver extends AbstractHandlerExceptionResolver{
	
	protected Logger logger = Logger.getLogger(WebMappingExceptionResolver.class);
	
	public static final String DEFAULT_EXCEPTION_ATTRIBUTE = "exception";
	
	private static final String USER_UNCAUGHT_EXCEPTION_PAGE = "userUncaughtException";
	
	private static final String DEBUG_UNCAUGHT_EXCEPTION_PAGE = "uncaughtException";
	
	private Properties exceptionMappings;
	
	private String defaultErrorView;
	
	private Integer defaultStatusCode;
	
	private Map<String, Integer> statusCodes = new HashMap<String, Integer>();
	
	private String exceptionAttribute = DEFAULT_EXCEPTION_ATTRIBUTE;
	
	public void setExceptionMappings(Properties exceptionMappings) {
		this.exceptionMappings = exceptionMappings;
	}
	
	public void setDefaultErrorView(String defaultErrorView) {
		this.defaultErrorView = defaultErrorView;
	}
	
	public void setStatusCodes(Properties statusCodes) {
		Enumeration<?> enumeration = statusCodes.propertyNames();
		while (enumeration.hasMoreElements()) {
			String viewName = (String) enumeration.nextElement();
			Integer statusCode = new Integer(statusCodes.getProperty(viewName));
			this.statusCodes.put(viewName, statusCode);
		}
	}
	
	public void addStatusCode(String viewName, int statusCode){
		this.statusCodes.put(viewName, statusCode);
	}
	
	public Map<String, Integer> getStatusCodesAsMap(){
		return Collections.unmodifiableMap(statusCodes);
	}
	
	public void setDefaultStatusCode(int defaultStatusCode) {
		this.defaultStatusCode = defaultStatusCode;
	}
	
	public void setExceptionAttribute(String exceptionAttribute) {
		this.exceptionAttribute = exceptionAttribute;
	}
	
	@Override
	protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		ModelAndView returnView = null;
		String jspErrorPage = null;
		BaseWebFormIF commandForm = null;
		String methodName = null;
		Map preloadMap = null;
		ModelAndView mvc = null;
		
		String viewName = determineViewName(ex, request);

		boolean useGenericExceptionPage = false;
		if(ex instanceof ApplicationCoreException){
			Throwable probableCause = ex;
			while(probableCause.getClass().equals(ApplicationCoreException.class) && probableCause.getCause()!=null){
				probableCause = probableCause.getCause();
			}
			
			boolean redirectOnError = true;
			if((probableCause.getCause() instanceof RuntimeException || probableCause instanceof RuntimeException) && !(probableCause.getCause() instanceof DataIntegrityViolationException)){
				jspErrorPage = USER_UNCAUGHT_EXCEPTION_PAGE;
				useGenericExceptionPage = true;
			}else {
				jspErrorPage = (String) request.getAttribute(BaseWebController.JSP_ERRORS_PAGE);
				if(jspErrorPage==null){
					jspErrorPage = USER_UNCAUGHT_EXCEPTION_PAGE;
					useGenericExceptionPage = true;
				}
				redirectOnError = (Boolean) request.getAttribute(BaseWebController.REDIRECT_ON_ERROR);
			}
			
			commandForm = (BaseWebFormIF) request.getAttribute(BaseWebEnum.COMMAND_FORM.toString());
			methodName = (String) request.getAttribute(BaseWebController.CONTROLLER_METHOD_NAME);
			preloadMap = (Map) request.getAttribute(BaseWebController.PRELOAD_MAPPING);
			mvc = (ModelAndView) request.getAttribute(BaseWebEnum.MODELVIEW.toString());
			
			if(mvc==null){
				mvc = getModelAndView(viewName, ex);
			}
			
			request.setAttribute("faultyJsp", mvc.getViewName());
			request.setAttribute("controllerName", getControllerName(handler));
			
			String message = ex.getMessage();
			if(StringUtils.isEmpty(message)){
				message = ((ApplicationCoreException)ex).getMessageCode();
			}
			
			if(redirectOnError){
				FlashMap errOutputFlashMap = new FlashMap();
				errOutputFlashMap.put(BaseWebController.REDIRECT_ERR_MSG, message);				
				RequestContextUtils.getFlashMapManager(request).saveOutputFlashMap(errOutputFlashMap, request, response);
				viewName = jspErrorPage;
			}else {
				String moduleContext = (String) request.getAttribute(BaseWebController.MODULE_WEB_CONTEXT);
				if(useGenericExceptionPage){
					viewName = jspErrorPage;
				}else{
					if(moduleContext!=null && jspErrorPage!=null){
						viewName = moduleContext+jspErrorPage;
					}else {
						viewName = USER_UNCAUGHT_EXCEPTION_PAGE;
					}
				}
					
			}
			
			mvc.setViewName(viewName);
			
			ServletRequestDataBinder binder = (ServletRequestDataBinder) request.getAttribute(BaseWebController.CONTROLLER_BINDER);
			if(binder!=null){
				binder.getBindingResult().reject(message, message);
				mvc.addAllObjects(binder.getBindingResult().getModel());
			}
		}
		
		if(viewName == null){
			return null;
		}
		
		// Apply HTTP status code for error views, if specified.
	    // Only apply it if we're processing a top-level request.
		
		Integer statusCode = determineStatusCode(request, viewName);
		if(statusCode!=null){
			applyStatusCodeIfPossible(request, response, statusCode);
		}
		
		returnView = (mvc!=null) ? mvc : getModelAndView(viewName, ex);
		
		if(commandForm!=null && methodName!=null && preloadMap !=null && returnView!=null){
			if(jspErrorPage == null){
				returnView.setViewName(USER_UNCAUGHT_EXCEPTION_PAGE);
			}
		}
		
		return null;
	}
	
	protected Integer determineStatusCode(HttpServletRequest request, String viewName){
		if(this.statusCodes.containsKey(viewName)){
			return this.statusCodes.get(viewName);
		}
		return defaultStatusCode;
	}
	
	protected void applyStatusCodeIfPossible(HttpServletRequest request, HttpServletResponse response, int statusCode){
		if(!WebUtils.isIncludeRequest(request)){
			response.setStatus(statusCode);
			request.setAttribute(WebUtils.ERROR_STATUS_CODE_ATTRIBUTE, statusCode);
		}
	}
	
	protected String determineViewName(Exception ex, HttpServletRequest request){
		String viewName = null;
		
		if(this.exceptionMappings!=null){
			viewName = findMatchingViewName(exceptionMappings, ex);
		}
		
		if(viewName==null && this.defaultErrorView!=null){
			viewName = this.defaultErrorView;
		}
		
		return viewName;
	}
	
	protected String findMatchingViewName(Properties exceptionMappings, Exception ex){
		String viewName = null;
		String dominantMapping = null;
		int deepest = Integer.MAX_VALUE;
		
		Enumeration<?> names = exceptionMappings.propertyNames();
		while (names.hasMoreElements()) {
			String exceptionMapping = (String) names.nextElement();
			int depth = getDepth(exceptionMapping, ex);
			if (depth >= 0 && depth < deepest) {
				deepest = depth;
				dominantMapping = exceptionMapping;
				viewName = exceptionMappings.getProperty(exceptionMapping);
			}
		}
		
		return viewName;
	}

	protected int getDepth(String exceptionMapping, Exception ex){
		return getDepth(exceptionMapping, ex.getClass(), 0);
	}
	
	private int getDepth(String exceptionMapping, Class<?> exceptionClass, int depth){
		if(exceptionClass.getName().contains(exceptionMapping)){
			return depth;
		}
		// If we've gone as far as we can go and haven't found it...
		if(exceptionClass.equals(Throwable.class)){
			return -1;
		}
		
		return getDepth(exceptionMapping, exceptionClass.getSuperclass(), depth+1);
	}
	
	protected ModelAndView getModelAndView(String viewName, Exception ex){
		ModelAndView mv = new ModelAndView(viewName);
		if(this.exceptionAttribute!=null){
			mv.addObject(this.exceptionAttribute, ex);
		}
		
		return mv;
	}
	
	private String getControllerName(Object handler){
		
		String controllerName = "";
		
		if(handler instanceof HandlerMethod){
			controllerName = ((HandlerMethod)handler).getMethod().getDeclaringClass().getName();
		}else if (handler instanceof Advised) {
			controllerName = ((Advised)handler).getTargetClass().getName();
		}
		
		return controllerName;
	}
	
}

















