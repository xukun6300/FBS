package sg.com.fbs.services.security.external.crypto.provider;

import java.util.ArrayList;
import java.util.List;

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

	@Override
	public List<String> decryptSecurityAnswers(List<String> securityAnswers) {

		if(securityAnswers.isEmpty()){
			throw new IllegalArgumentException("security answer is empty.");
		}

		List<SecurityAnswerComparisonToken> tokens = new ArrayList<SecurityAnswerComparisonToken>();
		
		for (String securityAnswer : securityAnswers) {
			tokens.add(new SecurityAnswerComparisonToken(securityAnswer.toCharArray(), null));
		}
		
		SecurityAnswerComparisonTokenList list = new SecurityAnswerComparisonTokenList(tokens);		
		SecurityAnswerComparisonTokenList encryptSecurityAnswer = cryptoOperations.decryptHashedSecurityAnswers(list);	
		List<String> encryptAnswers = new ArrayList<String>();
		
		for (SecurityAnswerComparisonToken securityAnswerComparisonToken : encryptSecurityAnswer.getTokens()) {
			encryptAnswers.add(new String(securityAnswerComparisonToken.getAnswer()));
		}
		
		return encryptAnswers;
	}

	@Override
	public String getNonce() {
		return cryptoOperations.getNonce();
	}

	@Override
	public boolean comparePassword(char[] serverHashedPassword, char[] userHashedPassword, char[] nonce) {
		PasswordComparisonToken token = new PasswordComparisonToken(serverHashedPassword, userHashedPassword, nonce);
		return cryptoOperations.comparePassword(token);
	}

	

}

























