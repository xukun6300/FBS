package sg.com.fbs.web.ui.controller.mastercode;

import sg.com.fbs.core.techinfra.web.WebDropDownListIF;

/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu $
 * @Created 2:51:08 pm 16 Sep, 2015 $
 * 
 */
public enum MasterCodeWebEnum implements WebDropDownListIF{

	MASTER_CODE_JSP_PLACEHOLDER {
		@Override
		public String toString() {
			return "mastercode/";
		}
	},
	
	SHOW_ADD_CODE_KEY_JSP {
		@Override
		public String toString() {
			return "showAddCodeKey";
		}
	},
	
	ADD_CODE_KEY_JSP {
		@Override
		public String toString() {
			return "addCodeKey";
		}
	},
	
	CONFIRM_ADD_CODE_KEY_JSP {
		@Override
		public String toString() {
			return "confirmCategoryType";
		}
	},
	
	SHOW_ADD_CODE_VALUE_JSP {
		@Override
		public String toString() {
			return "showAddCodeValue";
		}
	}
}
