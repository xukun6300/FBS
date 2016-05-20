package sg.com.fbs.core.techinfra.web;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.MethodUtils;
import org.apache.log4j.Logger;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.InternalPathMethodNameResolver;
import org.springframework.web.servlet.mvc.multiaction.MethodNameResolver;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

import sg.com.fbs.core.techinfra.exception.ApplicationCoreException;
import sg.com.fbs.core.techinfra.exception.CRUDException;
import sg.com.fbs.core.techinfra.persistence.exception.DataAccessObjectException;
import sg.com.fbs.core.techinfra.util.LoggerUtil;
import sg.com.fbs.core.techinfra.util.ReflectionUtil;
import sg.com.fbs.core.techinfra.util.SqlErrorCodes;
import sg.com.fbs.core.techinfra.util.StringUtil;
import sg.com.fbs.core.techinfra.util.constant.CoreErrorCodes;
import sg.com.fbs.model.business.pojo.BasePojoIF;
import sg.com.fbs.model.business.pojo.BasePojoRequest;
import sg.com.fbs.model.business.pojo.BasePojoRequestIF;
import sg.com.fbs.model.system.persistence.query.Criteria;
import sg.com.fbs.model.system.persistence.query.Order;
import sg.com.fbs.model.system.persistence.response.ResponseCRUD;


public abstract class BaseWebController extends MultiActionController{
	private Logger logger = Logger.getLogger(BaseWebController.class);
	
	public static final String GLOBAL_ERRORS = "globalErrors";
	
	public static final String HAS_ERRORS = "hasErrors";
	
	public static final String RESPONSE_CRUD = "RESPONSE_CRUD";
	
	public static final String JSP_ERRORS_PAGE = "jspErrorPage";
	
	public static final String MODULE_WEB_CONTEXT = "moduleWebContext";
	
	public static final String CONTROLLER_BINDER = "controllerBinder";
	
	public static final String CONTROLLER_METHOD_NAME = "methodName";
	
	public static final String PRELOAD_MAPPING = "preloadMapping";
	
	public static final String HTTP_SERVLET_REQUEST = "httpServletRequest";
	
	public static final String REDIRECT_ON_ERROR = "redirectOnError";
	
	public static final String REDIRECT_ERR_MSG = "redirectErrorMessage";
	
	public static final String REQUESTED_PAGE = "requestedPage";
	
	public static final String IS_FETCH_ALL = "isFetchAll";
	
	public static final String IS_ASCENDING = "isAscending";
	
	public static final String SORTING_PROPERTY = "sortingProperty";
	
	public static final String DEFAULT_VALIDATOR = "sg.com.fbs.validator.web.validation.spring.support.SpringMVCBridgeMetaDataDrivenValidator"; 
	
	public abstract String getModuleWebContext();
	
	public abstract void preLoad(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response);
	
	public abstract ModelAndView postLoad(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response);
	
	public abstract Validator getCustomValidator();
	
	private MethodNameResolver methodNameResolver = new InternalPathMethodNameResolver();
	
	private ThreadLocal<WebCRUDEnum> tlCrudMode = new ThreadLocal<WebCRUDEnum>();
	private ThreadLocal<WebCRUDIF> tlCrudOperation = new ThreadLocal<WebCRUDIF>();
	private ThreadLocal<String> tlValidationErrorPage = new ThreadLocal<String>();
	private ThreadLocal<Boolean> tlRedirectOnError = new ThreadLocal<Boolean>();
	private ThreadLocal<String> tlActionMethod = new ThreadLocal<String>();
	
	protected void setActionMethod(String actionMethod){
		tlActionMethod.set(actionMethod);
	}
	
	private WebCRUDEnum getCrudMode(){
		return (tlCrudMode.get() != null) ? tlCrudMode.get() : WebCRUDEnum.NONE;
	}
	
	protected void setCrudMode(WebCRUDEnum crudMode){
		tlCrudMode.set(crudMode);
	}
	
	private String getValidationErrorPage(){
		return tlValidationErrorPage.get();
	}
	
	protected void setValidationErrorPage(String validationErrorPage){
		tlValidationErrorPage.set(validationErrorPage);
	}
	
	private WebCRUDIF getCRUDOperation(){
		return tlCrudOperation.get();
	}
	
	protected void setCRUDOperation(Class crudOperation) {
		tlCrudOperation.set((WebCRUDIF)ReflectionUtil.getClassObject(crudOperation.getName()));
	}
	
	protected void setCRUDOperation(WebCRUDIF crudOperation){
		tlCrudOperation.set(crudOperation);
	}
	
