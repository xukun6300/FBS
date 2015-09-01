package sg.com.fbs.security.web.auth.fbsid;

import org.apache.log4j.Logger;
import org.hibernate.cfg.annotations.Nullability;
import org.joda.time.DateTime;

import sg.com.fbs.core.techinfra.exception.ApplicationCoreException;
import sg.com.fbs.model.domain.enumeration.ActiveStatusEnum;
import sg.com.fbs.model.system.activity.Activity;
import sg.com.fbs.model.system.activity.ActivityLog;
import sg.com.fbs.model.system.persistence.query.Criteria;
import sg.com.fbs.model.system.persistence.query.CriteriaIF;
import sg.com.fbs.model.system.persistence.query.Criterion;
import sg.com.fbs.model.system.persistence.query.CriterionIF;
import sg.com.fbs.model.system.persistence.query.RestrictionType;
import sg.com.fbs.model.system.persistence.response.ResponseCRUD;
import sg.com.fbs.model.system.security.User;
import sg.com.fbs.model.system.security.uam.AccountStatusEnum;
import sg.com.fbs.services.common.activity.mgr.ActivityMgrBD;
import sg.com.fbs.services.logging.mgr.ActivityLogMgrBD;
import sg.com.fbs.services.system.security.uam.exception.UserAccountManagementException;
import sg.com.fbs.services.system.security.uam.mgr.UserAccountManagerBD;

/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu $
 * @Created 9:13:38 am 31 Aug, 2015 $
 * 
 */
public class WebAuthActivityLogger {

	private static final Logger logger = Logger.getLogger(WebAuthActivityLogger.class);
	
	private static final int MAX_LOGIN_ATTEMPTS = 3;
	
	private long successfulLoginActivityId;
	
	private long failedLoginActivityId;
	
	private long logoutActivityId;
	
	private long timeoutActivityId;
	
	private long exceededMaxSessionsActivityId;
	

	public WebAuthActivityLogger(String successfulLoginActivityId, String failedLoginActivityId, String logoutActivityId, 
			String timeoutActivityId, String exceededMaxSessionsActivityId) throws ApplicationCoreException{
		
		this.successfulLoginActivityId = getActivityId(successfulLoginActivityId);
		this.failedLoginActivityId = getActivityId(failedLoginActivityId);
		this.logoutActivityId = getActivityId(logoutActivityId);
		this.timeoutActivityId = getActivityId(timeoutActivityId);
		this.exceededMaxSessionsActivityId = getActivityId(exceededMaxSessionsActivityId);
	}
	
	private enum WebAuthenticationAction{
		LOGIN_SUCCESS("loginSuccessful"),
		LOGIN_FAIL("loginFailed"),
		LOGOUT("logout"),
		TIME_OUT("timeOut"),
		EXCEED_SESSION("maxSessionReached");
		
		private String description;
		
		private WebAuthenticationAction(final String description){
			this.description = description;
		}
		
		public String getDescription() {
			return description;
		}
	}
	
	public void logSuccessfulLogin(long userId, String sessionId){
		ActivityLog activityLog = createActivityLog(WebAuthenticationAction.LOGIN_SUCCESS, userId, sessionId);
		
		try {
			new ActivityLogMgrBD().saveActivityLog(activityLog);
		} catch (ApplicationCoreException e) {
			logger.error("Error in saving successful login activity log.", e);
		}
		
	}
	
	public AccountStatusEnum logFailedLogin(String loginId, String details){
		UserAccountManagerBD userAccountManagerBD = new UserAccountManagerBD();
		ActivityLogMgrBD activityLogMgrBD = new ActivityLogMgrBD();
		User createdByUser = null;
		ActivityLog activityLog = null;
		try {
			createdByUser = userAccountManagerBD.getUserByLoginId(loginId);
			
			if(createdByUser!=null){
				activityLog = createActivityLog(WebAuthenticationAction.LOGIN_FAIL, createdByUser.getId(), details);
			}else {
				return null;
			}
			
		} catch (UserAccountManagementException e) {
			logger.error("Error in getting user details for failed login for " + loginId, e);
			return null;
		}
		
		if(createdByUser.getFailedLoginAttempt()>=MAX_LOGIN_ATTEMPTS){
			createdByUser.setStatus(AccountStatusEnum.LOCKED.getDescription());
			createdByUser.setFailedLoginAttempt(0);
			
			//Email Notification for Account Locked 
			
		}else {
			createdByUser.setFailedLoginAttempt(createdByUser.getFailedLoginAttempt()+1);
		}
		
		createdByUser.setLastFailedLoginDate(DateTime.now());
		
		try {
			activityLogMgrBD.saveActivityLog(activityLog);
			activityLogMgrBD.updateUser(createdByUser);
		} catch (ApplicationCoreException e) {
			logger.error("Error in saving failed login activity log for " + loginId, e);
		}
		
		return AccountStatusEnum.getEnumFromValue(createdByUser.getStatus());
	}
	
	public void logLogout(long createdBy, String sessionId){
		ActivityLog activityLog = createActivityLog(WebAuthenticationAction.LOGOUT, createdBy, sessionId);
		try {
			new ActivityLogMgrBD().saveActivityLog(activityLog);
		} catch (ApplicationCoreException e) {
			logger.error("Error in saving logout activity log", e);
		}
	}
	
	public void logTimeout(long createdBy, String sessionId){
		ActivityLog activityLog = createActivityLog(WebAuthenticationAction.TIME_OUT, createdBy, sessionId);
		try {
			new ActivityLogMgrBD().saveActivityLog(activityLog);
		} catch (ApplicationCoreException e) {
			logger.error("Error in saving timeout activity log", e);
		}
	}
	
	public void logExceededMaxSessions(long createdBy, String sessionId){
		ActivityLog activityLog = createActivityLog(WebAuthenticationAction.EXCEED_SESSION, createdBy, sessionId);
		try {
			new ActivityLogMgrBD().saveActivityLog(activityLog);
		} catch (ApplicationCoreException e) {
			logger.error("Error in saving exceed max session activity log", e);
		}
	}
	
	private ActivityLog createActivityLog(WebAuthenticationAction action, long createdBy, String sessionId){
		ActivityLog activityLog = new ActivityLog();
		
		activityLog.setController(this.getClass().getName());
		activityLog.setAction(action.getDescription());
		activityLog.setActiveStatus(ActiveStatusEnum.YES.toString());
		activityLog.setDetails(sessionId);
		activityLog.setCreatedby(createdBy);
		
		return activityLog;
	}
	
	@SuppressWarnings("rawtypes")
	private long getActivityId(String transactionType) throws ApplicationCoreException{
		CriteriaIF criteria = new Criteria();
		CriterionIF[] defaultCriterion = {new Criterion(Activity.TXN_TYPE, RestrictionType.EQUAL, transactionType, true)};
		criteria.setCriterion(defaultCriterion);
		ActivityMgrBD activityMgrBD = new ActivityMgrBD();
		ResponseCRUD response = activityMgrBD.searchActivity(criteria);
		Activity activity = (Activity) response.getCrudResult();
		return activity.getId();
	}
	
	
	
	
	
}

































