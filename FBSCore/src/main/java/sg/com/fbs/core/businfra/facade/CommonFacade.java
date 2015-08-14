package sg.com.fbs.core.businfra.facade;


import sg.com.fbs.core.techinfra.exception.ApplicationCoreException;

public class CommonFacade implements CommonFacadeIF,ServiceDefinitionIF {

	public Object run() throws ApplicationCoreException{
		FacadeExecutor facadeExecutor = (FacadeExecutor) ServiceLocator.getService(FACADE_EXECUTOR);
		
		return facadeExecutor.execute(this, null);
	}
	
	public Object run(Object parameter) throws ApplicationCoreException{
		FacadeExecutor facadeExecutor = (FacadeExecutor) ServiceLocator.getService(FACADE_EXECUTOR);
		
		return facadeExecutor.execute(this, parameter);
	}
	
	public Object run(long parameter) throws ApplicationCoreException{
		FacadeExecutor facadeExecutor = (FacadeExecutor) ServiceLocator.getService(FACADE_EXECUTOR);
		
		return facadeExecutor.execute(this, new Long(parameter));
	}
	
	public Object run(Object obj, long parameter) throws ApplicationCoreException{
		FacadeExecutor facadeExecutor = (FacadeExecutor) ServiceLocator.getService(FACADE_EXECUTOR);
		
		return facadeExecutor.execute(this, obj, new Long(parameter));
	}
	
	public Object run(long param1, long param2) throws ApplicationCoreException{
		FacadeExecutor facadeExecutor = (FacadeExecutor) ServiceLocator.getService(FACADE_EXECUTOR);
		
		return facadeExecutor.execute(this, new Long(param1), new Long(param2));
	}
	
	public Object run(Object obj1, Object obj2) throws ApplicationCoreException{
		FacadeExecutor facadeExecutor = (FacadeExecutor) ServiceLocator.getService(FACADE_EXECUTOR);
		
		return facadeExecutor.execute(this, obj1, obj2);
	}
	
	public Object run(Object obj1, Object obj2, Object obj3) throws ApplicationCoreException{
		FacadeExecutor facadeExecutor = (FacadeExecutor) ServiceLocator.getService(FACADE_EXECUTOR);
		
		return facadeExecutor.execute(this, obj1, obj2, obj3);
	}
	
	public Object run(Object obj1, Object obj2, Object obj3, Object obj4) throws ApplicationCoreException{
		FacadeExecutor facadeExecutor = (FacadeExecutor) ServiceLocator.getService(FACADE_EXECUTOR);
		
		return facadeExecutor.execute(this, obj1, obj2, obj3, obj4);
	}
	
	public Object run(Object obj1, Object obj2, Object obj3, Object obj4, Object obj5) throws ApplicationCoreException{
		FacadeExecutor facadeExecutor = (FacadeExecutor) ServiceLocator.getService(FACADE_EXECUTOR);
		
		return facadeExecutor.execute(this, obj1, obj2, obj3, obj4, obj5);
	}
}
