package sg.com.fbs.web.ui.controller.budgetconfig;

import sg.com.fbs.core.techinfra.web.WebDropDownListIF;

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
	
}
