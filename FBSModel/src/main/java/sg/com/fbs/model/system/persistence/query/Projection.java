package sg.com.fbs.model.system.persistence.query;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

public class Projection implements ProjectionIF, Serializable {
    
	
	private static final long serialVersionUID = 4031209230122624726L;


	private String property;
	
	private boolean rowCount;
	
	private String type;

	private String withProperty;
	
	
	
	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getWithProperty() {
		return withProperty;
	}

	public void setWithProperty(String withProperty) {
		this.withProperty = withProperty;
	}

	public Projection(){
		
	}
	
	public Projection(String type){
		this.setType(type);
	}
	
	public Projection(String type, String property){
		this.setType(type);
		this.setProperty(property);
	}
	
	public Projection(String type, String property, String withProperty){
		this.setType(type);
		this.setProperty(property);
		this.setWithProperty(withProperty);
	}
	
	@Override
	public boolean isWeekProjection() {
		return isType(ProjectionIF.GROUP_BY_WEEK);
	}

	@Override
	public boolean isMonthProjection() {
		return isType(ProjectionIF.GROUP_BY_MONTH);
	}

	@Override
	public boolean isYearProjection() {
		return isType(ProjectionIF.GROUP_BY_YEAR);
	}

	@Override
	public boolean isShowRowCount() {
		return rowCount;
	}

	@Override
	public boolean isMax() {
		return MAX.equalsIgnoreCase(this.getType());
	}

	@Override
	public boolean isMin() {
		return MIN.equalsIgnoreCase(this.getType());
	}

	@Override
	public boolean isAverage() {
		return AVERAGE.equalsIgnoreCase(this.getType());
	}

	@Override
	public boolean isCount() {
		return COUNT.equalsIgnoreCase(this.getType());
	}

	@Override
	public boolean isWeightedAverage() {
		return WEIGHTED_AVERAGE.equalsIgnoreCase(this.getType());
	}

	@Override
	public boolean isGroup() {
		return GROUP_BY.equalsIgnoreCase(this.getType());
	}

	@Override
	public boolean isSum() {
		return SUM.equalsIgnoreCase(this.getType());
	}

	@Override
	public boolean isRowCount() {
		return ROWCOUNT.equalsIgnoreCase(this.getType());
	}

	@Override
	public boolean isSelectProperty() {
		return SELECT_PROPERTY.equalsIgnoreCase(this.getType());
	}

	@Override
	public boolean isType(String type) {
		return type.equals(this.type);
	}



	
}
