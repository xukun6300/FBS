package sg.com.fbs.web.ui.controller.system.security.uam;

import sg.com.fbs.core.techinfra.web.WebDropDownListIF;
import sg.com.fbs.web.ui.form.system.security.uam.RegisterUserForm;
import sg.com.fbs.web.ui.form.system.security.uam.UserSearchForm;

/**
 * @Author Frank Xu $
 * @Created 9:36:58 am 30 Jul, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public enum UserAccountManagementWebEnum implements WebDropDownListIF{

	USERACCOUNTMANAGEMENT_JSP_PLACEHOLDER {
		@Override
		public String toString(){
			return "useraccountmanagement/";
		}
	},

	SHOW_REGISTER_USER_JSP {
		@Override
		public String toString(){
			return "showRegisterUser";
		}
	},
	
	UAM_CONFIRMATION_JSP {
		@Override
		public String toString() {
			return "uamConfirmation";
		}
	},
	
	USER_SEARCH_JSP {
		@Override
		public String toString() {
			return "userSearch";
		}
	},
	
	USER_DETAILS_JSP {
		@Override
		public String toString() {
			return "userDetails";
		}
	},
	
	SHOW_UPDATE_USER_DETAILS_JSP {
		@Override
		public String toString() {
			return "showUpdateUserDetails";
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
	
	SALUTATION_TYPE {
		@Override
		public String toString() {
			return RegisterUserForm.SALUTATION_TYPE;
		}
	},
	
	GENDER_TYPE_T_LIST {
		@Override
		public String toString() {
			return RegisterUserForm.GENDER_TYPE;
		}
	},
	
	PRIMARY_CONTACT_TYPE_T_LIST {
		@Override
		public String toString() {
			return RegisterUserForm.PRIMARYCONTACT_TYPE;
		}
	},
	
	SECURITY_QUESTION_LIST {
		@Override
		public String toString() {
			return RegisterUserForm.SECURITY_QUESTION;
		}
	},
	
	ACCOUNT_STATUS_LIST{
		@Override
		public String toString() {
			return UserSearchForm.ACCOUNT_STATUS;
		}
	},
	
	ALL_ACCOUNTS_LIST{
		@Override
		public String toString() {
			return "allAccounts";
		}
	}
}
















