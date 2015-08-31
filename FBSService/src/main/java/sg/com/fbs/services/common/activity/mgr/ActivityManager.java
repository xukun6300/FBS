package sg.com.fbs.services.common.activity.mgr;

import java.util.List;

import sg.com.fbs.core.businfra.facade.CommonFacade;
import sg.com.fbs.core.techinfra.persistence.exception.DataAccessObjectException;
import sg.com.fbs.model.system.activity.Activity;
import sg.com.fbs.model.system.persistence.query.CriteriaIF;
import sg.com.fbs.model.system.persistence.response.IResponseCRUD;
import sg.com.fbs.services.common.activity.dao.ActivityDAO;

/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu $
 * @Created 9:32:37 am 31 Aug, 2015 $
 * 
 */
public class ActivityManager extends CommonFacade{
	
	@SuppressWarnings("rawtypes")
	public IResponseCRUD searchActivities(CriteriaIF criteria) throws DataAccessObjectException{
		addActiveStatusCriterion(criteria);
		ActivityDAO activityDAO = new ActivityDAO();
		IResponseCRUD response = activityDAO.searchActivity(criteria);
		return response;
	}
	
	@SuppressWarnings("rawtypes")
	public IResponseCRUD searchActivity(CriteriaIF criteria) throws DataAccessObjectException{
		IResponseCRUD response = searchActivities(criteria);
		if(response.getQueryResult().size()==1){
			response.setCrudResult(response.getQueryResult().iterator().next());
		}
		
		return response;
	}
	
	@SuppressWarnings("unchecked")
	public List<Activity> getAllActivities() {
		return (List<Activity>) new ActivityDAO().findAll(Activity.class);
	}
	
	

}
