package sg.com.fbs.web.ui.controller.account;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Validator;
import org.springframework.web.servlet.ModelAndView;

import sg.com.fbs.core.techinfra.web.BaseWebController;
import sg.com.fbs.core.techinfra.web.DefaultRefDataSource;
import sg.com.fbs.core.techinfra.web.Mvc;
import sg.com.fbs.core.techinfra.web.WebCRUDEnum;
import sg.com.fbs.model.account.AccountRequest;
import sg.com.fbs.web.ui.form.account.AccountForm;
import sg.com.fbs.web.ui.form.account.AccountSearchForm;
import sg.com.fbs.web.ui.form.system.security.uam.UserSearchForm;



/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu Kun $
 * @Created May 5, 2016 $
 * 
 */
/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu Kun $
 * @Created May 11, 2016 $
 * 
 */
/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu Kun $
 * @Created May 13, 2016 $
 * 
 */
public class AccountController extends BaseWebController{

	@Autowired
	private AccountCRUD accountCrud;
	
	@Autowired
	private AccountValidator accountValidator;
	
	@Override
	public String getModuleWebContext() {
		return AccountWebEnum.ACCOUNT_JSP_PLACEHOLDER.toString();
	}

	@Override
	public void preLoad(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) {
		AccountWebEnum[] comboInitializer = {
				AccountWebEnum.FINANCIAL_YEAR__LIST
		};
		
		String[] views = {
				AccountWebEnum.SEARCH_ACCOUNT_JSP.toString()
		};
		
		for (String view : views) {
			map.put(view, comboInitializer);
		}
		
		map.put(DefaultRefDataSource.WEB_LIST_DATA_SOURCE, new AccountRefDataSource());
	}

	@Override
	public ModelAndView postLoad(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) {
		
		return null;
	}

	@Override
	public Validator getCustomValidator() {
		return accountValidator;
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
	
	/**
	 * Context /account/addNewAccount.action
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView addNewAccount(HttpServletRequest request, HttpServletResponse response){
		AccountForm accountForm = new AccountForm();
		AccountRequest accountRequest = new AccountRequest();
		setCrudMode(WebCRUDEnum.INSERT_MODE);
		setCRUDOperation(accountCrud);
		setValidationErrorPage(AccountWebEnum.SHOW_ADD_ACCOUNT_JSP.toString());
		Mvc mvc = new Mvc(accountForm, AccountWebEnum.CONFIRM_ADD_ACCOUNT_JSP.toString(), accountRequest);
		return mvc;
	}
	
	/**
	 * Context /account/searchAccount.action
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView searchAccount(HttpServletRequest request, HttpServletResponse response){
		AccountSearchForm accountSearchForm = new AccountSearchForm();
		AccountRequest accountRequest = new AccountRequest();
		setCrudMode(WebCRUDEnum.QUERY_MODE);
		setCRUDOperation(accountCrud);
		setValidationErrorPage(AccountWebEnum.SEARCH_ACCOUNT_JSP.toString());
		Mvc mvc = new Mvc(accountSearchForm, AccountWebEnum.SEARCH_ACCOUNT_JSP.toString(), accountRequest);
		return mvc;
	}
	
	
	/**
	 * Context /account/showAccountDetails.action
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView showAccountDetails(HttpServletRequest request, HttpServletResponse response){
		AccountForm accountForm = new AccountForm();
		AccountRequest accountRequest = new AccountRequest();
		setCrudMode(WebCRUDEnum.DETAILS_MODE);
		setCRUDOperation(accountCrud);
		setValidationErrorPage(AccountWebEnum.SHOW_ACCOUNT_DETAILS_JSP.toString());
		Mvc mvc = new Mvc(accountForm, AccountWebEnum.SHOW_ACCOUNT_DETAILS_JSP.toString(), accountRequest);
		return mvc;
	}
	
	/**
	 * Context /account/updateAccount.action
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView updateAccount(HttpServletRequest request, HttpServletResponse response){
		AccountForm accountForm = new AccountForm();
		AccountRequest accountRequest = new AccountRequest();
		setCrudMode(WebCRUDEnum.UPDATE_MODE);
		setCRUDOperation(accountCrud);
		setValidationErrorPage(AccountWebEnum.SHOW_ACCOUNT_DETAILS_JSP.toString());
		Mvc mvc = new Mvc(accountForm, AccountWebEnum.CONFIRM_ADD_ACCOUNT_JSP.toString(), accountRequest);
		return mvc;
	}

}









