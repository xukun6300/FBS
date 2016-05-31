package sg.com.fbs.model.system.security;

import sg.com.fbs.model.business.pojo.BaseLogPojo;

/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu Kun $
 * @Created May 30, 2016 5:15:24 PM $
 * 
 */
public class UserAccountMapping extends BaseLogPojo{

	private static final long serialVersionUID = -3824212909934706113L;

	private User user;
	
	private String accountCode;
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}
	
	public String getAccountCode() {
		return accountCode;
	}

}
