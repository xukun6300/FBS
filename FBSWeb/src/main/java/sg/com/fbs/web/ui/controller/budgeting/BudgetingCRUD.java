package sg.com.fbs.web.ui.controller.budgeting;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import sg.com.fbs.core.techinfra.exception.CRUDException;
import sg.com.fbs.core.techinfra.web.WebCRUDIF;
import sg.com.fbs.model.account.Account;
import sg.com.fbs.model.budgeting.PlanBudgetRequest;
import sg.com.fbs.model.business.pojo.BasePojoRequest;
import sg.com.fbs.model.system.persistence.response.IResponseCRUD;
import sg.com.fbs.services.budgeting.exception.BudgetingException;
import sg.com.fbs.services.budgeting.mgr.BudgetingManagerBD;
import sg.com.fbs.web.ui.form.budgeting.PlanBudgetForm;

/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu Kun $
 * @Created Feb 27, 2017 $
 * 
 */
public class BudgetingCRUD implements WebCRUDIF{

	private BudgetingManagerBD budgetingManagerBD = new BudgetingManagerBD();
	
	@Override
	public IResponseCRUD<?> runQuery(BasePojoRequest pojoRequest, Object form, HttpServletRequest request)
			throws CRUDException {
		
		IResponseCRUD<?> response = null;
		try {
			if(form instanceof PlanBudgetForm){
				PlanBudgetForm planBudgetForm = (PlanBudgetForm) form;
				PlanBudgetRequest planBudgetRequest = (PlanBudgetRequest) pojoRequest;
				response = budgetingManagerBD.loadPlanBudget(planBudgetRequest);
				
			}
		} catch (BudgetingException e) {
			throw new CRUDException(e.getMessageCode(), e);
		}

		return response;
	}

	@Override
	public IResponseCRUD<?> runDetails(BasePojoRequest pojoRequest, Object form, HttpServletRequest request)
			throws CRUDException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IResponseCRUD<?> insert(BasePojoRequest pojoRequest, Object form, HttpServletRequest request)
			throws CRUDException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IResponseCRUD<?> update(BasePojoRequest pojoRequest, Object form, HttpServletRequest request)
			throws CRUDException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IResponseCRUD<?> delete(BasePojoRequest pojoRequest, Object form, HttpServletRequest request)
			throws CRUDException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IResponseCRUD<?> dynamic(BasePojoRequest pojoRequest, Object form, HttpServletRequest request)
			throws CRUDException {
		// TODO Auto-generated method stub
		return null;
	}

}
