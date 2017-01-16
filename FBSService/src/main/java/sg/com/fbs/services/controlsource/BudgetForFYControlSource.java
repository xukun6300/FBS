package sg.com.fbs.services.controlsource;

import java.util.Map;

import sg.com.fbs.core.techinfra.exception.ApplicationCoreException;
import sg.com.fbs.services.budgetconfig.mgr.BudgetConfigManagerBD;

/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu Kun $
 * @Created Jan 16, 2017 3:41:12 PM $
 * 
 */
public class BudgetForFYControlSource extends BaseControlSource{

	@Override
	public Map getControlSourceValues(Map params) throws ApplicationCoreException {
		return new BudgetConfigManagerBD().getBudgetForFYs();
	}

}
