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

	private String nonceHex;
	
	private char[] passwordHash;
	
	
	public FbsIdAuthenticationToken(Object principal, Object credentials) {
		super(principal, credentials);
	}

	public FbsIdAuthenticationToken(Object principal, Object credentials, char[] passwordHash, String nonceHex) {
		super(principal, credentials);
		this.nonceHex = nonceHex;
		if(passwordHash!=null){
			this.passwordHash = Arrays.copyOf(passwordHash, passwordHash.length);
		}
	}
	
	public String getNonceHex() {
		return nonceHex;
	}
	
	public void setNonceHex(String nonceHex) {
		this.nonceHex = nonceHex;
	}
	
	public char[] getPasswordHash() {
		return passwordHash;
	}
	
	public void setPasswordHash(char[] passwordHash) {
		if(passwordHash!=null){
			this.passwordHash = Arrays.copyOf(passwordHash, passwordHash.length);
		}
	}
	
}
