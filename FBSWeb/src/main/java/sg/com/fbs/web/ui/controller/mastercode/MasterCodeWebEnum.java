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
	},
	
	CONFIRM_ADD_CODE_VALUE_JSP {
		@Override
		public String toString() {
			return "confirmCodeValue";
		}
	},
	
	SEARCH_CATEGORY_TYPE_JSP {
		@Override
		public String toString() {
			return "searchCategoryType";
		}
	},
	
	CODE_KEYS_T_LIST {
		@Override
		public String toString() {
			return "codeKeys";
		}
	},
	
	LIST_CODE_VALUES_TXN_TYPE {
	    public String toString() {
	        return "listCodeValues";
	    }
	},

	UPDATE_CODE_VALUE_SEQUENCE_TXN_TYPE {
	    public String toString() {
	        return "updateCodeValueSequence";
	    }
	},
	
	LIST_CODE_VALUES_JSP {
		@Override
		public String toString() {
			return "listCodeValues";
		}
	},
	
	SHOW_DELETE_CODE_KEY_JSP {
		@Override
		public String toString() {
			return "showDeleteCodeKey";
		}
	},
	
	SHOW_DELETE_CODE_VALUE_JSP {
		@Override
		public String toString() {
			return "showDeleteCodeValue";
		}
	}
}













