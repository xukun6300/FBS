package sg.com.fbs.web.ui.controller.system.security.uam;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import sg.com.fbs.model.system.security.uam.PrimaryContactModeEnum;
import sg.com.fbs.services.system.security.uam.exception.UserAccountManagementException;
import sg.com.fbs.services.system.security.uam.mgr.UserAccountManagerBD;
import sg.com.fbs.web.ui.form.system.security.uam.RegisterUserForm;

/**
 * @Author Frank Xu $
 * @Created 3:08:33 pm 20 Aug, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public class UserAccountManagementValidator implements Validator{

	@Override
	public boolean supports(Class clazz) {
		return true;
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		if(target instanceof RegisterUserForm){
			RegisterUserForm registerUserForm = (RegisterUserForm) target;
			
			if(registerUserForm.getPrimaryContactTypeT()==null){
				errors.rejectValue("primaryContactTypeT", "fbs.common.errors.registerpublicuser.primary.contact.mode");
			}else if(registerUserForm.getPrimaryContactTypeT().equalsIgnoreCase(PrimaryContactModeEnum.OFFICE.getContactMode())){
				if(registerUserForm.getOfficeTelNo().trim().length()==0){
					errors.rejectValue("officeTelNo", "fbs.common.errors.ana.mandatory.officetelno");
				}
			}else if(registerUserForm.getPrimaryContactTypeT().equalsIgnoreCase(PrimaryContactModeEnum.MOBILE.getContactMode())){
				if(registerUserForm.getMobileNo().trim().length()==0){
					errors.rejectValue("mobileNo", "fbs.common.errors.ana.mandatory.mobileno");
				}
			}
			
			if(registerUserForm.getDob()==null){
				errors.rejectValue("dob", "fbs.common.errors.ana.valid.dateofbirth");
			}
			
			if(StringUtils.isEmpty(registerUserForm.getEmail())){
				errors.rejectValue("email", "fbs.common.errors.ana.registerpublicuser.email");
			}else {
				if(!EmailValidator.getInstance().isValid(registerUserForm.getEmail())){
					errors.rejectValue("email", "fbs.common.errors.registeruser.emailvalid");
				}else {
					UserAccountManagerBD userAccountManagerBD = new UserAccountManagerBD();
					Boolean emailExist = false;
					try {
						emailExist = userAccountManagerBD.checkEmailIdExsit(registerUserForm.getEmail().trim());
					} catch (UserAccountManagementException e) {
						errors.rejectValue("email", "fbs.common.errors.registeruser.emailvalid");
					}
					
					if(emailExist){
						errors.rejectValue("email", "fbs.common.errors.registeruser.emailvalid.exists");
					}
					
				}
			}
			
			if(StringUtils.isEmpty(registerUserForm.getName())){
				errors.rejectValue("name", "fbs.common.errors.ana.registerpublicuser.name");
			}
			
			if(StringUtils.isEmpty(registerUserForm.getPassword())){
				errors.rejectValue("password", "fbs.common.errors.ana.passwordmangement.password.mandatory");
			}
			
			if(StringUtils.isEmpty(registerUserForm.getConfirmPassword())){
				errors.rejectValue("confirmPassword", "fbs.common.errors.ana.passwordmangement.confirm.password.mandatory");;
			}
			
			if(!registerUserForm.getPassword().equals(registerUserForm.getConfirmPassword())){
				errors.rejectValue("confirmPassword", "fbs.common.errors.ana.passwordmangement.matchpassword");
			}
			
			if(Long.parseLong(registerUserForm.getSecurityQuestion1())==-1){
				errors.rejectValue("securityQuestion1", "fbs.common.errors.ana.useraccountprofile.securityquestion.mandatory.securityquestion1");
			}
			
			if(Long.parseLong(registerUserForm.getSecurityQuestion2())==-1){
				errors.rejectValue("securityQuestion2", "fbs.common.errors.ana.useraccountprofile.securityquestion.mandatory.securityquestion2");
			}
			
			if (Long.parseLong(registerUserForm.getSecurityQuestion1()) != -1
					&& Long.parseLong(registerUserForm.getSecurityQuestion2()) != -1
					&& registerUserForm.getSecurityQuestion1().equals(registerUserForm.getSecurityQuestion2())) {
				errors.rejectValue("securityQuestion2", "fbs.common.errors.ana.useraccountprofile.securityquestion.unique");
			}else {
				if(Long.parseLong(registerUserForm.getSecurityQuestion1())!=-1 && (registerUserForm.getAns1()==null || registerUserForm.getAns1().trim().length()==0)){
					errors.rejectValue("ans1", "fbs.common.errors.ana.useraccountprofile.securityquestion.mandatory.securityquestion1.answer1");
				}
				
				if(Long.parseLong(registerUserForm.getSecurityQuestion2())!=-1 && (registerUserForm.getAns2()==null || registerUserForm.getAns2().trim().length()==0)){
					errors.rejectValue("ans2", "fbs.common.errors.ana.useraccountprofile.securityquestion.mandatory.securityquestion2.answer2");
				}
			}
			
			if(registerUserForm.getGenderTypeTDesc()==null || registerUserForm.getGenderTypeTDesc() ==0L){
				errors.rejectValue("genderTypeTDesc", "fbs.common.errors.required", new String[]{"Gender"}, "Mandatory field: Gender");
			}
			
			if(registerUserForm.getSalutationTypeTId() == null || registerUserForm.getSalutationTypeTId()==-1){
				errors.rejectValue("salutationTypeTId", "fbs.common.errors.required", new String[]{"Salutation"}, "Mandatory field: Salutation");
			}
			
			
			
			
		}
	}

	

	

}
