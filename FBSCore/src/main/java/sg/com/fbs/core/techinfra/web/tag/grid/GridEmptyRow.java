package sg.com.fbs.core.techinfra.web.tag.grid;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import lombok.Getter;
import lombok.Setter;

import org.apache.log4j.Logger;



/**
 * @Author Frank Xu
 * @Created 2:30:20 pm 24 Jun, 2015
 * 
 */
public class GridEmptyRow extends GridComponent{

	private static final long serialVersionUID = -4694611804206332840L;

	protected Logger logger = Logger.getLogger(GridEmptyRow.class);
	
	@Setter
	@Getter
	private String display;
	
	@Setter
	@Getter
	private int columnNumber;
	
	private static final String COLUMNS_NAME = "GridRows";
	
	@Override
	protected int renderStartTag() throws JspException {
		return BodyTagSupport.SKIP_BODY;
	}

	@Override
	protected int renderEndTag() throws JspException {
		display = null;
		super.releaseResource();
		return BodyTagSupport.EVAL_PAGE;
	}

	@Override
	protected String getTagName() {
		return COLUMNS_NAME;
	}

}
