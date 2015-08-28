package sg.com.fbs.security.web.auth.session;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.security.web.authentication.session.SessionFixationProtectionStrategy;
import org.springframework.util.Assert;



/**
 * @Author Frank Xu $
 * @Created 3:06:18 pm 28 Aug, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public class FbsConcurrentSessionControlStrategy extends SessionFixationProtectionStrategy implements MessageSourceAware{

	protected MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();
	
	private final SessionRegistry sessionRegistry;
	
	private boolean exceptionIfMaximumExceeded = false;
	
	private int maximumSessions = 1; 
	
	/**
     * the session registry which should be updated when the authenticated session is changed.
     */
	public FbsConcurrentSessionControlStrategy(SessionRegistry sessionRegistry){
		Assert.notNull(sessionRegistry, "The sessionRegistry cannot be null.");
		super.setAlwaysCreateSession(true);
		this.sessionRegistry = sessionRegistry;
	}
	
	/**
     * In addition to the steps from the superclass, the sessionRegistry will be updated with the new session information.
     */
	@Override
	public void onAuthentication(Authentication authentication, HttpServletRequest request, HttpServletResponse response) {
		
		checkAuthenticationAllowed(authentication, request);

		// Allow the parent to create a new session if necessary
		super.onAuthentication(authentication, request, response);
		
		sessionRegistry.registerNewSession(request.getSession().getId(), authentication.getPrincipal());
		
		
	}
	
	@Override
	public void setMessageSource(MessageSource messageSource) {
		this.messages = new MessageSourceAccessor(messageSource);
	}
	
	/**
     * Method intended for use by subclasses to override the maximum number of sessions that are permitted for
     * a particular authentication. The default implementation simply returns the <code>maximumSessions</code> value
     * for the bean.
     *
     * @param authentication to determine the maximum sessions for
     *
     * @return either -1 meaning unlimited, or a positive integer to limit (never zero)
     */
	protected int getMaximumSessionsForThisUser(Authentication authentication){
		return this.maximumSessions;
	}
	
	private void checkAuthenticationAllowed(Authentication authentication, HttpServletRequest request){
		final List<SessionInformation> sessions = sessionRegistry.getAllSessions(authentication.getPrincipal(), false);
		int sessionCount = sessions.size();
		int allowedSessions = getMaximumSessionsForThisUser(authentication);
		
		if (sessionCount < allowedSessions) {
			// They haven't got too many login sessions running at present
			return;
		}
		
		if(allowedSessions == -1){
			// We permit unlimited logins
			return;
		}
		
		if (sessionCount == allowedSessions) {
			HttpSession session = request.getSession(false);
			if (session != null) {
				// Only permit it though if this request is associated with one of the already registered sessions
				for (SessionInformation sessionInformation : sessions) {
					if (sessionInformation.getSessionId().equals(session.getId())) {
						return;
					}
				}
			}
		}
		
		allowableSessionsExceeded(sessions, allowedSessions, sessionRegistry);
	}
	
	
	/**
     * Allows subclasses to customise behaviour when too many sessions are detected.
     *
     * @param sessions either <code>null</code> or all unexpired sessions associated with the principal
     * @param allowableSessions the number of concurrent sessions the user is allowed to have
     * @param registry an instance of the <code>SessionRegistry</code> for subclass use
     *
     */
	protected void allowableSessionsExceeded(List<SessionInformation> sessions, int allowableSessions, SessionRegistry registry){
		if(exceptionIfMaximumExceeded || (sessions == null)){
			throw new SessionAuthenticationException(messages.getMessage("ConcurrentSessionControlStrategy.exceededAllowed", 
					new Object[]{Integer.valueOf(allowableSessions)}, 
					"Maximum sessions of {0} for this principal exceeded"));
		}
		
		// Determine least recently used session, and mark it as invalidated
		SessionInformation leastRecentlyUsed = null;
		for (SessionInformation session : sessions) {
			if((leastRecentlyUsed ==null)||session.getLastRequest().before(leastRecentlyUsed.getLastRequest())){
				leastRecentlyUsed = session;
			}
		}
		leastRecentlyUsed.expireNow();
		((FbsSessionInformation)leastRecentlyUsed).setMaxSessions(true);
	}
	
}
















