package sg.com.fbs.web.ui.controller.budgetconfig;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import sg.com.fbs.web.ui.form.budgetconfig.ConfigNewBudgetingForm;

/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu Kun $
 * @Created Jan 20, 2017 $
 * 
 */
public class BudgetConfigValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

	@Override
	public void validate(Object target, Errors errors) {
		if(target instanceof ConfigNewBudgetingForm){
			ConfigNewBudgetingForm configNewBudgetingForm = (ConfigNewBudgetingForm) target;
			
			if(BudgetConfigWebEnum.ACTION_SAVE_NEW_BUDGETING.toString().equalsIgnoreCase(configNewBudgetingForm.getAction())){
				if(configNewBudgetingForm.getBudgetStartDate()==null){
					errors.rejectValue("budgetStartDate", "fbs.common.errors.budget.config.mandatory.new.budget.start.date");
				}else{
					if(configNewBudgetingForm.getBudgetStartDate().getYear()!=configNewBudgetingForm.getBudgetForFY()){
						errors.rejectValue("budgetStartDate", "fbs.common.errors.budget.config.new.budget.start.date.same.year");
					}
				}
				
				if(configNewBudgetingForm.getBudgetCutOffDate()==null){
					errors.rejectValue("budgetCutOffDate", "fbs.common.errors.budget.config.mandatory.new.budget.end.date");
				}else{
					if(configNewBudgetingForm.getBudgetCutOffDate().getYear()!=configNewBudgetingForm.getBudgetForFY()){
						errors.rejectValue("budgetCutOffDate", "fbs.common.errors.budget.config.new.budget.end.date.same.year");
					}
				}
				
				if(configNewBudgetingForm.getBudgetForFY()==0){
					errors.rejectValue("budgetForFY", "fbs.common.errors.budget.config.mandatory.new.budget.budget.fy");
				}
				
				if(configNewBudgetingForm.getBudgetStartDate()!=null && configNewBudgetingForm.getBudgetCutOffDate()!=null){
					if(configNewBudgetingForm.getBudgetStartDate().isAfter(configNewBudgetingForm.getBudgetCutOffDate())){
						errors.rejectValue("budgetCutOffDate", "fbs.common.errors.budget.config.new.budget.date.compare");
					}
				}
			}
			
		}
	}

}
