package sg.com.fbs.techinfra.persistence.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.hibernate.sql.JoinType;

import sg.com.fbs.core.techinfra.persistence.HibernateUtil;
import sg.com.fbs.core.techinfra.persistence.exception.DataAccessObjectException;
import sg.com.fbs.core.techinfra.util.StringUtil;
import sg.com.fbs.model.business.pojo.BasePojo;
import sg.com.fbs.model.domain.enumeration.ActiveStatusEnum;
import sg.com.fbs.model.system.persistence.query.CriteriaIF;
import sg.com.fbs.model.system.persistence.query.CriterionIF;
import sg.com.fbs.model.system.persistence.query.LogProjection;
import sg.com.fbs.model.system.persistence.query.OrderIF;
import sg.com.fbs.model.system.persistence.query.PercentageProjection;
import sg.com.fbs.model.system.persistence.query.PercentagePropertyProjection;
import sg.com.fbs.model.system.persistence.query.ProjectionIF;
import sg.com.fbs.model.system.persistence.query.RankProjection;
import sg.com.fbs.model.system.persistence.query.WeightedAverageProjection;
import sg.com.fbs.model.system.persistence.response.IResponseCRUD;
import sg.com.fbs.model.system.persistence.response.ResponseCRUD;

public class BaseDao extends AbstractBaseDao{

	private static Logger logger = Logger.getLogger(BaseDao.class);
	
	@Override
	public Object insert(Object object) throws DataAccessObjectException {
		logger.debug("----------insert dao----------");
		getSession().save(object);
		return object;
	}

	public Object insert(String entityName, Object object) throws DataAccessObjectException{
		logger.debug("----------insert dao----------");
		getSession().save(entityName, object);
		return object;
	}
	
	/**
	 * Otherwise this would fall over with an OutOfMemoryException somewhere around the 50,000th row.
	 * That is because Hibernate caches all the newly inserted Customer instances in the session-level cache. 
	 */
	public Object[] insertBatch(Object[] objs) {
		for (int i = 0; i < objs.length; i++) {
			getSession().save(objs[i]);

			if (i != 0 && i % 50 == 0) {
				getSession().flush();
				getSession().clear();
			}
		}
		return objs;
	}
	
	//return a new track object or not????
	public Object Update(String entityName, Object object) throws DataAccessObjectException{
		Object obj = null;
		
		if(object instanceof BasePojo){
			//BasePojoIF pojo = (BasePojoIF) object;
			obj = getSession().merge(entityName, object);
		}	
		return obj;
	}
	
	@Override
	public Object update(Object object) throws DataAccessObjectException{
		logger.debug("----------update dao----------");
		Object obj=null;
		if(object instanceof BasePojo){
			//merge will return a new object copy which is tracked in persistent context.
			obj = getSession().merge(object);
		}
		return obj;
	}
	
	
	public Object[] updateBatch(Object[] objects){
		
		for (int i = 0; i < objects.length; i++) {
			getSession().merge(objects[i]);
			
			if(i!=0 && i%50==0){
				getSession().flush();
				getSession().clear();
			}
		}
		return objects;
	}

	
	@Override
	public Object delete(Object object) {
		getSession().delete(object);
		return object;
	}
	
	public Object delete(String entityName, Object object){
		getSession().delete(entityName, object);
		return object;
	}
	
	public Object softDelete(Object object) throws DataAccessObjectException{
		if(object instanceof BasePojo){
			BasePojo pojo = (BasePojo) object;
			pojo.setActiveStatus(ActiveStatusEnum.NO.toString());
			return update(object);
		}
		return object;
	}

	public Object softDelete(String entityName, Object object) throws DataAccessObjectException{
		if(object instanceof BasePojo){
			BasePojo pojo = (BasePojo) object;
			pojo.setActiveStatus(ActiveStatusEnum.NO.toString());
			return Update(entityName, object);
		}
		return object;
	}
	@Override
	public Collection findAll(Class clz) {
		logger.debug("----------findAll dao----------");
		return findAll(clz, false);
	}
	
	public Collection findAll(Class clz, boolean cacheable){
		logger.debug("----------findAll dao----------");
		org.hibernate.Criteria criteria = getSession().createCriteria(clz).setCacheable(cacheable);
		return criteria.list();
	}

	public Collection findAll(Class clz, String keyName, String orderProperty){
		Criteria criteria = getSession().createCriteria(clz);
		if(orderProperty==null||orderProperty.equals("ASC")){
			criteria.addOrder(Order.asc(keyName));
		}else {
			criteria.addOrder(Order.desc(keyName));
		}
		List list = criteria.list();
		return list;
	}
	
	@Override
	public void flush() {
		getSession().flush();
	}

	@Override
	public List findByKey(String className, String keyName, String keyValue) throws DataAccessObjectException {
		Criteria criteria = getSession().createCriteria(className, "modelClass");
		criteria.add(Restrictions.eq("keyName", keyValue));
		List list = criteria.list();
		return list;
	}

	@Override
	public int getTotalCount(String className) throws DataAccessObjectException {
		Criteria criteria = getSession().createCriteria(className);
		List list = criteria.list();
		return list.size();
	}

	@Override
	public List findByProperties(String sql) throws DataAccessObjectException {
		SQLQuery sqlQuery = getSession().createSQLQuery(sql);
		List list = sqlQuery.list();
		return list;
	}

	@Override
	public Object merge(Object object) {
		long id = ((BasePojo)object).getId();
		getSession().merge(object);
		return object;
	}
	
