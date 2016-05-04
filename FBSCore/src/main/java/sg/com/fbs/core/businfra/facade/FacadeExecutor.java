package sg.com.fbs.core.businfra.facade;

import java.lang.reflect.InvocationTargetException;
import java.util.Hashtable;
import java.util.Map;

import org.apache.commons.beanutils.MethodUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTransactionManager;

import sg.com.fbs.core.techinfra.exception.ApplicationCoreException;
import sg.com.fbs.core.techinfra.util.LoggerUtil;

public class FacadeExecutor {

	private Logger logger = Logger.getLogger(FacadeExecutor.class);
	
	@Autowired
	private HibernateTransactionManager transactionManager;
	
	private static Map<String,String> invalidMethods = new Hashtable<String, String>();
	
	static{
		invalidMethods.put("getMethodName", "invalid");
		invalidMethods.put("invokeJoinpoint","invalid");
		invalidMethods.put("execute", "invalid");
		invalidMethods.put("run", "invalid");
		invalidMethods.put("invoke", "invalid");
		
		
		invalidMethods.put("getMethodName","invalid");
		invalidMethods.put("execute","invalid");
		invalidMethods.put("executeNew","invalid");
		invalidMethods.put("executeBatch","invalid");
		invalidMethods.put("invoke","invalid");
		invalidMethods.put("invokeJoinpoint","invalid");
		invalidMethods.put("proceed","invalid");
		invalidMethods.put("invoke","invalid");
		invalidMethods.put("proceed","invalid");
		invalidMethods.put("intercept","invalid");
		invalidMethods.put("execute","invalid");
		invalidMethods.put("run","invalid");
		invalidMethods.put("runNew","invalid");
		invalidMethods.put("runBatch","invalid");
		invalidMethods.put("<init>","invalid");
		invalidMethods.put("setup","invalid");
		invalidMethods.put("init","invalid");
		invalidMethods.put("begin","invalid");
		invalidMethods.put("invoke0","invalid");
		invalidMethods.put("processActionPerform","invalid");
		invalidMethods.put("process","invalid");
		invalidMethods.put("doGet","invalid");
		invalidMethods.put("service","invalid");
		invalidMethods.put("service","invalid");
		invalidMethods.put("ready","invalid");
		invalidMethods.put("handleDiscrimination","invalid");
		invalidMethods.put("handleNewInformation","invalid");
		invalidMethods.put("ready","invalid");
		invalidMethods.put("sendToDiscriminators","invalid");
		invalidMethods.put("complete","invalid");
		invalidMethods.put("futureCompleted","invalid");
		invalidMethods.put("invokeCallback","invalid");
		invalidMethods.put("fireCompletionActions","invalid");
		invalidMethods.put("completed","invalid");
		invalidMethods.put("complete","invalid");
		invalidMethods.put("runEventProcessingLoop","invalid");
	}

	public HibernateTransactionManager getTransactionManager() {
		return transactionManager;
	}

	public void setTransactionManager(HibernateTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}
	
