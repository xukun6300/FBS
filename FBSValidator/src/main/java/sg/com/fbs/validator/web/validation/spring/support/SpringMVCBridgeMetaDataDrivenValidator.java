package sg.com.fbs.validator.web.validation.spring.support;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import sg.com.fbs.core.techinfra.util.ResourceBundleUtil;
import sg.com.fbs.validator.core.FBSValidationContext;
import sg.com.fbs.validator.core.ObjectRegistry;
import sg.com.fbs.validator.core.PropertiesUtil;
import sg.com.fbs.validator.core.ValidationConstants;
import sg.com.fbs.validator.core.spring.support.SpringBeanWrapperPropertiesUtil;
import sg.com.fbs.validator.validation.FieldValidator;
import sg.com.fbs.validator.validation.ValidatorMessage;
import sg.com.fbs.validator.validation.ValidatorMessageHolder;
import sg.com.fbs.validator.validation.ValidatorMessages;
import sg.com.fbs.validator.validation.ValidatorMetaData;
import sg.com.fbs.validator.validation.ValidatorMetaDataReader;
import sg.com.fbs.validator.validation.readers.AnnotationValidatorMetaDataReader;
import sg.com.fbs.validator.validation.validators.CompositeValidator;
import sg.com.fbs.validator.web.util.ValidationWebContext;

