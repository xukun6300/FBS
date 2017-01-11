package sg.com.fbs.model.budgetconfig;

import org.joda.time.DateTime;

import sg.com.fbs.model.business.pojo.BasePojoRequest;

/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu Kun $
 * @Created Nov 17, 2016 4:01:11 PM $
 * 
 */
public class BudgetConfigRequest extends BasePojoRequest{

	private static final long serialVersionUID = 1L;

	private DateTime budgetStartDate;
	
	private DateTime budgetCutOffDate;
	
	private DateTime workingBudgetStartDate;
	
	private DateTime workingBudgetEndDate;
	
	private int budgetConfigFY;
	
	private long budgetSnapshotId;
	
	public void setBudgetCutOffDate(DateTime budgetCutOffDate) {
		this.budgetCutOffDate = budgetCutOffDate;
	}
	
	public DateTime getBudgetCutOffDate() {
		return budgetCutOffDate;
	}
	
	public void setBudgetStartDate(DateTime budgetStartDate) {
		this.budgetStartDate = budgetStartDate;
	}
	
	public DateTime getBudgetStartDate() {
		return budgetStartDate;
	}

	public DateTime getWorkingBudgetStartDate() {
		return workingBudgetStartDate;
	}

	public void setWorkingBudgetStartDate(DateTime workingBudgetStartDate) {
		this.workingBudgetStartDate = workingBudgetStartDate;
	}

	public DateTime getWorkingBudgetEndDate() {
		return workingBudgetEndDate;
	}

	public void setWorkingBudgetEndDate(DateTime workingBudgetEndDate) {
		this.workingBudgetEndDate = workingBudgetEndDate;
	}

	public int getBudgetConfigFY() {
		return budgetConfigFY;
	}

	public void setBudgetConfigFY(int budgetConfigFY) {
		this.budgetConfigFY = budgetConfigFY;
	}

	public long getBudgetSnapshotId() {
		return budgetSnapshotId;
	}

	public void setBudgetSnapshotId(long budgetSnapshotId) {
		this.budgetSnapshotId = budgetSnapshotId;
	}
	
	
}
