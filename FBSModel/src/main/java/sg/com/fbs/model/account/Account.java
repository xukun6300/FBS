package sg.com.fbs.model.account;

import java.util.HashSet;
import java.util.Set;

import sg.com.fbs.model.business.pojo.BaseLogPojo;

/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu Kun $
 * @Created May 6, 2016 12:06:40 PM $
 * 
 */
public class Account extends BaseLogPojo{

	private static final long serialVersionUID = -948756286819561386L;
	
	private String accountCode;
	
	private String accountDesc;
	
	private String requisitionForm;
	
	private int spendPeriod;
	
	private String financialYear;
	
	private Set<AccountStructure> acctStructures = new HashSet<AccountStructure>(0);

	public String getAccountCode() {
		return accountCode;
	}

	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}

	public String getAccountDesc() {
		return accountDesc;
	}

	public void setAccountDesc(String accountDesc) {
		this.accountDesc = accountDesc;
	}

	public String getRequisitionForm() {
		return requisitionForm;
	}
	
	public void setRequisitionForm(String requisitionForm) {
		this.requisitionForm = requisitionForm;
	}

	public int getSpendPeriod() {
		return spendPeriod;
	}

	public void setSpendPeriod(int spendPeriod) {
		this.spendPeriod = spendPeriod;
	}

	public String getFinancialYear() {
		return financialYear;
	}

	public void setFinancialYear(String financialYear) {
		this.financialYear = financialYear;
	}
	
	public Set<AccountStructure> getAcctStructures() {
		return acctStructures;
	}
	
	public void setAcctStructures(Set<AccountStructure> acctStructures) {
		this.acctStructures = acctStructures;
	}

}
