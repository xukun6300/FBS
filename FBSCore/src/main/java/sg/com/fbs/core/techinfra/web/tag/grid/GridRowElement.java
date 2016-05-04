package sg.com.fbs.core.techinfra.web.tag.grid;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.TagSupport;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.StringEscapeUtils;
import org.joda.time.DateTime;


import sg.com.fbs.core.techinfra.util.DateUtil;


/**
 * @Author Frank Xu $
 * @Created 3:11:09 pm 26 Jun, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public class GridRowElement extends GridComponent{

	private static final long serialVersionUID = -8735248197441070068L;

	public static final String DEFAULT_DATE_FORMAT = "dd/MM/yyyy";
	
	public static final String DEFAULT_LONG_FORMAT = "0";
	
	public static final String DEFAULT_FLOAT_FORMAT = "#,###,##0.00";
	
	public static final String COLUMN_NAME = "GridRowElement";

    private String replaceNull;

    private String name;
 
    private String codeSetID;

    private String codeSetIDConfigKey;

    private String format;
  
    private String index;

    private boolean repeatable = true;

    private String repeatableChkField;
 
    private GridRows gridRows;

    private String uri;
 
    private String keyName;
 
    public String getReplaceNull() {
		return replaceNull;
	}

	public void setReplaceNull(String replaceNull) {
		this.replaceNull = replaceNull;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCodeSetID() {
		return codeSetID;
	}

	public void setCodeSetID(String codeSetID) {
		this.codeSetID = codeSetID;
	}

	public String getCodeSetIDConfigKey() {
		return codeSetIDConfigKey;
	}

	public void setCodeSetIDConfigKey(String codeSetIDConfigKey) {
		this.codeSetIDConfigKey = codeSetIDConfigKey;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public boolean isRepeatable() {
		return repeatable;
	}

	public void setRepeatable(boolean repeatable) {
		this.repeatable = repeatable;
	}

	public GridRows getGridRows() {
		return gridRows;
	}

	public void setGridRows(GridRows gridRows) {
		this.gridRows = gridRows;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getKeyName() {
		return keyName;
	}

	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	public boolean isHtmlEscape() {
		return htmlEscape;
	}

	public void setHtmlEscape(boolean htmlEscape) {
		this.htmlEscape = htmlEscape;
	}

	public String getRepeatableChkField() {
		return repeatableChkField;
	}

	private boolean htmlEscape = true; 
    
    public GridRowElement(){
    	this.name = null;
    }
    
    protected void prepare() {
		super.prepare();
		//Find the instance of a GridRows that is closest to this GridRowElement instance
		gridRows = (GridRows)TagSupport.findAncestorWithClass(this, GridRows.class);
	}
    
    public void release(){
    	super.release();
    }
    
	@Override
	protected int renderStartTag() throws JspException {
		StringBuilder urlBuilder = new StringBuilder();
		
        String result = renderCell();
        if(htmlEscape){
        	result=StringEscapeUtils.escapeXml(result); //to prevent XSS attack
        }
        
        try {
     
        	if(this.uri!=null && !this.uri.trim().equals("")){
        		this.setUri(getUri()+"&"+getKeyName()+"="+getValue());
        		
        		urlBuilder.append("<a href=\"");
        		urlBuilder.append(getUri());
        		urlBuilder.append("\">");
        		urlBuilder.append(result);
        		urlBuilder.append("</a>");
        		
        		result=urlBuilder.toString();
        	}
        	write(result);
		} catch (Exception e) {
			
		}		
		return BodyTagSupport.SKIP_BODY;
	}

	@Override
	protected int renderEndTag() throws JspException {

		// release resource
		replaceNull = null;
		name = null;
		codeSetID = null;
		codeSetIDConfigKey = null;
		format = null;
		index = null;
		repeatableChkField = null;
		gridRows = null;
		uri = null;
		keyName = null;
		htmlEscape = true;
		super.releaseResource();

		return BodyTagSupport.EVAL_PAGE;
	}

	@Override
	protected String getTagName() {
		return COLUMN_NAME;
	}
	
	/**
	 * use apache.commons.beanutils to get 
	 * property(raw type)/property value(string) to render the cell.
	 * 
	 * bean is get through responseCRUD.getQueryResult() iterator. (each row is treated as one bean)
	 * property name is get from tag attribute.
	 */
	public String renderCell(){
		String value = null;
		Object actualValue = null;
		
		if (this.index != null && !this.index.equals("")) {
			int fieldIndex = Integer.parseInt(this.index);
			actualValue = getDataContext().getActualValueByIndex(fieldIndex);
			value = formatValue(actualValue);
		} else {
			actualValue = getDataContext().getActualValue(getName());
			value = getValueReplaceNull();
			
			if(repeatable){
				return formatValue(actualValue); //normally will return from here
			}
			
			Object previousValue = getDataContext().getActualPreviousValue(getName());
			if(previousValue==null){
				previousValue="";
			}
			if(actualValue==null){
				actualValue="";
			}
			
			if(!previousValue.equals(actualValue)){
				return (String) actualValue;
			}
			
			if(repeatableChkField==null || repeatableChkField.equals("")){
				return "";
			}
			
			Object previousChkFieldValue = getDataContext().getActualPreviousValue(this.repeatableChkField);
			Object curChkFieldValue = getDataContext().getActualValue(this.repeatableChkField);
			
			if (previousChkFieldValue == null) {
				previousChkFieldValue = "";
			}
			if (curChkFieldValue == null) {
				curChkFieldValue = "";
			}

			if(curChkFieldValue==null||curChkFieldValue.equals("")){
				return "";
			}
			
			if(previousChkFieldValue.equals(curChkFieldValue)){
				return "";
			}
			
			return (String) actualValue;
			
		}
		
		if(value==null){
			value ="";
		}
		return value;
	}

	public String getValueReplaceNull(){
		String value = getDataContext().getValue(getName());
		
		if(value==null || value.trim().length()==0){
			if(this.replaceNull!=null){
				return this.replaceNull;
			}else {
				return "";
			}
		}
		return value;
	}
	
	private String formatValue(Object actualValue){
		
		if(actualValue instanceof DateTime){
			if(format!=null && !format.trim().equals("")){
				return DateUtil.convertDateToDateString((DateTime)actualValue, format);
			}
			return DateUtil.convertDateToDateString((DateTime)actualValue, DEFAULT_DATE_FORMAT);
		}
		
		if(actualValue instanceof Date){
			if(format!=null && !format.trim().equals("")){
				return DateUtil.dateToString((Date)actualValue, format);
			}
			return DateUtil.dateToString((Date)actualValue, DEFAULT_DATE_FORMAT);
		}
		
		if(actualValue instanceof Long){
			NumberFormat formatter;
			if(format!=null && !format.trim().equals("")){
				formatter = new DecimalFormat(format);
			}else {
				formatter = new DecimalFormat(DEFAULT_LONG_FORMAT);
			}
			return formatter.format(actualValue);
		}
		
		if(actualValue instanceof Float || actualValue instanceof Double ||actualValue instanceof BigDecimal){
			NumberFormat formatter;
			if(format!=null && !format.trim().equals("")){
				formatter = new DecimalFormat(format);
			}else {
				formatter = new DecimalFormat(DEFAULT_FLOAT_FORMAT);
			}
			return formatter.format(actualValue);
		}
		
		if(actualValue!=null){
			return actualValue.toString();
		}
		
		return "";		
	}
	
	public String getValue(){
		String value = getDataContext().getValue(getKeyName());
		
		if(value == null ||value.length()<1){
			if(this.replaceNull!=null){
				return replaceNull;
			}else {
				return "";
			}
		}
		
		Object actualValue = getDataContext().getActualValue(getKeyName());
		if(actualValue instanceof String){
			return (String)actualValue;
		}
		
		if (actualValue instanceof DateTime) {
			if (format != null && !format.trim().equals("")){
				return DateUtil.convertDateToDateString((DateTime) actualValue,format);
			}	
			return DateUtil.convertDateToDateString((DateTime) actualValue,DEFAULT_DATE_FORMAT);
		}

		if (actualValue instanceof Long) {
			NumberFormat formatter;
			if (format != null && !format.trim().equals("")){
				formatter = new DecimalFormat(format);
			}else {
				formatter = new DecimalFormat(DEFAULT_LONG_FORMAT);
			}

			return formatter.format(getDataContext().getActualValue(getKeyName()));
		}

		if (actualValue instanceof Float) {
			NumberFormat formatter;
			if (format != null && !format.trim().equals("")){
				formatter = new DecimalFormat(format);
			}else {
				formatter = new DecimalFormat(DEFAULT_FLOAT_FORMAT);
			}
			return formatter.format(getDataContext().getActualValue(getKeyName()));
		}

		return value.toString();		
	}
	
    public void setRepeatableChkField(String repeatableChkField) {
		this.repeatableChkField = repeatableChkField;
		if(this.repeatableChkField!=null && !this.repeatableChkField.equals("")){
			this.repeatable = false;
		}
	}

	
	
}






















