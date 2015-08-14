package sg.com.fbs.services.security.password;

import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.cert.X509Certificate;
import java.util.Properties;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

/**
 * @Author Frank Xu $
 * @Created 4:17:30 pm 6 Aug, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public abstract class AbstractCryptoProvider implements CryptoProvider{
	
	// key store settings
	public static final String KEYSTORE_PASSWORD = "keystore.password";
	public static final String KEYSTORE_TYPE = "keystore.storetype";
	
	// transport password encryption settings
	public static final String USER_PASSWORD_TRANSPORT_ENCRYPTION_ALG = "user.password.transport.encryption.alg";
	public static final String USER_PASSWORD_TRANSPORT_ENCRYPTION_ALIAS= "user.password.transport.encryption.alias";
	
	// IV settings
	public static final String IV ="iv";
	
	private KeyStore ks;
	
	private String keyStoreType;
	
	private String userPasswordTransportEncryptionAlg;
	
	private String userPasswordTransportEncryptionAlias;
	
	private IvParameterSpec iv; 
	
	
	public AbstractCryptoProvider(Properties properties){
		userPasswordTransportEncryptionAlg = properties.getProperty(USER_PASSWORD_TRANSPORT_ENCRYPTION_ALG);
		userPasswordTransportEncryptionAlias = properties.getProperty(USER_PASSWORD_TRANSPORT_ENCRYPTION_ALIAS);
		keyStoreType = properties.getProperty(KEYSTORE_TYPE);
		
		try {
			iv = new IvParameterSpec(Hex.decodeHex(properties.getProperty(IV).toCharArray()));
		} catch (DecoderException e) {
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}
	
	protected void setKeyStore(KeyStore ks) {
		this.ks = ks;
	}
	
	protected KeyStore getKeyStore() {
		if(ks==null){
			throw new IllegalStateException("Key store not initialized or loaded");
		}		
		return this.ks;
	}
	
	protected String getKeyStoreType() {
		return keyStoreType;
	}
	
	protected X509Certificate getCertificate(String alias) {
		X509Certificate certificate = null;
		try {
			certificate = (X509Certificate) getKeyStore().getCertificate("rsa");
		} catch (KeyStoreException e) {
			throw new CryptoConfigException(e.getMessage(), e);
		}
		return certificate;
	}
	
	@Override
	public byte[] encryptCredentials(byte[] clearText) {
		return encryptAesCbc(clearText, userPasswordTransportEncryptionAlias, userPasswordTransportEncryptionAlg);
	}
	
	@Override
	public byte[] getDataTransportationPublicKey() {
		X509Certificate certificate = getCertificate(userPasswordTransportEncryptionAlias);
		return certificate.getPublicKey().getEncoded();
	}
	
	@Override
	public byte[] decryptHashedPassword(byte[] encryptedHash) {
		Key key = getKey(userPasswordTransportEncryptionAlias);
		
		Cipher cipher = null;
		
		try {

			cipher = Cipher.getInstance(userPasswordTransportEncryptionAlg, getJCEProvider());
		} catch (Exception e) {
			throw new CryptoConfigException(e.getMessage(), e);
		} 
		
		try {
			cipher.init(Cipher.DECRYPT_MODE, key);
			byte[] plainText = cipher.doFinal(encryptedHash);
			plainText = Hex.decodeHex(new String(plainText).toCharArray());
			return plainText;
		} catch (Exception e) {
			throw new CryptoConfigException(e.getMessage(), e);
		}
		
	}
	
	protected Key getKey(String alias) {
		if(alias == null){
			return null;
		}
		
		KeyStore keyStore = getKeyStore();
		char[] keyStorePwd = getKeyStorePassword();
		
		Key key = null;
		try {
			if (keyStore.containsAlias(alias) && keyStore.isKeyEntry(alias)) {
				key = keyStore.getKey(alias, keyStorePwd);
			}
		} catch (Exception e) {
			throw new CryptoConfigException(e.getMessage(), e);
		}
		
		if(key==null){
			throw new IllegalStateException("Not able to retrieve key from keystore.");
		}

		return key;
	}
	
	private byte[] encryptAesCbc(byte[] clearText, String alias, String alg){
		Key key = getKey(alias);
		
		Cipher cipher = null;
		
		try {
			
			cipher = Cipher.getInstance(alg, getJCEProvider());
		} catch (Exception e) {
			throw new CryptoConfigException(e.getMessage(), e);
		}
		
		try {
			cipher.init(Cipher.ENCRYPT_MODE, key, iv);
			byte[] cipherText = cipher.doFinal(clearText);
			return cipherText;
		} catch (Exception e) {
			throw new CryptoConfigException(e.getMessage(), e);
		} 
		
	}

	
	protected abstract char[] getKeyStorePassword();
	
	protected abstract String getJCEProvider();
	
}
























