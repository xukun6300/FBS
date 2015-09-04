package sg.com.fbs.security.web.auth.session;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationListener;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionDestroyedEvent;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.util.Assert;

import sg.com.fbs.model.system.security.User;
import sg.com.fbs.security.web.auth.constants.AppConstants;
import sg.com.fbs.security.web.auth.fbsid.WebAuthActivityLogger;

/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu $
 * @Created 4:45:03 pm 28 Aug, 2015 $
 * 
 */
public class FbsSessionRegistryImpl implements SessionRegistry, ApplicationListener<SessionDestroyedEvent>{
  
	protected final Logger logger = Logger.getLogger(FbsSessionRegistryImpl.class);
	
	/** <principal:Object,SessionIdSet> */
	private final ConcurrentMap<Object, Set<String>> principals = new ConcurrentHashMap<Object, Set<String>>();
	
	 /** <sessionId:Object,SessionInformation> */
	private final Map<String, FbsSessionInformation> sessionIds = new ConcurrentHashMap<String, FbsSessionInformation>();
	
	private WebAuthActivityLogger activityLogger;
	
	public FbsSessionRegistryImpl(WebAuthActivityLogger activityLogger){
		this.activityLogger = activityLogger;
	}
	
	@Override
	public List<Object> getAllPrincipals() {
		return new ArrayList<Object>(principals.keySet());
	}

	@Override
	public List<SessionInformation> getAllSessions(Object principal, boolean includeExpiredSessions) {
		//session id set
		final Set<String> sessionsUsedByPrincipal = principals.get(principal);
		
		if(sessionsUsedByPrincipal==null){
			return Collections.emptyList();
		}
		
		List<SessionInformation> list = new ArrayList<SessionInformation>(sessionsUsedByPrincipal.size());
		
		for (String sessionId : sessionsUsedByPrincipal) {
			SessionInformation sessionInformation = getSessionInformation(sessionId);
			if(sessionInformation==null){
				continue;
			}
			if(includeExpiredSessions||!sessionInformation.isExpired()){
				list.add(sessionInformation);
			}
		}
		
		return list;
	}

	@Override
	public SessionInformation getSessionInformation(String sessionId) {
		Assert.hasText(sessionId, "SessionId required as per interface contract");
		
		return sessionIds.get(sessionId);
	}

	@Override
	public void refreshLastRequest(String sessionId) {
		Assert.hasText(sessionId, "SessionId required as per interface contract");
		
		SessionInformation sessionInformation = getSessionInformation(sessionId);
		if(sessionInformation!=null){
			sessionInformation.refreshLastRequest();
		}
		
	}

	@Override
	public void registerNewSession(String sessionId, Object principal) {
		Assert.hasText(sessionId, "SessionId required as per interface contract");
		Assert.notNull(principal, "principal required as per interface contract");
		
		if(getSessionInformation(sessionId)!=null){
			removeSessionInformation(sessionId);
		}
		
		FbsSessionInformation newSession = new FbsSessionInformation(principal, sessionId, new Date());
		sessionIds.put(sessionId, newSession);
		
		Set<String> sessionsUsedByPrincipal = principals.get(principal);
		
		if(sessionsUsedByPrincipal==null){
			sessionsUsedByPrincipal = new CopyOnWriteArraySet<String>();
			
			Set<String> prevSessionsUsedByPrincipal = principals.putIfAbsent(principal, sessionsUsedByPrincipal);
			if(prevSessionsUsedByPrincipal!=null){
				sessionsUsedByPrincipal = prevSessionsUsedByPrincipal;
			}
		}
		
		sessionsUsedByPrincipal.add(sessionId);
	}
	

	@Override
	public void removeSessionInformation(String sessionId) {
		Assert.hasText(sessionId, "SessionId required as per interface contract");
		
		SessionInformation sessionInformation = getSessionInformation(sessionId);
		if(sessionInformation==null){
			return;
		}
		
		sessionIds.remove(sessionId);
		Set<String> sessionsUsedByPrincipal = principals.get(sessionInformation.getPrincipal());
		
		if(sessionsUsedByPrincipal==null){
			return;
		}
		
		sessionsUsedByPrincipal.remove(sessionId);
		
		//if after remove seesion, this principal dont have any session associated, then remove this principal
		if(sessionsUsedByPrincipal.isEmpty()){
			principals.remove(sessionInformation.getPrincipal());
		}
		
	}
	
	public void updateSessionInformation(String sessionId, String ipAddress, String userAgent){
		FbsSessionInformation sessionInformation = (FbsSessionInformation) getSessionInformation(sessionId);
		if(sessionInformation==null){
			return;
		}
		sessionInformation.setIpAddress(ipAddress);
		sessionInformation.setUserAgent(userAgent);
	}

	@Override
	public void onApplicationEvent(SessionDestroyedEvent event) {
		String sessionId = event.getId();
		FbsSessionInformation sessionInformation = sessionIds.get(sessionId);
		boolean maxSessions = false;
		if(sessionInformation!=null){
			maxSessions = sessionInformation.isMaxSessions();
		}
		
		removeSessionInformation(sessionId);
		
		HttpSession session = (HttpSession) event.getSource();
		
		SecurityContext securityContext = (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");
		if(securityContext!=null){
			Object obj = securityContext.getAuthentication().getPrincipal();
			Long userId = 0L;
			if(obj instanceof User){
				User user = (User)obj;
				userId = user.getId();
			}
			
			if(session.getAttribute(AppConstants.SESSION_LOGOUT)!=null){
				activityLogger.logLogout(userId, session.getId());
			}else {
				if(!maxSessions){
					activityLogger.logTimeout(userId, session.getId());				
				}else {
					activityLogger.logExceededMaxSessions(userId, session.getId());
				}
			}
		}
	}

}



