	public Object execute(CommonFacadeIF facade, Object parameter) throws ApplicationCoreException{
		String methodName = getMethodName();
		
		logger.info("["+facade.getClass().getName()+"]"+methodName);
		try {
			if (parameter == null) {
				Object[] emptyPara = null;

				return MethodUtils.invokeMethod(facade, methodName, emptyPara);
			} else {
				return MethodUtils.invokeMethod(facade, methodName, parameter);
			}
		}catch (InvocationTargetException e) {
			e.printStackTrace(); 
			LoggerUtil.logError(logger, e);
			//System.out.println(e.getCause().getMessage());
			Throwable t = e.getCause();
			if(t instanceof ApplicationCoreException){
				logger.debug ("Application Exception at Facade.execute : " + t.getMessage());
				throw (ApplicationCoreException)t;
			}
			throw new RuntimeException(e);
		}catch (Exception e) {
			
			LoggerUtil.logError(logger, e);
			throw new RuntimeException(e);
		}
		
	}
	
	
	public Object execute(CommonFacadeIF facade, Object parameter1, Object parameter2) throws ApplicationCoreException{
		String methodName = getMethodName();
		
		logger.info("["+facade.getClass().getName()+"]"+methodName);
		try {
			if (parameter1 == null && parameter2 == null) {
				Object[] emptyParams = null;

				return MethodUtils.invokeMethod(facade, methodName, emptyParams);

			} else {
				Object[] params = new Object[2];
				params[0] = parameter1;
				params[1] = parameter2;

				return MethodUtils.invokeMethod(facade, methodName, params);
			}
		} catch (InvocationTargetException e) {
			LoggerUtil.logError(logger, e);
			Throwable t = e.getCause();
			if(t instanceof ApplicationCoreException){
				logger.debug ("Application Exception at Facade.execute" + t.getMessage());
				throw (ApplicationCoreException)t;
			}
			throw new RuntimeException(t);

		} catch (Exception e) {
			LoggerUtil.logError(logger, e);
			throw new RuntimeException(e);
		}
	}
	
	
	public Object execute(CommonFacadeIF facade, Object parameter1, Object parameter2, Object parameter3) throws ApplicationCoreException{
		String methodName = getMethodName();
		
		logger.info("["+facade.getClass().getName()+"]"+methodName);
		try {
			if (parameter1 == null && parameter2 == null && parameter3==null) {
				Object[] emptyParams = null;

				return MethodUtils.invokeMethod(facade, methodName, emptyParams);

			} else {
				Object[] params = new Object[3];
				params[0] = parameter1;
				params[1] = parameter2;
				params[2] = parameter3;
				
				return MethodUtils.invokeMethod(facade, methodName, params);
			}
		} catch (InvocationTargetException e) {
			LoggerUtil.logError(logger, e);
			Throwable t = e.getCause();
			if(t instanceof ApplicationCoreException){
				logger.debug ("Application Exception at Facade.execute" + t.getMessage());
				throw (ApplicationCoreException)t;
			}
			throw new RuntimeException(t);

		} catch (Exception e) {
			LoggerUtil.logError(logger, e);
			throw new RuntimeException(e);
		}
	}
	
	public Object execute(CommonFacadeIF facade, Object parameter1, Object parameter2, Object parameter3, Object parameter4) throws ApplicationCoreException{
		String methodName = getMethodName();
		
		logger.info("["+facade.getClass().getName()+"]"+methodName);
		try {
			if (parameter1 == null && parameter2 == null && parameter3==null && parameter4==null) {
				Object[] emptyParams = null;

				return MethodUtils.invokeMethod(facade, methodName, emptyParams);

			} else {
				Object[] params = new Object[4];
				params[0] = parameter1;
				params[1] = parameter2;
				params[2] = parameter3;
				params[3] = parameter4;
				
				return MethodUtils.invokeMethod(facade, methodName, params);
			}
		} catch (InvocationTargetException e) {
			LoggerUtil.logError(logger, e);
			Throwable t = e.getCause();
			if(t instanceof ApplicationCoreException){
				logger.debug ("Application Exception at Facade.execute" + t.getMessage());
				throw (ApplicationCoreException)t;
			}
			throw new RuntimeException(t);

		} catch (Exception e) {
			LoggerUtil.logError(logger, e);
			throw new RuntimeException(e);
		}
	}
	
	public Object execute(CommonFacadeIF facade, Object parameter1, Object parameter2, Object parameter3, Object parameter4, Object parameter5) throws ApplicationCoreException{
		String methodName = getMethodName();
		
		logger.info("["+facade.getClass().getName()+"]"+methodName);
		try {
			if (parameter1 == null && parameter2 == null && parameter3==null && parameter4 == null && parameter5==null) {
				Object[] emptyParams = null;

				return MethodUtils.invokeMethod(facade, methodName, emptyParams);

			} else {
				Object[] params = new Object[3];
				params[0] = parameter1;
				params[1] = parameter2;
				params[2] = parameter3;
				params[3] = parameter4;
				params[4] = parameter5;
				
				return MethodUtils.invokeMethod(facade, methodName, params);
			}
		} catch (InvocationTargetException e) {
			LoggerUtil.logError(logger, e);
			Throwable t = e.getCause();
			if(t instanceof ApplicationCoreException){
				logger.debug ("Application Exception at Facade.execute" + t.getMessage());
				throw (ApplicationCoreException)t;
			}
			throw new RuntimeException(t);

		} catch (Exception e) {
			LoggerUtil.logError(logger, e);
			throw new RuntimeException(e);
		}
	}
	private String getMethodName(){
		Throwable t = new Throwable();
		StackTraceElement[] ste = t.getStackTrace();
		for (int i = 0; i < ste.length; i++) {
			String currentMethod = ste[i].getMethodName();
			
			if(invalidMethods.get(currentMethod)==null){
				return currentMethod;
			}
		}
		
		throw new RuntimeException("Error in getting Method Name");
	}
}
