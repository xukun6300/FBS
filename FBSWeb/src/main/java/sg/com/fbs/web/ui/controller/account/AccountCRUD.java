package sg.com.fbs.web.ui.controller.account;

import javax.servlet.http.HttpServletRequest;

import sg.com.fbs.core.techinfra.exception.CRUDException;
import sg.com.fbs.core.techinfra.web.WebCRUDIF;
import sg.com.fbs.model.account.AccountRequest;
import sg.com.fbs.model.business.pojo.BasePojoRequest;
import sg.com.fbs.model.system.persistence.response.IResponseCRUD;
import sg.com.fbs.services.account.exception.AccountException;
import sg.com.fbs.services.account.mgr.AccountManagerBD;

/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu Kun $
 * @Created May 6, 2016 $
 * 
 */
public class AccountCRUD implements WebCRUDIF{

	@Override
	public IResponseCRUD<?> runQuery(BasePojoRequest pojoRequest, Object form, HttpServletRequest request)
			throws CRUDException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IResponseCRUD<?> runDetails(BasePojoRequest pojoRequest, Object form, HttpServletRequest request)
			throws CRUDException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IResponseCRUD<?> insert(BasePojoRequest pojoRequest, Object form, HttpServletRequest request)
			throws CRUDException {
		
		IResponseCRUD<?> response = null;
		AccountManagerBD accountManagerBD = new AccountManagerBD();
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

	@Override
	public IResponseCRUD<?> update(BasePojoRequest pojoRequest, Object form, HttpServletRequest request)
			throws CRUDException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IResponseCRUD<?> delete(BasePojoRequest pojoRequest, Object form, HttpServletRequest request)
			throws CRUDException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IResponseCRUD<?> dynamic(BasePojoRequest pojoRequest, Object form, HttpServletRequest request)
			throws CRUDException {
		// TODO Auto-generated method stub
		return null;
	}

}
