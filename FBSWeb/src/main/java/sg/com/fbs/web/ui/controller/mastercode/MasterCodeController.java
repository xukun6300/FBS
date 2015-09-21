package sg.com.fbs.web.ui.controller.mastercode;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.Validator;
import org.springframework.web.servlet.ModelAndView;

import sg.com.fbs.core.techinfra.web.BaseWebController;
import sg.com.fbs.core.techinfra.web.Mvc;
import sg.com.fbs.core.techinfra.web.WebCRUDEnum;
import sg.com.fbs.model.domain.mastercode.MasterCodeTypeRequest;
import sg.com.fbs.web.ui.form.mastercode.MasterCodeForm;
import sg.com.fbs.web.ui.form.mastercode.MasterCodeTypeForm;

/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu $
 * @Created 2:46:18 pm 16 Sep, 2015 $
 * 
 */
public class MasterCodeController extends BaseWebController{

	@Override
	public String getModuleWebContext() {
		return MasterCodeWebEnum.MASTER_CODE_JSP_PLACEHOLDER.toString();
	}

	@Override
	public void preLoad(Map<String, Object> map, HttpServletRequest request,
			HttpServletResponse response) {
		
	}

	@Override
	public ModelAndView postLoad(Map<String, Object> map,
			HttpServletRequest request, HttpServletResponse response) {

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
		//setCrudMode(WebCRUDEnum.NONE);
		Mvc mvc = new Mvc(masterCodeForm, MasterCodeWebEnum.SHOW_ADD_CODE_VALUE_JSP.toString());
		return mvc;
	}
	
	

}














