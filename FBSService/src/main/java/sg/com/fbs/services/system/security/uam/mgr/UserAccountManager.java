package sg.com.fbs.services.system.security.uam.mgr;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.springframework.security.authentication.BadCredentialsException;

import sg.com.fbs.core.businfra.facade.CommonFacade;
import sg.com.fbs.core.techinfra.persistence.exception.DataAccessObjectException;
import sg.com.fbs.model.system.persistence.response.ResponseCRUD;
import sg.com.fbs.model.system.security.User;
import sg.com.fbs.model.system.security.uam.RegisterUserRequest;
import sg.com.fbs.services.security.external.crypto.provider.CryptoServicesClientIF;
import sg.com.fbs.services.security.password.PasswordServices;
import sg.com.fbs.services.system.security.uam.dao.UserAccountManagementDAO;

/**
 * @Author Frank Xu $
 * @Created 9:44:10 am 30 Jul, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public class UserAccountManager extends CommonFacade{

	@Resource
	private PasswordServices passwordServices;
	
	@Resource
	private CryptoServicesClientIF restClient;
	
	@SuppressWarnings("rawtypes")
	public ResponseCRUD saveNewUser(RegisterUserRequest registerUserRequest) throws DataAccessObjectException{
		ResponseCRUD response = new ResponseCRUD();
		UserAccountManagementDAO userAccountManagementDAO = new UserAccountManagementDAO();
		
	    User user = new User();
		user.setLoginId(registerUserRequest.getEmail().toLowerCase());
		user.setPassword(registerUserRequest.getPassword());
		user.setSalutation(registerUserRequest.getSalutationTypeTId());
		user.setName(registerUserRequest.getName());
		user.setGender(registerUserRequest.getGenderTypeTDesc());
		user.setDateOfBirth(registerUserRequest.getDob());
		//user.setPreferredContactMode(registerUserRequest.getPrimaryContactTypeT());

		if(!StringUtils.isEmpty(registerUserRequest.getOfficeTelNo())){
			user.setOfficeTel(registerUserRequest.getOfficeTelNo());
		}
		
		if(!StringUtils.isEmpty(registerUserRequest.getMobileNo())){
			user.setMobileNum(registerUserRequest.getMobileNo());
		}
		user.setStatus(RegisterUserEnum.PENDING_ACTIVATION.toString());
		user.setPasswordLastUpdateDate(new DateTime());
		
		String password = registerUserRequest.getPassword();
		String confirmPassword = registerUserRequest.getConfirmPassword();
		String encryptedPassword = null;
		if(password!=null){
			if(!password.equals(confirmPassword)){
				throw new BadCredentialsException("Password and confirm password mismatch.");
			}
			
			encryptedPassword = passwordServices.decryptPassword(password);
			user.setPassword(encryptedPassword);
		}
		
		user = (User) userAccountManagementDAO.insert(user);
		
		// sent notification
		if (user != null) {
			
			response.setCrudResult(user);
		}
		
		return response;
	}
	
	
	
}











































