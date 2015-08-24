package sg.com.fbs.security.web.auth.fbsid;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.StringUtils;

import com.octo.captcha.service.multitype.MultiTypeCaptchaService;

import sg.com.fbs.model.system.security.User;
import sg.com.fbs.model.system.security.UserCredentials;
import sg.com.fbs.model.system.security.uam.AccountStatusEnum;
import sg.com.fbs.services.security.password.PasswordServices;
import sg.com.fbs.services.system.security.uam.mgr.UserAccountManagerBD;

/**
 * @Author Frank Xu $
 * @Created 9:02:17 am 24 Aug, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public final class UsernamePasswordHashAuthenticationFilter extends UsernamePasswordAuthenticationFilter{

	private static final Logger logger = Logger.getLogger(UsernamePasswordHashAuthenticationFilter.class);
	
	private final String SESSION_NONCE_ATTR = "nonce";
	
	private final String SESSION_PWD_HASH_ATTR = "hash";
	
	private final String SESSION_FAILED_LOGIN_ATTR = "fail";
	
	//private final String REQUEST_CAPTCHA_ATTR = "captcha";
	
	private final String SUPPORTED_HTTP_METHOD = "POST";
	
	private final String ERROR = "Error: ";
	
	private final String ERROR_MSG_BAD_CREDENTIALS = "The userid or password you entered is incorrect.";
	
	@Autowired
	private PasswordServices passwordServices;
	
	@Autowired
	private MultiTypeCaptchaService captchaService;
	
	UserAccountManagerBD userAccountManagerBD = null;
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		if(!request.getMethod().equals(SUPPORTED_HTTP_METHOD)){
			throw new AuthenticationServiceException("Authentication method not supported: "+request.getMethod());
		}
		
		Authentication authentication = null;
		userAccountManagerBD = new UserAccountManagerBD();
		
		HttpSession session = request.getSession(false);
		if(session==null){
			write(response, ERROR+"User Session Expired.");
			return null;
		}
		
		String nonce = (String) session.getAttribute(SESSION_NONCE_ATTR);
		
		String username = obtainUsername(request);
		//username empty
		if(!StringUtils.hasLength(username)){
			cleanup(session, null);
			write(response, ERROR+ ERROR_MSG_BAD_CREDENTIALS);
			return null;
		}
		
		username = username.trim().toLowerCase();
		
		
		
		return super.attemptAuthentication(request, response);
	}
	
	private boolean attemptAuthenticationFBS(String username, HttpServletResponse response, HttpSession session){
		User user = null;
		
		try {
			user = userAccountManagerBD.getUserByLoginId(username);
			
			if(user==null){
				cleanup(session, null);
				write(response, ERROR + ERROR_MSG_BAD_CREDENTIALS);
				return false;
			}
			
			String status = user.getStatus();
			AccountStatusEnum accountStatusEnum = AccountStatusEnum.getEnumFromValue(status);
			switch (accountStatusEnum) {
			case ACTIVE:
				break;
			case INACTIVE:
				write(response, ERROR+"Account is not activated.");
				cleanup(session, null);
				return false;
			case REJECTED:
				write(response, ERROR+"Your account is rejected");
				cleanup(session, null);
				return false;
			default:
				break;
			}
						
		} catch (Exception e) {
			write(response, ERROR+ ERROR_MSG_BAD_CREDENTIALS);
			cleanup(session, null);
			return false;
		}
		
		// if this is the first request for login, send back nonce and return (for frontierID)
		String nonce = (String) session.getAttribute(SESSION_NONCE_ATTR);
		
		if(!StringUtils.hasLength(nonce)){
			String nonceHex = "";
			try {
				nonceHex = passwordServices.getNonce();
			} catch (Exception e) {
				write(response, ERROR+ERROR_MSG_BAD_CREDENTIALS);
				cleanup(session, null);
				return false;
			}
			
			UserCredentials decryptedUserCredentials = null;
			
			try {
				decryptedUserCredentials = passwordServices.getUserCredentials(user);
				session.setAttribute(SESSION_NONCE_ATTR, nonceHex);
				session.setAttribute(SESSION_PWD_HASH_ATTR, decryptedUserCredentials.getPassword());
						
				StringBuilder sb = new StringBuilder();
				sb.append(decryptedUserCredentials.getSalt());
				sb.append(nonceHex);
				write(response, sb.toString());
				
			} catch (Exception e) {
				write(response, ERROR+ERROR_MSG_BAD_CREDENTIALS);
				cleanup(session, null);
				return false;
			}
			
			return false;
		}
		
		return true;
		
	}
	
	private void write(HttpServletResponse response, String message){
		PrintWriter pw = null;
		try {
			 pw = response.getWriter();
		} catch (IOException e) {
			logger.error("Not able to get Writer from response object", e);
		}		
		pw.println(message);
		pw.close();
	}
	
	private void cleanup(HttpSession session, Authentication authentication) {
		if (session != null) {
			session.removeAttribute(SESSION_NONCE_ATTR);
			session.removeAttribute(SESSION_PWD_HASH_ATTR);
			if (authentication != null) {
				session.removeAttribute(SESSION_FAILED_LOGIN_ATTR);
			}
		}
	}
	
}














































