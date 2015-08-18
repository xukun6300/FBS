package sg.com.fbs.services.security.external.crypto.provider;

/**
 * @Author Frank Xu $
 * @Created 6:43:40 pm 6 Aug, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public interface CryptoOperationsIF {
	
	String getTransportKeyModulus();
	
	String getTransportKeyExponent();
	
	String decryptHashedPassword(String cipherText);
	
	String getSalt();
}
