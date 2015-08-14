package sg.com.fbs.core.businfra.facade;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ServiceLocator implements ApplicationContextAware,ServiceDefinitionIF{

	private static ApplicationContext applicationContext;
	
	@SuppressWarnings("static-access")
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		System.out.println("From ServiceLocator which implements ApplicationContextAware ::::::::::: Setting ApplicationContext :::::::");
		this.applicationContext = applicationContext;
	}

	public static Object getService(String serviceId){
		if(applicationContext==null){
			return ApplicationContextFactory.getApplicationContext().getBean(serviceId);
		}else{
			return applicationContext.getBean(serviceId);
		}
	}
	
}