	public Object merge(String entityName, Object object) {
		long id = ((BasePojo)object).getId();
		getSession().merge(entityName, object);
		return object;
	}

	@Override
	public Object saveOrUpdate(Object object) {
		if(object instanceof BasePojo){
			getSession().saveOrUpdate(object);
		}
		return object;
	}

	public Object saveOrUpdate(String entityName, Object object) {
		if(object instanceof BasePojo){
			getSession().saveOrUpdate(entityName, object);
		}
		return object;
	}
	
	@Override
	public IResponseCRUD search(Class clz, CriteriaIF criteria) throws DataAccessObjectException {
		return search(clz, criteria, false);
	}
	
	
	public IResponseCRUD search(Class clz, CriteriaIF criteria, boolean cacheable){
		
		IResponseCRUD response = null;
		try {
			if (criteria.getProjection() != null && criteria.getProjection().length > 0) {
				response = projection(clz, criteria);
			} else {
				response = doSearch(clz, criteria, cacheable);
			}

		} catch (DataAccessObjectException e) {
			logger.error(e.getMessage());
		}
		
		return response;
	}
	
	public IResponseCRUD search(String entityName, CriteriaIF criteria){
		return search(entityName, criteria, false);
	}
	
	public IResponseCRUD search(String entityName, CriteriaIF criteria, boolean cacheable){
		IResponseCRUD response = null;
		try {
			if (criteria.getProjection() != null && criteria.getProjection().length > 0) {
				response = projection(entityName, criteria);
			} else {
				response = doSearch(entityName, criteria, cacheable);
			}
		} catch (DataAccessObjectException e) {
			logger.error(e.getMessage());
		}
		return response;
	}
	
	public IResponseCRUD projectionWithPaging(Class clz, CriteriaIF criteria) {

		return projection(clz, criteria, true);
	}

	public IResponseCRUD projectionWithPaging(String entityName, CriteriaIF criteria) {

		return projection(entityName, criteria, true);
	}
	
	public IResponseCRUD projection(String entityName, CriteriaIF criteria){
		return projection(entityName, criteria, false);
	}
	
	public IResponseCRUD projection(Class clz, CriteriaIF criteria){
		return projection(clz, criteria, false);
	}
	
    public IResponseCRUD projection(String entityName, CriteriaIF criteria, boolean paging){
    	Criteria hbmCriteria = HibernateUtil.currentSession().createCriteria(entityName);
		return projection(hbmCriteria, criteria, paging);
	}
	
	public IResponseCRUD projection(Class clz, CriteriaIF criteria, boolean paging){
		Criteria hbmCriteria = HibernateUtil.currentSession().createCriteria(clz);
		return projection(hbmCriteria, criteria, paging);
	}
	
	public IResponseCRUD projection(Criteria hbmCriteria, CriteriaIF fbsCriteria, boolean paging){
		
		if(fbsCriteria==null){
			throw new IllegalArgumentException("criteria is null");
		}
		
		IResponseCRUD response = new ResponseCRUD();
		
		Hashtable aliasList = new Hashtable();
		
		//??
		if(!paging && fbsCriteria.isFetchAll()){
			fbsCriteria.setFetchAll(true);
		}else {
			hbmCriteria.setFirstResult((fbsCriteria.getRequestedPage()-1)*fbsCriteria.getPageSize());
			hbmCriteria.setMaxResults(fbsCriteria.getPageSize());
		}
		
		response.setCriteria(fbsCriteria);
		
		Hashtable criteriaList = new Hashtable();
		
		CriterionIF[] criterions = fbsCriteria.getCriterion();
		
		for (int i = 0; i < criterions.length; i++) {
			CriterionIF criterion = criterions[i];
			
			String propertyName = criterion.getPropertyName();
			Object searchValue = criterion.getSearchValue();
			
			if(!criterion.excludeNull() && !criterion.isNull()){
				if(searchValue == null || searchValue.toString().trim().equals("")){
					if(!criterion.hasSearchValues()){
						continue;
					}
				}
			}
			
			
			Criteria curCriteria = hbmCriteria;
			propertyName = createAlias(hbmCriteria, aliasList, propertyName);
			
			if(!(searchValue instanceof String)||criterion.equal()){
				criterion.setExactSearch(true);
			}
			
			if(criterion.excludeNull()){
				curCriteria.add(Restrictions.isNotNull(propertyName));
				continue;
			}
			
			MatchMode matchMode = null;
			if(criterion.isExactSearch()){
				matchMode = MatchMode.EXACT;
			}else {
				matchMode = MatchMode.ANYWHERE;
			}
			
			//add CategoryCriterionIF here
			
			if(criterion.isSearchStartWith()){
				curCriteria.add(Restrictions.like(propertyName, (String) searchValue + "%"));
			}else if (criterion.hasHighSearchValue()) {
				curCriteria.add(Restrictions.between(propertyName, searchValue, criterion.getHighSearchValue()));
			}else if (criterion.lessThanOrEqual()) {
				curCriteria.add(Restrictions.le(propertyName, searchValue));
			}else if (criterion.less()) {
				curCriteria.add(Restrictions.lt(propertyName, searchValue));
			}else if (criterion.greater()) {
				curCriteria.add(Restrictions.gt(propertyName, searchValue));
			}else if (criterion.in()) {
				curCriteria.add(Restrictions.in(propertyName, (Object[])searchValue));
			}else if (criterion.notEqual()) {
				constructMultipleRestriction(curCriteria, criterion, propertyName, searchValue);
			}else if (criterion.greaterThanOrEqual()) {
				curCriteria.add(Restrictions.ge(propertyName, searchValue));
			}else if (criterion.isExactSearch()) {
				constructMultipleRestriction(curCriteria, criterion, propertyName, searchValue);
			}else if (criterion.isNull()) {
				curCriteria.add(Restrictions.isNull(propertyName));
			}else if (criterion.isCaseSensitive()) {
				curCriteria.add(Restrictions.like(propertyName, (String)searchValue, matchMode));
			}else if (!criterion.isCaseSensitive()) {
				curCriteria.add(Restrictions.ilike(propertyName, (String)searchValue, matchMode));
			}
			
		}
		
		addProjection(hbmCriteria, criteriaList, aliasList, fbsCriteria);
		OrderIF[] orders = fbsCriteria.getOrder();
		for (int i = 0; i < orders.length; i++) {
			OrderIF order = orders[i];
			if(order.isAscending()){
				hbmCriteria.addOrder(Order.asc(createAlias(hbmCriteria, aliasList, order.getProperty())));
			}else {
				hbmCriteria.addOrder(Order.desc(createAlias(hbmCriteria, aliasList, order.getProperty())));
			}
		}
		
		Collection result = hbmCriteria.list();
		
		result = handelRankProjection(result, fbsCriteria);
		
		response.setQueryResult(result);
		
		if(fbsCriteria.isFetchAll()){
			response.setTotalRecords(result.size());
			response.setTotalPages(1);
		}else {
			hbmCriteria.setFirstResult(0);
			hbmCriteria = HibernateUtil.removeOrders(hbmCriteria);
			
			int totalRec = ((Long)hbmCriteria.setProjection(Projections.rowCount()).uniqueResult()).intValue(); 
			response.setTotalRecords(totalRec);
			response.setTotalPages(totalRec / fbsCriteria.getPageSize() + (totalRec % fbsCriteria.getPageSize()) > 0 ? 1 : 0);
		}
		
		return response;
	}
	
	
	private Collection handelRankProjection(Collection result, CriteriaIF criteria){
	
		RankProjection rankProjection = null;
		ProjectionIF[] projections = criteria.getProjection();
		
		int position = -1;
		
		for (int i = 0; i < projections.length; i++) {
			ProjectionIF projection = projections[i];
			if(projection.isType(ProjectionIF.RANK)){
				rankProjection = (RankProjection) projection;
				position = 1;
				break;
			}
		}
		
		if(rankProjection==null){
			return result;
		}
		
		result = filerRankProjection((List)result, rankProjection.getMaxResult(), position);
		return result;
	}
	
