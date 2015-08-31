package sg.com.fbs.services.logging.mgr;

import sg.com.fbs.core.businfra.businessdelegate.BusinessDelegate;
import sg.com.fbs.core.techinfra.exception.ApplicationCoreException;
import sg.com.fbs.model.system.activity.ActivityLog;
import sg.com.fbs.model.system.persistence.query.CriteriaIF;
import sg.com.fbs.model.system.persistence.response.IResponseCRUD;
import sg.com.fbs.model.system.security.User;

/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu $
 * @Created 4:23:08 pm 31 Aug, 2015 $
 * 
 */
public class ActivityLogMgrBD extends BusinessDelegate{

	@SuppressWarnings("rawtypes")
	public IResponseCRUD searchActivityLogs(CriteriaIF criteria) throws ApplicationCoreException{
		Object obj = new ActivityLogMgr().run(criteria);
		return (IResponseCRUD) obj;
	}
	
	@SuppressWarnings("rawtypes")
	public IResponseCRUD searchActivityLogsForDisplay(CriteriaIF criteria) throws ApplicationCoreException{
		Object obj = new ActivityLogMgr().run(criteria);
		return (IResponseCRUD) obj;
	}
	
	@SuppressWarnings("rawtypes")
	public IResponseCRUD saveActivityLog(ActivityLog activityLog) throws ApplicationCoreException{
		Object obj = new ActivityLogMgr().run(activityLog);
		return (IResponseCRUD) obj;
	}
	
	public void updateUser(User user) throws ApplicationCoreException{
		new ActivityLogMgr().run(user);
	}
}
