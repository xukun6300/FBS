package sg.com.fbs.core.businfra.datalog;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map.Entry;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import sg.com.fbs.model.business.pojo.BasePojo;
import sg.com.fbs.model.system.activity.ActivityLog;
import sg.com.fbs.model.system.datalog.DataLog;
import sg.com.fbs.techinfra.persistence.dao.BaseDao;


/**
 * @Author Frank Xu $
 * @Created 5:22:34 pm 28 Jul, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public class AuditLogInterceptor extends EmptyInterceptor{

	private static final long serialVersionUID = 1849009150731417106L;

	@Override
	public void onDelete(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
		if(entity instanceof BasePojo && !(entity instanceof DataLog) && !(entity instanceof ActivityLog)){
			final BasePojo log = (BasePojo) entity;
			String data = "";
			for (final Entry<String, String> entry: log.getAllProperties().entrySet()) {
				appendPair(data, entry.getKey(), entry.getValue());
			}
			
			DataLog newLog = new DataLog(null, log.getClass().getName(), log.getId(), data, null);
			newLog.setAuditTransaction(AuditLogTransactionTypeEnum.DELETE.name());
			
			new BaseDao().saveOrUpdate(newLog);
		}
	}
	
	
	@Override
	public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) {
		
		audit: if(entity instanceof BasePojo &&!(entity instanceof DataLog) && !(entity instanceof ActivityLog)){
			if(previousState==null||currentState==null||propertyNames==null){
			    break audit;
			}
			
			BaseDao dao = new BaseDao();
			final BasePojo log = (BasePojo) entity;
			
			if (log.getId() != 0L) {
				String oldChanges = "";
				String newChanges = "";
				
				for (int index = 0; index < currentState.length; index++) {
					Object oldStateValue = previousState[index];
					Object newStateValue = currentState[index];
					String propertyName = propertyNames[index];
					
					if("versionLock".equals(propertyName)){
						continue;
					}
					
					if(newStateValue instanceof Collection<?>){
						final String oldCollection = retrieveCollectionInformation((Collection<?>)oldStateValue);
						final String newCollection = retrieveCollectionInformation((Collection<?>)newStateValue);
						if(!oldCollection.equals(newCollection)){
							oldChanges = appendPair(oldChanges, propertyName, oldCollection);
							newChanges = appendPair(newChanges, propertyName, newCollection);
						}
						
					}else {
						if(newStateValue==null && oldStateValue ==null){
							continue;   //??
						}else if (newStateValue==null && oldStateValue !=null) {
							
							oldChanges = appendPair(oldChanges, propertyName, String.valueOf(oldStateValue));
						}else if (newStateValue!=null && oldStateValue ==null) {
							
							newChanges = appendPair(newChanges, propertyName, String.valueOf(newStateValue));
						}else {
							if(oldStateValue instanceof BasePojo){
								continue;
							}
							
							if(!newStateValue.equals(oldStateValue)){
								oldChanges = appendPair(oldChanges, propertyName, String.valueOf(oldStateValue));
								newChanges = appendPair(newChanges, propertyName, String.valueOf(newStateValue));
							}
						}
					}
				}
				
				if(oldChanges.isEmpty() && newChanges.isEmpty()){
					break audit;
				}
				
				DataLog newLog = new DataLog(null, log.getClass().getName(), log.getId(), oldChanges, newChanges);
				newLog.setAuditTransaction(AuditLogTransactionTypeEnum.UPDATE.name());
				dao.saveOrUpdate(newLog);
			}
		}
	  return false;
	}
	
	@Override
	public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
		if(entity instanceof BasePojo && !(entity instanceof DataLog) &&!(entity instanceof ActivityLog)){
			final BasePojo log = (BasePojo) entity;
			String data = "";
			
			for (final Entry<String, String> entry : log.getAllProperties().entrySet()) {
				data = appendPair(data, entry.getKey(), entry.getValue());
			}
			
			final DataLog newLog = new DataLog(null, log.getClass().getName(), log.getId(), null, data);
			newLog.setAuditTransaction(AuditLogTransactionTypeEnum.INSERT.name());
			new BaseDao().saveOrUpdate(newLog);
		}	
		return false;
	}
	
	
	private String appendPair(final String initialString, final String key, final String value) {
		String returnString;

		if (initialString.isEmpty()) {
			returnString = initialString;
		} else {
			returnString = initialString + '|';
		}
		returnString += key + ':' + value;

		return returnString;
	}
	
	private String retrieveCollectionInformation(final Collection<?> collection){
		String returnString = "{";
		
		if(collection!=null){
			for (Object obj : collection) {
				if(obj==null) continue;
				
				if(returnString.length()==1){
					returnString = returnString +obj.toString();
				}else {
					returnString = returnString + ',' +obj.toString();
				}
				//??
				//returnString += obj.toString();		
			}
		}
				
		return returnString +'}';
	}
	

	
}

























