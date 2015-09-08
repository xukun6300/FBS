package sg.com.fbs.web.ui.controller.security;

import sg.com.fbs.core.techinfra.web.WebDropDownListIF;

/**
 * @Author Frank Xu $
 * @Created 11:59:21 am 26 Aug, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public enum AuthenticationWebEnum implements WebDropDownListIF{
	
	AUTHENTICATION_JSP_PLACEHOLDER{
		@Override
		public String toString() {
			return "security/"; 
		}
	},
	
	SHOW_LOGIN_JSP{
		@Override
		public String toString() {
			return "showLogin";
		}
	},
	
	SHOW_LOGOUT_JSP{
		@Override
		public String toString() {
			return "showLogout";
		}
	}
	
}
