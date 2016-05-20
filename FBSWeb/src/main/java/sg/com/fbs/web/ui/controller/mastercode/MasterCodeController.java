package sg.com.fbs.web.ui.controller.mastercode;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.Validator;
import org.springframework.web.servlet.ModelAndView;

import sg.com.fbs.core.techinfra.web.BaseWebController;
import sg.com.fbs.core.techinfra.web.DefaultRefDataSource;
import sg.com.fbs.core.techinfra.web.Mvc;
import sg.com.fbs.core.techinfra.web.WebCRUDEnum;
import sg.com.fbs.model.domain.mastercode.MasterCodeRequest;
import sg.com.fbs.model.domain.mastercode.MasterCodeTypeEnum;
import sg.com.fbs.model.domain.mastercode.MasterCodeTypeRequest;
import sg.com.fbs.web.ui.form.mastercode.MasterCodeForm;
import sg.com.fbs.web.ui.form.mastercode.MasterCodeTypeForm;
import sg.com.fbs.web.ui.form.mastercode.MasterCodeTypeListForm;
import sg.com.fbs.web.ui.form.mastercode.MasterCodeTypeSearchForm;

/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu $
 * @Created 2:46:18 pm 16 Sep, 2015 $
 * 
 */
/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu Kun $
 * @Created May 20, 2016 $
 * 
 */
/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu Kun $
 * @Created May 20, 2016 $
 * 
 */
public class MasterCodeController extends BaseWebController{

	@Override
	public String getModuleWebContext() {
		return MasterCodeWebEnum.MASTER_CODE_JSP_PLACEHOLDER.toString();
	}

	@Override
	public void preLoad(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) {
		MasterCodeWebEnum[] comboInitializer = {
				MasterCodeWebEnum.CODE_KEYS_T_LIST
		};
		
		String[] views = {
				MasterCodeWebEnum.SHOW_ADD_CODE_VALUE_JSP.toString()
		};
		
		for (String view : views) {
			map.put(view, comboInitializer);
		}
		
		map.put(DefaultRefDataSource.WEB_LIST_DATA_SOURCE, new MasterCodeRefDataSource());
 	}

	@Override
	public ModelAndView postLoad(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) {

		return null;
	}

	@Override
	public Validator getCustomValidator() {
		return null;
	}
	
	/**
	 * Context /mastercode/showAddCodeKey.action
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView showAddCodeKey(HttpServletRequest request, HttpServletResponse response){
		MasterCodeTypeForm masterCodeTypeForm = new MasterCodeTypeForm();
		Mvc mvc = new Mvc(masterCodeTypeForm, MasterCodeWebEnum.SHOW_ADD_CODE_KEY_JSP.toString());
		return mvc;
	}
	
	/**
	 * Context /mastercode/addCodeKey.action
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView addCodeKey(HttpServletRequest request, HttpServletResponse response){
		MasterCodeTypeForm masterCodeTypeForm = new MasterCodeTypeForm();
		MasterCodeTypeRequest masterCodeTypeRequest = new MasterCodeTypeRequest();
		setCrudMode(WebCRUDEnum.INSERT_MODE);
		setCRUDOperation(MasterCodeCRUD.class);
		setValidationErrorPage(MasterCodeWebEnum.ADD_CODE_KEY_JSP.toString());
		Mvc mvc = new Mvc(masterCodeTypeForm, MasterCodeWebEnum.CONFIRM_ADD_CODE_KEY_JSP.toString(), masterCodeTypeRequest);
		return mvc;
		
	}
	
	/**
	 * Context /mastercode/showAddCodeValue.action
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView showAddCodeValue(HttpServletRequest request, HttpServletResponse response){
		MasterCodeForm masterCodeForm = new MasterCodeForm();
		Mvc mvc = new Mvc(masterCodeForm, MasterCodeWebEnum.SHOW_ADD_CODE_VALUE_JSP.toString());
		return mvc;
	}
	
	/**
	 * Context /mastercode/addCodeValue.action
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView addCodeValue(HttpServletRequest request, HttpServletResponse response){
		MasterCodeForm masterCodeForm = new MasterCodeForm();
		MasterCodeRequest masterCodeRequest = new MasterCodeRequest();
		setCrudMode(WebCRUDEnum.INSERT_MODE);
		setCRUDOperation(MasterCodeCRUD.class);
		setValidationErrorPage(MasterCodeWebEnum.SHOW_ADD_CODE_VALUE_JSP.toString());
		Mvc mvc = new Mvc(masterCodeForm, MasterCodeWebEnum.CONFIRM_ADD_CODE_VALUE_JSP.toString(), masterCodeRequest);
		return mvc;
	}
	
	/**
	 * Context /mastercode/searchCategoryType.action 
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView searchCategoryType(HttpServletRequest request, HttpServletResponse response){
		MasterCodeTypeSearchForm masterCodeTypeSearchForm = new MasterCodeTypeSearchForm();
		MasterCodeTypeRequest masterCodeTypeRequest = new MasterCodeTypeRequest();
		setCrudMode(WebCRUDEnum.QUERY_MODE);
		setCRUDOperation(MasterCodeCRUD.class);
		setValidationErrorPage(MasterCodeWebEnum.SEARCH_CATEGORY_TYPE_JSP.toString());
		Mvc mvc = new Mvc(masterCodeTypeSearchForm, MasterCodeWebEnum.SEARCH_CATEGORY_TYPE_JSP.toString(), masterCodeTypeRequest);
		return mvc;
	}
	
	
	/**
	 * Context /mastercode/listCodeValuesDetails.action 
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView listCodeValuesDetails(HttpServletRequest request, HttpServletResponse response){
		MasterCodeTypeListForm masterCodeTypeListForm = new MasterCodeTypeListForm();
		MasterCodeTypeRequest masterCodeTypeRequest = new MasterCodeTypeRequest();
		masterCodeTypeListForm.setTxnType(MasterCodeWebEnum.LIST_CODE_VALUES_TXN_TYPE.toString());
		setCrudMode(WebCRUDEnum.DETAILS_MODE);
		setCRUDOperation(MasterCodeCRUD.class);
		setValidationErrorPage(MasterCodeWebEnum.LIST_CODE_VALUES_JSP.toString());
		Mvc mvc = new Mvc(masterCodeTypeListForm, MasterCodeWebEnum.LIST_CODE_VALUES_JSP.toString(), masterCodeTypeRequest);
		return mvc;
	}
	
	
	/**
	 * Context /mastercode/showDeleteCodeKey.action 
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView showDeleteCodeKey(HttpServletRequest request, HttpServletResponse response){
		MasterCodeTypeForm masterCodeTypeForm = new MasterCodeTypeForm();
		MasterCodeTypeRequest masterCodeTypeRequest = new MasterCodeTypeRequest();
		setCrudMode(WebCRUDEnum.DETAILS_MODE);
		setCRUDOperation(MasterCodeCRUD.class);
		Mvc mvc = new Mvc(masterCodeTypeForm, MasterCodeWebEnum.SHOW_DELETE_CODE_KEY_JSP.toString(), masterCodeTypeRequest);
		return mvc;		
	}

}














