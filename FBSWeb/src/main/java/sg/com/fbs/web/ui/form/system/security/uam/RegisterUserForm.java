package sg.com.fbs.web.ui.form.system.security.uam;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import lombok.Getter;
import lombok.Setter;

import org.joda.time.DateTime;

import sg.com.fbs.common.form.BusinessQueryWebForm;
import sg.com.fbs.model.system.persistence.query.Criteria;
import sg.com.fbs.model.system.persistence.query.CriteriaIF;

/**
 * @Author Frank Xu $
 * @Created 5:03:07 pm 30 Jul, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public class RegisterUserForm extends BusinessQueryWebForm{

	private static final long serialVersionUID = -8180848893263301207L;

	public static final String SALUTATION_TYPE = "salutationType";
	
	public static final String GENDER_TYPE = "genderType";
	
	public static final String PRIMARYCONTACT_TYPE = "primaryContactType";
	
	public static final String SECURITY_QUESTION = "securityQuestion";
	
	@Setter
	@Getter
	private String name;
	@Setter
	@Getter
	private String email;
	@Setter
	@Getter
	private DateTime dob;
	@Setter
	@Getter
	private Long salutationTypeTId;
	@Setter
	@Getter
	private Long genderTypeTDesc;
	@Setter
	@Getter
	private String primaryContactTypeT;
	@Setter
	@Getter
	private String captchaResponse;
	@Setter
	@Getter
	private String captchaValidationRtl;
	@Setter
	@Getter
	private String securityQuestion1;
	@Setter
	@Getter
	private String securityQuestion2;
	@Setter
	@Getter
	private String ans1;
	@Setter
	@Getter
	private String ans2;
	@Setter
	@Getter
	private String encryptedAnswer1;
	@Setter
	@Getter
	private String encryptedAnswer2;
	@Setter
	@Getter
	private String password;
	@Setter
	@Getter
	private String confirmPassword;
	@Setter
	@Getter
	private String salt;
	@Setter
	@Getter
	private String officeTelNo;
	@Setter
	@Getter
	private String countryCodeOfficeTelNo;
	@Setter
	@Getter
	private String mobileNo;
	@Setter
	@Getter
	private String countryCodeMobileNo;
	@Setter
	@Getter
	private String modulus;
	@Setter
	@Getter
	private String exponent;
	
	
	
	@Override
	public Map<String, Map<String, Object>> getDefaultValues() {
		
		return super.getDefaultValues();
	}
	
	@Override
	public CriteriaIF getSearchCriteria(HttpServletRequest request) {
		CriteriaIF searchCriteria = new Criteria();
		return null;
	}

}





































