package sg.com.fbs.services.budgeting.mgr;

import java.util.List;

import sg.com.fbs.core.businfra.facade.CommonFacade;
import sg.com.fbs.core.techinfra.persistence.exception.DataAccessObjectException;
import sg.com.fbs.model.account.Account;
import sg.com.fbs.model.budgetconfig.BudgetConfig;
import sg.com.fbs.model.budgeting.LineItem;
import sg.com.fbs.model.budgeting.PlanBudgetRequest;
import sg.com.fbs.model.system.persistence.response.IResponseCRUD;
import sg.com.fbs.model.system.persistence.response.ResponseCRUD;
import sg.com.fbs.services.account.exception.AccountException;
import sg.com.fbs.services.account.mgr.AccountManager;
import sg.com.fbs.services.budgetconfig.exception.BudgetConfigException;
import sg.com.fbs.services.budgetconfig.mgr.BudgetConfigManager;
import sg.com.fbs.services.budgeting.dao.BudgetingDao;
import sg.com.fbs.services.budgeting.exception.BudgetingException;

/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu Kun $
 * @Created Mar 3, 2017 3:03:54 PM $
 * 
 */
public class BudgetingManager extends CommonFacade{

	private AccountManager accountManager = new AccountManager();
	
	private BudgetConfigManager budgetConfigManager = new BudgetConfigManager();
	
	private BudgetingDao budgetingDao = new BudgetingDao();
	
	@SuppressWarnings({"rawtypes" })
	public IResponseCRUD loadPlanBudget(PlanBudgetRequest planBudgetRequest) throws BudgetingException{
		IResponseCRUD response = new ResponseCRUD();		
		
		try {
			List<Account> accounts = accountManager.getAccountsForBudgetingByUser();
			planBudgetRequest.setAccounts(accounts);
			BudgetConfig budgetConfig = budgetConfigManager.getBudgetConfigForNow();
			if(budgetConfig!=null){
				planBudgetRequest.setBudgetForFY(budgetConfig.getBudgetConfigFY());
				planBudgetRequest.setBudgetStartDate(budgetConfig.getBudgetingStartDt());
				planBudgetRequest.setBudgetCutOffDate(budgetConfig.getBudgetingEndDt());
			}
		} catch (BudgetConfigException e) {
			e.printStackTrace();
			throw new BudgetingException(e.getMessageCode(), e);
		} catch (AccountException e) {
			e.printStackTrace();
			throw new BudgetingException(e.getMessageCode(), e);
		}
		
		response.setCrudResult(planBudgetRequest);
		return response;
	}
	
	
	public void saveLineItem(LineItem lineItem) throws BudgetingException{
		try {
			budgetingDao.insert(lineItem);
		} catch (DataAccessObjectException e) {
			e.printStackTrace();
			throw new BudgetingException(e.getMessageCode(), e);
		}
	}
	
	
}
























