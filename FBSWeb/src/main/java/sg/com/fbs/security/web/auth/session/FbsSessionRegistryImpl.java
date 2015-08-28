package sg.com.fbs.security.web.auth.session;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionDestroyedEvent;

/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu $
 * @Created 4:45:03 pm 28 Aug, 2015 $
 * 
 */
public class FbsSessionRegistryImpl implements SessionRegistry, ApplicationListener<SessionDestroyedEvent>{

	protected final Logger logger = Logger.getLogger(FbsSessionRegistryImpl.class);
	
	private final ConcurrentMap<Object, Set<String>> principals = new ConcurrentHashMap<Object, Set<String>>();
	
	private final Map<String, FbsSessionInformation> sessionIds = new ConcurrentHashMap<String, FbsSessionInformation>();
	
	@Override
	public List<Object> getAllPrincipals() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SessionInformation> getAllSessions(Object principal,
			boolean includeExpiredSessions) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SessionInformation getSessionInformation(String sessionId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void refreshLastRequest(String sessionId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void registerNewSession(String sessionId, Object principal) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeSessionInformation(String sessionId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onApplicationEvent(SessionDestroyedEvent event) {
		// TODO Auto-generated method stub
		
	}

}
