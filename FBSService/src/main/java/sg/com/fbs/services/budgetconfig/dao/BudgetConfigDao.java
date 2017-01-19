package sg.com.fbs.services.budgetconfig.dao;

import sg.com.fbs.core.techinfra.persistence.exception.DataAccessObjectException;
import sg.com.fbs.model.budgetconfig.BudgetConfig;
import sg.com.fbs.model.domain.enumeration.ActiveStatusEnum;
import sg.com.fbs.techinfra.persistence.dao.BaseDao;

/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu Kun $
 * @Created Nov 17, 2016 5:43:52 PM $
 * 
 */
public class BudgetConfigDao extends BaseDao{

	public BudgetConfig getBudgetConfigByFy(Integer financialYear) throws DataAccessObjectException{
		BudgetConfig budgetConfig = (BudgetConfig)findObject(BudgetConfig.class, BudgetConfig.BUDGET_CONFIG_FY, financialYear, BudgetConfig.ACT_IND, ActiveStatusEnum.YES.toString());
		return budgetConfig;
	}
}
