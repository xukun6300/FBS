package sg.com.fbs.services.budgeting.mgr;

import sg.com.fbs.core.businfra.businessdelegate.BusinessDelegate;
import sg.com.fbs.core.techinfra.exception.ApplicationCoreException;
import sg.com.fbs.model.system.persistence.response.IResponseCRUD;
import sg.com.fbs.services.budgeting.exception.BudgetingException;

/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu Kun $
 * @Created Mar 3, 2017 3:03:30 PM $
 * 
 */
public class BudgetingManagerBD extends BusinessDelegate{

	private BudgetingManager budgetingManager = new BudgetingManager();
	
	@SuppressWarnings("rawtypes")
	public IResponseCRUD showPlanBudgetForUser() throws BudgetingException{
		try {
			Object obj = budgetingManager.run();
			return (IResponseCRUD) obj;
		} catch (ApplicationCoreException e) {
			throw new BudgetingException(e.getMessageCode(), e);
		}
		
	}
}
