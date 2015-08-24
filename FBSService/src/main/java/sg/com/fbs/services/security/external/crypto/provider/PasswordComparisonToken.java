package sg.com.fbs.services.security.external.crypto.provider;

import java.util.Arrays;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Author Frank Xu $
 * @Created 12:24:51 pm 24 Aug, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
@XmlRootElement(name="token")
public class PasswordComparisonToken {

	private char[] password;
	private char[] authPassword;
	private char[] nonce;
	
	public PasswordComparisonToken(){
		
	}
	
	public PasswordComparisonToken(char[] password, char[] authPassword, char[] nonce) {

		if(password!=null){
			this.password = Arrays.copyOf(password, password.length);
		}
		if(authPassword!=null){
			this.authPassword = Arrays.copyOf(authPassword, authPassword.length);
		}
		if(nonce!=null){
			this.nonce = Arrays.copyOf(nonce, nonce.length);
		}
	}
	
	public char[] getPassword() {
		return password;
	}
	
	public void setPassword(char[] password) {
		if(password!=null){
			this.password = Arrays.copyOf(password, password.length);
		}
	}
	
	public char[] getAuthPassword() {
		return authPassword;
	}
	
	public void setAuthPassword(char[] authPassword) {
		if(authPassword!=null){
			this.authPassword = Arrays.copyOf(authPassword, authPassword.length);
		}
	}
	
	public char[] getNonce() {
		return nonce;
	}
	
	public void setNonce(char[] nonce) {
		if(nonce!=null){
			this.nonce = Arrays.copyOf(nonce, nonce.length);
		}
	}
	
}
