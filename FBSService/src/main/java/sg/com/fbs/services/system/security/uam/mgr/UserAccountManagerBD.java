package sg.com.fbs.services.system.security.uam.mgr;


import com.sun.org.apache.bcel.internal.generic.NEW;

import sg.com.fbs.core.businfra.businessdelegate.BusinessDelegate;
import sg.com.fbs.core.techinfra.exception.ApplicationCoreException;
import sg.com.fbs.model.system.persistence.query.CriteriaIF;
import sg.com.fbs.model.system.persistence.response.ResponseCRUD;
import sg.com.fbs.model.system.security.User;
import sg.com.fbs.model.system.security.uam.RegisterUserRequest;
import sg.com.fbs.services.system.security.uam.exception.UserAccountManagementException;

/**
 * @Author Frank Xu $
 * @Created 9:41:46 am 30 Jul, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public class UserAccountManagerBD extends BusinessDelegate{

	
	@SuppressWarnings("rawtypes")
	public ResponseCRUD saveNewUser(RegisterUserRequest registerUserRequest) throws UserAccountManagementException{
		try {
			Object object = new UserAccountManager().run(registerUserRequest);
			return (ResponseCRUD) object;
		} catch (ApplicationCoreException e) {
			throw new UserAccountManagementException(e.getMessageCode(), e);
		}
	}
	
	@SuppressWarnings("rawtypes")
	public ResponseCRUD searchSecurityQuestion(CriteriaIF criteria) throws UserAccountManagementException{
		try {
			Object obj = new UserAccountManager().run(criteria);
			return (ResponseCRUD) obj;
		} catch (ApplicationCoreException e) {
			throw new UserAccountManagementException(e.getMessageCode(), e);
		}
	}
	
	public Boolean checkEmailIdExsit(String userId) throws UserAccountManagementException{
		try {
			Object obj = new UserAccountManager().run(userId);
			return (Boolean) obj;
		} catch (ApplicationCoreException e) {
			throw new UserAccountManagementException(e.getMessageCode(), e);
		}
	}
	
	public User getUserByLoginId(String loginId) throws UserAccountManagementException{
		try {
			Object obj = new UserAccountManager().run(loginId);
			return (User) obj;
		} catch (ApplicationCoreException e) {
			throw new UserAccountManagementException(e.getMessageCode(), e);
		}
	}
	
	public User getUser(String name, Object value) throws UserAccountManagementException{
		try {
			Object obj = new UserAccountManager().run(name, value);
			return (User)obj;
		} catch (ApplicationCoreException e) {
			throw new UserAccountManagementException(e.getMessageCode(), e);
		}
		
	}
}






