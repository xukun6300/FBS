package sg.com.fbs.services.budgetconfig.mgr;

import org.springframework.beans.factory.annotation.Autowired;

import sg.com.fbs.core.businfra.facade.CommonFacade;
import sg.com.fbs.core.techinfra.persistence.exception.DataAccessObjectException;
import sg.com.fbs.model.budgetconfig.BudgetConfig;
import sg.com.fbs.model.system.persistence.query.CriteriaIF;
import sg.com.fbs.model.system.persistence.response.IResponseCRUD;
import sg.com.fbs.services.budgetconfig.dao.BudgetConfigDao;
import sg.com.fbs.services.budgetconfig.exception.BudgetConfigException;

/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu Kun $
 * @Created Nov 17, 2016 5:40:07 PM $
 * 
 */
public class BudgetConfigManager extends CommonFacade{

	@Autowired
	private BudgetConfigDao budgetConfigDao;
	
	
	@SuppressWarnings("rawtypes")
	public IResponseCRUD searchBudgetConfig(CriteriaIF criteria) throws BudgetConfigException{
		try {
			IResponseCRUD response = budgetConfigDao.search(BudgetConfig.class, criteria);
			return response;
		} catch (DataAccessObjectException e) {		 
			e.printStackTrace();
			throw new BudgetConfigException(e.getMessageCode(), e);
		}
	}
	
	
	
}






