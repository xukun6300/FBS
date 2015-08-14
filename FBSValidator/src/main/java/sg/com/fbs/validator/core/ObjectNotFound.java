package sg.com.fbs.validator.core;

/**
 * @Author Frank Xu $
 * @Created 10:17:20 am 6 Jul, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public class ObjectNotFound extends RuntimeException{

	private static final long serialVersionUID = 6576362541288056539L;
	
	public ObjectNotFound(String name, Exception ex){
		super("Object not found "+name, ex);
	}

}
