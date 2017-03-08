package sg.com.fbs.services.budgeting.mgr;

import java.util.List;

import sg.com.fbs.core.businfra.facade.CommonFacade;
import sg.com.fbs.model.account.Account;
import sg.com.fbs.model.system.persistence.response.IResponseCRUD;
import sg.com.fbs.model.system.persistence.response.ResponseCRUD;
import sg.com.fbs.services.account.exception.AccountException;
import sg.com.fbs.services.account.mgr.AccountManager;
import sg.com.fbs.services.budgeting.exception.BudgetingException;

/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu Kun $
 * @Created Mar 3, 2017 3:03:54 PM $
 * 
 */
public class BudgetingManager extends CommonFacade{

	private AccountManager accountManager = new AccountManager();
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public IResponseCRUD showPlanBudgetForUser() throws BudgetingException, AccountException{
		IResponseCRUD response = new ResponseCRUD();		
		List<Account> accounts = accountManager.getAccountsForBudgetingByUser();
		System.out.println("------"+accounts.get(0).getAcctStructures().get(0).getColumnName());
		response.setQueryResult(accounts);
		return response;
	}
	
	
}
























