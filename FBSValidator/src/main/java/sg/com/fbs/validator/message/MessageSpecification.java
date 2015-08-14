package sg.com.fbs.validator.message;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import sg.com.fbs.validator.annotations.design.AllowsConfigurationInjection;
import sg.com.fbs.validator.annotations.design.ExpectsInjection;
import sg.com.fbs.validator.core.Log;
import sg.com.fbs.validator.core.ResourceBundleLocator;

/**
 * @Author Frank Xu $
 * @Created 3:39:53 pm 6 Jul, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 * 
 * 
 * <p>Contains information about how to generate a message. 
 * This class knows how to create a message. 
 * It will look up the message in the resource bundle if it
 * starts with a "{". 
 * <p><p>
 * Future: It will look up the message in the 
 * EL context if it starts with a "#{"<p>
 * */
public class MessageSpecification implements Serializable{

	private static final long serialVersionUID = -8952804188674687399L;

	Log log = Log.getLog(MessageSpecification.class);
	
	private String detailMessage = "detailMessage";
	
    private String summaryMessage="summaryMessage";
    
    private String labelFields="labelFields";
	
    private List<String> detailArgs;
    
    private List<String> summaryArgs;
    
    private String name;
    
    private String parent;
    
    /** Used to determine if an argument refers to an expression like a
     * JSF expression, a universal EL expression, or an OGNL expression.
     * This is configurable and could be changed if you are working
     * with another expression language parser.
     */
    private String expressionMarker = "#{";
    
    /**
     * Used to determine if an argument or message refers to an item that
     * should be looked up in the resourceBundle. 
     */
    private String i18nMarker = "{";
    
    /**
     * Used to find the resource bundle. This can vary based on the
     * web framework you are using, and whether you want to use Spring
     * Message sources or not.
     */
    private ResourceBundleLocator resourceBundleLocator;
    
    /** Who is this message about? For example for field validation
     * the subject is the name of the field. 
     */
    private String subject = "";
    
	/** Holds the current subject. This allows this class to be stateless
	 * yet still allow us to change the subject on a per thread basis. */
    private ThreadLocal<String> subjectHolder = new ThreadLocal<String>();
    
    private static final String SUMMARY_KEY = ".summary";
    
    private static final String DETAIL_KEY = ".detail";
    
    /** The init method tries to generate the message keys.
     * You should only call the init method if you don't inject
     * values into the detailMessage and summaryMessage.
     *
     */
	public void init() {
		if (name == null && parent == null) {
			this.setDetailMessage("{"+this.getClass().getName()+DETAIL_KEY+"}");
			this.setSummaryMessage("{"+this.getClass().getName()+SUMMARY_KEY+"}");
		}else if (name != null && parent == null) {
			this.setDetailMessage("{" + "message." + getName() + DETAIL_KEY + "}");
            this.setSummaryMessage("{" + "message." + getName() + SUMMARY_KEY + "}");
		}else if (parent!=null) {
			this.setDetailMessage("{" + "message." + parent + DETAIL_KEY + "}");
            this.setSummaryMessage("{" + "message." + parent + SUMMARY_KEY + "}");
		}
	}

	public String createSummaryMessage(Object... args){
		return createMessage(summaryMessage, summaryArgs, args);
	}
	
    public String createDetailMessage(Object... args){
		return createMessage(detailMessage, detailArgs, args);
	}
    
    public String createMessage(String key, List<String> argKeys, Object... args){
    	String message = doGetMessage(key);
    	
    	Object[] actualArgs;
    	/* If they passed arguments, 
    	 * then use this as the actual arguments. */
    	if(args.length>0){
    		actualArgs = args;
    	}else if (argKeys!=null) {  /* If they did not pass arguments, use the configured ones. */
			actualArgs = keysToValues(argKeys);
		}else {
			actualArgs = new Object[]{};
		}
    	
    	return doCreateMessage(message, actualArgs); //equal to return message
    	 
    }
    
