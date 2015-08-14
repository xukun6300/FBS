package sg.com.fbs.validator.core;

/**
 * @Author Frank Xu $
 * @Created 12:01:06 pm 6 Jul, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public class FBSValidationContext {

	private static ObjectRegistry objectRegistry;
	
	private static Object createObjectBaseOnConstant(String className){
		try {
			return Class.forName(className).newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static ObjectRegistry getObjectRegistry(){
		if(objectRegistry==null){
			objectRegistry = (ObjectRegistry) createObjectBaseOnConstant(ValidationConstants.OBJECT_REGISTRY);
		}
		return objectRegistry;
	}
	
	public static void setObjectRegistry(ObjectRegistry objectRegistry){
		FBSValidationContext.objectRegistry = objectRegistry;
	}
}
