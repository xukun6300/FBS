package sg.com.fbs.model.system.persistence.query;

/**
 * @author Frank Xu
 *
 */
public interface OrderIF {
	
   public void setProperty(String p);
   
   public String getProperty();
   
   public String getLastProperty();
   
   public void setIsAscending(boolean isAscending);
   
   public boolean getIsAscending();
   
   public boolean isAscending();
}
