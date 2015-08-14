package sg.com.fbs.validator.web.validation.spring.support;

import java.util.Stack;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author Frank Xu $
 * @Created 5:47:12 pm 3 Jul, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public class SpringValidatorContext {

	private Stack<String> bindingPath = new Stack<String>();
	
	@Getter
	@Setter
	private Object parentObject;
	
	private static ThreadLocal<SpringValidatorContext> validatorContext = new ThreadLocal<SpringValidatorContext>();
	
	public SpringValidatorContext(){
		
	}
	
	public static SpringValidatorContext create(){
		validatorContext.set(new SpringValidatorContext());
		return get();
	}
	
	public static SpringValidatorContext get(){
		return validatorContext.get();
	}
	
	public static void destroy(){
		validatorContext.set(null);
	}
	
	public static String getBindingPath(){
		if(validatorContext.get()!=null){
			return validatorContext.get().calculateBindingPath();
		}
		return "";
	}
	
	private String calculateBindingPath(){
		StringBuilder builder = new StringBuilder(255);
		int index = 0;
		for(String component: bindingPath){
			index++;
			builder.append(component);
			if(index!=bindingPath.size()){
				builder.append('.');
			}
		}
		return builder.toString();
	}
	
	public void pop(){
		bindingPath.pop();
	}
	
	public void pushProperty(final String component){
		bindingPath.push(component);
	}
	
	public void pushObject(final Object object){
		String simpleName = object.getClass().getSimpleName();
		simpleName = simpleName.substring(0, 1).toLowerCase() + simpleName.substring(1, simpleName.length());    //can use StringUtils.unCapitialise()?
		bindingPath.push(simpleName);
	}
	
	public Object getProposedPropertyValue(String propertyName){
		BeanWrapper beanWrapper = PropertyAccessorFactory.forBeanPropertyAccess(parentObject);
		Object value = beanWrapper.getPropertyValue(propertyName);
		return value;
	}
}



















