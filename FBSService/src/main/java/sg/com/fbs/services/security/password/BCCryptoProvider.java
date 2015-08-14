package sg.com.fbs.services.security.password;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.security.KeyStore;
import java.security.Security;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.core.io.Resource;

/**
 * @Author Frank Xu $
 * @Created 5:11:06 pm 6 Aug, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public final class BCCryptoProvider extends AbstractCryptoProvider{

	Logger logger = Logger.getLogger(BCCryptoProvider.class);
	
	private String keyStoreFile;
	
	private char[] keyStorePwd;
	
	private String targetDirectory =System.getProperty("java.io.tmpdir")+File.separator+ "fbs";
	
	private String targetFilename = targetDirectory+File.separator+"fbs.keystore";
	
	private static final String SECURITY_PROVIDER = "BC";
	
	static{
		Security.addProvider(new BouncyCastleProvider());
	}
	
	
	
	public BCCryptoProvider(Properties properties, Resource resource) {
		super(properties);
		File targetFile = new File(targetFilename);
		
		try {
			copyFile(resource.getFile(), targetFile);
		} catch (IOException e) {
			logger.error("Failed to get file object from Spring resource object",e);
		}
		
		keyStoreFile = targetFilename;
		
		keyStorePwd = properties.getProperty(AbstractCryptoProvider.KEYSTORE_PASSWORD).toCharArray();
		
		init();
	}


	private void init(){
		KeyStore keyStore;
		try {
			 keyStore = KeyStore.getInstance(getKeyStoreType(), SECURITY_PROVIDER);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new CryptoConfigException(e.getMessage(), e);
		} 
		
		File file = new File(keyStoreFile);
		
	//	char[] pwd = keyStorePwd;
		
		if(file.exists()&&file.canRead()){
			FileInputStream inputStream = null;
			
			try {
				inputStream = new FileInputStream(file);
				keyStore.load(inputStream, keyStorePwd);
				setKeyStore(keyStore);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new CryptoConfigException(e.getMessage(), e);
			} finally {
				if(inputStream!=null){
					try {
						inputStream.close();
					} catch (IOException e) {
						//
					}
				}
			}
		}else {
			try {
				keyStore.load(null, keyStorePwd);
				setKeyStore(keyStore);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new CryptoConfigException(e.getMessage(), e);
			}
		}

	}

	private void copyFile(File sourceFile, File destinationFile) throws IOException{
		File targetDir = new File(targetDirectory);
		if(!targetDir.exists()){
			targetDir.mkdir();
		}
		
		if(!destinationFile.exists()){
			destinationFile.createNewFile();
		}
		
		FileChannel source = null;
		FileChannel destination = null;
		
		try {
			source = new FileInputStream(sourceFile).getChannel();
			destination = new FileOutputStream(destinationFile).getChannel();
			destination.transferFrom(source, 0, source.size());
		} finally{
			safeClose(source);
			safeClose(destination);
		}
	}
	
	@SuppressWarnings("unused")
	private void safeClose(FileChannel fileChannel){
		if(fileChannel!=null){
			try {
				fileChannel.close();
			} catch (IOException e) {
			
			}
		}
	}


	@Override
	protected char[] getKeyStorePassword() {
		return keyStorePwd;
	}


	@Override
	protected String getJCEProvider() {
		return SECURITY_PROVIDER;
	}
	
	
}























