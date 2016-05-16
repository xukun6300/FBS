package sg.com.fbs.web.ui.form.account;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import sg.com.fbs.common.form.BusinessWebForm;
import sg.com.fbs.model.account.AccountStructure;
import sg.com.fbs.model.system.persistence.query.CriteriaIF;

/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu Kun $
 * @Created May 5, 2016 $
 * 
 */
public class AccountForm extends BusinessWebForm{

	private static final long serialVersionUID = 1L;

	private String accountCode;
	
	private String accountDesc;
	
	private boolean needRequisitionForm = true;
	
	private String acctSpendingPeriod;

	private String acctStructureJson;
	
	private List<AccountStructure> acctStructures = new ArrayList<AccountStructure>();
	
	private boolean updateFlag;
	
	public void setUpdateFlag(boolean updateFlag) {
		this.updateFlag = updateFlag;
	}
	
	public boolean isUpdateFlag() {
		return updateFlag;
	}
	
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

	public void setAcctStructureJson(String acctStructureJson) {
		this.acctStructureJson = acctStructureJson;
	}
	
	public String getAcctStructureJson() {
		return acctStructureJson;
	}
	
	@Override
	public CriteriaIF getSearchCriteria(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}
