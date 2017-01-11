package sg.com.fbs.model.budgetconfig;

import org.joda.time.DateTime;

import sg.com.fbs.model.business.pojo.BaseLogPojo;

/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu Kun $
 * @Created Nov 15, 2016 11:17:50 AM $
 * 
 */
public class BudgetConfig extends BaseLogPojo{

	private static final long serialVersionUID = 4228928425063887430L;
	
	public static final String BUDGET_CONFIG_FY = "budgetConfigFY";

	private Long budgetConfigId;
	
	private Integer budgetConfigFY;
	
	private DateTime budgetingStartDt;
	
	private DateTime budgetingEndDt;
	
	private DateTime workingBudgetStartDt;
	
	private DateTime workingBudgetEndDt;
	
	private Long budgetSnapshotId;

	public Long getBudgetConfigId() {
		return budgetConfigId;
	}

	public void setBudgetConfigId(Long budgetConfigId) {
		this.budgetConfigId = budgetConfigId;
	}

	public Integer getBudgetConfigFY() {
		return budgetConfigFY;
	}

	public void setBudgetConfigFY(Integer budgetConfigFY) {
		this.budgetConfigFY = budgetConfigFY;
	}

	public DateTime getBudgetingStartDt() {
		return budgetingStartDt;
	}

	public void setBudgetingStartDt(DateTime budgetingStartDt) {
		this.budgetingStartDt = budgetingStartDt;
	}

	public DateTime getBudgetingEndDt() {
		return budgetingEndDt;
	}

	public void setBudgetingEndDt(DateTime budgetingEndDt) {
		this.budgetingEndDt = budgetingEndDt;
	}

	public DateTime getWorkingBudgetStartDt() {
		return workingBudgetStartDt;
	}

	public void setWorkingBudgetStartDt(DateTime workingBudgetStartDt) {
		this.workingBudgetStartDt = workingBudgetStartDt;
	}

	public DateTime getWorkingBudgetEndDt() {
		return workingBudgetEndDt;
	}

	public void setWorkingBudgetEndDt(DateTime workingBudgetEndDt) {
		this.workingBudgetEndDt = workingBudgetEndDt;
	}

	public Long getBudgetSnapshotId() {
		return budgetSnapshotId;
	}

	public void setBudgetSnapshotId(Long budgetSnapshotId) {
		this.budgetSnapshotId = budgetSnapshotId;
	}
	
	
}
