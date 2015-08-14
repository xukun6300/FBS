package sg.com.fbs.model.system.persistence.response;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import sg.com.fbs.model.system.persistence.query.CriteriaIF;

/**
 * @author Frank Xu
 *
 * @param <T>
 */
public class ResponseCRUD<T> implements IResponseCRUD<T>,Serializable {

	private Object crudResult;
	
	private boolean crudFlag;
	
	@Getter
	@Setter
	private int totalRecords;
	
	private int totalPages;
	
	private Collection<T> queryResult;
	
	private CriteriaIF criteria;
	
	private Map<String, Object> moreQueryResults;
	
	
	public ResponseCRUD() {
		
	}
	
	public ResponseCRUD(Object crudResult){
		this.crudResult = crudResult;
		if(this.crudResult!=null){
			this.crudFlag = true;
		}
	}
	
	public ResponseCRUD(Collection<T> queryResult){
		this.queryResult = queryResult;
		if(this.queryResult != null){
			this.crudFlag =true;
		}
	}
	
	public ResponseCRUD(List<T> queryResult){
		this.queryResult = queryResult;
		if(this.queryResult!=null){
			this.crudFlag = true;
		}
	}
	
	@Override
	public boolean isCrudFlag() {
		return this.crudFlag;
	}

	@Override
	public void setCrudFlag(boolean crudFlag) {
		this.crudFlag = crudFlag;
	}

	@Override
	public Object getCrudResult() {
		return this.crudResult;
	}

	@Override
	public void setCrudResult(Object crudResult) {
		this.crudResult = crudResult;
	}

	@Override
	public int getTotalPages() {
		return this.totalPages;
	}

	@Override
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	@Override
	public Collection<T> getQueryResult() {
		return this.queryResult;
	}

	@Override
	public void setQueryResult(Collection<T> queryResult) {
		this.queryResult = queryResult;
	}

	@Override
	public CriteriaIF getCriteria() {
		return this.criteria;
	}

	@Override
	public void setCriteria(CriteriaIF criteria) {
		this.criteria = criteria;
	}

	@Override
	public Map<String, Object> getMoreQueryResults() {
		return this.moreQueryResults;
	}

	@Override
	public void setMoreQueryResult(Map<String, Object> moreQueryResult) {
		this.moreQueryResults = moreQueryResult;
	}





}
