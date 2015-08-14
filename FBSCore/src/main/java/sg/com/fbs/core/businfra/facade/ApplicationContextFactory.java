package sg.com.fbs.core.businfra.facade;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContextFactory {

	private static final String filename= "spring-fbs-transactions.xml";
	
	private static ClassPathXmlApplicationContext ac;
	
	private static ClassPathXmlApplicationContext vc;
	
	public static ApplicationContext getApplicationContext(){
		if(ac==null){
			ac = new ClassPathXmlApplicationContext(filename);
		}
		return ac;
	}
	
	public static ApplicationContext getApplicationContext(String file){
		if(vc==null){
			vc = new ClassPathXmlApplicationContext(filename);
		}
		return vc;
	}
}
