package sg.com.fbs.services.controlsource;

import java.util.Map;

import sg.com.fbs.core.techinfra.exception.ApplicationCoreException;
import sg.com.fbs.services.account.mgr.AccountManagerBD;

/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu Kun $
 * @Created May 11, 2016 5:41:36 PM $
 * 
 */
public class FinancialYearControlSource extends BaseControlSource{

	@Override
	public Map<String, String> getControlSourceValues(Map params) throws ApplicationCoreException {
		return new AccountManagerBD().getFinancialYears();
	}

}
