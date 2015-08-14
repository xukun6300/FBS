package sg.com.fbs.techinfra.persistence.dao;

import org.hibernate.Session;

import sg.com.fbs.core.techinfra.persistence.HibernateUtil;

public abstract class AbstractBaseDao implements IBaseDao{
	
	public Session getSession() {
		Session session = HibernateUtil.currentSession();
		
		if(session==null){
			session = HibernateUtil.getSession();
		}
		
		return session;
	}
	
}
