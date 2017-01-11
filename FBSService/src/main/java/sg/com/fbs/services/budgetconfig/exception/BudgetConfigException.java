package sg.com.fbs.services.budgetconfig.exception;

import sg.com.fbs.core.techinfra.exception.ApplicationCoreException;

/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu Kun $
 * @Created Nov 17, 2016 5:02:26 PM $
 * 
 */
public class BudgetConfigException extends ApplicationCoreException{

	private static final long serialVersionUID = 1L;

	public BudgetConfigException(String errorCode) {
		super(errorCode);
	}

	public BudgetConfigException(String errorCode, Throwable cause){
		super(errorCode, cause);
	}
}
