package sg.com.fbs.model.business.pojo;

import java.io.Serializable;

import org.joda.time.DateTime;

import lombok.Getter;
import lombok.Setter;

public abstract class BasePojoRequest implements BasePojoRequestIF, Serializable{

	@Setter
	@Getter
	protected long id;
	
	@Setter
	@Getter
	protected String activeStatus = "Y";
	
	@Setter
	@Getter
	protected DateTime createdon;
	
	@Setter
	@Getter
	protected DateTime modifiedon;
}
