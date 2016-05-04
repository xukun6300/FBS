package sg.com.fbs.model.system.persistence.query;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

public class RestrictionType implements Serializable{

	public static final RestrictionType EQUAL = new RestrictionType(RestrictionTypeIF.EQUAL);
	public static final RestrictionType GREATER = new RestrictionType(RestrictionTypeIF.GREATER);
	public static final RestrictionType LESS = new RestrictionType(RestrictionTypeIF.LESS);
	public static final RestrictionType GREATER_OR_EQUAL = new RestrictionType(RestrictionTypeIF.GREATER_OR_EQUAL);
	public static final RestrictionType LESS_OR_EQUAL = new RestrictionType(RestrictionTypeIF.LESS_OR_EQUAL);
	public static final RestrictionType NOT_EQUAL = new RestrictionType(RestrictionTypeIF.NOT_EQUAL);
	public static final RestrictionType IN = new RestrictionType(RestrictionTypeIF.NOT_EQUAL);
	public static final RestrictionType LIKE = new RestrictionType(RestrictionTypeIF.LIKE);	
	public static final RestrictionType IS = new RestrictionType(RestrictionTypeIF.IS);
	public static final RestrictionType IS_NOT_NULL = new RestrictionType(RestrictionTypeIF.IS_NOT_NULL);
	
	private String type;
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
	
	public RestrictionType(){
		
	}
	
	public RestrictionType(String type){
		this.setType(type);
	}
	
	public String getOperatorSign() {
		if (RestrictionTypeIF.EQUAL.equals(type)) {
			return "=";
		} else if (RestrictionTypeIF.GREATER.equals(type)) {
			return ">";
		} else if (RestrictionTypeIF.LESS.equals(type)) {
			return "<";
		} else if (RestrictionTypeIF.GREATER_OR_EQUAL.equals(type)) {
			return ">=";
		} else if (RestrictionTypeIF.LESS_OR_EQUAL.equals(type)) {
			return "<=";
		} else if (RestrictionTypeIF.NOT_EQUAL.equals(type)) {
			return "<>";
		} else if (RestrictionTypeIF.IN.equals(type)) {
			return "in";
		} else if (RestrictionTypeIF.LIKE.equals(type)) {
			return "like";
		} else if (RestrictionTypeIF.IS.equals(type)) {
			return "is";
		} else if (RestrictionTypeIF.IS_NOT_NULL.equals(type)) {
			return "is not null";
		}
		return "=";
	}
}
