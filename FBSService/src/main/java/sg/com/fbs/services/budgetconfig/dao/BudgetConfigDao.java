package sg.com.fbs.services.budgetconfig.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.joda.time.DateTime;

import sg.com.fbs.core.techinfra.persistence.exception.DataAccessObjectException;
import sg.com.fbs.model.budgetconfig.BudgetConfig;
import sg.com.fbs.model.domain.enumeration.ActiveStatusEnum;
import sg.com.fbs.model.system.persistence.query.Criteria;
import sg.com.fbs.model.system.persistence.query.CriteriaIF;
import sg.com.fbs.model.system.persistence.query.Criterion;
import sg.com.fbs.model.system.persistence.query.CriterionIF;
import sg.com.fbs.model.system.persistence.query.RestrictionType;
import sg.com.fbs.model.system.persistence.response.IResponseCRUD;
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
	
	@SuppressWarnings("unchecked")
	public BudgetConfig getBudgetConfigForDate(DateTime date) throws DataAccessObjectException{
		CriteriaIF criteria = new Criteria();
		List<CriterionIF> criterions = new ArrayList<CriterionIF>();
		criterions.add(new Criterion(BudgetConfig.BUDGETING_START_DATE, RestrictionType.LESS_OR_EQUAL, date));
		criterions.add(new Criterion(BudgetConfig.BUDGETING_END_DATE, RestrictionType.GREATER_OR_EQUAL, date));
		criterions.add(new Criterion(BudgetConfig.ACT_IND, RestrictionType.EQUAL, ActiveStatusEnum.YES.toString()));
		criteria.setCriterion(criterions.toArray(new CriterionIF[criterions.size()]));
		criteria.setFetchAll(true);
		
		IResponseCRUD<BudgetConfig> response = search(BudgetConfig.class, criteria);
		if(response!=null){
			Collection<BudgetConfig> budgetConfigs = response.getQueryResult();
			if(budgetConfigs!=null && budgetConfigs.size()>0){
				return budgetConfigs.iterator().next();
			}else{
				return null;
			}
		}		
		
		return null;
	}
	
	
	
}



















