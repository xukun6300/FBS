package sg.com.fbs.services.mastercode.exception;

import sg.com.fbs.core.techinfra.exception.ApplicationCoreException;

/**
 * @Author Frank Xu $
 * @Created 12:03:08 pm 4 Aug, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public class MasterCodeException extends ApplicationCoreException{

	private static final long serialVersionUID = -3597150481007699295L;

	public MasterCodeException(String errorCode) {
		super(errorCode);
	}

	public MasterCodeException(String errorCode, Throwable cause) {
		super(errorCode, cause);
	}
}
