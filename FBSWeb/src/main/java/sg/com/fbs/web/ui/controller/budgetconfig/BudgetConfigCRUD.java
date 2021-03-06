package sg.com.fbs.web.ui.controller.budgetconfig;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import sg.com.fbs.core.techinfra.exception.CRUDException;
import sg.com.fbs.core.techinfra.web.WebCRUDIF;
import sg.com.fbs.model.budgetconfig.BudgetConfigRequest;
import sg.com.fbs.model.business.pojo.BasePojoRequest;
import sg.com.fbs.model.system.persistence.response.IResponseCRUD;
import sg.com.fbs.services.budgetconfig.exception.BudgetConfigException;
import sg.com.fbs.services.budgetconfig.mgr.BudgetConfigManagerBD;
import sg.com.fbs.web.ui.form.budgetconfig.ConfigNewBudgetingForm;

/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu Kun $
 * @Created Nov 15, 2016 $
 * 
 */
public class BudgetConfigCRUD implements WebCRUDIF{

	private BudgetConfigManagerBD budgetConfigManagerBD = new BudgetConfigManagerBD();
	
	@Override
	public IResponseCRUD<?> runQuery(BasePojoRequest pojoRequest, Object form, HttpServletRequest request)
			throws CRUDException {
		IResponseCRUD<?> response = null;
		
		try {
			if(form instanceof ConfigNewBudgetingForm){
				ConfigNewBudgetingForm configNewBudgetingForm = (ConfigNewBudgetingForm) form;
				configNewBudgetingForm.setBudgetForFY(budgetConfigManagerBD.getBudgetForFY());
				response = budgetConfigManagerBD.searchBudgetConfig(configNewBudgetingForm.getSearchCriteria(request));
			}
			
		} catch (BudgetConfigException e) {
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
		IResponseCRUD<?> response = null;
		
		try {
			if(pojoRequest instanceof BudgetConfigRequest){
				if(form instanceof ConfigNewBudgetingForm){
					ConfigNewBudgetingForm configNewBudgetingForm = (ConfigNewBudgetingForm) form;
					BudgetConfigRequest budgetConfigRequest = (BudgetConfigRequest) pojoRequest;
					//clear form user input
					configNewBudgetingForm.setBudgetStartDate(null);
					configNewBudgetingForm.setBudgetCutOffDate(null);
					configNewBudgetingForm.setBudgetForFY(budgetConfigManagerBD.getBudgetForFY());
					response = budgetConfigManagerBD.saveNewBudgeting(budgetConfigRequest,configNewBudgetingForm.getSearchCriteria(request));
				}
			}
			
		} catch (BudgetConfigException e) {
			throw new CRUDException(e.getMessageCode(), e);
		}
		
		return response;
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
