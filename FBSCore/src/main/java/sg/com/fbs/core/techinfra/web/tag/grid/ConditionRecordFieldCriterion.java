package sg.com.fbs.core.techinfra.web.tag.grid;

import java.util.Arrays;

import lombok.Getter;
import lombok.Setter;

import org.apache.log4j.Logger;

import sg.com.fbs.core.techinfra.util.StringUtil;
import sg.com.fbs.model.system.persistence.query.Criterion;

/**
 * @author Frank Xu
 *
 */
public class ConditionRecordFieldCriterion extends Criterion {

	private static final long serialVersionUID = 1597661215355623698L;

	private Logger logger = Logger.getLogger(ConditionRecordFieldCriterion.class);

	private long conditionFieldConfigID;
	
	private String value;
	
	private String[] values;
	
	private String display;
	
	private String alias;
	
	private int index;
	
	private String conditionFieldConfigProperty;
	
	private String fieldValueProperty;
	
	private String fieldDisplayProperty;
	
	public ConditionRecordFieldCriterion(long conditionFieldConfigID, String value){
		this.conditionFieldConfigID = conditionFieldConfigID;
		this.value = value;
	}

	public ConditionRecordFieldCriterion(long conditionFieldConfigID, String[] values){
		this.conditionFieldConfigID = conditionFieldConfigID;
		if(values==null){
			this.values = new String[0];
		}else {
			this.values = Arrays.copyOf(values, values.length);
		}
	}
	
	public boolean hasValue(){
		if(!StringUtil.isEmptyString(this.value)){
			return true;
		}else if (this.values!=null && this.values.length>0) {
			return true;
		}
		
		return false;
	}
	
	public long getConditionFieldConfigID() {
		return conditionFieldConfigID;
	}

	public void setConditionFieldConfigID(long conditionFieldConfigID) {
		this.conditionFieldConfigID = conditionFieldConfigID;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String[] getValues() {
		return values;
	}

	public void setValues(String[] values) {
		if(values==null){
			this.values = new String[0];
		}else {
			this.values = Arrays.copyOf(values, values.length);
		}
	}

	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getConditionFieldConfigProperty() {
		return conditionFieldConfigProperty;
	}

	public void setConditionFieldConfigProperty(String conditionFieldConfigProperty) {
		this.conditionFieldConfigProperty = conditionFieldConfigProperty;
	}

	public String getFieldValueProperty() {
		return fieldValueProperty;
	}

	public void setFieldValueProperty(String fieldValueProperty) {
		this.fieldValueProperty = fieldValueProperty;
	}

	public String getFieldDisplayProperty() {
		return fieldDisplayProperty;
	}

	public void setFieldDisplayProperty(String fieldDisplayProperty) {
		this.fieldDisplayProperty = fieldDisplayProperty;
	}
	
	public String getConditionFieldConfigIDVariable() {
		return alias + "cfcid";
	}
	
	public String getValueVariable() {
		return alias + "value";
	}

	
}












