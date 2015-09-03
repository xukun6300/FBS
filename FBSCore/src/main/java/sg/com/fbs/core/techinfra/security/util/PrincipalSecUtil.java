package sg.com.fbs.core.techinfra.security.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

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
	
	public static String getName(){
		UserDetailsIF userDetails = getUserDetail();
		String name = "";
		if(userDetails!=null){
			name = userDetails.getName();
		}
		
		return name;
	}
	
	public static long getUserId(){
		long userId=0;
		UserDetailsIF userDetail = getUserDetail();
		if(userDetail!=null){
			userId = userDetail.getUserId();
		}	
		return userId;
	}
	
	
}
