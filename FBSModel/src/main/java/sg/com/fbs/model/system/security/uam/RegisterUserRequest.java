package sg.com.fbs.model.system.security.uam;

import lombok.Getter;
import lombok.Setter;

import org.joda.time.DateTime;

import sg.com.fbs.model.business.pojo.BasePojoRequest;

/**
 * @Author Frank Xu $
 * @Created 9:21:17 am 14 Aug, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public class RegisterUserRequest extends BasePojoRequest{

	private static final long serialVersionUID = 489773123605332593L;

	
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
}














































