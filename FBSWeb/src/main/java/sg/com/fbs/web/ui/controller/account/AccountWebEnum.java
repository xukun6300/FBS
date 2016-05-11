package sg.com.fbs.web.ui.controller.account;

import sg.com.fbs.core.techinfra.web.WebDropDownListIF;


/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu Kun $
 * @Created May 5, 2016 $
 * 
 */
public enum AccountWebEnum implements WebDropDownListIF{
	
	ACCOUNT_JSP_PLACEHOLDER{
		@Override
		public String toString() {
			return "account/";
		}
	},
	
	SHOW_ADD_ACCOUNT_JSP{
		@Override
		public String toString() {
			return "showAddAccount";
		}
	},
	
	CONFIRM_ADD_ACCOUNT_JSP{
		@Override
		public String toString() {
			return "confirmAddAccount";
		}
	},
	
	SEARCH_ACCOUNT_JSP{
		@Override
		public String toString() {
			return "searchAccount";
		}
	}
}
