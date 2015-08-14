package sg.com.fbs.core.techinfra.web;

import java.util.Arrays;

import sg.com.fbs.model.system.persistence.query.CriterionIF;



public abstract class BaseQueryWebForm extends BaseWebFormBean implements BaseQueryWebFormIF{

	private static final long serialVersionUID = 4363369419032982374L;

	private boolean resultVisible = false;
	
	private boolean isFetchAll = false;


	private int requestedPage = 1;
	
	protected CriterionIF[] criterion = null;
	
	public Long createLongSearchValue(String value){
		if(value==null || value.trim().equalsIgnoreCase("")){
			return null;
		}
		return new Long(value);
	}
	
	public CriterionIF[] getCriterion(){
		return criterion;
	}
	
	public void setCriterion(CriterionIF[] criterion) {
		if(criterion==null){
			this.criterion = new CriterionIF[0];
		}else {
			this.criterion = Arrays.copyOf(criterion, criterion.length);
		}
	}
	
	public boolean isResultVisible() {
		return resultVisible;
	}

	public void setResultVisible(boolean resultVisible) {
		this.resultVisible = resultVisible;
	}

	public boolean isFetchAll() {
		return isFetchAll;
	}

	public void setFetchAll(boolean isFetchAll) {
		this.isFetchAll = isFetchAll;
	}

	public int getRequestedPage() {
		return requestedPage;
	}

	public void setRequestedPage(int requestedPage) {
		this.requestedPage = requestedPage;
	}

	
	
}
