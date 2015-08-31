package sg.com.fbs.services.common.activity.mgr;

import java.util.List;

import sg.com.fbs.core.businfra.businessdelegate.BusinessDelegate;
import sg.com.fbs.core.techinfra.exception.ApplicationCoreException;
import sg.com.fbs.model.system.activity.Activity;
import sg.com.fbs.model.system.persistence.query.CriteriaIF;
import sg.com.fbs.model.system.persistence.response.ResponseCRUD;

/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu $
 * @Created 9:32:03 am 31 Aug, 2015 $
 * 
 */
public class ActivityMgrBD extends BusinessDelegate{

	@SuppressWarnings("rawtypes")
	public ResponseCRUD searchActivities(CriteriaIF criteria) throws ApplicationCoreException{
		Object obj = new ActivityManager().run(criteria);
		return (ResponseCRUD) obj;
	}
	
    @SuppressWarnings("rawtypes")
	public ResponseCRUD searchActivity(CriteriaIF criteria) throws ApplicationCoreException{
    	Object obj = new ActivityManager().run(criteria);
		return (ResponseCRUD) obj;
	}
	
    @SuppressWarnings("unchecked")
	public List<Activity> getAllActivities() throws ApplicationCoreException{
    	return (List<Activity>) new ActivityManager().run();
    }
    
}
