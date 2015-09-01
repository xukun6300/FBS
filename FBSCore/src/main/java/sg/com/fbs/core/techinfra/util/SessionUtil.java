package sg.com.fbs.core.techinfra.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu $
 * @Created 12:12:07 pm 1 Sep, 2015 $
 * 
 */
public class SessionUtil {

	private static String getSessionAttributeName(String attributeName){
		return "FBS_".concat(attributeName);
	}
	
	public static Object getSessionValue(HttpSession session, String attributeName){
		String sessionAttributeName = getSessionAttributeName(attributeName);
		return session.getAttribute(sessionAttributeName);
	}
	
	public static void setSessionValue(HttpSession session, String attributeName, Object value){
		String sessionAttributeName = getSessionAttributeName(attributeName);
		session.setAttribute(sessionAttributeName, value);
	}
	
	public static void removeSessionValue(HttpSession session, String attributeName){
		String sessionAttributeName = getSessionAttributeName(attributeName);
		session.removeAttribute(sessionAttributeName);
	}
	
	public static String getIpAddress(HttpServletRequest request){
		String ipAddress = request.getHeader("x-forwarded-for");
		
		if(ipAddress==null){
			ipAddress = request.getHeader("X-FORWARDED-FOR");
			if(ipAddress==null){
				ipAddress = request.getRemoteAddr();
			}
		}
		
		return ipAddress;
	}
	
	public static String getUserAgent(HttpServletRequest request){
		String userAgent = request.getHeader("User-Agent");
		if(userAgent!=null){
			if(userAgent.indexOf("Chrome")>=0){
				userAgent = "Chrome";
			}else if (userAgent.indexOf("Firefox")>=0) {
				userAgent = "Firefox";
			}else if (userAgent.indexOf("MSIE")>=0) {
				userAgent = "MSIE"+ StringUtils.substringBetween(userAgent, "MSIE",";");
			}else if (userAgent.indexOf("rv:11.0")>=0) {
				userAgent = "MSIE 11.0";
			}
		}
		return userAgent;
	}
}
