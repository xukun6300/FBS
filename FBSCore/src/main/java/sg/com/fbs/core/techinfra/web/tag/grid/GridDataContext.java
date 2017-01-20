package sg.com.fbs.core.techinfra.web.tag.grid;


import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;

import sg.com.fbs.model.system.persistence.query.CriteriaIF;
import sg.com.fbs.model.system.persistence.query.CriterionIF;
import sg.com.fbs.model.system.persistence.query.OrderIF;
import sg.com.fbs.model.system.persistence.response.ResponseCRUD;


/**
 * @Author Frank Xu $
 * @Created 5:19:55 pm 30 Jun, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public class GridDataContext {

	private Logger logger = Logger.getLogger(GridDataContext.class);
	
	private static final String ERROR_KEY = "ERROR KEY";

	private static final String FROM_DATE = "fromDate";

	private static final String TO_DATE = "toDate";

	private static final String TRUE = "true";

	private static final String FALSE = "false";

	private static final String IS_FETCH_ALL = "isFetchAll";

	private ResponseCRUD result;
	
	public ResponseCRUD getResult() {
		return result;
	}
	
	private long currentIndex;
	
	public long getCurrentIndex() {
		return currentIndex;
	}
	public void setCurrentIndex(long currentIndex) {
		this.currentIndex = currentIndex;
	}
	
	private Iterator iterator;
	
	private Object currentItem;
	
	public Object getCurrentItem() {
		return currentItem;
	}
	public void setCurrentItem(Object currentItem) {
		this.currentItem = currentItem;
	}
	
	private Object previousItem;
	
	private long iterationStart;
	
	public long getIterationStart() {
		return iterationStart;
	}
	
	public void setIterationStart(long iterationStart) {
		this.iterationStart = iterationStart;
	}
	
	private long iterationEnd;
	
	public long getIterationEnd() {
		return iterationEnd;
	}
	public void setIterationEnd(long iterationEnd) {
		this.iterationEnd = iterationEnd;
	}
	
	public boolean isFetchAll;
	
	private String resultQualifierName;
	
	public void setResultQualifierName(String resultQualifierName) {
		this.resultQualifierName = resultQualifierName;
	}
	public String getResultQualifierName() {
		return resultQualifierName;
	}
	
	public GridDataContext(ResponseCRUD queryResult){
		if(queryResult!=null){
			this.result = queryResult;
			if(result.getQueryResult()!=null){
				this.iterator = result.getQueryResult().iterator();
				this.iterationStart = 0;
				this.iterationEnd = result.getQueryResult().size();
				this.currentIndex = iterationStart;
				this.isFetchAll = result.getCriteria().isFetchAll();
			}
		}
		initDataContext();
	}
	
	public GridDataContext(Collection internalData){
		if (internalData == null) {
			throw new IllegalArgumentException("Interal data collection is null");
		}
		this.iterator = internalData.iterator();
		this.iterationStart = 0;
		this.iterationEnd = internalData.size();
	}
	
	protected void initDataContext(){
		//initIterator(); 
	    setIteratorRange();
	}
	
	private void initIterator(){
		this.iterator = result.getQueryResult().iterator();
	}
	
	private void setIteratorRange(){
		long startIndex = (getCurrentPage() - 1) * getPageSize();
		iterationStart = 0;
		iterationEnd = startIndex + getPageSize();

		currentIndex = iterationStart;
		if (iterationEnd > getTotalRecordFound()) {
			iterationEnd = getTotalRecordFound();
		}
		for (int i = 0; i < iterationStart; i++) {
			iterator.next();
		}
	}
	
	public long getPageSize(){
		if(result.getCriteria().isFetchAll()){
			return result.getTotalRecords();
		}
		
		long pageSize = result.getCriteria().getPageSize();
		if (pageSize < 1) {
			throw new IllegalArgumentException("Illegal Page Size");
		}
		return pageSize;
	}
	
	public long getCurrentPage(){
		long currentPage= 0L;
		CriteriaIF criteria = result.getCriteria();
		if(criteria!=null){
		     currentPage = criteria.getRequestedPage();
		}
		if (currentPage<0){
			throw new IllegalArgumentException("Illegal Current Page");
		}
		if(currentPage > result.getTotalPages()){
			currentPage = result.getTotalPages();
		}
		return currentPage;
	}
	
	public long getTotalRecordFound(){
		return result.getTotalRecords();
	}
	
	/**
	 * Response query result is a collection of Object array.
	 */
	public Object getActualValueByIndex(int index){
		Object[] currentItems = (Object[]) currentItem;
		return currentItems[index];
	}
	
	public long getTotalPage(){
		return result.getTotalPages();
	}
	
	public boolean isMaxRecordRetreivedExceeded(){
		return false;
	}
	
	public boolean hasNext(){
		if(currentIndex < iterationEnd && iterator.hasNext()){
			return true;
		}
		return false;
	}
	
	public Object next(){
		if(currentItem!=null){
			previousItem = currentItem;
		}
		currentItem = iterator.next();
		currentIndex++;
		
		return currentItem;
	}
	
	// return the property value as string
	public String getValue(String key){
		try {
			return BeanUtils.getProperty(currentItem, key);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return "";
		} 
	}
	
	public String getPreviousValue(String key){
		try {
			return BeanUtils.getProperty(previousItem, key);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return "";
		} 
	}
	
	// without type conversions, return orignal type
	public Object getActualValue(String key){
		try {
			return PropertyUtils.getProperty(currentItem, key);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	
	public Object getActualPreviousValue(String key){
		try {
			if(previousItem!=null){
				return PropertyUtils.getProperty(previousItem, key);
			}else {
				return null;
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	
	public Object getObject(String methodName){
		try {
			return PropertyUtils.getNestedProperty(currentItem, methodName);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	
	public Map<String, String> getOrderURIMap(){
		Map<String, String> uriMap = new HashMap<String, String>();
		OrderIF[] orders = result.getCriteria().getOrder();
		
		for (int i = 0; i < orders.length; i++) {
			//overwrite?
			uriMap.put("sortingProperty", orders[i].getProperty());
			uriMap.put("isAscending", orders[i].getIsAscending() ? TRUE : FALSE);
		}
		uriMap.put(IS_FETCH_ALL, result.getCriteria().isFetchAll() ? TRUE : FALSE);
		return uriMap;
 	}
	
	public Map<String, String> getOrderURIMap(String sortingProperty, boolean isAscending){
		Map<String, String> uriMap = new HashMap<String, String>();
		OrderIF[] orders = result.getCriteria().getOrder();
		//doesn't make sence?
		for (int i = 0; i < orders.length; i++) {
			//??
			uriMap.put("sortingProperty", sortingProperty);
			uriMap.put("isAscending", isAscending ? TRUE : FALSE);
		}
		uriMap.put(IS_FETCH_ALL, result.getCriteria().isFetchAll() ? TRUE : FALSE);
		return uriMap;
 	}
	
	
	public Map<String, Object> getSearchCriteriaMap(){
		Map<String, Object> map = new HashMap<String, Object>();
		CriterionIF[] criterions = result.getCriteria().getCriterion();
		
		if(criterions==null || criterions.length<1){
			return map;
		}
		
		for (int i = 0; i < criterions.length; i++) {
			if(criterions[i]==null){
				throw new IllegalArgumentException("Number of criteria set is less than Critieria length Spcificed.Check your formBean.getSearchCriteria()");
			}
			
			if(criterions[i].hasHighSearchValue()){
				map.put(criterions[i].getHighPropertyName(), criterions[i].getHighSearchValue());
			}
			
			if(criterions[i].hasShortPropertyName()){
				map.put(criterions[i].getShortPropertyName(), criterions[i].getSearchValue());
			}else {
				if(criterions[i] instanceof ConditionRecordFieldCriterion){
					
					ConditionRecordFieldCriterion crfc = (ConditionRecordFieldCriterion) criterions[i];
					
					map.put(crfc.getConditionFieldConfigProperty()+"["+crfc.getIndex()+"]", String.valueOf(crfc.getConditionFieldConfigID()));
					map.put(crfc.getFieldValueProperty()+"["+crfc.getIndex()+"]", String.valueOf(crfc.getValue()));
					map.put(crfc.getFieldDisplayProperty()+"["+crfc.getIndex()+"]", String.valueOf(crfc.getDisplay()));
				}else if (criterions[i].isSearchValueDataTypeDate()) {
					
					if(criterions[i].lessThanOrEqual()){
						map.put(TO_DATE, criterions[i].getSearchValue());
					}else if (criterions[i].greaterThanOrEqual()) {
						map.put(FROM_DATE, criterions[i].getSearchValue());
					}
				}else {
					map.put(criterions[i].getPropertyName(), criterions[i].getSearchValue());
				}
				
				
			}
		}
		
		//put all manual filers
		//put all dynamic filers		
		return map;
	}
	
	public Map<String, Object> getSearchCriteria(){
		return getSearchCriteriaMap();
	}
}






































































