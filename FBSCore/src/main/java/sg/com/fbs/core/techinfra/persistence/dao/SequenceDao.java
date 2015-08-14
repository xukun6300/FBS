package sg.com.fbs.core.techinfra.persistence.dao;

import org.hibernate.Query;
import org.hibernate.Session;

import sg.com.fbs.core.techinfra.persistence.HibernateUtil;

public class SequenceDao {
	
	private Session getHibernateSession(){
		
		Session session = HibernateUtil.currentSession();
		if(session==null){
			session = HibernateUtil.getSession();
		}
		return session;
	}
	
	public Long getNextKey(String sequenceName){
		
		Query query = getHibernateSession().createSQLQuery("select " +sequenceName +".nextval from dual");
		Long key = new Long(query.uniqueResult().toString());
	    return key;
	}
}
