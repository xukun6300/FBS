package sg.com.fbs.services.security.external.crypto.provider;

import javax.annotation.Resource;

/**
 * @Author Frank Xu $
 * @Created 9:46:18 am 11 Aug, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public class CryptoServicesClientManager implements CryptoServicesClientIF {

	@Resource
	private CryptoOperationsIF cryptoOperations;
	
	@Override
	public String getTransportRSAKeyModulus() {
		return cryptoOperations.getTransportKeyModulus();
	}

	@Override
	public String getTransportRSAKeyExponent() {
		return cryptoOperations.getTransportKeyExponent();
	}

	@Override
	public String decryptPassword(String password) {
		return cryptoOperations.decryptHashedPassword(password);
	}

	@Override
	public String getSalt() {
		return cryptoOperations.getSalt();
	}

	@Override
	public String encryptSalt(String salt) {
		return cryptoOperations.encryptSalt(salt);
	}

	@Override
	public String decryptSalt(String salt) {
		return cryptoOperations.decryptSalt(salt);
	}


}

























