package sg.com.fbs.core.techinfra.web.tag;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.Tag;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import sg.com.fbs.core.techinfra.util.ResponseUtils;

/**
 * @author Frank Xu
 *
 */
public abstract class StructuredBaseTag extends BodyTagSupport{

	private static final long serialVersionUID = -8457531007003920140L;
    
	private Logger logger = Logger.getLogger(StructuredBaseTag.class);
	
	protected static final String EMPTY_STRING = "";
	
	private StringBuffer contentBuffer;
	
	public StructuredBaseTag(){
		this.contentBuffer = null;
	}
	
	public void addContent(String content){
		if(contentBuffer == null){
			this.contentBuffer = new StringBuffer();
		}else {
			this.contentBuffer.append(content);
		}
	}
	
	public String getBeanValueSafe(String beanName, String beanProperty){
		String retVal = "";
		
		Object bean = pageContext.getAttribute(beanName,PageContext.REQUEST_SCOPE);
		if(bean == null){
			bean = pageContext.getAttribute(beanName, PageContext.SESSION_SCOPE);
		}
		
		if(bean instanceof String){
			return (String) bean;
		}
		
		if(bean == null){
			return retVal;
		}
		
		try {
			retVal = BeanUtils.getProperty(bean, beanProperty);
		} catch (IllegalAccessException e) {
			logger.error(e.getMessage());
		} catch (InvocationTargetException e) {
			logger.error(e.getMessage());
		} catch (NoSuchMethodException e) {
			logger.error(e.getMessage());
		}
		return retVal;
	}
	
	
	public String getBeanValue(String beanName, String beanProperty){
		String retVal = "";
		
		Object bean = pageContext.getAttribute(beanName, PageContext.REQUEST_SCOPE);	
		
		if(bean==null){
			bean = pageContext.getAttribute(beanName, PageContext.SESSION_SCOPE);
		}
		
		if(bean instanceof String){
			return (String) bean;
		}
		
		try {
			retVal = BeanUtils.getProperty(bean, beanProperty);
		} catch (IllegalAccessException e) {
			logger.error(e.getMessage());
		} catch (InvocationTargetException e) {
			logger.error(e.getMessage());
		} catch (NoSuchMethodException e) {
			logger.error(e.getMessage());
		}
		
		return retVal;
		
	}
	
	public String getContent(){
		if(contentBuffer==null){
			return null;
		}else {
			return contentBuffer.toString();
		}
	}
	
	@Override
	public int doStartTag() throws JspException{
		int ret = 0;
		
		prepare();
		verifyAttributes();
		
		try {
			ret = renderStartTag();	
		} catch (Exception e) {
			logger.error(e.getMessage());
			String msg = "An exception occurred when rendering the startTag of the tag \"" + getTagName() + "\" because: " + e.getMessage();   
            throw new JspException(msg, e);     
		}
		
		return ret;
	}
	
	@Override
	public int doEndTag() throws JspException{
		int ret = 0;
		
		try {		
			ret = renderEndTag();	
		} catch (Exception e) {
			logger.error(e.getMessage());
			String msg = "An exception occurred when rendering the endTag of the tag \"" + getTagName() + "\" because: " + e.getMessage();   
            throw new JspException(msg, e);     
		} finally{
			
			//release resource
			contentBuffer = null;
		}
		
		return ret;
	}
	
	public void release(){
		super.release();
		contentBuffer = null;
	}
	
	protected void prepare() {
		
	}
	
	protected void verifyAttributes() throws JspException{
		
	}
	
	protected abstract int renderStartTag() throws JspException;
	
	protected abstract int renderEndTag() throws JspException;
	
	protected abstract String getTagName();
	
	protected abstract Class getValidContainerType(); 
	
	protected final void write(String text){
		try {
			ResponseUtils.write(pageContext, text);
		} catch (JspException e) {
			logger.error(e.getMessage());
		}
		
	}
	
	//?
	protected final void verifyStructure() throws JspException{
		Tag parent = getParent();
		Class validContainer = getValidContainerType();
		
		if(validContainer !=null &&parent==null || validContainer!=null &&!parent.getClass().isAssignableFrom(validContainer)){
			String msg = "A tag of type \"" + getClass().getName() + "\" must be nested within a tag of type \"" + getValidContainerType().getName() + "\"";	
			throw new JspException(msg);
		}
	}
	
	
	
	
	
}























