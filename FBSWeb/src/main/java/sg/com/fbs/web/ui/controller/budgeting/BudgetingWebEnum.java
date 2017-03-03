package sg.com.fbs.web.ui.controller.budgeting;

import sg.com.fbs.core.techinfra.web.WebDropDownListIF;

/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu Kun $
 * @Created Feb 27, 2017 $
 * 
 */
public enum BudgetingWebEnum implements WebDropDownListIF{
	BUDGETING_PLACEHOLDER{
		@Override
		public String toString() {			
			return "budgeting/";
		}
	},
	
	SHOW_PLAN_BUDGET_JSP{
		@Override
		public String toString() {			
			return "planBudget";
		}
	},
}
