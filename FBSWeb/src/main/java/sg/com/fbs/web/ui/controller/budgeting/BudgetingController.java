package sg.com.fbs.web.ui.controller.budgeting;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.Validator;
import org.springframework.web.servlet.ModelAndView;

import sg.com.fbs.core.techinfra.web.BaseWebController;
import sg.com.fbs.core.techinfra.web.Mvc;
import sg.com.fbs.core.techinfra.web.WebCRUDEnum;
import sg.com.fbs.model.budgeting.PlanBudgetRequest;
import sg.com.fbs.web.ui.form.budgeting.PlanBudgetForm;

/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu Kun $
 * @Created Feb 27, 2017 $
 * 
 */
public class BudgetingController extends BaseWebController{

	private BudgetingCRUD budgetingCRUD = new BudgetingCRUD();
	
	@Override
	public String getModuleWebContext() {
		return BudgetingWebEnum.BUDGETING_PLACEHOLDER.toString();
	}

	@Override
	public void preLoad(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ModelAndView postLoad(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Validator getCustomValidator() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ModelAndView viewPlanBudget(HttpServletRequest request, HttpServletResponse response){
		PlanBudgetForm planBudgetForm = new PlanBudgetForm();
		PlanBudgetRequest planBudgetRequest = new PlanBudgetRequest();
		setCrudMode(WebCRUDEnum.QUERY_MODE);
		setCRUDOperation(budgetingCRUD);
		setValidationErrorPage(BudgetingWebEnum.SHOW_PLAN_BUDGET_JSP.toString());
		Mvc mvc = new Mvc(planBudgetForm, BudgetingWebEnum.SHOW_PLAN_BUDGET_JSP.toString(), planBudgetRequest);
		return mvc;
	}

    public ModelAndView savePlanBudget(HttpServletRequest request, HttpServletResponse response){
		PlanBudgetForm planBudgetForm = new PlanBudgetForm();
		PlanBudgetRequest planBudgetRequest = new PlanBudgetRequest();
		setCrudMode(WebCRUDEnum.INSERT_MODE);
		setCRUDOperation(budgetingCRUD);
		setValidationErrorPage(BudgetingWebEnum.SHOW_PLAN_BUDGET_JSP.toString());
		Mvc mvc = new Mvc(planBudgetForm, BudgetingWebEnum.SHOW_PLAN_BUDGET_JSP.toString(), planBudgetRequest);
		return mvc;
	}
}


