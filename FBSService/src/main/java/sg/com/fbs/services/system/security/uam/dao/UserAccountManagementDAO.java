package sg.com.fbs.services.system.security.uam.dao;

import sg.com.fbs.core.techinfra.persistence.exception.DataAccessObjectException;
import sg.com.fbs.model.system.persistence.query.CriteriaIF;
import sg.com.fbs.model.system.persistence.response.IResponseCRUD;
import sg.com.fbs.model.system.security.SecurityQuestions;
import sg.com.fbs.techinfra.persistence.dao.BaseDao;

/**
 * @Author Frank Xu $
 * @Created 9:45:06 am 30 Jul, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public class UserAccountManagementDAO extends BaseDao{

	
	public IResponseCRUD searchSecurityQuestion(CriteriaIF criteria) throws UserAccountManagementDaoException{
		try {
			IResponseCRUD response = search(SecurityQuestions.class, criteria);
			return response;
		} catch (DataAccessObjectException e) {
			throw new UserAccountManagementDaoException(e.getMessageCode(), e);
		}
	}
}
