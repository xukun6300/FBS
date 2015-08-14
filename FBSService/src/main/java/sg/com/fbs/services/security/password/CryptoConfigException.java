package sg.com.fbs.services.security.password;

/**
 * @Author Frank Xu $
 * @Created 5:03:14 pm 6 Aug, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public class CryptoConfigException extends CryptoException{

	private static final long serialVersionUID = -1277405158210165436L;

	public CryptoConfigException(String msg) {
		super(msg);		
	}

	public CryptoConfigException(String msg, Throwable t) {
		super(msg, t);		
	}
}
