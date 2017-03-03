package sg.com.fbs.model.budgeting;

import java.util.List;

import sg.com.fbs.model.account.Account;
import sg.com.fbs.model.business.pojo.BasePojoRequest;

/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu Kun $
 * @Created Mar 3, 2017 11:48:47 AM $
 * 
 */
public class PlanBudgetRequest extends BasePojoRequest{

	private static final long serialVersionUID = 1L;
	
	private List<Account> accounts;
	
	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
	
	public List<Account> getAccounts() {
		return accounts;
	}
}
