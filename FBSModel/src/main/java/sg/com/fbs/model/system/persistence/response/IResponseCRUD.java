package sg.com.fbs.model.system.persistence.response;

import java.util.Collection;
import java.util.Map;

import sg.com.fbs.model.system.persistence.query.CriteriaIF;

public interface IResponseCRUD<T> {

	public boolean isCrudFlag();
	public void setCrudFlag(boolean crudFlag);
	
	public Object getCrudResult();
	public void setCrudResult(Object crudResult);
	
	public int getTotalRecords();
	public void setTotalRecords(int totalRecords);
	
	public int getTotalPages();
	public void setTotalPages(int totalPages);
	
	public Collection<T> getQueryResult();
	public void setQueryResult(Collection<T> queryResult);
	
	public CriteriaIF getCriteria();
	public void setCriteria(CriteriaIF criteria);
	
	public Map<String, Object> getMoreQueryResults();
	public void setMoreQueryResult(Map<String, Object> moreQueryResult);
	
}
