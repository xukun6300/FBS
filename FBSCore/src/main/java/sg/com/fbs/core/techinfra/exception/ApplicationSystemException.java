package sg.com.fbs.core.techinfra.exception;

import lombok.Getter;
import lombok.Setter;

public class ApplicationSystemException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8344927563990313236L;
	
	@Setter
	@Getter
	private String errorCode;
	
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
