package sg.com.fbs.security.web.auth.event.listener;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.event.AbstractAuthenticationEvent;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import sg.com.fbs.model.system.security.User;
import sg.com.fbs.security.web.auth.fbsid.WebAuthActivityLogger;
import sg.com.fbs.services.system.security.uam.exception.UserAccountManagementException;
import sg.com.fbs.services.system.security.uam.mgr.UserAccountManagerBD;

/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu $
 * @Created 11:55:11 am 10 Sep, 2015 $
 * 
 */
public class AuthenticationEventListener implements ApplicationListener<AbstractAuthenticationEvent>{

	@Autowired
	private WebAuthActivityLogger activityLogger;
	
	private Logger logger = Logger.getLogger(AuthenticationEventListener.class);
	
	@Override
	public void onApplicationEvent(AbstractAuthenticationEvent event) {
		
		Object principal = event.getAuthentication().getPrincipal();
		long userId = 0L;		
		User user = null;
		if(principal instanceof User){
			userId = ((User)principal).getId();	
			user = (User)principal;
		}
		
		if(event instanceof AuthenticationSuccessEvent){
			
			WebAuthenticationDetails details = (WebAuthenticationDetails) ((UsernamePasswordAuthenticationToken)event.getSource()).getDetails();
			activityLogger.logSuccessfulLogin(userId, details!=null?details.getSessionId():"");
			
			try {
				updateUserSuccessLoginInformation(user);
			} catch (UserAccountManagementException e) {
				logger.error("Failed to update user success login information", e);
			}
			
		}else if (event instanceof AuthenticationFailureBadCredentialsEvent) {
			
			String principalName = event.getAuthentication().getName();
			String exceptionDetails = ((AuthenticationFailureBadCredentialsEvent)event).getException().getMessage();
			activityLogger.logFailedLogin(principalName, exceptionDetails);
			
			try {
				updateUserFailureLoginInformation(user);
			} catch (UserAccountManagementException e) {
				logger.error("Failed to update user failure login information", e);
			}
			
			/*if(principal instanceof User){
				UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) event.getSource();
				String loginId = (String) token.getPrincipal();
				updateUserFailureLoginInformation(loginId);
			}*/
			
		}
		
	}

	private void updateUserSuccessLoginInformation(User user) throws UserAccountManagementException{
		UserAccountManagerBD userAccountManagerBD = new UserAccountManagerBD();
		user.setFailedLoginAttempt(0);
		user.setLastSuccessLoginDate(DateTime.now());
		userAccountManagerBD.updateUser(user);
	}

	private void updateUserFailureLoginInformation(User user) throws UserAccountManagementException{
		UserAccountManagerBD userAccountManagerBD = new UserAccountManagerBD();
		user.setFailedLoginAttempt(user.getFailedLoginAttempt()+1);
		user.setLastFailedLoginDate(DateTime.now());
		userAccountManagerBD.updateUser(user);
	}
	
}



