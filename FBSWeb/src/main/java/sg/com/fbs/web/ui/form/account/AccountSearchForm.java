package sg.com.fbs.web.ui.form.account;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import sg.com.fbs.common.form.BusinessQueryWebForm;
import sg.com.fbs.model.account.Account;
import sg.com.fbs.model.system.persistence.query.Criteria;
import sg.com.fbs.model.system.persistence.query.CriteriaIF;
import sg.com.fbs.model.system.persistence.query.Criterion;
import sg.com.fbs.model.system.persistence.query.CriterionIF;
import sg.com.fbs.model.system.persistence.query.Order;
import sg.com.fbs.model.system.persistence.query.Projection;

/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu Kun $
 * @Created May 11, 2016 $
 * 
 */
public class AccountSearchForm extends BusinessQueryWebForm{

	private static final long serialVersionUID = 1L;

    private String accountCode;
	
	private String accountDesc;
	
	private Boolean requisitionForm;
	
	private String spendPeriod;
	
	private String financialYear;
	
    private boolean validateForm = true;
    
    public void setValidateForm(boolean validateForm) {
		this.validateForm = validateForm;
	}
    
    public boolean isValidateForm() {
		return validateForm;
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

	public Boolean getRequisitionForm() {
		return requisitionForm;
	}

	public void setRequisitionForm(Boolean requisitionForm) {
		this.requisitionForm = requisitionForm;
	}

	public String getSpendPeriod() {
		return spendPeriod;
	}

	public void setSpendPeriod(String spendPeriod) {
		this.spendPeriod = spendPeriod;
	}

	public String getFinancialYear() {
		return financialYear;
	}

	public void setFinancialYear(String financialYear) {
		this.financialYear = financialYear;
	}

	public void setSpendPeriod(int spendPeriod){
		this.spendPeriod = Integer.toString(spendPeriod);
	}
	


	@Override
	public CriteriaIF getSearchCriteria(HttpServletRequest request) {
		
		CriteriaIF criteria = new Criteria();
		criteria.setCriterion(getCriterion(request));
		
		Order[] orders = super.getOrder();
		
		if(request.getParameter(IS_ASCENDING)!=null && request.getParameter(IS_ASCENDING).trim().length()>0 
				&& request.getParameter(SORTING_PROPERTY)!=null &&request.getParameter(SORTING_PROPERTY).trim().length()>0){
			Order newOrder = new Order(request.getParameter(SORTING_PROPERTY), request.getParameter(IS_ASCENDING).equalsIgnoreCase("true") ? true : false);
			Order[] newOrders = {newOrder};
			orders = newOrders;
		}else {
			Order newOrder1 = new Order(Account.FINANCIAL_YEAR, false);
			Order newOrder2 = new Order(Account.ACCOUNT_CODE, true);
			Order[] newOrders = {newOrder2, newOrder1};
			orders = newOrders;
		}
		criteria.setOrder(orders);
		criteria.setFetchAll(super.isFetchAll());
		criteria.setRequestedPage(super.getRequestedPage());
		return criteria;
	}

	
	public CriterionIF[] getCriterion(HttpServletRequest request){
	
		CriterionIF[] criterion = super.getCriterion();
		
		if(criterion==null){
			List<CriterionIF> criterionList = new ArrayList<CriterionIF>();
			if(!StringUtils.isEmpty(accountCode)){
				criterionList.add(new Criterion(Account.ACCOUNT_CODE, accountCode));
			}
			
			if(!StringUtils.isEmpty(accountDesc)){
				criterionList.add(new Criterion(Account.ACCOUNT_DESC, accountDesc));
			}
			
			if(requisitionForm!=null){
				criterionList.add(new Criterion(Account.REQ_FORM, requisitionForm ? "Y" : "N"));
			}
			
			if(!StringUtils.isEmpty(spendPeriod)){
				if(StringUtils.isNumeric(spendPeriod)){
					criterionList.add(new Criterion(Account.SPEND_PERIOD, Integer.valueOf(spendPeriod)));
				}else{
					criterionList.add(new Criterion(Account.SPEND_PERIOD, -1));
				}
			}
			
			if(financialYear!=null && !financialYear.equals("-1")){
				criterionList.add(new Criterion(Account.FINANCIAL_YEAR, Integer.valueOf(financialYear)));
			}
			
			criterionList.add(new Criterion(Account.ACT_IND, "Y"));
			
			criterion = criterionList.toArray(new CriterionIF[criterionList.size()]);
		}
		
		return criterion;
	}

}



















