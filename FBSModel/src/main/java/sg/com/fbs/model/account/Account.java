package sg.com.fbs.model.account;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import sg.com.fbs.model.budgeting.LineItem;
import sg.com.fbs.model.business.pojo.BaseLogPojo;

/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu Kun $
 * @Created May 6, 2016 12:06:40 PM $
 * 
 */
public class Account extends BaseLogPojo{

	private static final long serialVersionUID = -948756286819561386L;
	
	public static final String ACCOUNT_CODE = "accountCode";
	
	public static final String ACCOUNT_DESC = "accountDesc";
	
	public static final String REQ_FORM = "requisitionForm";
	
	public static final String SPEND_PERIOD = "spendPeriod";
	
	public static final String FINANCIAL_YEAR = "financialYear";
	
	private String accountCode;
	
	private String accountDesc;
	
	private String requisitionForm;
	
	private int spendPeriod;
	
	private int financialYear;
	
	private List<AccountStructure> acctStructures = new ArrayList<AccountStructure>();

	private List<LineItem> lineItems = new ArrayList<LineItem>();
	
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

	public int getFinancialYear() {
		return financialYear;
	}

	public void setFinancialYear(int financialYear) {
		this.financialYear = financialYear;
	}
	
	public List<AccountStructure> getAcctStructures() {
		return acctStructures;
	}
	
	public void setAcctStructures(List<AccountStructure> acctStructures) {
		this.acctStructures = acctStructures;
	}

	public void setLineItems(List<LineItem> lineItems) {
		this.lineItems = lineItems;
	}
	
	public List<LineItem> getLineItems() {
		return lineItems;
	}
}
