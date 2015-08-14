package sg.com.fbs.core.techinfra.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

import sg.com.fbs.core.techinfra.exception.ValidationException;
import sun.security.util.Cache.EqualByteArray;

/**
 * @Author Frank Xu $
 * @Created 3:01:50 pm 3 Jul, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public final class TypeUtils {

	private static final String CLASSTYPE_PROPNAM_MUST_NOT_BE_NULL = "Class type and propertyName must not be null, type=%s, propertyName=%s";
	
	private static final String PROPERTY_WAS_NOT_NULL = "The Property was not found!, type=%s, propertyName=%s";
	
	
	public static PropertyDescriptor getPropertyDescriptor(final Class<?> type, final String propertyName){
		if(type ==null ||propertyName == null){
			throw new ValidationException(CLASSTYPE_PROPNAM_MUST_NOT_BE_NULL,type,propertyName);
		}
		
		if(!propertyName.contains(".")){
			return doGetPropertyDescriptor(type, propertyName);
		}else {
			String[] propertyNames = propertyName.split("[.]");
			Class<?> clazz = type;  //???
			PropertyDescriptor propertyDescriptor = null;
			for (String pName : propertyNames) {
				propertyDescriptor = doGetPropertyDescriptor(clazz, pName);
				if(propertyDescriptor == null){
					return null;
				}
				clazz = propertyDescriptor.getPropertyType();				
			}
			return propertyDescriptor;
		}
	}
	
	private static PropertyDescriptor doGetPropertyDescriptor(final Class<?> type, final String propertyName){
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(type);
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			
			for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
				if(propertyDescriptor.getName().equals(propertyName)){
					return propertyDescriptor;
				}
			}
			return null;
		} catch (Exception ex) {
			throw new RuntimeException("Unable to get property " + propertyName + " for class " + type, ex);
		}
	}
	
}












































