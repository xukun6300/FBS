package sg.com.fbs.services.logging.dao;

import sg.com.fbs.core.techinfra.persistence.exception.DataAccessObjectException;
import sg.com.fbs.model.system.activity.ActivityLog;
import sg.com.fbs.model.system.persistence.query.CriteriaIF;
import sg.com.fbs.model.system.persistence.response.IResponseCRUD;
import sg.com.fbs.techinfra.persistence.dao.BaseDao;

/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu $
 * @Created 3:27:57 pm 31 Aug, 2015 $
 * 
 */
public class ActivityLogDAO extends BaseDao{

	@SuppressWarnings("rawtypes")
	public IResponseCRUD searchActivityLog(CriteriaIF criteria) throws DataAccessObjectException{
		IResponseCRUD response = search(ActivityLog.class, criteria);
		return response;
	}
	
	
	
}