/**
 * @Author Frank Xu $
 * @Created 2:10:49 pm 6 Jul, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public class SpringMVCBridgeMetaDataDrivenValidator implements Validator {

	protected Logger logger = Logger.getLogger(SpringMVCBridgeMetaDataDrivenValidator.class);
	
	private ValidatorMetaDataReader validatorMetaDataReader = new AnnotationValidatorMetaDataReader();
	
	private PropertiesUtil validatorPropertiesUtil = new SpringBeanWrapperPropertiesUtil();
	
	public void setValidatorPropertiesUtil(PropertiesUtil validatorPropertiesUtil) {
		this.validatorPropertiesUtil = validatorPropertiesUtil;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

	@Override
	public void validate(Object target, Errors errors) {
		SpringValidatorContext.create();
		validateObject(target, errors);
		SpringValidatorContext.destroy();
	}
                               // object: TestUserForm
	private void validateObject(final Object object, final Errors errors){
/*		System.out.println("in validateObject() method");
		System.out.println(object.toString());*/
		List<PropertyDescriptor> fieldsToValidate = getFieldsToValidate(object); //get all fields for command form exclude class
		Map<String, Object> objectPropertiesAsMap = validatorPropertiesUtil.getObjectPropertiesAsMap(object);
		ValidationWebContext validationWebContext = ValidationWebContext.getInstance();
		
		if(validationWebContext!=null){
			Set paramSet = validationWebContext.getRequestParameters().keySet();
			
			for(PropertyDescriptor field: fieldsToValidate){  
				SpringValidatorContext.get().pushProperty(field.getName());
				SpringValidatorContext.get().setParentObject(object);
				
				if(shouldFieldBeValidated(paramSet)){
					Object propertyObject = objectPropertiesAsMap.get(field.getName());
					
					validateProperty(object, propertyObject, field.getName(), errors);
					
					if(propertyObject != null){
						validateObject(propertyObject, errors);//??
					}
				}
				
				SpringValidatorContext.get().pop();
			}
		}
	}
	
	/**
	 * 
	 * @param object  form object 
	 * @param objectProperty  field value
	 * @param property   field name
	 * @param errors
	 */
	private void validateProperty(final Object object, final Object objectProperty, final String property, final Errors errors){
		String labelFields = "";
		List<ValidatorMetaData> metaDataList = readMetaData(object.getClass(), property);
		for (ValidatorMetaData validatorMetaData : metaDataList) {
			labelFields = (String)validatorMetaData.getProperties().get("labelFields");
		}
		
		CompositeValidator compositeValidator = createValidator(metaDataList);
		ValidatorMessageHolder holder = compositeValidator.validate(objectProperty, property);
		
		extractMessages(SpringValidatorContext.getBindingPath(), errors, holder, object.getClass().getSimpleName(), labelFields);
	}
	
	private void extractMessages(final String property, final Errors errors, ValidatorMessageHolder holder, String objName, String labelFields){
		ValidatorMessages messages = (ValidatorMessages) holder;
		List<String> sortedList = new LinkedList<String>();
		
		for (ValidatorMessage message : messages) {
			String[] errorArgs = {property};
			
			if(StringUtils.isNotEmpty(labelFields)){
				StringTokenizer tokenizer = new StringTokenizer(labelFields, ",");
				while (tokenizer.hasMoreTokens()) {
					String value = ResourceBundleUtil.getMessage(tokenizer.nextToken());
					if(value!=null){
						sortedList.add(value);
					}
				}
				
				errorArgs = (String[])sortedList.toArray(new String[sortedList.size()]);
			}
			errors.rejectValue(property, message.getSummary(), errorArgs, message.getSummary());
		}
	}
	
	protected List<ValidatorMetaData> readMetaData(Class clazz, String propertyName){
		return validatorMetaDataReader.readMetaData(clazz, propertyName);
	}
	
	private boolean shouldFieldBeValidated(final Set paramSet){
		String bindingPath = SpringValidatorContext.getBindingPath();
		return paramSet.contains(bindingPath)?true:shouldNestedFieldBeValidated(bindingPath, paramSet);
	}
	
	private boolean shouldNestedFieldBeValidated(String bindingPath, Set<String> paramSet){
		for (String param : paramSet) {
			if(param.startsWith(bindingPath)){
				return true;
			}
		}
		return false;
	}
	
	private List<PropertyDescriptor> getFieldsToValidate(Object object){
		List<PropertyDescriptor> properties;
		BeanInfo beanInfo = null;
		try {
			beanInfo = Introspector.getBeanInfo(object.getClass());
		} catch (IntrospectionException e) {
			throw new RuntimeException(e);
		}
		
		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		properties = new ArrayList<PropertyDescriptor>(propertyDescriptors.length);
		for(PropertyDescriptor pd : propertyDescriptors){
			if(!pd.getName().equals("class")){
				properties.add(pd);
			}
		}
		return properties;
	}
	
	/**
	 * Create the validator by looking it up in the ObjectRegistry and then
	 * populating it with values from the meta-data list.
	 * 
	 * @param validationMetaDataList Holds metadataInformation about validation.
	 */
	protected CompositeValidator createValidator(List<ValidatorMetaData> validatorMetaDataList){
		/*
		 * A field (property) can be associated with many validators so we use a
		 * CompositeValidator to hold all of the validators associated with this validator.
		 */
		CompositeValidator compositeValidator = new CompositeValidator(); 
		
		/*
		 * Lookup the list of validators for the current field and initialize
		 * them with validation meta-data properties.
		 */
		List<FieldValidator> validatorList = lookupTheListOfValidatorsAndInitializeThemWithMetaDataProperties(validatorMetaDataList);
		compositeValidator.setValidatorList(validatorList);
		return compositeValidator;
	}
	
	/**
	 * Lookup the list of validators for the current field and initialize them
	 * with validation meta-data properties.
	 */
	private List<FieldValidator> lookupTheListOfValidatorsAndInitializeThemWithMetaDataProperties(List<ValidatorMetaData> validatorMetaDataList){
		
		List<FieldValidator> validatorList = new ArrayList<FieldValidator>();
		
		for (ValidatorMetaData validatorMetaData : validatorMetaDataList) {
			FieldValidator fieldValidator = lookupValidatorInRegistry(validatorMetaData.getName());
			applyValidationMetaDataPropertiesToValidator(validatorMetaData, fieldValidator);
			validatorList.add(fieldValidator);
		}
		return validatorList;
	}
	
	
	/**
	 * This method looks up the validator in the registry.
	 * @param validationMetaDataName
	 *            The name of the validator that we are looking up.
	 */
	private FieldValidator lookupValidatorInRegistry(String validationMetaDataName){
		ObjectRegistry applicationContext = FBSValidationContext.getObjectRegistry();
		String name =ValidationConstants.FRAMEWORK_PREFIX + ValidationConstants.FRAMEWORK_DELIM + "validator"+ ValidationConstants.FRAMEWORK_DELIM + validationMetaDataName;	
		FieldValidator validator = (FieldValidator) applicationContext.getObject(name, FieldValidator.class);	
		return validator;
	}
	
	/**
	 * This method applies the properties from the validationMetaData to the
	 * validator uses Spring's BeanWrapperImpl.
	 */
	private void applyValidationMetaDataPropertiesToValidator(ValidatorMetaData metaData, FieldValidator validator){
		Map<String, Object> properties = metaData.getProperties();
		ifPropertyBlankRemove(properties, "detailMessage");
		ifPropertyBlankRemove(properties, "summaryMessage");
		ifPropertyBlankRemove(properties, "labelFields");
		this.validatorPropertiesUtil.copyProperties(validator, properties); // here will copy min, max,summaryMessage those properties to LengthValidator
	}
	
	/** Removes a property if it is null or an empty string. 
     *  This allows the property to have a null or emtpy string in the
     *  meta-data but we don't copy it to the validator if the property
     *  is not set.
     * */
	private void ifPropertyBlankRemove(Map<String, Object> properties, String property){
		Object object = properties.get(property);
		if(object == null){
			properties.remove(property);
		}else if (object instanceof String) {
			String string = (String)object;
			if("".equals(string.trim())){
				properties.remove(property);
			}
		}
	}
	
	public void setValidatorMetaDataReader(ValidatorMetaDataReader validatorMetaDataReader) {
		this.validatorMetaDataReader = validatorMetaDataReader;
	}
	
	
	
	
	
	
	
	
	
	
}
