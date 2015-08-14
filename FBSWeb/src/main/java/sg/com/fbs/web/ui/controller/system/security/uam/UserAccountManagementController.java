package sg.com.fbs.web.ui.controller.system.security.uam;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.Validator;
import org.springframework.web.servlet.ModelAndView;

import sg.com.fbs.core.techinfra.web.BaseWebController;
import sg.com.fbs.core.techinfra.web.DefaultRefDataSource;
import sg.com.fbs.core.techinfra.web.Mvc;
import sg.com.fbs.core.techinfra.web.WebCRUDEnum;
import sg.com.fbs.services.security.password.PasswordServices;
import sg.com.fbs.web.ui.form.system.security.uam.RegisterUserForm;

/**
 * @Author Frank Xu $
 * @Created 9:33:33 am 30 Jul, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public class UserAccountManagementController extends BaseWebController {

	@Resource
	private PasswordServices passwordServices;
	
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
			UserAccountManagementWebEnum.PRIMARY_CONTACT_TYPE_T_LIST
		};
		
		String[] views = {UserAccountManagementWebEnum.SHOW_REGISTER_USER.toString()};
		
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
		// TODO Auto-generated method stub
		return null;
	}
	
	public ModelAndView showRegisterUser(HttpServletRequest request, HttpServletResponse response){
		RegisterUserForm registerUserForm = new RegisterUserForm();
		registerUserForm.setModulus(passwordServices.getTransportRSAKeyModulus());
		registerUserForm.setExponent(passwordServices.getTransportRSAKeyExponent());
		
		setCrudMode(WebCRUDEnum.NONE);
		setValidationErrorPage(UserAccountManagementWebEnum.SHOW_REGISTER_USER.toString());
		Mvc mvc = new Mvc(registerUserForm, UserAccountManagementWebEnum.SHOW_REGISTER_USER.toString());
		//System.out.println(passwordServices.encodePasswordWithBCrypt("a"));
		
	/*	CryptoServicesClientManager cry = new CryptoServicesClientManager();
		cry.getTransportRSAKeyExponent();
		CryptoOperationsManager aCryptoOperationsManager = new CryptoOperationsManager();
		aCryptoOperationsManager.getTransportKeyModulus();*/
		return mvc;
	}

	
	
}
