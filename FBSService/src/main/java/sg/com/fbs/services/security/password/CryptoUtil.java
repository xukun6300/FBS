package sg.com.fbs.services.security.password;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

import org.apache.log4j.Logger;

/**
 * @Author Frank Xu $
 * @Created 12:27:34 pm 11 Aug, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public class CryptoUtil {

	private static  Logger logger = Logger.getLogger(CryptoUtil.class);
	
	public static final String ALG_RSA = "RSA";
	
	public static PublicKey generatePublicKey(byte[] bytes){
		
		KeyFactory keyFactory = null;	
		try {
			keyFactory = KeyFactory.getInstance(ALG_RSA);
		} catch (NoSuchAlgorithmException e) {
			logger.error("No such algorithm: " + ALG_RSA, e);
			throw new CryptoConfigException(e.getMessage(), e);
		}
		
		PublicKey publicKey;
		
		try {
			publicKey = keyFactory.generatePublic(new X509EncodedKeySpec(bytes));
			return publicKey;
		} catch (InvalidKeySpecException e) {
			logger.error("No such algorithm: " + ALG_RSA, e);
			throw new CryptoConfigException(e.getMessage(), e);
		}

	}
}





























