package sg.com.fbs.services.security.external.crypto.provider;

import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import javax.annotation.Resource;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
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
	
	
	

}
























