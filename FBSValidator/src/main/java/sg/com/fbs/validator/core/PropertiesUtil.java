package sg.com.fbs.validator.core;

import java.util.Map;

/**
 * @Author Frank Xu $
 * @Created 5:02:37 pm 3 Jul, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public interface PropertiesUtil {

	public void copyProperties(Object object, Map<String, Object> properties);
	
	public Map<String, Object> getObjectPropertiesAsMap(Object object);
	
	public Object getPropertyValue(String propertyName, Object object);
}
