package sg.com.fbs.web.ui.controller.system.security.uam;

import sg.com.fbs.core.techinfra.web.WebDropDownListIF;
import sg.com.fbs.web.ui.form.system.security.uam.RegisterUserForm;

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

	SHOW_REGISTER_USER {
		@Override
		public String toString(){
			return "showRegisterUser";
		}
	},
	
	UAM_CONFIRMATION {
		@Override
		public String toString() {
			return "uamConfirmation";
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
	
	
}
