    // do nothing......
    private String doCreateMessage(String message, Object[] actualArgs){
    	List argumentList = new ArrayList(Arrays.asList(actualArgs));
    	/* If the subject is found add it as the first 
    	 * argument to the argument list. */
    	if(getSubject()!=null){
    		argumentList.add(0, MessageUtils.getLabel(getSubject(), resourceBundleLocator.getBundle()));
    	}
    	
    	return message;
    }
    
    private String getMessage(String key){
    	return doGetMessage(key);
    }
    
    /**Convert keys to values**/
    private Object[] keysToValues(List<String> argKeys){
    	List<String> values = new ArrayList<String>();
    	for (String key : argKeys) {
			values.add(getMessage(key));
		}
    	return values.toArray();
    }
    
    private String doGetMessage(String key){
    	ResourceBundle bundle = this.resourceBundleLocator.getBundle();
    	
    	String message = null;
    	/* If the message starts with an i18nMarker look it up
    	 * in the resource bundle.
    	 */
    	if(key.startsWith(i18nMarker)){
    		key = key.substring(1,key.length()-1);
    		try {
    			message = lookupMessageInBundle(key, bundle, message);
			} catch (MissingResourceException mre) {
				message = key;
			}   		  		
    	}else if (key.startsWith(expressionMarker)) {/* If the message starts with the expression marker
        	                                          * resolve it as an Expression (Universal, JSF, OGNL, etc.)
        	                                          */
			message = resolveExpression(key).toString();  //??
		}else {
			if(key.contains(".")){
				try {
                    key = key.substring(1, key.length()-1);
                    message = lookupMessageInBundle(key, bundle, message);
                } catch (MissingResourceException mre) {
                    message = key;
                }
			}else {
				message = key;
			}
		}
    	
    	return message;
    }
    
    private String lookupMessageInBundle(String key, ResourceBundle bundle, String message){
    	if(getSubject()!=null){
    		try {
				message = bundle.getString(key+"."+getSubject());
			} catch (MissingResourceException mre) {
				message = bundle.getString(key);
			}
    	}else {
			return bundle.getString(key);
		}
    	return message;
    }

	private Object resolveExpression(String key){
		throw new UnsupportedOperationException("future resolve expressions from Universal EL or OGNL");
	}
	
	public String getDetailMessage() {
		return detailMessage;
	}

	@AllowsConfigurationInjection
	public void setDetailMessage(String detailMessage) {
		this.detailMessage = detailMessage;
	}

	public String getSummaryMessage() {
		return summaryMessage;
	}

	@AllowsConfigurationInjection
	public void setSummaryMessage(String summaryMessage) {
		this.summaryMessage = summaryMessage;
	}

	public String getLabelFields() {
		return labelFields;
	}

	@AllowsConfigurationInjection
	public void setLabelFields(String labelFields) {
		this.labelFields = labelFields;
	}

	public List<String> getDetailArgs() {
		return detailArgs;
	}

	@AllowsConfigurationInjection
	public void setDetailArgs(List<String> detailArgs) {
		this.detailArgs = detailArgs;
	}

	public List<String> getSummaryArgs() {
		return summaryArgs;
	}

	@AllowsConfigurationInjection
	public void setSummaryArgs(List<String> summaryArgs) {
		this.summaryArgs = summaryArgs;
	}

	public String getName() {
		return name;
	}

	@AllowsConfigurationInjection
	public void setName(String name) {
		this.name = name;
	}

	public String getParent() {
		return parent;
	}

	@AllowsConfigurationInjection
	public void setParent(String parent) {
		this.parent = parent;
	}

	public ResourceBundleLocator getResourceBundleLocator() {
		return resourceBundleLocator;
	}

	@ExpectsInjection
	public void setResourceBundleLocator(ResourceBundleLocator resourceBundleLocator) {
		this.resourceBundleLocator = resourceBundleLocator;
	}

	public String getSubject() {
		if(subjectHolder.get()!=null){
			return subjectHolder.get();
		}
		return subject;
	}
	
	/** Allows client objects to set the subject for the current thread
	 * per instance of the MessageSpecification. */
	public void setCurrentSubject(String subject){
		this.subjectHolder.set(subject);
	}
	
	@AllowsConfigurationInjection
	public void setSubject(String subject) {
		this.subject = subject;
	}
    
    
	
	
}

























