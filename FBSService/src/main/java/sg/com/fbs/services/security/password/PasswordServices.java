package sg.com.fbs.services.security.password;

import java.util.List;

import sg.com.fbs.model.system.security.User;
import sg.com.fbs.model.system.security.UserCredentials;

/**
 * @Author Frank Xu $
 * @Created 11:52:04 am 11 Aug, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public interface PasswordServices {

	/**
	 * Retrieve transport RSA key modulus from HSM via RESTful call for client-side data encryption 
	 * @return HEX encoded RSA key modulus
	 */
	String getTransportRSAKeyModulus();
	
	/**
	 * Retrieve transport RSA key exponent from HSM via RESTful call for client-side data encryption 
	 * @return HEX encoded RSA key exponent
	 */
	String getTransportRSAKeyExponent();
	
	//String encodePasswordWithBCrypt(String rawPassword);
	
	/**
	 * Decrypt HEX encoded encrypted hashed password and re-encrypt using symmetric key with cryptographic services
	 * via RESTful call.
	 * 
	 * @param password HEX encoded hashed password string
	 * @return HEX encoded encrypted hashed password string
	 */
	String decryptPassword(String password);
	
	/**
	 * Retrieve random generated salt from HSM via RESTful call
	 * @return HEX encoded random generated salt
	 */
	String getSalt();
	
	/**
	 * Retrieve random generated nonce from HSM via RESTful call
	 * @return HEX encoded random generated nonce
	 */
	String getNonce();
	
	/**
	 * Decrypt HEX encoded encrypted hashed password and re-encrypt using symmetric key with cryptographic services
	 * via RESTful call.
	 * 
	 * @param securityAnswers HEX encoded encrypted hashed security answer list
	 * @return HEX encoded encrypted hashed security answer list in same order as securityAnswers argument
	 */
	List<String> decryptSecurityAnswers(List<String> securityAnswers);
	
	/**
	 * Load credentials from repository
	 * @param user
	 * @return credentials of a user
	 */
	UserCredentials getUserCredentials(User user);
	
	/**
	 * 
	 * @param serverHashedPassword
	 * @param userHashedPassword
	 * @param nonce
	 * @return
	 */
	boolean comparePassword(char[] serverHashedPassword, char[] userHashedPassword, char[] nonce);
}
