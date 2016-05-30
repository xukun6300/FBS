package sg.com.fbs.web.ui.controller.system.security.uam;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Validator;
import org.springframework.web.servlet.ModelAndView;

import sg.com.fbs.core.techinfra.web.BaseWebController;
import sg.com.fbs.core.techinfra.web.DefaultRefDataSource;
import sg.com.fbs.core.techinfra.web.Mvc;
import sg.com.fbs.core.techinfra.web.WebCRUDEnum;
import sg.com.fbs.model.system.security.uam.RegisterUserRequest;
import sg.com.fbs.model.user.UserRequest;
import sg.com.fbs.services.security.captcha.CaptchaService;
import sg.com.fbs.services.security.password.PasswordServices;
import sg.com.fbs.web.ui.form.system.security.uam.RegisterUserForm;
import sg.com.fbs.web.ui.form.system.security.uam.UserDetailsForm;
import sg.com.fbs.web.ui.form.system.security.uam.UserSearchForm;

/**
 * @Author Frank Xu $
 * @Created 9:33:33 am 30 Jul, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public class UserAccountManagementController extends BaseWebController {

	@Resource
	private PasswordServices passwordServices; 
	
	@Autowired
	private CaptchaService captchaService;
	
	@Override
	public String getModuleWebContext() {
		return UserAccountManagementWebEnum.USERACCOUNTMANAGEMENT_JSP_PLACEHOLDER.toString();
	}

	@Override
	public void preLoad(Map<String, Object> map, HttpServletRequest request,
			HttpServletResponse response) {
		UserAccountManagementWebEnum[] comboInitializer = {
			UserAccountManagementWebEnum.SALUTATION_TYPE,
			UserAccountManagementWebEnum.GENDER_TYPE_T_LIST,
			UserAccountManagementWebEnum.PRIMARY_CONTACT_TYPE_T_LIST,
			UserAccountManagementWebEnum.SECURITY_QUESTION_LIST,
			UserAccountManagementWebEnum.ACCOUNT_STATUS_LIST,
			UserAccountManagementWebEnum.ALL_ACCOUNTS_LIST
		};
		
		String[] views = {
				UserAccountManagementWebEnum.SHOW_REGISTER_USER_JSP.toString(),
				UserAccountManagementWebEnum.USER_SEARCH_JSP.toString()
				};
		
		for (String view : views) {
			map.put(view, comboInitializer);
		}
		
		map.put(DefaultRefDataSource.WEB_LIST_DATA_SOURCE, new UserAccountManagementRefDataSource());
	}

	@Override
	public ModelAndView postLoad(Map<String, Object> map,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Validator getCustomValidator() {
		return new UserAccountManagementValidator();
	}
	
	public ModelAndView showRegisterUser(HttpServletRequest request, HttpServletResponse response){
		RegisterUserForm registerUserForm = new RegisterUserForm();
		registerUserForm.setModulus(passwordServices.getTransportRSAKeyModulus());
		registerUserForm.setExponent(passwordServices.getTransportRSAKeyExponent());
		registerUserForm.setSalt(passwordServices.getSalt());
		setCrudMode(WebCRUDEnum.NONE);
		setValidationErrorPage(UserAccountManagementWebEnum.SHOW_REGISTER_USER_JSP.toString());
		Mvc mvc = new Mvc(registerUserForm, UserAccountManagementWebEnum.SHOW_REGISTER_USER_JSP.toString());

		return mvc;
	}

	public ModelAndView saveNewUser(HttpServletRequest request, HttpServletResponse response) {
		RegisterUserForm registerUserForm = new RegisterUserForm();
		registerUserForm.setModulus(passwordServices.getTransportRSAKeyModulus());
		registerUserForm.setExponent(passwordServices.getTransportRSAKeyExponent());
		registerUserForm.setSalt(passwordServices.getSalt());
		
		Boolean captchaValidationRlt = captchaValidation(request);
		registerUserForm.setCaptchaValidationRtl(String.valueOf(captchaValidationRlt));
		RegisterUserRequest registerUserRequest = new RegisterUserRequest();
		
		setCrudMode(WebCRUDEnum.INSERT_MODE);
		setCRUDOperation(UserAccountManagementCRUD.class);
		setValidationErrorPage(UserAccountManagementWebEnum.SHOW_REGISTER_USER_JSP.toString());
		
		Mvc mvc = new Mvc(registerUserForm, UserAccountManagementWebEnum.UAM_CONFIRMATION_JSP.toString(), registerUserRequest);		
		mvc.addObject("backBtnAction", "/authentication/showLogin.action");
		
		return mvc;
	}
	
	public ModelAndView searchUser(HttpServletRequest request, HttpServletResponse response){
		UserSearchForm userSearchForm = new UserSearchForm();
		UserRequest userRequest = new UserRequest();
		setCrudMode(WebCRUDEnum.QUERY_MODE);
		setCRUDOperation(UserAccountManagementCRUD.class);
		setValidationErrorPage(UserAccountManagementWebEnum.USER_SEARCH_JSP.toString());
		
		Mvc mvc = new Mvc(userSearchForm,UserAccountManagementWebEnum.USER_SEARCH_JSP.toString(),userRequest);
		return mvc;
	}
	
	public ModelAndView showUserDetails(HttpServletRequest request, HttpServletResponse response){
		UserDetailsForm userDetailsForm = new UserDetailsForm();
		UserRequest userRequest = new UserRequest();
		setCrudMode(WebCRUDEnum.DETAILS_MODE);
		setCRUDOperation(UserAccountManagementCRUD.class);
		setValidationErrorPage(UserAccountManagementWebEnum.USER_DETAILS_JSP.toString());
		Mvc mvc = new Mvc(userDetailsForm, UserAccountManagementWebEnum.USER_DETAILS_JSP.toString(), userRequest);
		return mvc;
	}
	
	
	
	private boolean captchaValidation(HttpServletRequest request){
		HttpSession session = request.getSession(false);
		return captchaService.validateResponseForID(session.getId(), request.getParameter("captchaResponse"));
	}
	
	
}












