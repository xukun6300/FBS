package sg.com.fbs.core.techinfra.web;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import lombok.Getter;
import lombok.Setter;

import org.joda.time.DateTime;

import sg.com.fbs.model.domain.enumeration.ActiveStatusEnum;
import sg.com.fbs.model.system.persistence.query.Criteria;
import sg.com.fbs.model.system.persistence.query.CriteriaIF;
import sg.com.fbs.model.system.persistence.query.Order;
import sg.com.fbs.model.system.persistence.response.IResponseCRUD;

public abstract class BaseWebFormBean implements BaseWebFormIF,Serializable{
	
	private static final long serialVersionUID = 5339739610586110814L;
	
	public static final String ID ="id";
	
	public static final String STATUS = "activeStatus";
	
	public static final String SORTING_PROPERTY = "sortingProperty";
	
	public static final String IS_ASCENDING = "isAscending";
	

	@Getter
	@Setter
	private long id;
	
	@Setter
	@Getter
	protected DateTime createdon;
	
	@Setter
	@Getter
	protected DateTime modifiedon;
	
	@Setter
	@Getter
	private WebCRUDEnum crudMode;
	
	@Setter
	@Getter
	private IResponseCRUD crudResponse;
	
	@Setter
	@Getter
	private String activeStatus = "Y";
	
	private Order[] order = {new Order(null,true)};
	
	public boolean getIsActive(){
		return activeStatus.equals(ActiveStatusEnum.YES.toString());
	}

	public Order[] getOrder() {
		return order;
	}

	public void setOrder(Order[] vOrder) {
		if(order==null){
			this.order = new Order[0];
		}else{
			this.order = Arrays.copyOf(vOrder, vOrder.length);
		}
	}
	
	public Map<String, Map<String, Object>> getDefaultValues(){
		return null;
	}
	
	public String getResultQualifierName(){
		return "queryResult.results";
	}
	
	public abstract CriteriaIF getSearchCriteria(HttpServletRequest request);
}













