package sg.com.fbs.web.ui.controller.budgetconfig;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Validator;
import org.springframework.web.servlet.ModelAndView;

import sg.com.fbs.core.techinfra.web.BaseWebController;
import sg.com.fbs.core.techinfra.web.DefaultRefDataSource;
import sg.com.fbs.core.techinfra.web.Mvc;
import sg.com.fbs.core.techinfra.web.WebCRUDEnum;
import sg.com.fbs.model.budgetconfig.BudgetConfigRequest;
import sg.com.fbs.web.ui.form.budgetconfig.ConfigNewBudgetingForm;

/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu Kun $
 * @Created Nov 15, 2016 $
 * 
 */
public class BudgetConfigController extends BaseWebController{

	private BudgetConfigCRUD budgetConfigCrud = new BudgetConfigCRUD();

	private BudgetConfigValidator budgetConfigValidator = new BudgetConfigValidator();
	
	@Override
	public String getModuleWebContext() {
		return BudgetConfigWebEnum.BUDGET_CONFIG_PLACEHOLDER.toString();
	}

	@Override
	public void preLoad(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) {
		BudgetConfigWebEnum[] comboInitializer ={
				BudgetConfigWebEnum.BUDGET_FOR_FYS			
		};
		
		String[] views = {
				BudgetConfigWebEnum.SHOW_CONFIG_NEW_BUDGETING_JSP.toString()
		};
		
		for (String view : views) {
			map.put(view, comboInitializer);
		}
		
		map.put(DefaultRefDataSource.WEB_LIST_DATA_SOURCE, new BudgetConfigRefDataSource());
	}

	@Override
	public ModelAndView postLoad(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Validator getCustomValidator() {
		return budgetConfigValidator;
	}

	public ModelAndView showConfigNewBudgeting(HttpServletRequest request, HttpServletResponse response){
		ConfigNewBudgetingForm configNewBudgetingForm = new ConfigNewBudgetingForm();
		BudgetConfigRequest budgetConfigRequest = new BudgetConfigRequest();
		setCrudMode(WebCRUDEnum.QUERY_MODE);
		setCRUDOperation(budgetConfigCrud);
		setValidationErrorPage(BudgetConfigWebEnum.SHOW_CONFIG_NEW_BUDGETING_JSP.toString());
		Mvc mvc = new Mvc(configNewBudgetingForm, BudgetConfigWebEnum.SHOW_CONFIG_NEW_BUDGETING_JSP.toString(), budgetConfigRequest);
		return mvc;
	}
	
	public ModelAndView saveNewBudgeting(HttpServletRequest request, HttpServletResponse response){
		ConfigNewBudgetingForm configNewBudgetingForm = new ConfigNewBudgetingForm();
		BudgetConfigRequest budgetConfigRequest = new BudgetConfigRequest();
		setCrudMode(WebCRUDEnum.INSERT_MODE);
		setCRUDOperation(budgetConfigCrud);
		setValidationErrorPage(BudgetConfigWebEnum.SHOW_CONFIG_NEW_BUDGETING_JSP.toString());
		Mvc mvc = new Mvc(configNewBudgetingForm, BudgetConfigWebEnum.SHOW_CONFIG_NEW_BUDGETING_JSP.toString(), budgetConfigRequest);
		return mvc;
	}
	
	
	public ModelAndView showConfigWorkingBudget(HttpServletRequest request, HttpServletResponse response){
		
		return null;
	}
	
}










