package sg.com.fbs.services.budgetconfig.mgr;

import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.joda.time.DateTime;

import sg.com.fbs.core.businfra.facade.CommonFacade;
import sg.com.fbs.core.techinfra.persistence.exception.DataAccessObjectException;
import sg.com.fbs.core.techinfra.util.DateUtil;
import sg.com.fbs.model.budgetconfig.BudgetConfig;
import sg.com.fbs.model.budgetconfig.BudgetConfigRequest;
import sg.com.fbs.model.domain.enumeration.ActiveStatusEnum;
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

	private BudgetConfigDao budgetConfigDao = new BudgetConfigDao();
	
	
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
	
	public Integer getBudgetForFY() throws BudgetConfigException{
		Calendar calendar = Calendar.getInstance();
		Integer currentYear = calendar.get(Calendar.YEAR);
		try {
			BudgetConfig currentYearBgtCfg = budgetConfigDao.getBudgetConfigByFy(currentYear);
	        if(currentYearBgtCfg!=null){
	        	return ++currentYear;
	        }else {
				return currentYear;
			}
		} catch (DataAccessObjectException e) {
			e.printStackTrace();
			throw new BudgetConfigException(e.getMessageCode(), e);
		}
	}
	
	public Map<String, String> getBudgetForFYs() throws BudgetConfigException{
		Map<String, String> resultMap = new LinkedHashMap<String, String>();
		Integer currentFy = DateUtil.getCurrentYear();	
		try {
			BudgetConfig currentYearBgtCfg = budgetConfigDao.getBudgetConfigByFy(currentFy);
		    if(currentYearBgtCfg!=null){
		    	DateTime budgetStartDt = currentYearBgtCfg.getBudgetingStartDt();
		    	//If budget stage already started, cannot change budget period anymore.
		    	if(budgetStartDt.isAfterNow()){ 
		    		resultMap.put(String.valueOf(currentFy), String.valueOf(currentFy)+" (Current FY)");
		    	}
		    }else{
		    	resultMap.put(String.valueOf(currentFy), String.valueOf(currentFy)+" (Current FY)");
		    }
		} catch (DataAccessObjectException e) {
			e.printStackTrace();
			throw new BudgetConfigException(e.getMessageCode(), e);
		}
		Integer nextFy = ++currentFy;
		resultMap.put(String.valueOf(nextFy), String.valueOf(nextFy)+" (Next FY)");				
		return resultMap;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public IResponseCRUD saveNewBudgeting(BudgetConfigRequest budgetConfigRequest, CriteriaIF criteria) throws BudgetConfigException{
		try {
			IResponseCRUD response = null;
			Map<String, Object> moreQueryResult = new HashMap<String, Object>();
			if(budgetConfigRequest!=null){
				BudgetConfig budgetConfig = budgetConfigDao.getBudgetConfigByFy(budgetConfigRequest.getBudgetForFY());
				if(budgetConfig==null){
					budgetConfig = new BudgetConfig();
					budgetConfig.setBudgetConfigFY(budgetConfigRequest.getBudgetForFY());
					budgetConfig.setBudgetingStartDt(budgetConfigRequest.getBudgetStartDate());
					budgetConfig.setBudgetingEndDt(budgetConfigRequest.getBudgetCutOffDate());
					budgetConfigDao.insert(budgetConfig);
				}else {
					budgetConfig.setBudgetConfigFY(budgetConfigRequest.getBudgetForFY());
					budgetConfig.setBudgetingStartDt(budgetConfigRequest.getBudgetStartDate());
					budgetConfig.setBudgetingEndDt(budgetConfigRequest.getBudgetCutOffDate());
					budgetConfigDao.update(budgetConfig);
				}
				budgetConfigDao.getSession().flush();
				
				//after save new budget config, need to show the list again
				response = budgetConfigDao.search(BudgetConfig.class, criteria);
				
				moreQueryResult.put("successMsg", true);
				moreQueryResult.put("financialYear", budgetConfigRequest!=null ? budgetConfigRequest.getBudgetForFY() : "");
				
			}else{
				moreQueryResult.put("errorMsg", true);
			}
		    
			response.setMoreQueryResult(moreQueryResult);
			return response;
		} catch (DataAccessObjectException e) {
			e.printStackTrace();
			throw new BudgetConfigException(e.getMessageCode(), e);
		}
	}
	
	
}






