package sg.com.fbs.security.web.auth.session;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

import org.springframework.security.core.session.SessionInformation;

import sg.com.fbs.model.system.security.UserDetailsIF;

/**
 * @Author Frank Xu $
 * @Created 2:49:12 pm 28 Aug, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public class FbsSessionInformation extends SessionInformation{

	private static final long serialVersionUID = 6777398026746725596L;

	@Getter
	@Setter
	private boolean maxSessions;
	
	@Getter
	@Setter
	private Date loginTime;
	
	@Getter
	@Setter
	private String ipAddress;
	
	@Getter
	@Setter
	private String userAgent;
	
	public FbsSessionInformation(Object principal, String sessionId, Date lastRequest) {
		super(principal, sessionId, lastRequest);
		setLoginTime(lastRequest);
	}
	
	public String getName(){
		Object p = getPrincipal();
		UserDetailsIF userDetails = null;
		if (p != null && p instanceof UserDetailsIF) {
			userDetails = (UserDetailsIF) p;
			return userDetails.getName();
		}
		return null;
	}
	
	public String getLoginId() {
		Object p = getPrincipal();
		UserDetailsIF userDetails = null;
		if (p != null && p instanceof UserDetailsIF) {
			userDetails = (UserDetailsIF) p;
			return userDetails.getLoginId();
		}
		return null;
	}
	
	public long getIdleTime(){
		return ((new Date()).getTime() - getLastRequest().getTime())/1000L/60;
	}

}













