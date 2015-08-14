package sg.com.fbs.core.techinfra.web.tag.grid;


import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import sg.com.fbs.core.techinfra.util.StringUtil;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author Frank Xu $
 * @Created 9:26:12 am 26 Jun, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public class GridRows extends GridComponent {

	private static final long serialVersionUID = -7658920727672153648L;
    
	private static final String COLUMNS_NAME = "GridRows";
	
	@Setter
	@Getter
	private String id;
	
	public GridRows(){
		
	}
	
	@Override
	protected int renderStartTag() throws JspException {
		
		if (getDataContext() != null && getDataContext().hasNext()) {
			Object element = getDataContext().next();
			
			if (!StringUtil.isEmptyString(id)) {
				if (element == null) {
					pageContext.removeAttribute(id);
				} else {
					pageContext.setAttribute(id, element);
				}
			}
			return BodyTagSupport.EVAL_BODY_INCLUDE; //when return EVAL_BODY_INCLUDE, doAfterBody() is invoked and, 
			                                         //after zero or more iterations, doEndTag() is invoked.
		}
		return BodyTagSupport.SKIP_BODY;   //If SKIP_BODY is returned the body is not evaluated, and then doEndTag() is invoked.
	}

	@Override
	protected int renderEndTag() throws JspException {
		id = null;
		super.releaseResource();
		return BodyTagSupport.EVAL_PAGE;
	}

	/**
	 *  If doAfterBody() returns IterationTag.EVAL_BODY_AGAIN, then the body will be reevaluated. 
	 *  If doAfterBody() returns Tag.SKIP_BODY, then the body will be skipped and doEndTag() will be evaluated instead.
	 */
	@Override
	public int doAfterBody() throws JspException {
		if(getDataContext().hasNext()){
			Object element = getDataContext().next();
			if(!StringUtil.isEmptyString(id)){
				if(element==null){
					pageContext.removeAttribute(id);
				}else {
					pageContext.setAttribute(id, element);
				}
			}
			return BodyTagSupport.EVAL_BODY_AGAIN; // only returned from doAfterBody() //suppose to do the iteration from here, if hasNext then render the body again with new row
		}else {
			return BodyTagSupport.EVAL_PAGE;
		}
	};
	
	@Override
	protected String getTagName() {
		return COLUMNS_NAME;
	}
	
	public void release(){
		super.release();
	}

}
