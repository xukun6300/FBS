package sg.com.fbs.model.account;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import sg.com.fbs.model.business.pojo.BasePojoRequest;

/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu Kun $
 * @Created May 6, 2016 10:06:15 AM $
 * 
 */
public class AccountRequest extends BasePojoRequest{

	private static final long serialVersionUID = 1L;
	
    private String accountCode;
	
	private String accountDesc;
	
	private boolean needRequisitionForm;
	
	private String acctSpendingPeriod;

	private String acctStructureJson;

    private List<AccountStructure> acctStructures = new ArrayList<AccountStructure>();
	
	public List<AccountStructure> getAcctStructures() {
		return acctStructures;
	}
	
	public void setAcctStructures(List<AccountStructure> acctStructures) {
		this.acctStructures = acctStructures;
	}
	
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

	public boolean isNeedRequisitionForm() {
		return needRequisitionForm;
	}

	public void setNeedRequisitionForm(boolean needRequisitionForm) {
		this.needRequisitionForm = needRequisitionForm;
	}

	public String getAcctSpendingPeriod() {
		return acctSpendingPeriod;
	}

	public void setAcctSpendingPeriod(String acctSpendingPeriod) {
		this.acctSpendingPeriod = acctSpendingPeriod;
	}

	public String getAcctStructureJson() {
		return acctStructureJson;
	}

	public void setAcctStructureJson(String acctStructureJson) {
		this.acctStructureJson = acctStructureJson;
	}

	
}
