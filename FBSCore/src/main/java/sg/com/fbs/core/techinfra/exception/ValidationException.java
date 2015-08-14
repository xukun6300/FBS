package sg.com.fbs.core.techinfra.exception;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Formatter;

/**
 * @Author Frank Xu $
 * @Created 2:47:31 pm 3 Jul, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public class ValidationException extends RuntimeException{
	private static final long serialVersionUID = -37360125476318883L;
	
	private Throwable wrappedException;
	
	public ValidationException(){
		super();
	}

	public ValidationException(String message, Throwable wrappedException){
		super(message);
		this.wrappedException =wrappedException;
	}
	
	public ValidationException(String message){
		super(message);
	}
	
	@SuppressWarnings("resource")
	public ValidationException(String message, Object... args){
		super((new Formatter()).format(message, args).toString());
	}
	
	public ValidationException(Throwable wrappedException, String message, Object... args){
		super((new Formatter()).format(message, args).toString());
		this.wrappedException =wrappedException;
	}
	
	public ValidationException(Throwable wrappedException){
		super();
		this.wrappedException =wrappedException;
	}
	
	
	@Override
	public void printStackTrace() {
		System.err.println(this.getMessage());
		System.err.println("------------------ ROOT CAUSE -----------------------");
		
		if(this.wrappedException!=null){
			wrappedException.printStackTrace();
		}
		super.printStackTrace();
	}
	
	@Override
	public void printStackTrace(PrintStream s) {
		s.println(this.getMessage());
		s.println("------------------ ROOT CAUSE -----------------------");
		if(this.wrappedException!=null){
			wrappedException.printStackTrace(s);
		}
		super.printStackTrace(s);
	}
	
	@Override
	public void printStackTrace(PrintWriter s) {
		s.println(this.getMessage());
		s.println("------------------ ROOT CAUSE -----------------------");
		if(this.wrappedException!=null){
			wrappedException.printStackTrace(s);
		}
		super.printStackTrace(s);
	}
}













































