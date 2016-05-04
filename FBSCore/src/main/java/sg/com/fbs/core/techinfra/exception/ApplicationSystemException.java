package sg.com.fbs.core.techinfra.exception;

import lombok.Getter;
import lombok.Setter;

public class ApplicationSystemException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8344927563990313236L;
	

	private String errorCode;
	
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
	public String getErrorCode() {
		return errorCode;
	}
	
	public ApplicationSystemException(Throwable e){
		super(e);
	}
	
	public ApplicationSystemException(String message){
		super(message);
	}
	
	public ApplicationSystemException(Throwable e, String message){
		super(message, e);
	}
	
}
