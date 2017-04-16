package sg.com.fbs.techinfra.web.xss;

/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu Kun $
 * @Created Apr 16, 2017 10:10:06 PM $
 * 
 */
public class JSONResponse extends BaseJSONResponse{

	private Object data;
	
	public Object getData() {
		return data;
	}
	
	public void setData(Object data) {
		this.data = data;
	}
	
	public JSONResponse() {
		super();
	}
	
	public JSONResponse(Object data) {
		this.data = data;
	}
}
