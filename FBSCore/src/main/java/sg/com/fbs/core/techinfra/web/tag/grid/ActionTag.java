package sg.com.fbs.core.techinfra.web.tag.grid;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.TagSupport;

import org.joda.time.DateTime;

import sg.com.fbs.core.techinfra.util.DateUtil;
import sg.com.fbs.core.techinfra.util.ResourceBundleUtil;
import sg.com.fbs.core.techinfra.util.StringUtil;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author Frank Xu $
 * @Created 3:31:59 pm 30 Jun, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public class ActionTag extends GridComponent{

	private static final long serialVersionUID = 5367895869793111874L;

	private static final String DEFAULT_DATE_FORMAT = "dd/MM/yyyy";
	
	private static final String DEFAULT_LONG_FORMAT ="0";
	
	private static final String DEFAULT_FLOAT_FORMAT ="#,###,##0.00";
	
	private static final String COLUMN_NAME ="ActionTag";
	
	private String replaceNull;
	
	private String index;

	private String name;

	private String keyName;

	private String codeSetID;

	private String codeSetIDConfigKey;

	private String format;

	protected String title;

	private String uri;

	protected String picName;

	private GridRows gridRows;

	private String javascript;

	private String target;

	private String buttonClass;

	private String iconClass;
	
	
	
	public String getReplaceNull() {
		return replaceNull;
	}

	public void setReplaceNull(String replaceNull) {
		this.replaceNull = replaceNull;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKeyName() {
		return keyName;
	}

	public void setKeyName(String keyName) {
		this.keyName = keyName;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getPicName() {
		return picName;
	}

	public void setPicName(String picName) {
		this.picName = picName;
	}

	public GridRows getGridRows() {
		return gridRows;
	}

	public void setGridRows(GridRows gridRows) {
		this.gridRows = gridRows;
	}

	public String getJavascript() {
		return javascript;
	}

	public void setJavascript(String javascript) {
		this.javascript = javascript;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getButtonClass() {
		return buttonClass;
	}

	public void setButtonClass(String buttonClass) {
		this.buttonClass = buttonClass;
	}

	public String getIconClass() {
		return iconClass;
	}

	public void setIconClass(String iconClass) {
		this.iconClass = iconClass;
	}

	public ActionTag() {
		name = null;
		title = null;
	}
	
    @Override
    protected void prepare() {
    	super.prepare();
    	gridRows = (GridRows) TagSupport.findAncestorWithClass(this, GridRows.class);
    }
	
    @Override
    public void release() {	
    	super.release();
    	name = null;
    	title = null;
    }
    
	@Override
	protected int renderStartTag() throws JspException {
		return BodyTagSupport.EVAL_BODY_AGAIN;  // need to change? SKIP_BODY?
	}

	@Override
	protected int renderEndTag() throws JspException {
		String value = renderCell();
		write(value);

		// release resource
		replaceNull = null;
		index = null;
		name = null;
		keyName = null;
		codeSetID = null;
		codeSetIDConfigKey = null;
		format = null;
		title = null;
		uri = null;
		picName = null;
		gridRows = null;
		javascript = null;
		target = null;
		buttonClass = null;
		iconClass = null;
		super.releaseResource();
		
		return BodyTagSupport.EVAL_PAGE;
	}

	@Override
	protected String getTagName() {
		return COLUMN_NAME;
	}
	
	public String renderCell(){
		StringBuffer buf = new StringBuffer();
		
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		String content = null;
		
		if(bodyContent!=null){
			content = bodyContent.getString();
		}

		if (content != null && !content.trim().equals("")) {
			this.setUri(content.trim());
		} else if (this.getUri() != null && !this.getUri().equals("")) {
			this.setUri(this.getUri() + "&" + getKeyName() + "=" + getValue());
		}

		if (this.getJavascript() != null && !this.getJavascript().equals("")) {
			buf.append("<button onClick=\"javascript:")
					.append(this.getJavascript()).append("\" class=\"").append(this.getButtonClass()).append("\" title=\"")
					.append(this.getTitle()).append("\" ><i class=\"").append(this.getIconClass()).append("\" ></i></button>");
			
			return buf.toString();
		}
		
		if(this.getUri()==null || this.getUri().trim().equals("")){
			buf.append("Please specify your URI");
		}else {
			buf.append("<button type=\"button\" onClick=\"javascript:changeScreen('").append(this.getUri());
			
			if(!StringUtil.isEmptyString(this.getTarget())){
				buf.append("', '"+this.getTarget());
			}
			
			buf.append("')\" class=\"").append(this.getButtonClass()).append("\" title=\"");
			
			if(ResourceBundleUtil.getMessage(this.getTitle())!=null){
				buf.append(ResourceBundleUtil.getMessage(this.getTitle()));
			}else{
				buf.append(this.getTitle());
			}
			
			buf.append("\" > <i class=\"").append(this.getIconClass()).append("\" ></i></button>");
			
		}
		
		return buf.toString();
	}

	public String getValue(){
		String value;
		
		if(this.getIndex()!=null && !this.getIndex().equals("")){
			int fieldIndex = Integer.parseInt(this.getIndex());
			value = getDataContext().getActualValueByIndex(fieldIndex).toString();
		}else {
			value = getDataContext().getValue(getName());
		}
		
		if(value==null || value.trim().length()<1){
			if(this.replaceNull!=null){
				return this.replaceNull;
			}else {
				return "";
			}
		}
		
		Object actualValue;
        if(this.getIndex()!=null&& !this.getIndex().equals("")){
        	int fieldIndex = Integer.parseInt(this.getIndex());
        	actualValue = getDataContext().getActualValueByIndex(fieldIndex);
        }else {
			actualValue = getDataContext().getActualValue(getName());
		}
		
        if(actualValue instanceof String){
        	return (String)actualValue;
        }
        
		if (actualValue instanceof DateTime) {
			if(format!=null && !format.trim().equals("")){
				return DateUtil.convertDateToDateString((DateTime)actualValue, format);
			}else{
				return DateUtil.convertDateToDateString((DateTime)actualValue, DEFAULT_DATE_FORMAT);
			}
			
		}

		if (actualValue instanceof Long) {
			NumberFormat formatter;
			if (format != null && !format.trim().equals("")) {
				formatter = new DecimalFormat(format);
			} else {
				formatter = new DecimalFormat(DEFAULT_LONG_FORMAT);
			}
			return formatter.format(actualValue);
		}

		if (actualValue instanceof Float) {
			NumberFormat formatter;
			if (format != null && !format.trim().equals("")) {
				formatter = new DecimalFormat(format);
			} else {
				formatter = new DecimalFormat(DEFAULT_FLOAT_FORMAT);
			}
			return formatter.format(actualValue);
		}

		return value;
	}
	
	
	
}






















