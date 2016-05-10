package sg.com.fbs.services.account.mgr;

import org.springframework.beans.factory.annotation.Autowired;

import sg.com.fbs.core.businfra.businessdelegate.BusinessDelegate;
import sg.com.fbs.core.techinfra.exception.ApplicationCoreException;
import sg.com.fbs.model.account.AccountRequest;
import sg.com.fbs.model.system.persistence.response.ResponseCRUD;
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
}
