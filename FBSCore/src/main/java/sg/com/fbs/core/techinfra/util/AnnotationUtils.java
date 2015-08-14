package sg.com.fbs.core.techinfra.util;

import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import sg.com.fbs.core.techinfra.exception.ValidationException;
import sg.com.fbs.core.techinfra.web.validation.AnnotationData;

/**
 * @Author Frank Xu $
 * @Created 2:21:39 pm 3 Jul, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public class AnnotationUtils {

	protected static Logger logger = Logger.getLogger(AnnotationUtils.class);
	
	public static List<AnnotationData> getAnnotationDataForProperty(Class<?> clazz, String propertyName, boolean useReadMethod, Set<String> allowedPackages){
		Annotation[] annotations = extractAllAnnotationsForProperty(clazz, propertyName, useReadMethod);
		return extractValidationAnnotationData(annotations, allowedPackages);
	}
	
	
	private static List<AnnotationData> extractValidationAnnotationData(Annotation[] annotations, Set<String> allowedPackages){
		List<AnnotationData> annotationDataList = new ArrayList<AnnotationData>();
		
		for (Annotation annotation : annotations) {
			AnnotationData annotationData = new AnnotationData(annotation, allowedPackages);
			if(annotationData.isAllowed()){
				annotationDataList.add(annotationData);
			}
		}
		return annotationDataList;
	}

	 /**
     * Extract all annotations for a given property.
     * Searches current class and if none found searches super class for annotations.
     * We do this because the class could be proxied with AOP.
     */
	@NeedsRefactoring("There has to be a better way to do this. Read comments about potential bug.")
	private static Annotation[] extractAllAnnotationsForProperty(Class<?> clazz, String propertyName, boolean useRead) {
		try {
			Annotation[] annotations = findPropertyAnnotations(clazz, propertyName, useRead);
			/*
			 * In the land of dynamic proxied AOP classes, this class could be a
			 * proxy. This seems like a bug waiting to happen. So far it has worked...
			 */
			if (annotations.length == 0) {
				annotations = findPropertyAnnotations(clazz.getSuperclass(), propertyName, useRead);
			}

			return annotations;
		} catch (Exception ex) {
			throw new ValidationException(ex, "Unable to extract annotations for property %s of class %s. useRead = %s", propertyName, clazz, useRead);
		}
	}
	
	
	private static Annotation[] findPropertyAnnotations(Class<?> clazz, String propertyName, boolean useRead){
		PropertyDescriptor propertyDescriptor = TypeUtils.getPropertyDescriptor(clazz, propertyName);
		
		if(propertyDescriptor==null){
			return new Annotation[]{};
		}
		
		Method accessMethod = null;
		if(useRead){
			accessMethod = propertyDescriptor.getReadMethod();
		}else {
			accessMethod = propertyDescriptor.getWriteMethod();
		}
		
		if(accessMethod!=null){
			Annotation[] annotations = accessMethod.getAnnotations();
			return annotations;
		}else {
			return new Annotation[]{};
		}
		
		
	}
	
	
}


















































