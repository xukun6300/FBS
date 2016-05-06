package sg.com.fbs.services.account.exception;

import sg.com.fbs.core.techinfra.exception.ApplicationCoreException;

/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu Kun $
 * @Created May 6, 2016 10:24:43 AM $
 * 
 */
public class AccountException extends ApplicationCoreException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AccountException(String errorCode) {
		super(errorCode);
	}
	
	public AccountException(String errorCode, Throwable cause){
		super(errorCode, cause);
	}
}
