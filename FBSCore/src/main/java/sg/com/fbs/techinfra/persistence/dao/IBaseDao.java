package sg.com.fbs.techinfra.persistence.dao;

import java.util.Collection;
import java.util.List;

import sg.com.fbs.core.techinfra.persistence.exception.DataAccessObjectException;
import sg.com.fbs.model.system.persistence.query.CriteriaIF;
import sg.com.fbs.model.system.persistence.response.IResponseCRUD;



public interface IBaseDao {

   public Object insert(Object object) throws DataAccessObjectException;
   
   public Object update(Object object) throws DataAccessObjectException;	
   
   public Object delete(Object object) throws DataAccessObjectException;
   
   public Collection findAll(Class clz) throws DataAccessObjectException;
   
   public Object findByKey(String className, String keyName, String keyValue) throws DataAccessObjectException;
   
   public int getTotalCount(String className) throws DataAccessObjectException;
   
   public List findByProperties(String sql) throws DataAccessObjectException;
   
   public Object merge(Object object);
   
   public Object saveOrUpdate(Object object);
   
   public IResponseCRUD search(Class clz, CriteriaIF criteria) throws DataAccessObjectException;
   
   public void flush();
}
