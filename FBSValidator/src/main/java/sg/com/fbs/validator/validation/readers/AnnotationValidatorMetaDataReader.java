package sg.com.fbs.validator.validation.readers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import sg.com.fbs.core.techinfra.util.AnnotationUtils;
import sg.com.fbs.core.techinfra.util.NeedsRefactoring;
import sg.com.fbs.core.techinfra.web.validation.AnnotationData;
import sg.com.fbs.validator.annotations.design.AllowsConfigurationInjection;
import sg.com.fbs.validator.annotations.design.Implements;
import sg.com.fbs.validator.validation.ValidatorMetaData;
import sg.com.fbs.validator.validation.ValidatorMetaDataReader;


/**
 * @Author Frank Xu $
 * @Created 11:39:27 am 3 Jul, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public class AnnotationValidatorMetaDataReader implements ValidatorMetaDataReader,Serializable {

	private static final long serialVersionUID = 2958113842332058832L;

	private Logger logger = Logger.getLogger(AnnotationValidatorMetaDataReader.class);
	
	/** 
	 * Holds a cache of meta-data to reduce parsing with regex and to avoid reflection. 
     * Since this could get hit by multiple threads. I made it threadsafe.
     * */
	private static Map<String, List<ValidatorMetaData>> metaDataCache = Collections.synchronizedMap(new HashMap<String, List<ValidatorMetaData>>());
	
	
	/** 
	 * Holds a list of pacakges that contain annotations that we will process.
     * If the annotation package is not in this list, it will not be processed.
     */
	private Set<String> validationAnnotationPackages = new HashSet<String>();{
		/* By default, we only process our own annotions. */
        validationAnnotationPackages.add("sg.com.fbs.validator.annotations.validation");
	}

	
	@Override
	@Implements(interfaceClass=ValidatorMetaDataReader.class)
	public List<ValidatorMetaData> readMetaData(Class clazz, String propertyName) {
		
		String propertyKey = clazz.getName()+"."+propertyName;
		List<ValidatorMetaData> validatorMetaDataList = metaDataCache.get(propertyKey);

		if(validatorMetaDataList==null){
        	List<AnnotationData> annotationDataList = AnnotationUtils.getAnnotationDataForProperty(clazz, propertyName, false, this.validationAnnotationPackages);   	
        	validatorMetaDataList = extractMetaDataFromAnnotations(annotationDataList);     
        	metaDataCache.put(propertyKey, validatorMetaDataList);
		}
			
		return validatorMetaDataList;
	}
	

	private List<ValidatorMetaData> extractMetaDataFromAnnotations(List<AnnotationData> annotations){
		List<ValidatorMetaData> list = new ArrayList<ValidatorMetaData>();
		for (AnnotationData annotationData : annotations) {
			ValidatorMetaData validatorMetaData = convertAnnotationDataToValidatorMetaData(annotationData);
			list.add(validatorMetaData);
		}
		
		return list;
	}
	
	@NeedsRefactoring("This method shows we are calling annotationData.getValues a lot. "
			        + "Therefore, we must cache the results of getValues as the annoationData is static "
			        + "per property per class. ")
	private ValidatorMetaData convertAnnotationDataToValidatorMetaData(AnnotationData annotationData){
		ValidatorMetaData metaData = new ValidatorMetaData();
		metaData.setName(annotationData.getName());
		 /* INNEFFECIENT... FIX THIS... */
		metaData.setProperties(annotationData.getValues());
		return metaData;
	}
	
	 /** We allow a set of validation annotation packages to be configured. */
	@AllowsConfigurationInjection
    public void setValidationAnnotationPackages(Set<String> validationAnnotationPackages) {
        this.validationAnnotationPackages = validationAnnotationPackages;
    }
}


























































