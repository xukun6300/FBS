package sg.com.fbs.services.system.security.uam.mgr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import sg.com.fbs.core.businfra.facade.CommonFacade;
import sg.com.fbs.core.businfra.facade.ServiceLocator;
import sg.com.fbs.core.techinfra.persistence.exception.DataAccessObjectException;
import sg.com.fbs.model.system.persistence.query.Criteria;
import sg.com.fbs.model.system.persistence.query.CriteriaIF;
import sg.com.fbs.model.system.persistence.query.Criterion;
import sg.com.fbs.model.system.persistence.response.IResponseCRUD;
import sg.com.fbs.model.system.persistence.response.ResponseCRUD;
import sg.com.fbs.model.system.security.SecurityQuestions;
import sg.com.fbs.model.system.security.User;
import sg.com.fbs.model.system.security.UserSecurityQuestion;
import sg.com.fbs.model.system.security.uam.AccountStatusEnum;
import sg.com.fbs.model.system.security.uam.RegisterUserRequest;
import sg.com.fbs.services.security.external.crypto.provider.CryptoServicesClientIF;
import sg.com.fbs.services.security.password.PasswordServices;
import sg.com.fbs.services.system.security.uam.dao.UserAccountManagementDAO;
import sg.com.fbs.services.system.security.uam.dao.UserAccountManagementDaoException;

