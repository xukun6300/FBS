package sg.com.fbs.model.business.pojo;

import java.io.Serializable;

import org.joda.time.DateTime;

import lombok.Getter;
import lombok.Setter;

public abstract class BasePojoRequest implements BasePojoRequestIF, Serializable{


	protected long id;

	protected String activeStatus = "Y";
	
	protected DateTime createdon;
	
	protected DateTime modifiedon;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(String activeStatus) {
		this.activeStatus = activeStatus;
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
	
	
}
