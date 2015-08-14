package sg.com.fbs.core.techinfra.web.validation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import sg.com.fbs.core.techinfra.util.StringUtil;

/**
 * @Author Frank Xu $
 * @Created 1:50:24 pm 3 Jul, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public class AnnotationData {

	private Annotation annotation;
	
	private String annotationClassName;
	
	private String annotationSimpleName;
	
	private String annotationPackageName;
	
	private Set<String> allowedAnnotations;
	
	private String name;
	
	private Map<String, Object> values;
	
	public AnnotationData(Annotation annotation, Set<String> allowedAnnotations){
		this.annotationSimpleName = annotation.annotationType().getSimpleName();
		this.annotationClassName = annotation.annotationType().getName();
		this.annotationPackageName = annotationClassName.substring(0, annotationClassName.length()-annotationSimpleName.length()-1);
		this.annotation = annotation;
		this.allowedAnnotations = allowedAnnotations;
		this.name = StringUtil.unCapitalize(annotationSimpleName);
		this.values = doGetValues();
	}
	
	public AnnotationData(Annotation annotation){
		this(annotation, new HashSet<String>());
	}
	
   /**
     *  Determines if this is an annotation we care about. 
     * Checks to see if the package name is in the set.
     **/
	public boolean isAllowed(){
		return this.allowedAnnotations.contains(annotationPackageName);
	}
	
	public String getName(){
		return this.name;
	}
	
	public Map<String, Object> getValues(){
		return this.values;
	}
	
	/**
     * Get the values from the annotation.
     * We use reflection to turn the annotation into a simple HashMap of values. 
     */
	private Map<String, Object> doGetValues(){
		Map<String, Object> values = new HashMap<String, Object>();
		
		//all methods declared in @Length annotation interface
		Method[] methods = this.annotation.annotationType().getDeclaredMethods();
		final Object[] noargs = (Object[])null;
		
		/* 
		 * Iterate through declared methods and extract values
         * by invoking decalared methods if they are no arg methods.
         */
		for (Method method : methods) {
			if(method.getParameterTypes().length==0){
				try {
					Object value = method.invoke(annotation, noargs);
					values.put(method.getName(), value);
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
				
			}
		}
		
		return values;
	}
	
 }
























