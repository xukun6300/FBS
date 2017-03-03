package sg.com.fbs.services.budgeting.mgr;

import java.util.List;

import sg.com.fbs.core.businfra.facade.CommonFacade;
import sg.com.fbs.model.system.persistence.response.IResponseCRUD;
import sg.com.fbs.model.system.persistence.response.ResponseCRUD;
import sg.com.fbs.services.budgeting.exception.BudgetingException;

/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu Kun $
 * @Created Mar 3, 2017 3:03:54 PM $
 * 
 */
public class BudgetingManager extends CommonFacade{

	public IResponseCRUD showPlanBudgetForUser() throws BudgetingException{
		IResponseCRUD response = new ResponseCRUD();
		
		return response;
	}
	
	
}
