	/**
	 * As BaseWebcontroller is extending MultiActionController  hence it will be a singleton class (as per Spring controller design)
	 * Due to this variables being declared are set by one request are getting used by other request if they are not reset.
	 * Suppose, if we didn't set ValidationErrorPage for some use cases, Controller would be using ValidationErrorPage of previous request because controller is Singleton.
	 */
	private void resetControllerState(){
		tlCrudMode.set(WebCRUDEnum.NONE);
		tlCrudOperation.set(null);
		tlValidationErrorPage.set(null);
		tlRedirectOnError.set(Boolean.FALSE);
		tlActionMethod.set(null);
	}
	
	private void removeThreadLocal(){
		tlCrudMode.remove();
		tlCrudOperation.remove();
		tlValidationErrorPage.remove();
		tlRedirectOnError.remove();
		tlActionMethod.remove();
	}
	
	private void setupValidator() throws ApplicationCoreException{
		List<Validator> validatorsArr = new ArrayList<Validator>();
		try {
			validatorsArr.add((Validator)Class.forName(DEFAULT_VALIDATOR).newInstance());
			if(getCustomValidator()!=null){
				validatorsArr.add(getCustomValidator());
			}
			Validator[] validators = validatorsArr.toArray(new Validator[validatorsArr.size()]);
			
			super.setValidators(validators);
			
		} catch (InstantiationException e) {
			LoggerUtil.logError(logger, e);
			throw new ApplicationCoreException(e);
		} catch (IllegalAccessException e) {
			LoggerUtil.logError(logger, e);
			throw new ApplicationCoreException(e);
		} catch (ClassNotFoundException e) {
			LoggerUtil.logError(logger, e);
			throw new ApplicationCoreException(e);
		}
	}
	
	
	/**First request will come to here
	 * @throws Exception **/
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception
	{   
		long startTime = System.currentTimeMillis();
		System.out.println("----------------In BaseWebController--------------");		
		ModelAndView modelAndView = null;			
		Map<String, Object> extraMap = new HashMap<String, Object>();
		
		try {
			resetControllerState();		
			String methodName = this.methodNameResolver.getHandlerMethodName(request);		
			setActionMethod(methodName);			
			setupValidator();
			BaseWebBindingInitializer initializer = new BaseWebBindingInitializer();
			setWebBindingInitializer(initializer);			
			preLoad(extraMap, request, response);

			/** This will invoke module controller handler method, since it will extend BaseWebController**/
			/** Add empty formBean and pojo request to modelAndView**/
			modelAndView = invokeNamedMethod(methodName, request, response);		
			
			/**add dropdown list/radio button... to modelAndView**/
			setReferenceDataToModel(extraMap, modelAndView, request);
			
			/**Empty form now**/
			BaseWebFormIF commandForm = (BaseWebFormIF) modelAndView.getModelMap().get(BaseWebEnum.COMMAND_FORM.toString());
			if(commandForm!=null){
				commandForm.setCrudMode(getCrudMode()); //set from module controller
			}		
			
			/**
			 * this will map http parameters to form bean, and then copy form properties to model bean(pojo request)
			 * also will call validator to validate form bean
			 */
			mapFormToModel(modelAndView, commandForm, request);			
			executeCRUDOperation(modelAndView, commandForm, request, methodName, extraMap);
			overrideModuleContext(modelAndView);
            postLoad(extraMap, request, response);
            
		} catch (NoSuchRequestHandlingMethodException e) {
			
		} finally{
			removeThreadLocal();
		}
		
        System.out.println("--------------------"+(System.currentTimeMillis()-startTime)+" milliseconds-------------------");
        return modelAndView;
	} 
	
	
	private void setReferenceDataToModel(Map<String, Object> extraMap, ModelAndView modelView, HttpServletRequest request) throws ApplicationCoreException{
	
		Set<String> keys = extraMap.keySet();
		WebDropDownListIF[] weblist = null; //XXWebEnum, DropDown list or radio button name
		WebRefDataSourceIF dataSource = null;
		try {
			for (String key : keys) {
				if (modelView.getViewName().equals(key) || (!StringUtil.isEmptyString(getValidationErrorPage()) && getValidationErrorPage().equals(key))|| modelView.getViewName().contains(key + ".action")) {
					weblist = (WebDropDownListIF[]) extraMap.get(key);
					dataSource = (WebRefDataSourceIF) extraMap.get(DefaultRefDataSource.WEB_LIST_DATA_SOURCE);
					break;
				}
			}

			if (dataSource == null) {
				// if datasource is null load dummy web datasource
				dataSource = new DefaultRefDataSource();
			}
			extraMap.put(HTTP_SERVLET_REQUEST, request);//??
			Object[] params = { weblist, modelView, extraMap };

			if (weblist != null && dataSource != null) {
				MethodUtils.invokeMethod(dataSource, "populateReferenceData",params);
			}
		} catch (NoSuchMethodException e) {
			throw new ApplicationCoreException(CoreErrorCodes.CORE_GENERAL_ERROR.toString(),e);
		} catch (IllegalAccessException e) {
			throw new ApplicationCoreException(CoreErrorCodes.CORE_GENERAL_ERROR.toString(),e);
		} catch (InvocationTargetException e) {
			throw new ApplicationCoreException(CoreErrorCodes.CORE_GENERAL_ERROR.toString(),e);
		}
	}
	
