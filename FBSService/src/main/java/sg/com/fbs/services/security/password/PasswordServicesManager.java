package sg.com.fbs.services.security.password;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;




import sg.com.fbs.model.system.security.User;
import sg.com.fbs.model.system.security.UserCredentials;
import sg.com.fbs.services.security.external.crypto.provider.CryptoServicesClientIF;

/**
 * @Author Frank Xu $
 * @Created 11:52:16 am 11 Aug, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public class PasswordServicesManager implements PasswordServices{

	private static final Logger logger = Logger.getLogger(PasswordServicesManager.class);
	
	@Resource
	private CryptoServicesClientIF restClient;

	private String transportRSAKeyModulus;
	
	private String transportRSAKeyExponent;
	
	@Override
	public String getTransportRSAKeyModulus() {
		if (transportRSAKeyModulus == null) {
			transportRSAKeyModulus = restClient.getTransportRSAKeyModulus();
		}
		return transportRSAKeyModulus;
	}

	@Override
	public String getTransportRSAKeyExponent() {
		if (transportRSAKeyExponent == null) {
			transportRSAKeyExponent = restClient.getTransportRSAKeyExponent();
		}
		return transportRSAKeyExponent;
	}
	
/*	@Override
	public String encodePasswordWithBCrypt(String rawPassword) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String password =passwordEncoder.encode(rawPassword);
		return password;
	}*/


	@Override
	public String decryptPassword(String password) {
		//decrypt encrypted hmac and encrypt using symmetric alg using crypto services
		return restClient.decryptPassword(password);
	}

	@Override
	public String getSalt() {
		return restClient.getSalt();
	}

	@Override
	public List<String> decryptSecurityAnswers(List<String> securityAnswers) {
		// decrypt encrypted hmac  and encrypt using symmetric alg using crypto services
		return restClient.decryptSecurityAnswers(securityAnswers);
	}

	@Override
	public String getNonce() {
		return restClient.getNonce();
	}

	@Override
	public UserCredentials getUserCredentials(User user) {
		UserCredentials userCredentials = null;
		UserCredentials decryptedUserCredentials = null;
		
		try {
			userCredentials = new UserCredentials();
			
			if(user!=null){
				if(!StringUtils.isEmpty(user.getPassword())){
					userCredentials.setPassword(user.getPassword().toCharArray());
				}
				
				if(!StringUtils.isEmpty(user.getSalt())){
					userCredentials.setSalt(user.getSalt().toCharArray());
				}
			}
			
			String decryptedSalt = restClient.decryptSalt(new String(userCredentials.getSalt()));
			decryptedUserCredentials = new UserCredentials(userCredentials.getPassword(), decryptedSalt.toCharArray());
			
		} catch (Exception e) {
			logger.error("Exception happened while decypting User credentials");
		}
		
		return decryptedUserCredentials;
	}

	
	

}
