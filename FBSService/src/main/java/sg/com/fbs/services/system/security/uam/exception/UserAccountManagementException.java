package sg.com.fbs.services.system.security.uam.exception;

import sg.com.fbs.core.techinfra.exception.ApplicationCoreException;

/**
 * @Author Frank Xu $
 * @Created 10:36:12 am 14 Aug, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public class UserAccountManagementException extends ApplicationCoreException{

	private static final long serialVersionUID = 306183930280593309L;

	public UserAccountManagementException(String _errorCode){
		super(_errorCode);
	}
	
	public UserAccountManagementException(String _errorCode, Throwable cause){
		super(_errorCode, cause);
	}
	
}