	private Collection filerRankProjection(List res, int maxSize, int position){
		
		List newResult = new ArrayList();
		
		for (Iterator iterator = res.iterator(); iterator.hasNext();) {
			Object[] temp = (Object[]) iterator.next();
			if (maxSize == 0) {
				newResult.add(temp);
			} else if (Integer.valueOf(String.valueOf(temp[position])).intValue() <= maxSize) {
				newResult.add(temp);
			}
		}
		
		return newResult;
	}
	
	protected void addProjection(Criteria rootCriteria, Hashtable criteriaList, Hashtable aliasList, CriteriaIF criteria ){
		ProjectionList projectionList = Projections.projectionList();
		ProjectionIF[] projections = criteria.getProjection();
		
		for (int i = 0; i < projections.length; i++) {
			ProjectionIF projection = projections[i];
			
			Projection p = getProjection(projection, rootCriteria, aliasList);
			
			if(p!=null){
				projectionList.add(p);
			}
		}
		rootCriteria.setProjection(projectionList);
	}
	
	
	private Projection getProjection(ProjectionIF projection, Criteria rootCriteria, Hashtable aliasList){
		
		Projection p = null;
		String propertyName = projection.getProperty();
		
		if(!(projection instanceof PercentageProjection) && !(projection instanceof LogProjection)){
			if(propertyName == null){
				throw new IllegalArgumentException("Proerty is null in projection");
			}
			
			propertyName = createAlias(rootCriteria, aliasList, propertyName);
		}
		
		
		if (projection.isMin()) {
			p = Projections.min(propertyName);
		} else if (projection.isMax()) {
			p = Projections.max(propertyName);
		} else if (projection.isAverage()) {
			p = Projections.avg(propertyName);
		} else if (projection.isCount()) {
			p = Projections.count(propertyName);
		} else if (projection.isWeightedAverage()) {
			if(projection.getWithProperty() == null || projection.getWithProperty().equals("")){
				throw new IllegalArgumentException("Denominator Property not specifed for Weighted Avg Projection");
			}
			
			String withProperty = createAlias(rootCriteria, aliasList, projection.getWithProperty());
			p =new WeightedAverageProjection(propertyName, withProperty);
			
		} else if (projection.isType(ProjectionIF.PERCENTAGE)) {

			if(projection instanceof PercentageProjection){
				
				PercentageProjection percProj = (PercentageProjection) projection;
				Projection tempProj = getProjection(percProj.getProj(), rootCriteria, aliasList);
				Projection tempDenoProj = getProjection(percProj.getDenoProj(), rootCriteria, aliasList);
				
				p= new PercentagePropertyProjection(tempProj, tempDenoProj);
				
			}else if (!StringUtil.isEmptyString(projection.getWithProperty())) {
				String withProperty = createAlias(rootCriteria, aliasList, projection.getWithProperty());
				
				p = new PercentagePropertyProjection(propertyName, withProperty);
			}else {
				throw new IllegalArgumentException("Denominator Property not specifed for Percentage Projection");
			}
			
		} else if (projection.isType(ProjectionIF.LOG)) {
             //
		} else if (projection.isSum()){
			p = Projections.sum(propertyName);
		} else if (projection.isGroup()) {
			p = Projections.groupProperty(propertyName);
		} else if (projection.isSelectProperty()) {
			p = Projections.property(propertyName);
		} else if (projection.isType(ProjectionIF.GROUP_BY_MONTH)) {
			//
		} else if (projection.isType(ProjectionIF.GROUP_BY_WEEK)) {
			//
		} else if (projection.isType(ProjectionIF.GROUP_BY_QUARTER)) {
			//
		} else if (projection.isType(ProjectionIF.GROUP_BY_YEAR)) {
			//
		} else if (projection.isType(ProjectionIF.RANK)) {
			RankProjection rankProjection = (RankProjection) projection;
			
			if(rankProjection.getProj()!=null){
				Projection tempProj = getProjection(rankProjection.getProj(), rootCriteria, aliasList);
				//p = new 
			}else {
				
			}
			
		} else if (projection.isType(ProjectionIF.DISTINCT)) {
			p = Projections.distinct(Projections.property(propertyName));
		}else {
			throw new IllegalArgumentException("projection with type [" + projection.getType() + "] not supported");
		}
		
		return p;
	}
	

