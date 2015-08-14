package sg.com.fbs.model.system.persistence.query;

import java.io.Serializable;

public class Order implements OrderIF,Serializable{

	public static final boolean IS_ASCENDING = true;

	public static final boolean IS_DESCENDING = false;
	
	private boolean order = IS_ASCENDING;
	
	private String property;
	
	public Order(String property, boolean order){
		this.property = property;
		this.order = order;
	}
	
	@Override
	public void setProperty(String p) {
		 this.property = p;
	}

	@Override
	public String getProperty() {
		return property;
	}

	@Override
	public String getLastProperty() {
		int index = property.lastIndexOf('.');
		if(index==-1){
			return property;
		}
		return property.substring(index+1);
	}

	@Override
	public void setIsAscending(boolean isAscending) {
		order = isAscending;	
	}

	@Override
	public boolean getIsAscending() {		
		return order;
	}

	@Override
	public boolean isAscending() {
		return order;
	}

	public String toString(){
		StringBuffer sb = new StringBuffer().append("<Order>\n")
				.append("<Property>"+this.property+"</Property>\n")
				.append("<IsAscending>"+getIsAscending()+"</IsAscending>\n")
				.append(super.toString())
				.append("\n</Order>\n");
		return sb.toString();
	}
	
}
