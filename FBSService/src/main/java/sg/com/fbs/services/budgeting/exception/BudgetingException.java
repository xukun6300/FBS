package sg.com.fbs.services.budgeting.exception;

import sg.com.fbs.core.techinfra.exception.ApplicationCoreException;

/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu Kun $
 * @Created Mar 3, 2017 2:56:54 PM $
 * 
 */
public class BudgetingException extends ApplicationCoreException{

	private static final long serialVersionUID = 1L;

	public BudgetingException(String errorCode) {
		super(errorCode);
	}

	public BudgetingException(String errorCode, Throwable cause){
		super(errorCode, cause);
	}
}
