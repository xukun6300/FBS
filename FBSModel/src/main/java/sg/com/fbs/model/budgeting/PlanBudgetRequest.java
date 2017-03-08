package sg.com.fbs.model.budgeting;

import java.util.List;

import org.joda.time.DateTime;

import sg.com.fbs.model.account.Account;
import sg.com.fbs.model.business.pojo.BasePojoRequest;

/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu Kun $
 * @Created Mar 3, 2017 11:48:47 AM $
 * 
 */
public class PlanBudgetRequest extends BasePojoRequest{

	private static final long serialVersionUID = 1L;
	
    private DateTime budgetStartDate;
    
    private DateTime budgetCutOffDate;
	
	private int budgetForFY;
	
	private List<Account> accounts;
	
	public DateTime getBudgetStartDate() {
		return budgetStartDate;
	}

	public void setBudgetStartDate(DateTime budgetStartDate) {
		this.budgetStartDate = budgetStartDate;
	}

	public DateTime getBudgetCutOffDate() {
		return budgetCutOffDate;
	}

	public void setBudgetCutOffDate(DateTime budgetCutOffDate) {
		this.budgetCutOffDate = budgetCutOffDate;
	}

	public int getBudgetForFY() {
		return budgetForFY;
	}

	public void setBudgetForFY(int budgetForFY) {
		this.budgetForFY = budgetForFY;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	
}
