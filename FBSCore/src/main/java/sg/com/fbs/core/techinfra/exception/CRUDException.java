package sg.com.fbs.core.techinfra.exception;

public class CRUDException extends ApplicationCoreException{

	public CRUDException(ApplicationCoreException e){
		super(e);
	}
	
	public CRUDException(String errorCode) {
		super(errorCode);
	}
	
	public CRUDException(StringBuffer fullMessage){
		super(fullMessage);
	}
	
	public CRUDException(Throwable cause) {
		super(cause);
	}
	
	public CRUDException(String errorCode, Throwable cause) {
		super(errorCode, cause);
	}
}