	/**
	 * Auto map form bean to business model bean(pojo request)
	 * 
	 */
	private void mapFormToModel(ModelAndView modelView, BaseWebFormIF commandForm, HttpServletRequest request) throws ApplicationCoreException {
		try {
			if (modelView != null && commandForm != null) {
				//copy form value from httprequest to commandForm obj
				getFormValues(request, commandForm, modelView);

				Map<String, Map<String, Object>> defaultValuesMapWithURI = commandForm .getDefaultValues();
				if (defaultValuesMapWithURI != null) {
					// ??
					Map<String, Object> defaultValues = defaultValuesMapWithURI.get(request.getServletPath());

					if (defaultValues != null) {
						for (Map.Entry<String, Object> entry : defaultValues.entrySet()) {
							ReflectionUtil.setProperty(commandForm, entry.getKey(), defaultValues.get(entry.getKey()));
						}
					}
				}
			}
			
			if(modelView!=null && modelView.getModel()!=null && modelView.getModel().get(BaseWebEnum.MODEL_BEAN.toString())!=null){
				Object modelBean = modelView.getModel().get(BaseWebEnum.MODEL_BEAN.toString());
				Object commandBean = modelView.getModel().get(BaseWebEnum.COMMAND_FORM.toString());//why dont user param commandForm?
				ReflectionUtil.copyProperties(modelBean, commandBean);
				modelView.addObject(BaseWebEnum.MODEL_BEAN.toString(), modelBean);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationCoreException(e);
		}
		
	}
	
	// will bind all form value to command object
	private void getFormValues(HttpServletRequest request, Object form, ModelAndView modelView) throws Exception{
		
		ServletRequestDataBinder binder = createControllerBinder(request, form);
		if(binder!=null){
			// Bind the parameters of the given request to this binder's target obj
			binder.bind(request);
		}
		
		if(this.getValidators()!=null && WebCRUDEnum.NONE!=this.getCrudMode() && WebCRUDEnum.DETAILS_MODE!=this.getCrudMode()){
			
			for (Validator validator : this.getValidators()) {
				if(validator.supports(form.getClass())){
					//pass the command form as target object to validate
					ValidationUtils.invokeValidator(validator, form, binder.getBindingResult());
				}
			}
		}
		
		if(binder!=null && binder.getBindingResult()!=null &&(binder.getBindingResult().hasGlobalErrors()||binder.getBindingResult().hasFieldErrors())){
			outputBindingError(binder);
			if(binder.getBindingResult().hasGlobalErrors()){
				modelView.addObject(GLOBAL_ERRORS, binder.getBindingResult().getGlobalErrors());
			}
			// what is this for??
			//only for validation failed senario?
			modelView.addAllObjects(binder.getBindingResult().getModel());

			BaseWebFormIF commandForm = (BaseWebFormIF) binder.getBindingResult().getModel().get(BaseWebEnum.COMMAND_FORM.toString());
			
			/**
			 * may because after validation failed, still want to keep the last response result?
			 * added in executeCRUDOperation method
			 */
			if(request.getSession().getAttribute(RESPONSE_CRUD)!=null){
				commandForm.setCrudResponse((ResponseCRUD)request.getSession().getAttribute(RESPONSE_CRUD));
			}
			
			modelView.addObject(BaseWebEnum.COMMAND_FORM.toString(), commandForm);
			modelView.setViewName(getValidationErrorPage());
			modelView.addObject(HAS_ERRORS, Boolean.TRUE);
		}
		
	}

	private void outputBindingError(ServletRequestDataBinder binder){
		if (binder.getBindingResult().hasGlobalErrors() || binder.getBindingResult().hasFieldErrors()) {
			System.out.println(binder.getBindingResult());
		}
	}
	
	private Order[] convertToOrderObject(String[] strOrder){
		Order[] orders = new Order[strOrder.length];
		
		for (int i = 0; i < strOrder.length; i++) {
			String orderString = strOrder[i];
		    orders[i] = new Order(orderString, true);
		}		
		return orders;
	}
	
	protected void executeCRUDOperation(ModelAndView modelView, BaseWebFormIF commandForm, HttpServletRequest request, String methodName, Map extraMap) throws Exception {
		BasePojoIF pojo = null;
		Boolean hasError = (Boolean) modelView.getModelMap().get(HAS_ERRORS);
		
		WebCRUDEnum crudMode = getCrudMode();
		
		if (crudMode != null && !crudMode.equals(WebCRUDEnum.NONE)
				&& getCRUDOperation() != null
				&& (hasError == null || !(hasError.booleanValue()))) {

			ResponseCRUD response = null;
			populatePaginationParams(modelView, request);
			
			request.setAttribute(JSP_ERRORS_PAGE, getValidationErrorPage());
			request.setAttribute(MODULE_WEB_CONTEXT, getModuleWebContext());
			request.setAttribute(BaseWebEnum.COMMAND_FORM.toString(), commandForm); //add command form in request attribute
			request.setAttribute(CONTROLLER_BINDER, createControllerBinder(request, commandForm));
			request.setAttribute(CONTROLLER_METHOD_NAME, methodName);
			request.setAttribute(PRELOAD_MAPPING, extraMap);
			request.setAttribute(REDIRECT_ON_ERROR, tlRedirectOnError.get());
			request.setAttribute(BaseWebEnum.MODELVIEW.toString(), modelView);

			if (crudMode.equals(WebCRUDEnum.UPDATE_MODE) || crudMode.equals(WebCRUDEnum.DYNAMIC_MODE)
					|| crudMode.equals(WebCRUDEnum.DETAILS_MODE)|| crudMode.equals(WebCRUDEnum.QUERY_MODE)) {
				commandForm.setOrder(convertToOrderObject(commandForm.getDefaultOrders()));
			}
			
			response = executeCRUD(crudMode, getCRUDOperation(), modelView, request);
			
			if(response!=null){
				if(response.getCrudResult() instanceof BasePojoIF){
					pojo = (BasePojoIF)response.getCrudResult();
					
					if(pojo!=null && commandForm !=null){
						commandForm.setId(pojo.getId());
						
						if (crudMode.equals(WebCRUDEnum.DETAILS_MODE)
								|| crudMode.equals(WebCRUDEnum.INSERT_MODE)
								|| crudMode.equals(WebCRUDEnum.DELETE_MODE)
								|| crudMode.equals(WebCRUDEnum.UPDATE_MODE)
								|| crudMode.equals(WebCRUDEnum.QUERY_MODE)
								|| crudMode.equals(WebCRUDEnum.DYNAMIC_MODE)) {

							if(response.getCrudResult()!=null){
								//??
								commandForm.setOrder(convertToOrderObject(commandForm.getDefaultOrders()));
								ReflectionUtil.copyProperties(commandForm, response.getCrudResult());
								modelView.addObject(BaseWebEnum.COMMAND_FORM.toString(), commandForm);
							}
						}
						
					}
				}else if (response.getCrudResult() instanceof BasePojoRequestIF) {
					BasePojoRequestIF pojoRequest = (BasePojoRequestIF) response.getCrudResult();
					
					if(pojoRequest!=null && commandForm!=null){
						commandForm.setId(pojoRequest.getId());
						
						if (crudMode.equals(WebCRUDEnum.DETAILS_MODE)
								|| crudMode.equals(WebCRUDEnum.INSERT_MODE)
								|| crudMode.equals(WebCRUDEnum.DELETE_MODE)
								|| crudMode.equals(WebCRUDEnum.UPDATE_MODE)
								|| crudMode.equals(WebCRUDEnum.QUERY_MODE)
								|| crudMode.equals(WebCRUDEnum.DYNAMIC_MODE)) {
							
							if(response.getCrudResult()!=null){
								//??
								commandForm.setOrder(convertToOrderObject(commandForm.getDefaultOrders()));
								ReflectionUtil.copyProperties(commandForm, response.getCrudResult());
								modelView.addObject(BaseWebEnum.COMMAND_FORM.toString(), commandForm);
							}
						}
					}
				}
				
				commandForm.setCrudResponse(response);
				//for validation failed case
				request.getSession().setAttribute(RESPONSE_CRUD, response);
			}
		}
	
	}
	
	private ResponseCRUD executeCRUD(WebCRUDEnum crudMode, WebCRUDIF crudOperation, ModelAndView modelView, HttpServletRequest request) throws ApplicationCoreException{
		ResponseCRUD responseCRUD = null;
		
		if(modelView==null || modelView.getModel()==null){
			return null;
		}
		try {
			if (modelView != null && modelView.getModel() != null) {
				Object obj = modelView.getModel().get(BaseWebEnum.MODEL_BEAN.toString());
				BasePojoRequest pojoRequest = null;

				if (obj instanceof BasePojoRequest) {
					pojoRequest = (BasePojoRequest) obj;
				}

				Object form = modelView.getModel().get(BaseWebEnum.COMMAND_FORM.toString());

				Object[] params = new Object[] { pojoRequest, form, request };

				if (pojoRequest != null && modelView != null) {
					responseCRUD = (ResponseCRUD) MethodUtils.invokeMethod(crudOperation, crudMode.toString(), params);
				}
			}
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			throw new ApplicationCoreException(e);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			throw new ApplicationCoreException(e);
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			ApplicationCoreException ace = handleException(e);
			throw ace;
		}
		return responseCRUD;
		
	}
	
	private ServletRequestDataBinder createControllerBinder(HttpServletRequest request, Object obj) throws Exception{
		ServletRequestDataBinder binder = super.createBinder(request, obj);
		return binder;
	}

	private String constructWebApplicationURI(String viewName){
		StringBuilder pathStr = new StringBuilder();
		
		if(!viewName.startsWith("redirect:")&&!viewName.startsWith("forward:")){
			pathStr.append(getModuleWebContext());
		}
		
		pathStr.append(viewName);
		return pathStr.toString();
	}

	private void overrideModuleContext(ModelAndView modelAndView){
		if(getModuleWebContext()!=null){
			String viewName = modelAndView.getViewName();
			modelAndView.setViewName(constructWebApplicationURI(viewName));
		}
	}
	
	private ApplicationCoreException handleException(InvocationTargetException ite){
		
		ApplicationCoreException ace = null;
		
	    if(ite.getCause() instanceof CRUDException){
	    	
	    	CRUDException crudException = (CRUDException) ite.getCause();
	    	
	    	if(crudException.getFullMessage()!=null && crudException.getFullMessage().length()>0){
	    		ace = new ApplicationCoreException(crudException.getFullMessage(), ite.getCause());
	    	}else {
				ace = new ApplicationCoreException(crudException.getMessageCode(), ite.getCause());
			}
	    	
	    }else if (ite.getCause() instanceof DataAccessObjectException) {
	    	
			DataAccessObjectException dataAccessObjectException = (DataAccessObjectException) ite.getCause();
	    	ace = new ApplicationCoreException(dataAccessObjectException.getMessageCode(), ite.getCause());
	    	
		}else if (ite.getCause() instanceof ApplicationCoreException){
			
			ApplicationCoreException applicationCoreException = (ApplicationCoreException) ite.getCause();
			if(applicationCoreException.getFullMessage()!=null && applicationCoreException.getFullMessage().length()>0){
				ace = new ApplicationCoreException(applicationCoreException.getFullMessage(), ite.getCause());
			}else {
				ace = new ApplicationCoreException(applicationCoreException.getMessageCode(), ite.getCause());
			}
			
		}else if (ite.getCause() instanceof RuntimeException) {
			//..
		}
	    
	    if(ace ==null){
	    	ace = new ApplicationCoreException(SqlErrorCodes.SYSTEM_ERROR, ite.getCause());
	    }
		return ace;
	}
	
	private void populatePaginationParams(ModelAndView modelView, HttpServletRequest request){
		BaseWebFormIF commandForm = (BaseWebFormIF) modelView.getModelMap().get(BaseWebEnum.COMMAND_FORM.toString());
		if(BaseQueryWebFormIF.class.isAssignableFrom(commandForm.getClass())){
			BaseQueryWebFormIF queryForm = (BaseQueryWebFormIF) commandForm;
			String requestedPage = request.getParameter(REQUESTED_PAGE);
			String isFetchAll = request.getParameter(IS_FETCH_ALL);
			String isAscending = request.getParameter(IS_ASCENDING);
			String sortingProperty = request.getParameter(SORTING_PROPERTY);
			
			if(requestedPage!=null){
				queryForm.setRequestedPage(Integer.parseInt(requestedPage));
			}
			if(isFetchAll!=null){
				queryForm.setFetchAll(Boolean.parseBoolean(isFetchAll));
			}
			if(sortingProperty!=null && isAscending!=null){
				Order ordering = new Order(sortingProperty, true);
				ordering.setIsAscending(Boolean.parseBoolean(isAscending));
				Order[] orders = {ordering};
				queryForm.setOrder(orders);
			}
		}
	}
	
}



























