package sg.com.fbs.services.account.mgr;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import sg.com.fbs.core.businfra.businessdelegate.BusinessDelegate;
import sg.com.fbs.core.techinfra.exception.ApplicationCoreException;
import sg.com.fbs.model.account.AccountRequest;
import sg.com.fbs.model.system.persistence.query.CriteriaIF;
import sg.com.fbs.model.system.persistence.response.IResponseCRUD;
import sg.com.fbs.model.system.persistence.response.ResponseCRUD;
import sg.com.fbs.model.user.UserRequest;
import sg.com.fbs.services.account.exception.AccountException;

/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu Kun $
 * @Created May 6, 2016 10:22:16 AM $
 * 
 */
public class AccountManagerBD extends BusinessDelegate {

	@Autowired
	private AccountManager accountManager;
	
	@SuppressWarnings("rawtypes")
	public ResponseCRUD saveAccount(AccountRequest accountRequest) throws AccountException{
		try {
			Object object = accountManager.run(accountRequest);
			return (ResponseCRUD) object;
		} catch (ApplicationCoreException e) {
			throw new AccountException(e.getMessageCode(), e);
		}
		
	}
	
	public Boolean checkAccountCodeExist(String accountCode) throws AccountException{
		try {
             Object object = accountManager.run(accountCode);
             return (Boolean)object;
		} catch (ApplicationCoreException e) {
			throw new AccountException(e.getMessageCode(), e);
		}
	}
	
	@SuppressWarnings("rawtypes")
	public IResponseCRUD searchAccount(CriteriaIF criteria) throws AccountException{
		try {
            Object object = accountManager.run(criteria);
            return (IResponseCRUD)object;
		} catch (ApplicationCoreException e) {
			throw new AccountException(e.getMessageCode(), e);
		}
	}
	
	public Map<String, String> getFinancialYears() throws AccountException{
		try {
            Object object = new AccountManager().run();
            return (Map<String, String>)object;
		} catch (ApplicationCoreException e) {
			throw new AccountException(e.getMessageCode(), e);
		}
	}
	
	public void loadAccountDetails(AccountRequest accountRequest) throws AccountException{
		try {
			accountManager.run(accountRequest);			
		} catch (ApplicationCoreException e) {
			throw new AccountException(e.getMessageCode(), e);
		}
	}
	
	@SuppressWarnings("rawtypes")
	public IResponseCRUD updateAccount(AccountRequest accountRequest) throws AccountException{
		try {
            Object object = accountManager.run(accountRequest);
            return (IResponseCRUD)object;
		} catch (ApplicationCoreException e) {
			throw new AccountException(e.getMessageCode(), e);
		}
	}
	
	@SuppressWarnings("rawtypes")
	public ResponseCRUD deleteAndShowAccount(CriteriaIF criteria, long accountId) throws AccountException{
		try {
            Object object = new AccountManager().run(criteria, accountId);
            return (ResponseCRUD)object;
		} catch (ApplicationCoreException e) {
			throw new AccountException(e.getMessageCode(), e);
		}
	}
	
	
}




