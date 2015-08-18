package sg.com.fbs.services.security.password;

/**
 * @Author Frank Xu $
 * @Created 11:05:12 am 13 Aug, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public interface CryptoProvider {

	byte[] getSalt();
 	
	byte[] encryptCredentials(byte[] hash);
	
	byte[] getDataTransportationPublicKey();
	
	byte[] decryptHashedPassword(byte[] encryptedHash);
	
	byte[] encryptUserData(byte[] clearText);
	
	byte[] decryptUserData(byte[] cipherText);
}
