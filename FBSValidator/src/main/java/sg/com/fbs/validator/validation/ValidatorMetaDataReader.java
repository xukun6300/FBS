package sg.com.fbs.validator.validation;

import java.util.List;

import sg.com.fbs.validator.annotations.design.ExtentionPoint;

/**
 * @Author Frank Xu $
 * @Created 11:21:30 am 3 Jul, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 * 
 * 
 * ValidatorMetaDataReader is an extention point for classes that need
 * to read validation meta-data. 
 * 
 * There are currently two implmentations (planned) for this.
 * 
 * One implementation reads the meta-data from a properties file.
 * The other implementation reads the data from Java 5 Annotation. 
 */
@ExtentionPoint
public interface ValidatorMetaDataReader {
	
	public List<ValidatorMetaData> readMetaData(Class clazz, String propertyName);
	
}
