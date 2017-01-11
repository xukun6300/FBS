package sg.com.fbs.services.budgetconfig.mgr;

import org.springframework.beans.factory.annotation.Autowired;

import sg.com.fbs.core.businfra.businessdelegate.BusinessDelegate;
import sg.com.fbs.core.techinfra.exception.ApplicationCoreException;
import sg.com.fbs.model.system.persistence.query.CriteriaIF;
import sg.com.fbs.model.system.persistence.response.IResponseCRUD;
import sg.com.fbs.services.budgetconfig.exception.BudgetConfigException;

/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu Kun $
 * @Created Nov 17, 2016 5:39:49 PM $
 * 
 */
public class BudgetConfigManagerBD extends BusinessDelegate{

	@Autowired
	private BudgetConfigManager budgetConfigManager;
	
	@SuppressWarnings("rawtypes")
	public IResponseCRUD searchBudgetConfig(CriteriaIF criteria) throws BudgetConfigException{
		try {
			Object obj = budgetConfigManager.searchBudgetConfig(criteria);
			return (IResponseCRUD) obj;
		} catch (ApplicationCoreException e) {
			throw new BudgetConfigException(e.getMessageCode(), e);
		}
	}
	
}
