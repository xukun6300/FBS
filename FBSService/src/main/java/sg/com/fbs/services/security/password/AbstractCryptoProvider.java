package sg.com.fbs.services.security.password;

import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPublicKey;
import java.util.Properties;

import javax.annotation.Generated;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.crypto.params.RSAKeyParameters;

/**
 * @Author Frank Xu $
 * @Created 4:17:30 pm 6 Aug, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public abstract class AbstractCryptoProvider implements CryptoProvider{
	
	
	private KeyStore ks;
	
	private String keyStoreType;
	
	private String userDataEncryptionAlg;
	
	private String userDataEncryptionAlias;
	
	private String userPasswordTransportEncryptionAlg;
	
	private String userPasswordTransportEncryptionAlias;
	
	private IvParameterSpec iv = null; 
	
	private int passwordSaltLength ;
	
	private int passwordNonceLength;
	
	public AbstractCryptoProvider(Properties properties){
		userDataEncryptionAlg = properties.getProperty("user.data.encryption.alg");
		userDataEncryptionAlias = properties.getProperty("user.data.encryption.alias");
		userPasswordTransportEncryptionAlg = properties.getProperty("user.password.transport.encryption.alg");
		userPasswordTransportEncryptionAlias = properties.getProperty("user.password.transport.encryption.alias");
		keyStoreType = properties.getProperty("keystore.storetype");
		passwordSaltLength = Integer.parseInt(properties.getProperty("password.salt.length"));
		passwordNonceLength = Integer.parseInt(properties.getProperty("password.nonce.length"));
		
		/*try {
			iv = new IvParameterSpec(Hex.decodeHex(properties.getProperty("iv").toCharArray()));
		} catch (DecoderException e) {
			throw new IllegalArgumentException(e.getMessage(), e);
		}*/
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
	
	private byte[] generateRandom(int length){
		SecureRandom secureRandom = new SecureRandom();
		byte[] randomBytes = new byte[length];
		secureRandom.nextBytes(randomBytes);
		return randomBytes;
	}
	
	@Override
	public byte[] encryptUserData(byte[] clearText) {
		return encryptAesCbc(clearText, userDataEncryptionAlias, userDataEncryptionAlg);
	}
	
	@Override
	public byte[] decryptUserData(byte[] cipherText) {
		return decryptAesCbc(cipherText, userDataEncryptionAlias, userDataEncryptionAlg); 
	}
	
	@Override
	public byte[] getSalt() {
		return generateRandom(passwordSaltLength);
	}
	
	@Override
	public byte[] getNonce() {
		return generateRandom(passwordNonceLength);
	}
	
	@Override
	public byte[] encryptCredentials(byte[] clearText) {
		return encryptAesCbc(clearText, userPasswordTransportEncryptionAlias, userPasswordTransportEncryptionAlg);
	}
	
	@Override
	public byte[] decryptCredentials(byte[] cipherText) {
		Key key = getKey(userPasswordTransportEncryptionAlias);
		Cipher cipher = null;
		
		//RSAKeyParameters rsaKeyParameters = new RSAKeyParameters(false, ((RSAPublicKey)key).getModulus(), ((RSAPublicKey)key).getPublicExponent());
		try {
			cipher = Cipher.getInstance(userPasswordTransportEncryptionAlg, getJCEProvider());
		} catch (Exception e) {
			throw new CryptoConfigException(e.getMessage(), e);
		} 
		
		try {
			iv=null;
			cipher.init(Cipher.DECRYPT_MODE, key,iv);
			System.out.println("---- in decryptCredentials--"+cipherText);
			byte[] plainText = cipher.doFinal(cipherText);
			//plainText = Hex.decodeHex(new String(plainText).toCharArray());  //??
			return plainText;
			
			//cipher.init(Cipher.DECRYPT_MODE, key);
			/*cipher.init(Cipher.DECRYPT_MODE, key, iv);
			byte[] clearText = cipher.doFinal(cipherText);
			return clearText;*/ 
		} catch (Exception e) {
			
			throw new CryptoConfigException(e.getMessage(), e);
		}
		
		//return decryptAesCbc(cipherText, userPasswordTransportEncryptionAlias, userPasswordTransportEncryptionAlg);
	}
	
	@Override
	public byte[] getDataTransportationPublicKey() {
		X509Certificate certificate = getCertificate(userPasswordTransportEncryptionAlias);
		return certificate.getPublicKey().getEncoded();
	}
	
	@Override
	public byte[] decryptHashedPassword(byte[] encryptedHash) {
		Key key = getKey(userPasswordTransportEncryptionAlias);  //"rsa"
		
		Cipher cipher = null;
		
		try {
			cipher = Cipher.getInstance(userPasswordTransportEncryptionAlg, getJCEProvider()); //"RSA/NONE/PKCS1Padding"
		} catch (Exception e) {
			throw new CryptoConfigException(e.getMessage(), e);
		} 
		
		try {
			cipher.init(Cipher.DECRYPT_MODE, key);
			System.out.println("-----in decryptHashedPassword--"+encryptedHash);
			byte[] plainText = cipher.doFinal(encryptedHash);
			plainText = Hex.decodeHex(new String(plainText).toCharArray());  //??
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
			//cipher = Cipher.getInstance(alg);
			cipher = Cipher.getInstance(alg, getJCEProvider());
		} catch (Exception e) {
			throw new CryptoConfigException(e.getMessage(), e);
		}
		
		try {			
			cipher.init(Cipher.ENCRYPT_MODE, key, iv);
			//cipher.init(Cipher.ENCRYPT_MODE, key); 
			byte[] cipherText = cipher.doFinal(clearText);
			return cipherText;
		} catch (Exception e) {
			throw new CryptoConfigException(e.getMessage(), e);
		} 
		
	}
	
	private byte[] decryptAesCbc(byte[] cipherText, String alias, String alg){
		Key key = getKey(alias);
		Cipher cipher = null;
		
		try {
			cipher = Cipher.getInstance(alg, getJCEProvider());
		} catch (Exception e) {
			throw new CryptoConfigException(e.getMessage(), e);
		} 
		
		try {
			
			/*cipher.init(Cipher.DECRYPT_MODE, key);
			byte[] plainText = cipher.doFinal(cipherText);
			plainText = Hex.decodeHex(new String(plainText).toCharArray());  //??
			return plainText;*/
			
			//cipher.init(Cipher.DECRYPT_MODE, key);
			try {
				iv = new IvParameterSpec(Hex.decodeHex("00010203040506070809101112131415".toCharArray()));
			} catch (DecoderException e) {
				throw new IllegalArgumentException(e.getMessage(), e);
			}

			cipher.init(Cipher.DECRYPT_MODE, key, iv);
			byte[] clearText = cipher.doFinal(cipherText);
			return clearText;
		} catch (Exception e) {
			throw new CryptoConfigException(e.getMessage(), e);
		}

	}

	
	protected abstract char[] getKeyStorePassword();
	
	protected abstract String getJCEProvider();
	
}
























