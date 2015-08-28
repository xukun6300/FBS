package sg.com.fbs.security.web.auth.fbsid;

import java.util.Arrays;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/**
 * @Author Frank Xu $
 * @Created 2:38:46 pm 24 Aug, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public class FbsIdAuthenticationToken extends UsernamePasswordAuthenticationToken{


	private static final long serialVersionUID = -7833267793451585116L;

	//private String nonceHex;
	
	private String passwordHash;
	
	
	public FbsIdAuthenticationToken(Object principal, Object credentials) {
		super(principal, credentials);
	}

	public FbsIdAuthenticationToken(Object principal, Object credentials, String passwordHash) {
		super(principal, credentials);
		//this.nonceHex = nonceHex;
		this.passwordHash = passwordHash;
	}
	
	/*public String getNonceHex() {
		return nonceHex;
	}
	
	public void setNonceHex(String nonceHex) {
		this.nonceHex = nonceHex;
	}*/
	
	public String getPasswordHash() {
		return passwordHash;
	}
	
	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}
}
