package sg.com.fbs.core.techinfra.web;

import sg.com.fbs.model.system.persistence.query.Order;

public interface BaseQueryWebFormIF {
	
  public void setResultVisible(boolean resultVisible);
  
  public void setFetchAll(boolean fetchAll); 
  
  public void setRequestedPage(int requestedPage);
  
  public void setOrder(Order[] order);
  
}
