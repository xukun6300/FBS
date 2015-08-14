package sg.com.fbs.core.techinfra.web;

import java.util.Map;

import sg.com.fbs.model.system.persistence.query.Order;
import sg.com.fbs.model.system.persistence.response.IResponseCRUD;

public interface BaseWebFormIF {

	public void setId(long id);
	
	public IResponseCRUD getCrudResponse();
	
	public void setCrudResponse(IResponseCRUD crudResponse);
	
	public String getResultQualifierName();
	
	public String[] getDefaultOrders();
	
	public Order[] getOrder();
	
	public void setOrder(Order[] order);
	
	public Map<String, Map<String, Object>> getDefaultValues();
	
	public void setCrudMode(WebCRUDEnum crudMode);
}
