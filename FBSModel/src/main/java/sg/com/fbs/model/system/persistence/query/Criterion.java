package sg.com.fbs.model.system.persistence.query;

import java.util.Date;

import org.joda.time.DateTime;
import lombok.Getter;
import lombok.Setter;



public class Criterion implements CriterionIF{
	
	private static final long serialVersionUID = 3551723237600634594L;
	
	private static final boolean DEFAULT_CASE_SENSITIVE = false;
	
	private static final boolean DEFAULT_EXACT_SEARCH = false;
	
	private static final String DEFAULT_NULL_SEARCH ="null";
	
	private boolean caseSensitive = DEFAULT_CASE_SENSITIVE;
	
	private boolean exactSearch = DEFAULT_EXACT_SEARCH;
	
	private boolean searchStartWith = false;
	

	private String propertyName;
	
	private String highPropertyName;

	private Object searchValue;
	

	private Object[] searchValues;
	
	private Object highSearchValue;
	
	private String shortPropertyName;
	

	private String searchValueDataType = STRING;
	
	public void setSearchValueDataType(String searchValueDataType) {
		this.searchValueDataType = searchValueDataType;
	}

	RestrictionType restrictionType = RestrictionType.LIKE;
	
	public RestrictionType getRestrictionType() {
		return restrictionType;
	}
	
	public void setRestrictionType(RestrictionType restrictionType) {
		this.restrictionType = restrictionType;
	}
	
	public Criterion (){
		super();		
	}
	
	public Criterion(String propertyName, Object searchValue){
		this.setPropertyName(propertyName);
		this.setSearchValue(searchValue);
	}
	
	public Criterion (String propertyName, Object[] searchValues) {
		this.setPropertyName(propertyName);
		this.setSearchValues(searchValues);
	}
	
	public Criterion (RestrictionType restrictionType, String propertyName, String searchValueDataType) {
		this.setRestrictionType(restrictionType);
		this.setPropertyName(propertyName);
		this.setSearchValueDataType(CriterionIF.DATE);
	}
	
	public Criterion (RestrictionType restrictionType, String propertyName, Object[] searchValues) {
		this.setRestrictionType(restrictionType);
		this.setPropertyName(propertyName);
		this.setSearchValues(searchValues);
	}
	
	public Criterion(String propertyName, String shortPropertyName, Object searchValue){
		this.setPropertyName(propertyName);
		this.setShortPropertyName(shortPropertyName);
		this.setSearchValue(searchValue);
	}
	
	public Criterion(String propertyName, String shortPropertyName, Object[] searchValues){
		this.setPropertyName(propertyName);
		this.setShortPropertyName(shortPropertyName);
		this.setSearchValues(searchValues);
	}
	
	public Criterion(String propertyName, RestrictionType restrictionType, String shortPropertyName, Object searchValue){
		this.setPropertyName(shortPropertyName);
		this.setRestrictionType(restrictionType);
		this.setShortPropertyName(shortPropertyName);
		this.setSearchValue(searchValue);
		
		if((searchValue instanceof Date) || (searchValue instanceof DateTime)){
			this.setSearchValueDataType(CriterionIF.DATE);
		}
	}
	
	public Criterion(String propertyName, RestrictionType restrictionType, Object searchValue, boolean caseSensitive) {
		this.setPropertyName(propertyName);
		this.setRestrictionType(restrictionType);
		this.setSearchValue(searchValue);
		this.setCaseSensitive(caseSensitive);
		
		if((searchValue instanceof Date) || (searchValue instanceof DateTime)){
			this.setSearchValueDataType(CriterionIF.DATE);
		}
	}
	
	public Criterion(String propertyName, Object searchValue, boolean isExactSearch, boolean isCaseSensitive){
		this.setPropertyName(propertyName);
		this.setSearchValue(searchValue);
		this.setExactSearch(isExactSearch);
		this.setCaseSensitive(isCaseSensitive);		
	}
	
	public Criterion(String propertyName, Object searchValue, boolean isExactSearch){
		this.setPropertyName(propertyName);
		this.setSearchValue(searchValue);
		this.setExactSearch(isExactSearch);		
	}
	
	public Criterion(String propertyName, String shortPropertyName, Object searchValue, boolean isExactSearch){
		this.setPropertyName(propertyName);
		this.setShortPropertyName(shortPropertyName);
		this.setSearchValue(searchValue);
		this.setExactSearch(isExactSearch);		
	}
	
