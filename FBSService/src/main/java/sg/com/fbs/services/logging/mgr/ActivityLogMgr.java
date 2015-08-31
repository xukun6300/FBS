package sg.com.fbs.services.logging.mgr;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import sg.com.fbs.core.businfra.facade.CommonFacade;
import sg.com.fbs.core.techinfra.persistence.exception.DataAccessObjectException;
import sg.com.fbs.model.system.activity.ActivityLog;
import sg.com.fbs.model.system.persistence.query.CriteriaIF;
import sg.com.fbs.model.system.persistence.response.IResponseCRUD;
import sg.com.fbs.model.system.persistence.response.ResponseCRUD;
import sg.com.fbs.model.system.security.User;
import sg.com.fbs.services.logging.dao.ActivityLogDAO;
import sg.com.fbs.services.system.security.uam.exception.UserAccountManagementException;
import sg.com.fbs.services.system.security.uam.mgr.UserAccountManagerBD;

/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu $
 * @Created 3:40:41 pm 31 Aug, 2015 $
 * 
 */
public class ActivityLogMgr extends CommonFacade{


	@SuppressWarnings("rawtypes")
	public IResponseCRUD searchActivityLogs(CriteriaIF criteria) throws DataAccessObjectException{
		addActiveStatusCriterion(criteria);
		ActivityLogDAO activityLogDAO = new ActivityLogDAO();
		IResponseCRUD response = activityLogDAO.searchActivityLog(criteria);
		return response;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public IResponseCRUD searchActivityLogsForDisplay(CriteriaIF criteria) throws DataAccessObjectException, UserAccountManagementException{
		IResponseCRUD response = searchActivityLogs(criteria);
		if (response!=null) {
			Collection<ActivityLog> newQueryResult = new LinkedList<ActivityLog>();
			Iterator iterator = response.getQueryResult().iterator();
			while(iterator.hasNext()){
				ActivityLog activityLog = (ActivityLog) iterator.next();
				transformActivityLog(activityLog);
				newQueryResult.add(activityLog);
			}
			response.setQueryResult(newQueryResult);
		}
		return response;
	}
	
	@SuppressWarnings("rawtypes")
	public IResponseCRUD saveActivityLog(ActivityLog activityLog) throws DataAccessObjectException{
		IResponseCRUD response = new ResponseCRUD();
		ActivityLogDAO activityLogDAO = new ActivityLogDAO();
		activityLog = (ActivityLog) activityLogDAO.insert(activityLog);
		
		if(activityLog.getId()>0){
			response.setCrudFlag(true);
			response.setCrudResult(activityLog);
		}
		return response;
	}
	
	public void updateUser(User user) throws DataAccessObjectException{
		ActivityLogDAO activityLogDAO = new ActivityLogDAO();
		user = (User) activityLogDAO.update(user);
	}
	
	private void transformActivityLog(ActivityLog activityLog) throws UserAccountManagementException{
		UserAccountManagerBD userAccountManagerBD = new UserAccountManagerBD();
		User createdByUser = userAccountManagerBD.getUser(User.ID, activityLog.getCreatedby());
		activityLog.setCreatedbyUser(createdByUser == null ? new User() : createdByUser);
	}
	
}
