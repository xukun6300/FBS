package sg.com.fbs.security.web.auth.logout;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import sg.com.fbs.core.techinfra.security.util.PrincipalSecUtil;
import sg.com.fbs.security.web.auth.constants.AppConstants;
import sg.com.fbs.security.web.auth.fbsid.WebAuthActivityLogger;

/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu $
 * @Created 10:56:47 am 8 Sep, 2015 $
 * 
 */
public class FbsSecurityContextLogoutHandler extends SecurityContextLogoutHandler{

	@Autowired
	private WebAuthActivityLogger activityLogger;
	
	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		long createdBy = PrincipalSecUtil.getUserId();
		String sessionId = request.getSession().getId();
		activityLogger.logLogout(createdBy, sessionId);
		
		this.setInvalidateHttpSession(false);
		HttpSession session = request.getSession(false);
		if(session!=null){
			session.setAttribute(AppConstants.SESSION_LOGOUT, true);
		}
		
		super.logout(request, response, authentication);
	}
}