	@Override
	public void setCaseSensitive(boolean caseSensitive) {
		this.caseSensitive = caseSensitive;
	}

	@Override
	public boolean getCaseSensitive() {
		return this.caseSensitive;
	}

	@Override
	public boolean isCaseSensitive() {
		return this.getCaseSensitive();
	}

	@Override
	public boolean isSearchStartWith() {
		return searchStartWith;
	}

	@Override
	public void setExactSearch(boolean exactSearch) {
		this.exactSearch = exactSearch;
	}

	@Override
	public boolean getExactSearch() {
		return this.exactSearch;
	}

	@Override
	public boolean isExactSearch() {
		return this.getExactSearch();
	}

	@Override
	public void setHighSearchValue(Object highSearchValue) {
		this.highSearchValue = highSearchValue;
	}

	@Override
	public Object getHighSearchValue() {
		return this.highSearchValue;
	}

	@Override
	public boolean hasHighSearchValue() {
		return (this.getHighSearchValue()!=null);
	}

	@Override
	public void setSearchValue(Object searchValue) {
		this.searchValue = searchValue;
	}

	@Override
	public void setSearchValues(Object[] searchValues) {
		this.searchValues = searchValues;
	}

	@Override
	public Object getSearchValue() {
		return this.searchValue;
	}

	@Override
	public Object[] getSearchValues() {
		return this.searchValues;
	}

	@Override
	public boolean hasSearchValues() {
		return this.searchValues!=null && this.searchValues.length>0;
	}
	
	public boolean hasSearchValueSpecified(){
		return this.hasSearchValues()||(this.searchValue!=null && !this.searchValue.toString().trim().equals(""));
	}

	@Override
	public String getSearchValueDataType() {
		return this.searchValueDataType;
	}

	@Override
	public boolean isSearchValueDataTypeDate() {
		return CriterionIF.DATE.equalsIgnoreCase(this.getSearchValueDataType());
	}

	@Override
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	@Override
	public String getPropertyName() {
		return propertyName;
	}

	@Override
	public void setShortPropertyName(String shortPropertyName) {
		this.shortPropertyName = shortPropertyName;
	}

	@Override
	public String getShortPropertyName() {
		return this.shortPropertyName;
	}

	@Override
	public boolean hasShortPropertyName() {
		return this.shortPropertyName!=null && !this.shortPropertyName.trim().equals("");
	}

	@Override
	public void setHighPropertyName(String highPropertyName) {
		this.highPropertyName = highPropertyName;
	}

	@Override
	public String getHighPropertyName() {
		return this.highPropertyName;
	}

	@Override
	public boolean lessThanOrEqual() {
		return RestrictionTypeIF.LESS_OR_EQUAL.equalsIgnoreCase(this.getRestrictionType().getType());
	}

	@Override
	public boolean equal() {
		return RestrictionTypeIF.EQUAL.equalsIgnoreCase(this.getRestrictionType().getType());
	}

	@Override
	public boolean notEqual() {
		return RestrictionTypeIF.NOT_EQUAL.equalsIgnoreCase(this.getRestrictionType().getType());
	}

	@Override
	public boolean greaterThanOrEqual() {
		return RestrictionTypeIF.GREATER_OR_EQUAL.equalsIgnoreCase(this.getRestrictionType().getType());
	}

	@Override
	public boolean less() {
		return RestrictionTypeIF.LESS.equalsIgnoreCase(this.getRestrictionType().getType());
	}

	@Override
	public boolean greater() {
		return RestrictionTypeIF.GREATER.equalsIgnoreCase(this.getRestrictionType().getType());
	}

	@Override
	public boolean in() {
		return RestrictionTypeIF.IN.equalsIgnoreCase(this.getRestrictionType().getType());
	}

	@Override
	public void setSearchStartWith(boolean searchStartWith) {
		this.searchStartWith = searchStartWith;
	}

	@Override
	public boolean isNull() {
		return RestrictionTypeIF.IS.equalsIgnoreCase(this.getRestrictionType().getType());
	}

	@Override
	public boolean excludeNull() {
		return RestrictionTypeIF.IS_NOT_NULL.equalsIgnoreCase(this.getRestrictionType().getType());
	}

}
