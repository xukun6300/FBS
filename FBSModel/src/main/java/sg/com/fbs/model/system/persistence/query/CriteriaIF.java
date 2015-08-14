package sg.com.fbs.model.system.persistence.query;

import java.io.Serializable;

public interface CriteriaIF extends Serializable{

	public boolean isFetchAll();
	
	public void setFetchAll(boolean fetchAll);
	
	public void setPageSize(int pageSize);
	
	public int getPageSize();
	
	public void setRequestedPage(int requestedPage);
	
	public int getRequestedPage();
	
	public void setCriterion(CriterionIF[] criterion);
	
	public void setCriterion(Criterion[] criterion);
	
	public Criterion[] getCriterion();
	
	public void setOrder(OrderIF[] order);
	
	public OrderIF[] getOrder();
	
	public void setProjection(Projection[] projection);
	
	public Projection[] getProjection();
	
	public boolean isInitialise();
	
	public void setInitialise(boolean b);
	
	public boolean hasSearchProperty(String property);
	
	public Object getSearchValue(String property);
	
	public void appendCriterion(Criterion criterion);
	
	public void appendProjection(Projection projection);
	
	
}
