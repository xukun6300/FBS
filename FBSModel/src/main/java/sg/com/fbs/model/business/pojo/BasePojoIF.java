package sg.com.fbs.model.business.pojo;

import java.util.Map;

import org.joda.time.DateTime;

/**
 * @author Frank Xu
 *
 */
public interface BasePojoIF {
   public long getId();
   
   public DateTime getModifyon();
   
   public Map<String, String> getExportData();
}
