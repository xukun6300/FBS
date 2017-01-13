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
		//For Oracle
		//Query query = getHibernateSession().createSQLQuery("SELECT " +sequenceName +".NEXTVAL FROM DUAL");
		
		//For DB2
		//Query query = getHibernateSession().createSQLQuery("SELECT " +sequenceName +".NEXTVAL FROM SYSIBM.SYSDUMMY1");
		
		//For MySql #SELECT nextval('ACCOUNT_SEQ')
		Query query = getHibernateSession().createSQLQuery("SELECT nextval('" +sequenceName +"')");
		
		Long key = new Long(query.uniqueResult().toString());
	    return key;
	}
}
