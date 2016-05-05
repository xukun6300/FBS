package sg.com.fbs.web.ui.controller.account;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.Validator;
import org.springframework.web.servlet.ModelAndView;

import sg.com.fbs.core.techinfra.web.BaseWebController;
import sg.com.fbs.core.techinfra.web.Mvc;
import sg.com.fbs.web.ui.form.account.AccountForm;



/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu Kun $
 * @Created May 5, 2016 $
 * 
 */
public class AccountController extends BaseWebController{

	@Override
	public String getModuleWebContext() {
		return AccountWebEnum.ACCOUNT_JSP_PLACEHOLDER.toString();
	}

	@Override
	public void preLoad(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ModelAndView postLoad(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Validator getCustomValidator() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	/**
	 * Context /account/showAddAccount.action
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView showAddAccount(HttpServletRequest request, HttpServletResponse response){
		AccountForm accountForm = new AccountForm();
		Mvc mvc = new Mvc(accountForm, AccountWebEnum.SHOW_ADD_ACCOUNT_JSP.toString());
		return mvc;
	}

}
