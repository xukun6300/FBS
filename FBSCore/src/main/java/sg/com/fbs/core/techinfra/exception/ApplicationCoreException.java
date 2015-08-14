package sg.com.fbs.core.techinfra.exception;

import sg.com.fbs.core.techinfra.util.ResourceBundleUtil;

public class ApplicationCoreException extends Exception{

	private String messageCode;
	private String messageStr;
	private StringBuffer fullMessage = new StringBuffer();
	private static final String CORE_LVL_EXCEPTION = "Core Level Exception";
	
	public ApplicationCoreException(final String errorCode, final Throwable cause, final String... argParams){
		
		super(CORE_LVL_EXCEPTION+errorCode, cause);
		
		String message = ResourceBundleUtil.getExceptionMessage(errorCode, argParams);		
		this.messageCode = errorCode;
		
		if(message!=null){
			this.messageStr = errorCode+":"+message;
		}else {
			message = ResourceBundleUtil.getMessage(errorCode, argParams);
				
			if(message!=null){
				this.messageStr = errorCode + ":" +message;
			}else {
				if(errorCode==null && cause!=null){
					message = cause.getMessage();
				}else {
					message = errorCode;
				}
				
				messageCode = messageStr;
			}
		}
		
	}
	
	
	public ApplicationCoreException(final String errorCode, final String... argParams){
		this(errorCode,null,argParams);
	}
	
	public ApplicationCoreException(final String errorCode, final Throwable cause){
		this(errorCode,cause,(String)null);
	}
	
	public ApplicationCoreException(final String errorCode) {
		this(errorCode, (Throwable)null);
	}
	
	public ApplicationCoreException(final Throwable cause) {
		this((String)null, cause);
	}
	
	public ApplicationCoreException(final StringBuffer fullMessage) {
		super(CORE_LVL_EXCEPTION+fullMessage.toString());
		this.fullMessage = fullMessage;
		this.messageStr = fullMessage.toString();
	}
	
	public ApplicationCoreException(final StringBuffer fullMessage, final Throwable cause) {
		super(CORE_LVL_EXCEPTION+fullMessage.toString(),cause);
		this.fullMessage = fullMessage;
		this.messageStr = fullMessage.toString();
	}
	
	public StringBuffer getFullMessage(){
		return this.fullMessage;
	}
	
	public String getMessageCode(){
		return this.messageCode;
	}
	
	@Override
	public String getMessage(){
		return this.messageStr;
	}
}



















