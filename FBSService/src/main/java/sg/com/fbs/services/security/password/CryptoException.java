package sg.com.fbs.services.security.password;

/**
 * @Author Frank Xu $
 * @Created 5:01:47 pm 6 Aug, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public abstract class CryptoException extends RuntimeException {

	private static final long serialVersionUID = 4199969095302363447L;

	public CryptoException(String msg){
		super(msg);
	}
	
    public CryptoException(String msg, Throwable t){
		super(msg, t);
	}
}