/**
 * @Author Frank Xu $
 * @Created 9:44:10 am 30 Jul, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public class UserAccountManager extends CommonFacade{


	private PasswordServices passwordServices;
	
	private CryptoServicesClientIF restClient;
	
	@SuppressWarnings("rawtypes")
	public ResponseCRUD saveNewUser(RegisterUserRequest registerUserRequest) throws DataAccessObjectException{
		ResponseCRUD response = new ResponseCRUD();
		UserAccountManagementDAO userAccountManagementDAO = new UserAccountManagementDAO();
		passwordServices = (PasswordServices) ServiceLocator.getService("passwordServices");
		restClient = (CryptoServicesClientIF)ServiceLocator.getService("cryptoServicesClient");
		
	    User user = new User();
		user.setLoginId(registerUserRequest.getEmail().toLowerCase());
		user.setPassword(registerUserRequest.getPassword());
		user.setSalutation(registerUserRequest.getSalutationTypeTId());
		user.setName(registerUserRequest.getName());
		user.setGender(registerUserRequest.getGenderTypeTDesc());
	    user.setDateOfBirth(registerUserRequest.getDob());
		user.setPreferredContactMode(registerUserRequest.getPrimaryContactTypeT());

		if(!StringUtils.isEmpty(registerUserRequest.getOfficeTelNo())){
			user.setOfficeTel(registerUserRequest.getOfficeTelNo());
		}
		
		if(!StringUtils.isEmpty(registerUserRequest.getMobileNo())){
			user.setMobileNum(registerUserRequest.getMobileNo());
		}
		//user.setStatus(RegisterUserEnum.PENDING_ACTIVATION.toString());
		user.setStatus(AccountStatusEnum.PENDINGACTIVATION.getAccountStatus());
		
		String[] secQuestionIds = {registerUserRequest.getSecurityQuestion1(), registerUserRequest.getSecurityQuestion2()};
		String[] answer = {registerUserRequest.getEncryptedAnswer1(), registerUserRequest.getEncryptedAnswer2()};
		
		Long[] longSecQuestionIds = new Long[secQuestionIds.length];
		Set<UserSecurityQuestion> userQuestionSet = new HashSet<UserSecurityQuestion>();
		
		for (int i = 0; i < secQuestionIds.length; i++) {
			if(secQuestionIds[i]!=null && !secQuestionIds[i].equals("-1")){
				UserSecurityQuestion userSecurityQuestion = new UserSecurityQuestion();
				SecurityQuestions questions = new SecurityQuestions();
				longSecQuestionIds[i] = Long.parseLong(secQuestionIds[i]);
				questions.setId(longSecQuestionIds[i]);
				userSecurityQuestion.setQuestionId(questions);
				userSecurityQuestion.setAnswer(answer[i]);
				//List<String> decryptedAnswers = passwordServices.decryptSecurityAnswers(Arrays.asList(answer[i]));
				List<String> decryptedAnswers = passwordServices.decryptSecurityAnswers(new ArrayList<String>(Arrays.asList(answer[i])));
				if (decryptedAnswers != null && decryptedAnswers.size() > 0) {
					for (String decryptedAnswer : decryptedAnswers) {
						userSecurityQuestion.setAnswer(decryptedAnswer);
					}
				}
				userQuestionSet.add(userSecurityQuestion);
			}
		}
		user.setUserSecurityQuestion(userQuestionSet);
		
		user.setPasswordLastUpdateDate(new DateTime());
		
		String password = registerUserRequest.getPassword();
		String confirmPassword = registerUserRequest.getConfirmPassword();
		String salt = registerUserRequest.getSalt();
		
		String encryptedPassword = null;
        String encryptSalt = null;
        
		if(password!=null){
			if(!password.equals(confirmPassword)){
				throw new BadCredentialsException("Password and confirm password mismatch.");
			}
			
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			encryptedPassword = encoder.encode(password);
			user.setPassword(encryptedPassword);
			
			/*encryptedPassword = passwordServices.decryptPassword(password);
			encryptSalt = restClient.encryptSalt(salt);
			user.setPassword(encryptedPassword);
			user.setSalt(encryptSalt);	*/
		}
		
		user = (User) userAccountManagementDAO.insert(user);
		
		// sent notification
		if (user != null) {			
			response.setCrudResult(user);
		}
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	/*	String pwd = encoder.encode("123456");
		System.out.println(pwd);
		System.out.println(new BCryptPasswordEncoder().matches("123456", pwd));*/
		return response;
	}
	
	
	@SuppressWarnings("rawtypes")
	public IResponseCRUD searchSecurityQuestion(CriteriaIF criteria) throws UserAccountManagementDaoException {
		UserAccountManagementDAO userAccountManagementDAO = new UserAccountManagementDAO();
		
		IResponseCRUD response;
		try {
			response = userAccountManagementDAO.searchSecurityQuestion(criteria);
		} catch (UserAccountManagementDaoException e) {
			throw new UserAccountManagementDaoException("fbs.common.ana.exception.message.get.securityQuestions", e);
		}
		return response;
	}
	
	
	@SuppressWarnings("rawtypes")
	public Boolean checkEmailIdExsit(String userId) throws UserAccountManagementDaoException{
		UserAccountManagementDAO userAccountManagementDAO = new UserAccountManagementDAO();
		
		CriteriaIF searchCriteria = new Criteria();
		Criterion[] criterions = {new Criterion(User.LOGINID, userId, true)};
		searchCriteria.setCriterion(criterions);
		searchCriteria.setFetchAll(true);
		
		IResponseCRUD response = userAccountManagementDAO.searchUser(searchCriteria);
		
		return response.getQueryResult().size()>0;
	}
	
	
	public User getUserByLoginId(String loginId) throws UserAccountManagementDaoException{
		UserAccountManagementDAO userAccountManagementDAO = new UserAccountManagementDAO();
		return (User) userAccountManagementDAO.findObject(User.class, User.LOGINID, loginId.toLowerCase());
	}
	
	public User getUser(String name, Object value) throws DataAccessObjectException{
		UserAccountManagementDAO userAccountManagementDAO = new UserAccountManagementDAO();
		User user = (User) userAccountManagementDAO.find(User.class, name, value, true);
		return user;
	}
	
	@SuppressWarnings("rawtypes")
	public IResponseCRUD searchUsers(CriteriaIF criteria) throws UserAccountManagementDaoException{
		UserAccountManagementDAO userAccountManagementDAO = new UserAccountManagementDAO();
		IResponseCRUD response = userAccountManagementDAO.searchUser(criteria);
		return response;
	}
	
}











































