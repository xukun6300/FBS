package sg.com.fbs.model.system.security;

import sg.com.fbs.model.business.pojo.BaseLogPojo;

/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu Kun $
 * @Created May 30, 2016 5:15:24 PM $
 * 
 */
public class UserAccountMapping extends BaseLogPojo{

	private static final long serialVersionUID = -3824212909934706113L;

	private long userId;
	
	private String accountCode;
	
	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	public long getUserId() {
		return userId;
	}
	
	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}
	
	public String getAccountCode() {
		return accountCode;
	}

}
