package sg.com.fbs.services.common.activity.dao;

import sg.com.fbs.core.techinfra.persistence.exception.DataAccessObjectException;
import sg.com.fbs.model.system.activity.Activity;
import sg.com.fbs.model.system.persistence.query.CriteriaIF;
import sg.com.fbs.model.system.persistence.response.IResponseCRUD;
import sg.com.fbs.techinfra.persistence.dao.BaseDao;

/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu $
 * @Created 9:33:37 am 31 Aug, 2015 $
 * 
 */
public class ActivityDAO extends BaseDao{

	public IResponseCRUD searchActivity(CriteriaIF criteria) throws DataAccessObjectException{
		IResponseCRUD response = search(Activity.class, criteria);
		return response;
	}
}
