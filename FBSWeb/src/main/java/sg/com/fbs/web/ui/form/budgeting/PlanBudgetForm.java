package sg.com.fbs.web.ui.form.budgeting;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;

import sg.com.fbs.common.form.BusinessWebForm;
import sg.com.fbs.model.account.Account;
import sg.com.fbs.model.system.persistence.query.CriteriaIF;

/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu Kun $
 * @Created Feb 28, 2017 $
 * 
 */
public class PlanBudgetForm extends BusinessWebForm{

	private static final long serialVersionUID = 1L;

    private DateTime budgetStartDate;
	
	private DateTime budgetCutOffDate;
	
	private int budgetForFY;
	
	private List<Account> accounts;
	
	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
	
	public List<Account> getAccounts() {
		return accounts;
	}
	
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

	@Override
	public CriteriaIF getSearchCriteria(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}
