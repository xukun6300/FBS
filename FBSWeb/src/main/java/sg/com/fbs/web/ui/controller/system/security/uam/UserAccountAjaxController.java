package sg.com.fbs.web.ui.controller.system.security.uam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import sg.com.fbs.services.system.security.uam.exception.UserAccountManagementException;
import sg.com.fbs.services.system.security.uam.mgr.UserAccountManagerBD;

/**
 * @Author Frank Xu $
 * @Created 10:13:51 am 20 Aug, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
@Controller
@RequestMapping("/useraccountmanagementajax")
public class UserAccountAjaxController {

	@RequestMapping(value="/validateEmailId", method = RequestMethod.GET)
	public @ResponseBody String checkEmailIdAlreadyExist(HttpServletRequest request, HttpServletResponse response) throws UserAccountManagementException{
		String userId = request.getParameter("email");
		
		if(!EmailValidator.getInstance().isValid(userId)){
			return "Invalid Email Address";
		}
		
		UserAccountManagerBD userAccountManagerBD = new UserAccountManagerBD();
		Boolean emailIdExist = userAccountManagerBD.checkEmailIdExsit(userId.toLowerCase().trim());
		if(emailIdExist){
			return "This Email Address is already taken";
		}else {
			return "This Email Address is available";
		}
	}
}
