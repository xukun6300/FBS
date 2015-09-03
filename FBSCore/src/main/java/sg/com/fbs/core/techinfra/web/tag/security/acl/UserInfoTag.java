package sg.com.fbs.core.techinfra.web.tag.security.acl;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.lang.StringEscapeUtils;

import sg.com.fbs.core.techinfra.security.util.PrincipalSecUtil;
import sg.com.fbs.core.techinfra.util.ResponseUtils;

/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu $
 * @Created 5:23:15 pm 1 Sep, 2015 $
 * 
 */
public class UserInfoTag extends BodyTagSupport{

	private static final long serialVersionUID = -3396217687780448843L;

	@Override
	public int doStartTag() throws JspException {

		return BodyTagSupport.SKIP_BODY;
	}
	
	@Override
	public int doAfterBody() throws JspException {

		return BodyTagSupport.SKIP_BODY;
	}
	
	@Override
	public int doEndTag() throws JspException {	
		String username = "";
		
		if(PrincipalSecUtil.getName()!=null){
			username = PrincipalSecUtil.getName();
			username = StringEscapeUtils.escapeXml(username);// to prevent XSS attack
		}
		
		ResponseUtils.write(pageContext, username);
		return BodyTagSupport.SKIP_BODY;
	}
}
