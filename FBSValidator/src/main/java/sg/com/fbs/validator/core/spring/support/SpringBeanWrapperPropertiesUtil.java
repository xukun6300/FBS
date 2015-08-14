package sg.com.fbs.validator.core.spring.support;

import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import sg.com.fbs.validator.core.PropertiesUtil;

/**
 * @Author Frank Xu $
 * @Created 5:04:31 pm 3 Jul, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public class SpringBeanWrapperPropertiesUtil implements PropertiesUtil,Serializable{

	private static final long serialVersionUID = -2068889055060256523L;

	@Override
	public void copyProperties(Object object, Map<String, Object> properties) {
		BeanWrapper beanWrapper = new BeanWrapperImpl(object);
		
		Set<Entry<String, Object>> props = properties.entrySet();
		for (Entry<String, Object> entry : props) {
			if(beanWrapper.isWritableProperty(entry.getKey())){
				beanWrapper.setPropertyValue(entry.getKey(), entry.getValue());
			}
		}
	}

	@Override
	public Map<String, Object> getObjectPropertiesAsMap(Object object) {
		BeanWrapper beanWrapper = new BeanWrapperImpl(object);
		Map<String, Object> properties = new HashMap<String, Object>();
		
		PropertyDescriptor[] propertyDescriptors = beanWrapper.getPropertyDescriptors();
		for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
			String name = propertyDescriptor.getName();
			properties.put(name, beanWrapper.getPropertyValue(name));
		}
		return properties;
	}

	@Override
	public Object getPropertyValue(String propertyName, Object object) {
		BeanWrapper beanWrapper = new BeanWrapperImpl(object);
		return beanWrapper.getPropertyValue(propertyName);
	}

}



























