package sg.com.fbs.validator.core;

/**
 * @Author Frank Xu $
 * @Created 10:19:26 am 6 Jul, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public interface ObjectRegistry {

	public Object getObject(String name);
	
	public Object getObjectReturnNullIfMissing(String name);
	
	public Object getObject(String string, Class<?> clazz);
	
	public void resolveCollaborators(Object object);
	
	public Object[] getObjectsByType(Class<?> clazz);
	
	public Object convertObject(Object object, Class<?> clazz);
}
