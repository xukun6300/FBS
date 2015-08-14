package sg.com.fbs.model.system.security;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * @Author Frank Xu $
 * @Created 3:02:15 pm 29 Jul, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public interface UserDetailsIF extends UserDetails{

	@Override
	public String getPassword();
	
	@Override
	public String getUsername();
	
	long getUserId();
}
