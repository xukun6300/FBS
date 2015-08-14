package sg.com.fbs.model.system.persistence.query;

import java.io.Serializable;

public interface CriterionIF extends Serializable {

	public final String STRING = "STRING";
	
	public final String DATE = "DATE";
	
	public final String LONG = "RIGHTS";
	
	public void setCaseSensitive(boolean caseSensitive);
	
	public boolean getCaseSensitive();
	
	public boolean isCaseSensitive();
	
	public boolean isSearchStartWith();
	
	public void setExactSearch(boolean exactSearch);
	
	public boolean getExactSearch();
	
	public boolean isExactSearch();
	
	public void setHighSearchValue(Object highSearchValue);
	
	public Object getHighSearchValue();
	
	public boolean hasHighSearchValue();
	
	public void setSearchValue(Object searchValue);
	
	public void setSearchValues(Object[] searchValues);
	
	public Object getSearchValue();
	
	public Object[] getSearchValues();
	
	public boolean hasSearchValues();
	
	public String getSearchValueDataType();
	
	public boolean isSearchValueDataTypeDate();
	
	public void setPropertyName(String propertyName);
	
	public String getPropertyName();
	
	public void setShortPropertyName(String shortPropertyName);
	
	public String getShortPropertyName();
	
	public boolean hasShortPropertyName();
	
	public void setHighPropertyName(String highPropertyName);
	
	public String getHighPropertyName();
	
	public boolean lessThanOrEqual();
	
	public boolean equal();
	
	public boolean notEqual();
	
	public boolean greaterThanOrEqual();
	
	public boolean less();
	
	public boolean greater();
	
	public boolean in();
	
	public void setSearchStartWith(boolean searchStartWith);
	
	public boolean isNull();
	
	public boolean excludeNull();
	
 }

































