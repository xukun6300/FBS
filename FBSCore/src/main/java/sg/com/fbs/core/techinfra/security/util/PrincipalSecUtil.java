package sg.com.fbs.core.techinfra.security.util;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import sg.com.fbs.model.system.security.UserDetailsIF;

/**
 * @Author Frank Xu $
 * @Created 3:12:51 pm 29 Jul, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public class PrincipalSecUtil {

	public static UserDetailsIF getUserDetail(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication!=null){
			Object principal = authentication.getPrincipal();
			if(principal!=null && principal instanceof UserDetailsIF){
				return (UserDetailsIF)principal;
			}
		}		
		return null;
	}
	
	public static long getUserId(){
		long userId=0;
		UserDetailsIF userDetail = getUserDetail();
		if(userDetail!=null){
			userId = userDetail.getUserId();
		}	
		BCryptPasswordEncoder bCryptPasswordEncoder  = new BCryptPasswordEncoder();
		Authentication authentication ;
		AuthenticationProvider anAuthenticationProvider;
		UserDetailsService userDetailsService;
		UsernamePasswordAuthenticationFilter u;
		 
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken;
		return userId;
	}
	
	
}
