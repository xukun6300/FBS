package sg.com.fbs.web.ui.form.budgetconfig;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;

import sg.com.fbs.common.form.BusinessQueryWebForm;
import sg.com.fbs.model.budgetconfig.BudgetConfig;
import sg.com.fbs.model.system.persistence.query.Criteria;
import sg.com.fbs.model.system.persistence.query.CriteriaIF;
import sg.com.fbs.model.system.persistence.query.Criterion;
import sg.com.fbs.model.system.persistence.query.CriterionIF;
import sg.com.fbs.model.system.persistence.query.Order;

/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu Kun $
 * @Created Nov 17, 2016 $
 * 
 */
public class ConfigNewBudgetingForm extends BusinessQueryWebForm{

	private static final long serialVersionUID = 1L;
	
	public static final String BUDGET_FOR_FYS="budgetForFYs";

	
	private DateTime budgetStartDate;
	
	private DateTime budgetCutOffDate;
	
	private int budgetForFY;
	
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
	
	public void setBudgetForFY(int budgetForFY) {
		this.budgetForFY = budgetForFY;
	}
	
	public int getBudgetForFY() {
		return budgetForFY;
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
			Order newOrder1 = new Order(BudgetConfig.BUDGET_CONFIG_FY, true);			
			Order[] newOrders = {newOrder1};
			orders = newOrders;
		}
		
		criteria.setOrder(orders);
		criteria.setFetchAll(true); 
		criteria.setRequestedPage(super.getRequestedPage());
		return criteria;
	}
	
	public CriterionIF[] getCriterion(HttpServletRequest request){
		CriterionIF[] criterion = super.getCriterion();
		
		if(criterion == null){
			List<CriterionIF> criterionList = new ArrayList<CriterionIF>();
			
			criterionList.add(new Criterion(BudgetConfig.ACT_IND, "Y"));
			criterion = criterionList.toArray(new CriterionIF[criterionList.size()]);			
		}
		
		return criterion;
	}

}










