package sg.com.fbs.services.security.external.crypto.provider;

import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.hibernate.mapping.Array;

import sg.com.fbs.services.security.password.CryptoProvider;
import sg.com.fbs.services.security.password.CryptoUtil;


/**
 * @Author Frank Xu $
 * @Created 6:44:23 pm 6 Aug, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public class CryptoOperationsManager implements CryptoOperationsIF{

	private static final int HEX_RADIX = 16;
	
	@Resource
	private CryptoProvider cryptoProvider;

	@Override
	public String getTransportKeyModulus() {
		byte[] pubKeyBytes = cryptoProvider.getDataTransportationPublicKey();
		PublicKey publicKey = null;
		publicKey = CryptoUtil.generatePublicKey(pubKeyBytes);
		return ((RSAPublicKey)publicKey).getModulus().toString(HEX_RADIX);
	}

	@Override
	public String getTransportKeyExponent() {
		byte[] pubKeyBytes = cryptoProvider.getDataTransportationPublicKey();
		PublicKey publicKey = null;
		publicKey = CryptoUtil.generatePublicKey(pubKeyBytes);
		return ((RSAPublicKey)publicKey).getPublicExponent().toString(HEX_RADIX);
	}

	
	@Override
	public String decryptHashedPassword(String cipherText) {
        byte[] password = null;

        try {
			password = Hex.decodeHex(cipherText.toCharArray());
		} catch (DecoderException e) {
			throw new IllegalArgumentException(e.getMessage(), e);
		}
        
        byte[] decryptedPassword = cryptoProvider.decryptHashedPassword(password);      
        return Hex.encodeHexString(cryptoProvider.encryptCredentials(decryptedPassword));
	}

	@Override
	public String getSalt() {
		return Hex.encodeHexString(cryptoProvider.getSalt());
	}

	@Override
	public String encryptSalt(String dataHex) {
		return encryptUserData(dataHex);
	}

	@Override
	public String decryptSalt(String saltHex) {
		return decryptUserData(saltHex);
	}

	@Override
	public String encryptUserData(String dataHex) {
		byte[] data = null;
		try {
			data = Hex.decodeHex(dataHex.toCharArray());
		} catch (DecoderException e) {
			throw new IllegalArgumentException(e.getMessage(), e);
		}
		return Hex.encodeHexString(cryptoProvider.encryptUserData(data));
	}

	@Override
	public String decryptUserData(String dataHex) {
		byte[] data = null;
		
		try {
			data = Hex.decodeHex(dataHex.toCharArray());
		} catch (DecoderException e) {
			throw new IllegalArgumentException(e.getMessage(), e);
		}
		
		return Hex.encodeHexString(cryptoProvider.decryptUserData(data));	
	}

	@Override
	public SecurityAnswerComparisonTokenList decryptHashedSecurityAnswers(SecurityAnswerComparisonTokenList list) {

		byte[] password = null;
		byte[] decryptedSecurityAnswer = null;

		List<SecurityAnswerComparisonToken> securityAnswers = new ArrayList<SecurityAnswerComparisonToken>();
		List<SecurityAnswerComparisonToken> tokens = list.getTokens();
		
		for (SecurityAnswerComparisonToken token : tokens) {
			try {
				password = Hex.decodeHex(token.getAnswer());
			} catch (DecoderException e) {
				throw new IllegalArgumentException(e.getMessage(), e);
			}
			decryptedSecurityAnswer = cryptoProvider.decryptHashedPassword(password);
			SecurityAnswerComparisonToken securityAnswer = new SecurityAnswerComparisonToken(Hex.encodeHex(cryptoProvider.encryptUserData(decryptedSecurityAnswer)), null);
			securityAnswers.add(securityAnswer);
		}

		return new SecurityAnswerComparisonTokenList(securityAnswers);
	}

	@Override
	public String getNonce() {
		return Hex.encodeHexString(cryptoProvider.getNonce());
	}

	@Override
	public boolean comparePassword(PasswordComparisonToken token) {
		//decrypt client side password hash
		byte[] authPasswordHashBytes = null;
		
		try {
			authPasswordHashBytes = Hex.decodeHex(token.getAuthPassword());
		} catch (DecoderException e) {
			throw new IllegalArgumentException(e.getMessage(), e);
		}
		
		byte[] clientHmacBytes = cryptoProvider.decryptHashedPassword(authPasswordHashBytes);
		
		//decrypt server side password hash
		byte[] serverHashPasswordBytes = null;
		
		try {
			serverHashPasswordBytes = Hex.decodeHex(token.getPassword());
		} catch (DecoderException e) {
			throw new IllegalArgumentException(e.getMessage(), e);
		}
		
		byte[] serverPasswordHash = cryptoProvider.decryptCredentials(serverHashPasswordBytes);
		
		// compute hmac using password hash and nonce
		
		byte[] serverHmacBytes = null;
		
		try {
			serverHmacBytes = CryptoUtil.computeHmac(serverPasswordHash, Hex.decodeHex(token.getNonce()));
		} catch (DecoderException e) {
			throw new IllegalArgumentException(e.getMessage(), e);
		}
		
		boolean match = Arrays.equals(clientHmacBytes, serverHmacBytes);
	
		return match;
	}
	
	
	

}
























