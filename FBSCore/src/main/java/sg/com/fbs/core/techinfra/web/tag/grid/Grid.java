package sg.com.fbs.core.techinfra.web.tag.grid;

import java.util.Collection;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyTagSupport;

import lombok.Getter;
import lombok.Setter;

import org.apache.log4j.Logger;

import sg.com.fbs.core.techinfra.util.ResponseUtils;
import sg.com.fbs.core.techinfra.util.TagUtils;
import sg.com.fbs.core.techinfra.web.BaseWebFormIF;
import sg.com.fbs.model.system.persistence.response.ResponseCRUD;

public class Grid extends BodyTagSupport{

	private static final long serialVersionUID = -701531871933665834L;

	private Logger logger = Logger.getLogger(Grid.class);

	public final String TYPE_SEARCH_RESULT ="searchResult";
	
	private String type = TYPE_SEARCH_RESULT;
	
	public final String TYPE_INTERNAL_DATA = "internalData";
	
	public final String TYPE_INTERNAL_FROM_DATA = "internalFormData";
	
	public static final String NO_RECORD_MESSAGE = "Record not found.";

	@Setter
	@Getter
	private int currentState;
	
	private StringBuffer contentBuffer;
	
	@Setter
	@Getter
	private GridDataContext dataContext;
	
	@Setter
	@Getter
	private boolean resultVisibleFlag = true;
	
	@Setter
	@Getter
	private boolean fetchAllPage = true;
	
	@Setter
	@Getter
	private String queryFormName; 
	
	@Setter
	@Getter
	private String action;
	
	@Setter
	@Getter
	private String displayEmpty;
	
	@Setter
	@Getter
	private String searchResultKey;
	
	@Setter
	@Getter
	private boolean isAjax;
	
	@Setter
	@Getter
	private String mapKey;
	
	
	public Grid(){
		dataContext = null;
		contentBuffer = null;
		currentState = -1;
	}
	
	@Override
	public int doStartTag(){
		logger.debug("------ in doStartTag ------");
		
		initGrid();
		
		if(TYPE_SEARCH_RESULT.equals(type) && dataContext!=null && dataContext.getResult()!=null && dataContext.getTotalRecordFound()==0){
			
			write("<span id=\"noResult\" style=\"margin-left: 25px\" >"+ NO_RECORD_MESSAGE +"</span>");
			return BodyTagSupport.SKIP_BODY;
		}
		
		if(TYPE_SEARCH_RESULT.equals(type) && dataContext !=null && dataContext.isMaxRecordRetreivedExceeded()){
			write("<span id=\"noResult\" style=\"margin-left: 25px\" >"+ NO_RECORD_MESSAGE +"</span>");
			return BodyTagSupport.SKIP_BODY;
		}
		
		if(dataContext == null){
			return BodyTagSupport.SKIP_BODY;
		}
		
		if (dataContext != null && dataContext.getResult() == null) {
			return BodyTagSupport.SKIP_BODY;
		}
		
		return BodyTagSupport.EVAL_BODY_INCLUDE;
	}
	
	@Override   
	public int doAfterBody(){       //think no need here
		logger.debug("------ in doAfterBody ------");
		return BodyTagSupport.SKIP_BODY;
	}
	
	@Override
	public int doEndTag(){
		logger.debug("------ in doEndTag ------");
		
		//release resource
		contentBuffer = null;
		dataContext = null;
		queryFormName = null;
		action = null;
		displayEmpty = null;
		searchResultKey = null;
		mapKey = null;
		
		return BodyTagSupport.EVAL_PAGE;
	}
	
	private void initGrid(){
		if(!this.resultVisibleFlag){
			return;
		}
		
		if(TYPE_SEARCH_RESULT.equals(type)){
			BaseWebFormIF form = getQueryForm(queryFormName);
			ResponseCRUD response = (ResponseCRUD) form.getCrudResponse();
			this.dataContext = new GridDataContext(response);
			//?
			this.dataContext.setResultQualifierName(form.getResultQualifierName());
			
		}else if (TYPE_INTERNAL_DATA.equals(type)) {
			Collection internalData = (Collection) pageContext.getAttribute(searchResultKey);
			this.dataContext = new GridDataContext(internalData);
			
		}else if (TYPE_INTERNAL_FROM_DATA.equals(type)) {
			Collection internalData = (Collection) getFormPropertyValue();
			this.dataContext = new GridDataContext(internalData);
		}
	}
	
	private Object getFormPropertyValue(){
		Object result = null;
		try {
			result = TagUtils.lookup(pageContext, queryFormName, searchResultKey, null);
		} catch (JspException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	private BaseWebFormIF getQueryForm(String queryFormName){
		BaseWebFormIF form = (BaseWebFormIF) pageContext.getAttribute(queryFormName,PageContext.REQUEST_SCOPE);
		
		if(form==null){
			form = (BaseWebFormIF)pageContext.getAttribute(queryFormName, PageContext.SESSION_SCOPE);
		}
		return form;
	}
	
	
	protected final void write(String text){
		try {
			ResponseUtils.write(pageContext, text);
		} catch (JspException e) {
			e.printStackTrace();
		}
	}
	
	public final String getContent(){
		if(this.contentBuffer == null){
			return null;
		}else {
			return contentBuffer.toString();
		}
	}
	
	public void addContent(String content){
		if(contentBuffer == null){
			contentBuffer = new StringBuffer();
		}
		contentBuffer.append(content);
	}
	

}






