	private ResponseCRUD doSearch(Class clz, CriteriaIF criteria, boolean cacheable) throws DataAccessObjectException{
		
		try {
			
			Object defaultObject = clz.newInstance();
			Criteria hbmCriteria = getSession().createCriteria(clz).setCacheable(cacheable);
			return doSearch(criteria, hbmCriteria, defaultObject);
			
		} catch (InstantiationException e) {
			throw new DataAccessObjectException(e.getMessage());
		} catch (IllegalAccessException e) {
		    throw new DataAccessObjectException(e.getMessage());
		}

	}

	private ResponseCRUD doSearch(String entityName, CriteriaIF criteria, boolean cacheable) throws DataAccessObjectException{
		
		Criteria c = getSession().createCriteria(entityName).setCacheable(cacheable);
		
		return doSearch(criteria, c, null);
	}
	
	private ResponseCRUD doSearch(CriteriaIF fbsCriteria, Criteria hbmCriteria, Object defaultObject) throws DataAccessObjectException{
		
		ResponseCRUD result = new ResponseCRUD();
		result.setCriteria(fbsCriteria);
		
		//hibernate Criteria list
		Hashtable<String, Criteria> criteriaList = new Hashtable<String, Criteria>();
		
		if(fbsCriteria==null){
			throw new IllegalArgumentException("Criteria is null!");
		}
		
		int firstResult = 0;
		
		if (!fbsCriteria.isFetchAll()) {
			firstResult = (fbsCriteria.getRequestedPage() - 1)* fbsCriteria.getPageSize();
			hbmCriteria.setMaxResults(fbsCriteria.getPageSize());
		}
		
		hbmCriteria.setFirstResult(firstResult);
		
		CriterionIF[] criterions = fbsCriteria.getCriterion();
		
		for (int i = 0; i < criterions.length; i++) {
			CriterionIF criterion = criterions[i];
			String propertyName = criterion.getPropertyName();
			Object searchValue = criterion.getSearchValue();
			
			//?
			if(searchValue == null || searchValue.toString().trim().equals("")){
				if(!criterion.hasSearchValues() && ! criterion.isNull()){
					continue;
				}else {
					searchValue = criterion.getSearchValues();
				}
			}
			
			org.hibernate.Criteria curCriteria = hbmCriteria;
			
			if(propertyName==null){
				throw new IllegalArgumentException("property name is null");
			}
			
			int p = propertyName.indexOf('.');
			
			if(p!=-1){
				StringTokenizer tokens = new StringTokenizer(propertyName, ".", false);
				String parentToken = tokens.nextToken();
				//parentToken = parentToken.replace('>', '.');
				
				while(tokens.hasMoreTokens()){
					String currentToken = tokens.nextToken();
					//currentToken = currentToken.replace('>', '.');
					propertyName = currentToken;
					//propertyName = propertyName.replace('>', '.');
					
					String key = parentToken;
					if(criteriaList.get(key)==null){
						curCriteria = curCriteria.createCriteria(parentToken);
						criteriaList.put(key, curCriteria);
					}else {
						curCriteria = criteriaList.get(key);
					}
					
					parentToken = currentToken;
				}
			}
			//propertyName = propertyName.replace('>', '.');

			if(!(searchValue instanceof String) || criterion.equal()){
				criterion.setExactSearch(true);
			}
			
			MatchMode matchMode = null;
			
			if(criterion.isExactSearch()){
				matchMode = MatchMode.EXACT;
			}else {
				matchMode = MatchMode.ANYWHERE;
			}
			
			//need to add CategoryCriterionIF later
			
			if(criterion.isSearchStartWith()){
				curCriteria.add(Restrictions.like(propertyName, (String)searchValue+"%"));
			}else if (criterion.hasHighSearchValue()) {
				curCriteria.add(Restrictions.between(propertyName, searchValue, criterion.getHighSearchValue()));
			}else if (criterion.lessThanOrEqual()) {
				curCriteria.add(Restrictions.le(propertyName, searchValue));
			}else if (criterion.less()) {
				curCriteria.add(Restrictions.lt(propertyName, searchValue));
			}else if (criterion.greater()) {
				curCriteria.add(Restrictions.gt(propertyName, searchValue));
			}else if (criterion.in()) {
				curCriteria.add(Restrictions.in(propertyName, (Object[])searchValue));
			}else if (criterion.notEqual()) {
				constructMultipleRestriction(curCriteria, criterion, propertyName, searchValue);
			}else if (criterion.greaterThanOrEqual()) {
				curCriteria.add(Restrictions.ge(propertyName, searchValue));
			}else if (criterion.isExactSearch()) {
				constructMultipleRestriction(curCriteria, criterion, propertyName, searchValue);
			}else if (criterion.isNull()) {
				curCriteria.add(Restrictions.isNull(propertyName));
			}else if (criterion.excludeNull()) {
				curCriteria.add(Restrictions.isNotNull(propertyName));
			}else if (criterion.isCaseSensitive()) {
				curCriteria.add(Restrictions.like(propertyName, (String)searchValue, matchMode));
			}else if (!criterion.isCaseSensitive()) {
				curCriteria.add(Restrictions.ilike(propertyName, (String)searchValue, matchMode));
			}
			
		}
		
		OrderIF[] orders = fbsCriteria.getOrder();
		
		for (int i = 0; i < orders.length; i++) {
			OrderIF o = orders[i];
			if(o.isAscending()){
				getCriteria(hbmCriteria, criteriaList, o).addOrder(org.hibernate.criterion.Order.asc(o.getProperty()));
			}else {
				getCriteria(hbmCriteria, criteriaList, o).addOrder(org.hibernate.criterion.Order.desc(o.getProperty()));
			}
		}
		
		
		hbmCriteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		
		List res = hbmCriteria.list();
		
		result.setQueryResult(res);
		
		if(fbsCriteria.isFetchAll()){
			result.setTotalRecords(res.size());
			result.setTotalPages(1);
		}else {
			hbmCriteria.setFirstResult(0); 
			//why need to remove orders here
			hbmCriteria = HibernateUtil.removeOrders(hbmCriteria);
		//	hbmCriteria.setProjection(Projections.rowCount()).
		    //Projections.projectionList().add(Projections.property(""), "");
		  //  hbmCriteria.add(Criterion)
			//use Projection to get row count
			int totalRec = ((Long)hbmCriteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
			result.setTotalRecords(totalRec);
			result.setTotalPages(totalRec / fbsCriteria.getPageSize() + (totalRec % fbsCriteria.getPageSize()> 0 ? 1 : 0));	
		}

		return result;
		
	}
	
	
	protected void constructMultipleRestriction(org.hibernate.Criteria curCriteria, CriterionIF criterion, String propertyName, Object searchValue) {
		
		if(!criterion.hasSearchValues()){
			curCriteria.add(getCriterion(criterion, propertyName, searchValue));
		}else {
			if (criterion.getSearchValues().length == 1) {
				curCriteria.add(getCriterion(criterion, propertyName, criterion.getSearchValues()[0]));
				
			} else if (criterion.getSearchValues().length != 0) {

				SimpleExpression firExpression = (SimpleExpression) getCriterion(criterion, propertyName, criterion.getSearchValues()[0]);
				SimpleExpression secExpression = (SimpleExpression) getCriterion(criterion, propertyName, criterion.getSearchValues()[1]);
				LogicalExpression orExp = Restrictions.or(firExpression, secExpression);
				
				for (int i = 2; i < criterion.getSearchValues().length; i++) {
					SimpleExpression exp = (SimpleExpression) getCriterion(criterion, propertyName, criterion.getSearchValues()[i]);
					orExp = Restrictions.or(orExp, exp);
				}
				
				curCriteria.add(orExp);
			}
			
		}
	}
	
	// why only eq and ne?
	private Criterion getCriterion(CriterionIF criterion, String propertyName, Object value){
		if(criterion.notEqual()){
			return Restrictions.ne(propertyName, value);
		}else if (!criterion.isCaseSensitive() &&(value instanceof String)) {
			return Restrictions.eq(propertyName, value).ignoreCase();
		}else {
			return Restrictions.eq(propertyName, value);
		}
	}

    protected org.hibernate.Criteria getCriteria(org.hibernate.Criteria rootCriteria, Map<String, Criteria> criteriaList, OrderIF o){
    	
    	String propertyName = o.getProperty();
    	int index = propertyName.indexOf('.');
    	
    	if(index == -1){
    		return rootCriteria;
    	}
    	
    	StringTokenizer tokenizer = new StringTokenizer(propertyName, ".", false);
    	String parentToken = tokenizer.nextToken();
    	org.hibernate.Criteria c = rootCriteria;
    	
    	while (tokenizer.hasMoreTokens()) {
			String curToken = tokenizer.nextToken();
			propertyName = curToken;
			String key = parentToken;
			if(criteriaList.get(key)==null){
				c = c.createCriteria(parentToken, JoinType.LEFT_OUTER_JOIN);
			}else {
				c = criteriaList.get(key);
			}
			parentToken = curToken;
		}
    	
        return c;
    }

    private String createAlias(Criteria c, Hashtable aliasList, String property){
    	return createAlias(c, aliasList, "", property);
    }
    
    /**
	 * The outcome of the method for property "user.designation.name" should be
	 * 
	 * c.createAlais("user", "user");
	 * c.createAlias("user.designation", "designation");
	 * 
	 * property = "designation.name";
	 * 
	 * @param c
	 * @param aliasList
	 * @param parent
	 * @param property
	 * @return
	 */
    private String createAlias(Criteria hbmCriteria, Hashtable aliasList, String parent, String property){
    	
    	int p = property.indexOf('.');

		if (p != -1) {
			StringTokenizer tokens = new StringTokenizer(property, ".", false);
			String token = tokens.nextToken();
			
			String aliasOriginPath = getAliasOriginPath(parent, token);
			if(aliasList.get(aliasOriginPath)==null){
				hbmCriteria.createAlias(aliasOriginPath, token);
				aliasList.put(aliasOriginPath, token);
			}
			
			if(tokens.hasMoreTokens()){
				property = createAlias(hbmCriteria, aliasList, aliasOriginPath, getFullToken(tokens));
			}
		}else {
			if(!StringUtil.isEmptyString(parent)){
				String temp = (String) aliasList.get(parent);
				
				if(!StringUtil.isEmptyString(temp)){
					property = temp + "." + property;
				}
			}
		}
		
		return property;
    }
    

    private String getAliasOriginPath(String parent, String token){
    	
    	if(parent !=null && parent.length()>0){
    		token = parent +"."+token;
    	}
    	
    	return token;
    }
	
    
    private String getFullToken(StringTokenizer tokens){
    	String temp = tokens.nextToken("");
    	temp = temp.substring(1);
    	return temp;
    }
    
    
    public Collection find(Class clz, String name, Object val) throws DataAccessObjectException{
    	return find(clz, name, val, false);
    }
    
    public Collection find(Class clz, String name, Object val, boolean cacheable) throws DataAccessObjectException{
    	CriteriaIF criteria = new sg.com.fbs.model.system.persistence.query.Criteria();
    	CriterionIF criterion = new sg.com.fbs.model.system.persistence.query.Criterion(name, val);
    	criterion.setExactSearch(true);
    	criterion.setCaseSensitive(true);
    	CriterionIF[] criterions = {criterion};
    	criteria.setCriterion(criterions);
    	criteria.setFetchAll(true);
    	return search(clz, criteria, cacheable).getQueryResult();
    }
    
    public Collection find(String entityName, String name, Object val) throws DataAccessObjectException{
    	return find(entityName, name, val, false);
    }

    public Collection find(String entityName, String name, Object val, boolean cacheable) throws DataAccessObjectException{
    	CriteriaIF criteria = new sg.com.fbs.model.system.persistence.query.Criteria();
    	CriterionIF criterion = new sg.com.fbs.model.system.persistence.query.Criterion(name, val);
    	criterion.setExactSearch(true);
    	criterion.setCaseSensitive(true);
    	CriterionIF[] criterions = {criterion};
    	criteria.setCriterion(criterions);
    	criteria.setFetchAll(true);
    	return search(entityName, criteria, cacheable).getQueryResult();
    }
    
    public Collection findByOrder(Class clz, String name, Object val, String orderProperty, boolean isAscending) throws DataAccessObjectException{
    	return findByOrder(clz, name, val, orderProperty, isAscending, false);
    }
    
    public Collection findByOrder(Class clz, String name, Object val, String orderProperty, boolean isAscending, boolean cacheable) throws DataAccessObjectException{
    	CriteriaIF criteria = new sg.com.fbs.model.system.persistence.query.Criteria();
    	OrderIF order = new sg.com.fbs.model.system.persistence.query.Order(orderProperty, isAscending);
    	OrderIF[] orders = {order};
    	criteria.setOrder(orders);
    	
    	CriterionIF criterion = new sg.com.fbs.model.system.persistence.query.Criterion(name, val);
    	criterion.setCaseSensitive(true);
    	criterion.setExactSearch(true);
    	CriterionIF[] criterions = {criterion};
    	criteria.setCriterion(criterions);
    	criteria.setFetchAll(true);
    	
    	return search(clz, criteria, cacheable).getQueryResult();
    }
    
    public Collection findAllByOrder(Class clz, String orderProperty) throws DataAccessObjectException{
    	return findAllByOrder(clz, orderProperty, false);
    }
    
    public Collection findAllByOrder(Class clz, String orderProperty, boolean cacheable) throws DataAccessObjectException{
    	CriteriaIF criteria = new sg.com.fbs.model.system.persistence.query.Criteria();
    	OrderIF order = new sg.com.fbs.model.system.persistence.query.Order(orderProperty, true);
    	OrderIF[] orders = {order};
    	criteria.setOrder(orders);
    	CriterionIF [] criterions ={};
    	criteria.setCriterion(criterions);
    	criteria.setFetchAll(true);
    	
    	return search(clz, criteria, cacheable).getQueryResult();
    }
    
    public Collection find(Class clz, String property1, Object value1, String property2, Object value2) throws DataAccessObjectException{
    	return find(clz, property1, value1, property2, value2, false);
    }
    public Collection find(Class clz, String property1, Object value1, String property2, Object value2, boolean cacheable) throws DataAccessObjectException{
    	CriteriaIF criteria = new sg.com.fbs.model.system.persistence.query.Criteria();
    	
    	CriterionIF criterion1 = new sg.com.fbs.model.system.persistence.query.Criterion(property1, value1);
    	criterion1.setExactSearch(true);
    	criterion1.setCaseSensitive(true);
    	
    	CriterionIF criterion2 = new sg.com.fbs.model.system.persistence.query.Criterion(property2, value2);
    	criterion2.setExactSearch(true);
    	criterion2.setCaseSensitive(true);
    	
    	CriterionIF[] criterions = {criterion1, criterion2};
    	criteria.setCriterion(criterions);
    	criteria.setFetchAll(true);
    	
    	return search(clz, criteria, cacheable).getQueryResult();
    }
    
    public Collection find(String entityName, String property1, Object value1, String property2, Object value2) throws DataAccessObjectException{
    	return find(entityName, property1, value1, property2, value2, false);
    }
    public Collection find(String entityName, String property1, Object value1, String property2, Object value2, boolean cacheable) throws DataAccessObjectException{
    	CriteriaIF criteria = new sg.com.fbs.model.system.persistence.query.Criteria();
    	
    	CriterionIF criterion1 = new sg.com.fbs.model.system.persistence.query.Criterion(property1, value1);
    	criterion1.setExactSearch(true);
    	criterion1.setCaseSensitive(true);
    	
    	CriterionIF criterion2 = new sg.com.fbs.model.system.persistence.query.Criterion(property2, value2);
    	criterion2.setExactSearch(true);
    	criterion2.setCaseSensitive(true);
    	
    	CriterionIF[] criterions = {criterion1, criterion2};
    	criteria.setCriterion(criterions);
    	criteria.setFetchAll(true);
    	
    	return search(entityName, criteria, cacheable).getQueryResult();
    }
    
    
    public Collection find(String entityName, String property1, Object value1, String property2, Object value2, String property3, Object value3) throws DataAccessObjectException{
    	return find(entityName, property1, value1, property2, value2, property3, value3, false);
    }
    public Collection find(String entityName, String property1, Object value1, String property2, Object value2, String property3, Object value3, boolean cacheable) throws DataAccessObjectException{
    	CriteriaIF criteria = new sg.com.fbs.model.system.persistence.query.Criteria();
    	
    	CriterionIF criterion1 = new sg.com.fbs.model.system.persistence.query.Criterion(property1, value1);
    	criterion1.setExactSearch(true);
    	criterion1.setCaseSensitive(true);
    	
    	CriterionIF criterion2 = new sg.com.fbs.model.system.persistence.query.Criterion(property2, value2);
    	criterion2.setExactSearch(true);
    	criterion2.setCaseSensitive(true);
    	
    	CriterionIF criterion3 = new sg.com.fbs.model.system.persistence.query.Criterion(property3, value3);
    	criterion3.setExactSearch(true);
    	criterion3.setCaseSensitive(true);
    	
    	CriterionIF[] criterions = {criterion1, criterion2, criterion3};
    	criteria.setCriterion(criterions);
    	criteria.setFetchAll(true);
    	
    	return search(entityName, criteria, cacheable).getQueryResult();
    }
    

    public Collection find(Class clz, String property1, Object value1, String property2, Object value2, String property3, Object value3) throws DataAccessObjectException{
    	return find(clz, property1, value1, property2, value2, property3, value3, false);
    }
    
    public Collection find(Class clz, String property1, Object value1, String property2, Object value2, String property3, Object value3, boolean cacheable) throws DataAccessObjectException{
    	CriteriaIF criteria = new sg.com.fbs.model.system.persistence.query.Criteria();
    	
    	CriterionIF criterion1 = new sg.com.fbs.model.system.persistence.query.Criterion(property1, value1);
    	criterion1.setExactSearch(true);
    	criterion1.setCaseSensitive(true);
    	
    	CriterionIF criterion2 = new sg.com.fbs.model.system.persistence.query.Criterion(property2, value2);
    	criterion2.setExactSearch(true);
    	criterion2.setCaseSensitive(true);
    	
    	CriterionIF criterion3 = new sg.com.fbs.model.system.persistence.query.Criterion(property3, value3);
    	criterion3.setExactSearch(true);
    	criterion3.setCaseSensitive(true);
    	
    	CriterionIF[] criterions = {criterion1, criterion2, criterion3};
    	criteria.setCriterion(criterions);
    	criteria.setFetchAll(true);
    	
    	return search(clz, criteria, cacheable).getQueryResult();
    }
    
    /**
     * Remove this instance from the session cache. 
     * Changes to the instance will not be synchronized with the database. 
     * This operation cascades to associated instances if the association is mapped with cascade="evict".
     **/
    public Object dettachObject(Object obj){
    	getSession().evict(obj);
    	return obj;
    }
    
    public Collection executeNamedQuery(String queryName){
    	return executeNamedQuery(queryName, false);
    }
    
    public Collection executeNamedQuery(String queryName, boolean cacheable){
    	Query query = HibernateUtil.currentSession().getNamedQuery(queryName).setCacheable(cacheable);
    	return query.list();
    }
    
    public Collection executeNamedQuery(String queryName, String para1){
    	return executeNamedQuery(queryName, para1, false);
    }
    
    public Collection executeNamedQuery(String queryName, String para1, boolean cacheable){
    	if(para1 == null){
    		throw new IllegalArgumentException("Para 1 is null");
    	}
    	Query query = HibernateUtil.currentSession().getNamedQuery(queryName).setCacheable(cacheable);
    	query.setString(0, para1);
    	return query.list();
    }
    
    public Collection executeNamedQuery(String queryName, String para1, String para2){
    	return executeNamedQuery(queryName, para1, para2, false);
    }
    
    public Collection executeNamedQuery(String queryName, String para1, String para2, boolean cacheable){
    	if(para1 == null){
    		throw new IllegalArgumentException("Para 1 is null");
    	}
    	if(para2 == null){
    		throw new IllegalArgumentException("Para 2 is null");
    	}
    	Query query = HibernateUtil.currentSession().getNamedQuery(queryName).setCacheable(cacheable);
    	query.setString(0, para1);
    	query.setString(1, para2);
    	return query.list();
    }
    
    public Collection executeNamedQuery(String queryName, String para1, String para2, String para3){
    	return executeNamedQuery(queryName, para1, para2, para3, false);
    }
    
    public Collection executeNamedQuery(String queryName, String para1, String para2, String para3, boolean cacheable){
    	if(para1 == null){
    		throw new IllegalArgumentException("Para 1 is null");
    	}
    	if(para2 == null){
    		throw new IllegalArgumentException("Para 2 is null");
    	}
    	if(para3 == null){
    		throw new IllegalArgumentException("Para 3 is null");
    	}
    	Query query = HibernateUtil.currentSession().getNamedQuery(queryName).setCacheable(cacheable);
    	query.setString(0, para1);
    	query.setString(1, para2);
    	query.setString(2, para3);
    	return query.list();
    }
    
    public Collection executeNamedQuery(String queryName, Collection para1, String para2, String para3){
    	return executeNamedQuery(queryName, para1, para2, false);
    }
    
    public Collection executeNamedQuery(String queryName, Collection para1, String para2, boolean cacheable){
    	if(para1 == null){
    		throw new IllegalArgumentException("Para 1 is null");
    	}
    	if(para2 == null){
    		throw new IllegalArgumentException("Para 2 is null");
    	}
    	Query query = HibernateUtil.currentSession().getNamedQuery(queryName).setCacheable(cacheable);
    	query.setParameterList("collectionList", para1);
    	query.setString(1, para2);
    	return query.list();
    }
	
    protected List<?> executeNamedQuery(String queryName,Map<String, Object> params) {
    	return executeNamedQuery(queryName, params, false);
    }
    
    protected List<?> executeNamedQuery(String queryName,Map<String, Object> params , boolean cacheable) {
		Query query = getSession().getNamedQuery(queryName).setCacheable(cacheable);
		if(params!=null){
			for (String key : params.keySet()) {
				if(params.get(key) instanceof Collection){
					query.setParameterList(key, (Collection<?>)params.get(key));
				}else{
					query.setParameter(key, params.get(key));
				}
			}
		}
		
		List<?> list = query.list();
		return list;
	}
    
    public Query createQuery(String hql) {
		return HibernateUtil.currentSession().createQuery(hql);
	}
    
    public Query createSQLQuery(String sqlQuery) {
		return getSession().createSQLQuery(sqlQuery);
	}
    
    public Collection executeSQLQuery(String sqlQuery){
    	return executeSQLQuery(sqlQuery, false);
    }
    
    public Collection executeSQLQuery(String sqlQuery, boolean cacheable){
    	return createSQLQuery(sqlQuery).setCacheable(cacheable).list();
    }
    
    public Object findObject(Class clz, String name, Object val, boolean cacheable) throws DataAccessObjectException{
    	Collection coll = find(clz, name, val, cacheable);
    	if(coll.isEmpty()){
    		return null;
    	}else {
			List list = (List) coll;
			return list.get(0);
		}
    }
    
    public Object findObject(Class clz, String name, Object val) throws DataAccessObjectException{
    	return findObject(clz, name, val, false);
    }
    
    public Object findObject(String entityName, String name, Object val, boolean cacheable) throws DataAccessObjectException{
    	Collection coll = find(entityName, name, val, cacheable);
    	if(coll.isEmpty()){
    		return null;
    	}else {
			List list = (List) coll;
			return list.get(0);
		}
    }
    
    public Object findObject(String entityName, String name, Object val) throws DataAccessObjectException{
    	return findObject(entityName, name, val, false);
    }
    
    public Object findObject(Class clz, String property1, Object value1, String property2, Object value2, boolean cacheable) throws DataAccessObjectException{
    	Collection coll = find(clz, property1, value1, property2, value2, cacheable);
    	if(coll.isEmpty()){
    		return null;
    	}else{
    		List list = (List) coll;
    		return list.get(0);
    	}
    }
    
    public Object findObject(Class clz, String property1, Object value1, String property2, Object value2) throws DataAccessObjectException{
    	return findObject(clz, property1, value1, property2, value2, false);
    }
    
    public Object findObject(String entityName, String property1, Object value1, String property2, Object value2, boolean cacheable) throws DataAccessObjectException{
    	Collection coll = find(entityName, property1, value1, property2, value2, cacheable);
    	if(coll.isEmpty()){
    		return null;
    	}else{
    		List list = (List) coll;
    		return list.get(0);
    	}
    }
    
    public Object findObject(String entityName, String property1, Object value1, String property2, Object value2) throws DataAccessObjectException{
    	return findObject(entityName, property1, value1, property2, value2, false);
    }
    
    public Object getByPrimaryKey(Class cls, long pk){
    	try {
    		
    		Object obj = getSession().get(cls, pk);   	
        	return obj;
		} catch (Exception e) {
			throw new ObjectNotFoundException(pk, cls.getName());
		}
    	
    }


    public Object getByPrimaryKey(String entityName, long pk){
    	try {
    		
    		Object obj = getSession().get(entityName, pk);   	
        	return obj;
		} catch (Exception e) {
			throw new ObjectNotFoundException(pk, entityName);
		}
    	
    }
    
    
}































































