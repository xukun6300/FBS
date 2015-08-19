package sg.com.fbs.services.system.security.uam.dao;

import sg.com.fbs.core.techinfra.persistence.exception.DataAccessObjectException;

/**
 * @Author Frank Xu $
 * @Created 3:49:28 pm 19 Aug, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public class UserAccountManagementDaoException extends DataAccessObjectException{

	private static final long serialVersionUID = -5220587073185008761L;

	public UserAccountManagementDaoException(String messageCode) {
		super(messageCode);
	}

	public UserAccountManagementDaoException(String messageCode, Throwable cause) {
		super(messageCode, cause);
	}
}
