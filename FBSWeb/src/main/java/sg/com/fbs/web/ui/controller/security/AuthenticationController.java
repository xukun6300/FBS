package sg.com.fbs.web.ui.controller.security;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Validator;
import org.springframework.web.servlet.ModelAndView;

import sg.com.fbs.core.techinfra.web.BaseWebController;
import sg.com.fbs.core.techinfra.web.Mvc;
import sg.com.fbs.core.techinfra.web.WebCRUDEnum;
import sg.com.fbs.services.security.password.PasswordServices;

/**
 * @Author Frank Xu $
 * @Created 11:54:52 am 26 Aug, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public class AuthenticationController extends BaseWebController {

	@Autowired
	private PasswordServices passwordServices;
	
	@Override
	public String getModuleWebContext() {
		return AuthenticationWebEnum.AUTHENTICATION_JSP_PLACEHOLDER.toString();
	}

	@Override
	public void preLoad(Map<String, Object> map, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		
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
	
	
	public ModelAndView showLogin(HttpServletRequest request, HttpServletResponse response){
		setCrudMode(WebCRUDEnum.NONE);
		Mvc mvc = new Mvc(AuthenticationWebEnum.SHOW_LOGIN_JSP.toString());
		mvc.addObject("modulus", passwordServices.getTransportRSAKeyModulus());
		mvc.addObject("exponent", passwordServices.getTransportRSAKeyExponent());
		return mvc;
	}
	
	
	
	
	

}




















