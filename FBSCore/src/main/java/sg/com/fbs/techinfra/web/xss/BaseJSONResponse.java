package sg.com.fbs.techinfra.web.xss;

import java.util.Date;

import sg.com.fbs.core.techinfra.util.StringUtil;

/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu Kun $
 * @Created Apr 16, 2017 10:02:55 PM $
 * 
 */
public abstract class BaseJSONResponse {

	protected int errorCode;
	
	protected String errorMessage = "";
	
	protected boolean error = false;
	
    protected Object errorObject = null;
	
    private long timestamp = new Date().getTime();

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
		isError();
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
		isError();
	}

	public boolean isError() {
		return (errorCode>0) || (!StringUtil.isEmptyString(errorMessage)) || (errorObject!=null);
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public Object getErrorObject() {
		return errorObject;
	}

	public void setErrorObject(Object errorObject) {
		this.errorObject = errorObject;
		isError();
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
    
    
}
