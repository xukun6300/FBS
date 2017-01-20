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
	
	private long id;

	protected DateTime createdon;

	protected DateTime modifiedon;

	private WebCRUDEnum crudMode;

	private IResponseCRUD crudResponse;
	
	private String activeStatus = "Y";
	
	private String action;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public DateTime getCreatedon() {
		return createdon;
	}

	public void setCreatedon(DateTime createdon) {
		this.createdon = createdon;
	}

	public DateTime getModifiedon() {
		return modifiedon;
	}

	public void setModifiedon(DateTime modifiedon) {
		this.modifiedon = modifiedon;
	}

	public WebCRUDEnum getCrudMode() {
		return crudMode;
	}

	public void setCrudMode(WebCRUDEnum crudMode) {
		this.crudMode = crudMode;
	}

	public IResponseCRUD getCrudResponse() {
		return crudResponse;
	}

	public void setCrudResponse(IResponseCRUD crudResponse) {
		this.crudResponse = crudResponse;
	}

	public String getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(String activeStatus) {
		this.activeStatus = activeStatus;
	}

	private Order[] order = {new Order(ID,true)};
	
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
	
	public void setAction(String action) {
		this.action = action;
	}
	
	public String getAction() {
		return action;
	}
	
	public abstract CriteriaIF getSearchCriteria(HttpServletRequest request);
}













