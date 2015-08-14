package sg.com.fbs.model.system.persistence.query;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import org.apache.commons.beanutils.BeanUtils;

/**
 * @author Frank Xu
 *
 */
public class Criteria implements CriteriaIF{
	
	private static final long serialVersionUID = -8265348767454085720L;
	
	private static final int DEFAULT_REQUESTED_PAGE = 1;
	
	private int pageSize = 10;
	
	private int requestedPage = DEFAULT_REQUESTED_PAGE;
	
	private Criterion[] criterion = {};
	
	private OrderIF[] order = {};
	
	private Projection[] projection = {};
	
	private boolean fetchAll = false;
	
	private boolean initialise = true;
	
	final static Projection[] EMPTY_PROJECTION = {};
	
	public void removeProjection(){
		this.setProjection(EMPTY_PROJECTION);
	}
	
	public void removeEmptyCriterion(){
		ArrayList<Criterion> criterionList = new ArrayList<Criterion>();
		
		for (int i = 0; i < criterion.length; i++) {
			Criterion c = this.criterion[i];
			if(c.hasSearchValueSpecified()){
				criterionList.add(c);
			}
		}
		
		criterion = new Criterion[criterionList.size()];
		criterionList.toArray(criterion);
	}
	

	@Override
	public boolean isFetchAll() {
		return this.fetchAll;
	}

	@Override
	public void setFetchAll(boolean fetchAll) {
		this.fetchAll = fetchAll;
	}

	@Override
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	@Override
	public int getPageSize() {
		return this.pageSize;
	}

	@Override
	public void setRequestedPage(int requestedPage) {
		this.requestedPage = requestedPage;
	}

	@Override
	public int getRequestedPage() {
		return this.requestedPage;
	}

	@Override
	public void setCriterion(Criterion[] criterion) {
		this.criterion = criterion;
	}

	@Override
	public Criterion[] getCriterion() {
		return this.criterion;
	}

	@Override
	public void setOrder(OrderIF[] order) {
		this.order = order;
	}

	@Override
	public OrderIF[] getOrder() { 
		return this.order;
	}

	@Override
	public void setProjection(Projection[] projection) {
		this.projection = projection;
	}

	@Override
	public Projection[] getProjection() {
		return this.projection;
	}

	@Override
	public boolean isInitialise() {
		return this.initialise;
	}

	@Override
	public void setInitialise(boolean b) {
		this.initialise = b;
	}

	@Override
	public boolean hasSearchProperty(String property) {
		for (Criterion c : criterion) {
			if(c.getPropertyName()!=null &&c.getPropertyName().equals(property)){
				return true;
			}
		}
		return false;
	}

	@Override
	public Object getSearchValue(String property) {
		for (int i = 0; i < criterion.length; i++) {
			Criterion c = criterion[i];
			if(c.getPropertyName()!=null && c.getPropertyName().equals(property)){
				return c.getSearchValue();
			}
		}
		return null;
	}

	@Override
	public void appendCriterion(Criterion criterion) {
		Criterion[] newCriterions = new Criterion[this.criterion.length+1];
		
		for (int i = 0; i < this.criterion.length; i++) {
			newCriterions[i] = this.criterion[i];
		}
		
		newCriterions[newCriterions.length-1] = criterion;
		this.criterion = newCriterions;
	}

	@Override
	public void appendProjection(Projection projection) {
		Projection[] newProjections = new Projection[this.projection.length+1];
		
		for (int i = 0; i < this.projection.length; i++) {
			newProjections[i]= this.projection[i]; 
		}
		
		newProjections[newProjections.length-1] = projection;
		this.projection = newProjections;
	}

	@Override
	public void setCriterion(CriterionIF[] criterionIF) {
		criterion = new Criterion[criterionIF.length];
		for (int i = 0; i < criterion.length; i++) {
			criterion[i] = new Criterion();
			try {
				BeanUtils.copyProperties(criterion[i], criterionIF[i]);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
