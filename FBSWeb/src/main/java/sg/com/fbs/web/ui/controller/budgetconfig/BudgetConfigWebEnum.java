package sg.com.fbs.web.ui.controller.budgetconfig;

import sg.com.fbs.core.techinfra.web.WebDropDownListIF;
import sg.com.fbs.web.ui.form.budgetconfig.ConfigNewBudgetingForm;

/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu Kun $
 * @Created Nov 15, 2016 $
 * 
 */
public enum BudgetConfigWebEnum implements WebDropDownListIF{

	BUDGET_CONFIG_PLACEHOLDER{
		@Override
		public String toString() {			
			return "budgetconfig/";
		}
	},
	
	SHOW_CONFIG_NEW_BUDGETING_JSP{
		@Override
		public String toString() {			
			return "configNewBudgeting";
		}
	},
	
	SHOW_CONFIG_WORKING_BUDGET_JSP{
		@Override
		public String toString() {			
			return "configWorkingBudget";
		}
	},
	
	PLEASE_SELECT {
		@Override
		public String toString() {
			return "Please Select";
		}
	},
	
	DEFAULT_VALUE {
		@Override
		public String toString() {
			return "-1";
		}
	},
	
	BUDGET_FOR_FYS {
		@Override
		public String toString() {
			return ConfigNewBudgetingForm.BUDGET_FOR_FYS;
		}
	},
	
	ACTION_SHOW_CONFIG_NEW_BUDGET {
		@Override
		public String toString() {
			return "showConfigNewBudgeting";
		}
	},
	
	ACTION_SAVE_NEW_BUDGETING {
		@Override
		public String toString() {
			return "saveNewBudgeting";
		}
	},
}
