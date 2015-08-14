package sg.com.fbs.core.techinfra.persistence.exception;

import org.apache.log4j.Logger;

import sg.com.fbs.core.techinfra.exception.ApplicationCoreException;

public class DataAccessObjectException extends ApplicationCoreException {

	private static final long serialVersionUID = 309625536883218147L;
	
	Logger logger = Logger.getLogger(DataAccessObjectException.class);
	
	public DataAccessObjectException(String messageCode){
		super(messageCode);
	}
	
	public DataAccessObjectException(String messageCode, Throwable cause){
		super(messageCode, cause);
	}
	
	public DataAccessObjectException(String messageCode, Throwable cause, String[] params){
		super(messageCode, cause, params);
	}
	
	public DataAccessObjectException(String messageCode, String[] params){
		super(messageCode, params);
	}
	
	
}