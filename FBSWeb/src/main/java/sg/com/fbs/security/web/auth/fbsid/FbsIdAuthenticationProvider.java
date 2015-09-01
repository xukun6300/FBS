package sg.com.fbs.security.web.auth.fbsid;

import java.util.Hashtable;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.util.StringUtils;

import sg.com.fbs.model.system.security.User;
import sg.com.fbs.services.security.password.PasswordServices;
import sg.com.fbs.services.system.security.uam.exception.UserAccountManagementException;
import sg.com.fbs.services.system.security.uam.mgr.UserAccountManagerBD;

/**
 * @Author Frank Xu $
 * @Created 6:00:28 pm 24 Aug, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public class FbsIdAuthenticationProvider implements AuthenticationProvider{

	@Autowired
	private PasswordServices passwordServices;
	
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		final UsernamePasswordAuthenticationToken userToken = (UsernamePasswordAuthenticationToken) authentication;
		String username = userToken.getName();
		String password = (String) authentication.getCredentials();
		
		if(!StringUtils.hasLength(username)){
			throw new BadCredentialsException("Empty Username");
		}
		
		//FbsIdAuthenticationToken authToken = (FbsIdAuthenticationToken) authentication;
		
		User user =null;
		Map<String, Object> map = new Hashtable<String, Object>();
		UserAccountManagerBD userAccountManagerBD = new UserAccountManagerBD();
		try {
			user = userAccountManagerBD.getUserByLoginId(username);
		} catch (UserAccountManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/** Here will set Authentication object principal and cridential value **/
		UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(user, password);
		result.setDetails(authentication.getDetails());
		return result;
	}

	@Override
	public boolean supports(Class<?> authentication) {
	
		
		return true;
	}

}
