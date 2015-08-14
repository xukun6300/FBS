package sg.com.fbs.core.techinfra.web.tag.grid;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 * @Author Frank Xu $
 * @Created 9:19:53 am 30 Jun, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public class GridCurrentRowNumber extends GridComponent{

	private static final long serialVersionUID = 9029516089977782408L;

	private static final String COLUMN_NAME = "GridCurrentRowNumber";
	@Override
	protected int renderStartTag() throws JspException {
		String result = renderCell();	
		write(result);
		return BodyTagSupport.SKIP_BODY;
	}

	@Override
	protected int renderEndTag() throws JspException {
		super.releaseResource();
		return BodyTagSupport.EVAL_PAGE;
	}

	@Override
	protected String getTagName() {
		return COLUMN_NAME;
	}
	
	public String renderCell(){
		String value = String.valueOf(getDataContext().getCurrentIndex()+(getDataContext().getCurrentPage()-1)*getDataContext().getPageSize());
		return value;
	}
	
	public void release(){
		super.release();
	}

}
