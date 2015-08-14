package sg.com.fbs.core.techinfra.persistence;

import java.lang.reflect.Field;
import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.internal.CriteriaImpl;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil {

	public static final ThreadLocal<Session> session = new ThreadLocal<Session>();
	

	private static SessionFactory sessionFactory;
	
	private static ServiceRegistry serviceRegistry;
	
	private static Configuration configuration;
	
	public synchronized static Configuration getConfiguration(){
		if(configuration==null){
			configuration=new Configuration();
			configuration.configure();
			configuration.buildMappings();
		}
		return configuration;
	}
	
	
	private synchronized static void initSessionFactory(){
		getConfiguration();
		serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
		if(sessionFactory==null){
			System.out.println("Failed to bind spring SessionFactory, Create Hibernate Session Factory from hibernate.cfg.xml...");
			
			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		}
		
	}
	
	public static void setSessionFactory(SessionFactory sessionFactory){
	    HibernateUtil.sessionFactory = sessionFactory;	
	}
	
	public static SessionFactory getSessionFactory(){
		if(sessionFactory==null){
			initSessionFactory();
		}
		return sessionFactory;
	}
	
	/**
	 * Since this session object belongs to the hibernate context, we dont need to close it. 
	 * Once the session factory is closed, this session object gets closed.
	 * in hibernate.cfg.xml configure "hibernate.current_session_context_class" for this getCurrentSession()
	 * 
	 * is this thread safe???? all the threads shares one session??
	 */
	public static Session getSession(){
		Session s = getSessionFactory().getCurrentSession();
		s.setFlushMode(FlushMode.COMMIT);
		return s;
	}
	
	public static Session currentSession() {
		Session s = session.get();
		if(s==null){
			s=getSession();
		}
		s.setFlushMode(FlushMode.COMMIT);
		return s;
	}
	
	public static void setSession(Session s) {
		s.setFlushMode(FlushMode.COMMIT);
		session.set(s);
	}
	
	
	public static Criteria removeOrders(Criteria criteria){
		CriteriaImpl impl = (CriteriaImpl) criteria;
		
		try {   // orderEntries is a private field, here make it accessible 
			Field field = CriteriaImpl.class.getDeclaredField("orderEntries");
			field.setAccessible(true);
			field.set(impl, new ArrayList());  //set the object which this field belongs to, and the value of this field
			return impl;
		} catch (Exception e) {
			throw new InternalError(" Runtime Exception impossibility can't throw ");
		} 
		
	}
	
	
}


























