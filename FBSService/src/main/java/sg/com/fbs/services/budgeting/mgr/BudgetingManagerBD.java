package sg.com.fbs.services.budgeting.mgr;

import sg.com.fbs.core.businfra.businessdelegate.BusinessDelegate;
import sg.com.fbs.core.techinfra.exception.ApplicationCoreException;
import sg.com.fbs.model.budgeting.LineItem;
import sg.com.fbs.model.budgeting.PlanBudgetRequest;
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
	public IResponseCRUD loadPlanBudget(PlanBudgetRequest planBudgetRequest) throws BudgetingException{
		try {
			Object obj = budgetingManager.run(planBudgetRequest);
			return (IResponseCRUD) obj;
		} catch (ApplicationCoreException e) {
			throw new BudgetingException(e.getMessageCode(), e);
		}		
	}
	

	public LineItem saveLineItem(LineItem lineItem) throws BudgetingException{
		try {
			return (LineItem) budgetingManager.run(lineItem);
		} catch (ApplicationCoreException e) {
			throw new BudgetingException(e.getMessageCode(), e);
		}		
	}

	public LineItem deleteLineItem(Long lineItemId) throws BudgetingException{
		try {
			return (LineItem) budgetingManager.run(lineItemId);
		} catch (ApplicationCoreException e) {
			throw new BudgetingException(e.getMessageCode(), e);
		}		
	}
}
