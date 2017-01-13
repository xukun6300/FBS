package sg.com.fbs.web.ui.controller.account;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import sg.com.fbs.core.techinfra.exception.CRUDException;
import sg.com.fbs.core.techinfra.web.WebCRUDIF;
import sg.com.fbs.model.account.Account;
import sg.com.fbs.model.account.AccountRequest;
import sg.com.fbs.model.business.pojo.BasePojoRequest;
import sg.com.fbs.model.system.persistence.query.CriteriaIF;
import sg.com.fbs.model.system.persistence.response.IResponseCRUD;
import sg.com.fbs.model.system.persistence.response.ResponseCRUD;
import sg.com.fbs.model.user.UserRequest;
import sg.com.fbs.services.account.exception.AccountException;
import sg.com.fbs.services.account.mgr.AccountManagerBD;
import sg.com.fbs.web.ui.form.account.AccountForm;
import sg.com.fbs.web.ui.form.account.AccountSearchForm;

/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu Kun $
 * @Created May 6, 2016 $
 * 
 */
public class AccountCRUD implements WebCRUDIF{

	public static final String YES = "Yes";
	
	public static final String NO = "No";
	
	@Autowired
	private AccountManagerBD accountManagerBD;
	
	@SuppressWarnings("unchecked")
	@Override
	public IResponseCRUD<?> runQuery(BasePojoRequest pojoRequest, Object form, HttpServletRequest request)
			throws CRUDException {
		
		IResponseCRUD<?> response = null;
		try {
			if (form instanceof AccountSearchForm) {
				AccountSearchForm accountSearchForm = (AccountSearchForm) form;
				response = accountManagerBD.searchAccount(accountSearchForm.getSearchCriteria(request));
				
				Collection<Account> accounts = (Collection<Account>) response.getQueryResult();
				for (Account account : accounts) {
					if("Y".equalsIgnoreCase(account.getRequisitionForm())){
						account.setRequisitionForm(YES);
					}else {
						account.setRequisitionForm(NO);
					}
				}
			}
		} catch (AccountException e) {
			throw new CRUDException(e.getMessageCode(), e);
		}

		return response;
	}

	@Override
	public IResponseCRUD<?> runDetails(BasePojoRequest pojoRequest, Object form, HttpServletRequest request)
			throws CRUDException {
		
		IResponseCRUD<?> response = null;
		try {
			if(pojoRequest instanceof AccountRequest){
				AccountRequest accountRequest = (AccountRequest) pojoRequest;
				accountManagerBD.loadAccountDetails(accountRequest);
				response = new ResponseCRUD();
				response.setCrudResult(accountRequest);
			}
		} catch (AccountException e) {
			throw new CRUDException(e.getMessageCode(), e);
		}
		return response;
	}

	@Override
	public IResponseCRUD<?> insert(BasePojoRequest pojoRequest, Object form, HttpServletRequest request)
			throws CRUDException {
		
		IResponseCRUD<?> response = null;

		try {
			if (pojoRequest instanceof AccountRequest) {
				AccountRequest accountRequest = (AccountRequest) pojoRequest;
				response = accountManagerBD.saveAccount(accountRequest);
			}
		} catch (AccountException e) {
			throw new CRUDException(e.getMessageCode(), e);
		}
		return response;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public IResponseCRUD<?> update(BasePojoRequest pojoRequest, Object form, HttpServletRequest request)
			throws CRUDException {
		IResponseCRUD response = null;
		
		try {			
			if(pojoRequest instanceof AccountRequest){
				AccountRequest accountRequest = (AccountRequest) pojoRequest;
				
				response = accountManagerBD.updateAccount(accountRequest);
			}
			
		} catch (AccountException e) {
			throw new CRUDException(e.getMessageCode(), e);
		}
		
		
		return response;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public IResponseCRUD<?> delete(BasePojoRequest pojoRequest, Object form, HttpServletRequest request) throws CRUDException {
		IResponseCRUD response = null;
		AccountManagerBD accountManagerBD = new AccountManagerBD();
		try {
			if(form instanceof AccountSearchForm){
				AccountSearchForm formBean = (AccountSearchForm) form;
				CriteriaIF criteria = formBean.getSearchCriteria(request);
				response = accountManagerBD.deleteAndShowAccount(criteria, formBean.getId());
			}
		} catch (AccountException e) {
			throw new CRUDException(e.getMessageCode(), e);
		}
		
		return response;
	}

	@Override
	public IResponseCRUD<?> dynamic(BasePojoRequest pojoRequest, Object form, HttpServletRequest request)
			throws CRUDException {
		// TODO Auto-generated method stub
		return null;
	}

}
