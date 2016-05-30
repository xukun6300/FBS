package sg.com.fbs.services.controlsource;

import java.util.Map;

import sg.com.fbs.core.techinfra.exception.ApplicationCoreException;
import sg.com.fbs.services.account.mgr.AccountManagerBD;

/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu Kun $
 * @Created May 30, 2016 3:59:43 PM $
 * 
 */
public class AccountControlSource extends BaseControlSource{

	@Override
	public Map getControlSourceValues(Map params) throws ApplicationCoreException {
		AccountManagerBD accountManagerBD= new AccountManagerBD();
		return accountManagerBD.getAllAccountsForCurrentFY();
	}

}
