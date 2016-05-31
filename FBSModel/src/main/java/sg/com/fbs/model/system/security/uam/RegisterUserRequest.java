package sg.com.fbs.model.system.security.uam;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

import org.joda.time.DateTime;

import sg.com.fbs.model.business.pojo.BasePojoRequest;

/**
 * @Author Frank Xu $
 * @Created 9:21:17 am 14 Aug, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public class RegisterUserRequest extends BasePojoRequest{

	private static final long serialVersionUID = 489773123605332593L;

	private String name;

	private String email;

	private DateTime dob;

	private Long salutationTypeTId;
	
	private Long genderTypeTDesc;

	private String primaryContactTypeT;
	
	private String captchaResponse;
	
	private String captchaValidationRtl;
	
	private String securityQuestion1;
	
	private String securityQuestion2;

	private String ans1;
	
	private String ans2;
	
	private String encryptedAnswer1;
	
	private String encryptedAnswer2;
	
	private String password;
	
	private String confirmPassword;
	
	private String salt;
	
	private String officeTelNo;
	
	private String countryCodeOfficeTelNo;
	
	private String mobileNo;
	
	private String countryCodeMobileNo;
	
	private String modulus;
	
	private String exponent;

    private String selectedAccounts;
    
    private String allAccounts;
    
    private List<String> selectedAccountList;
	
	public void setSelectedAccountList(List<String> selectedAccountList) {
		this.selectedAccountList = selectedAccountList;
	}
	
	public List<String> getSelectedAccountList() {
		return selectedAccountList;
	}
    
    public String getAllAccounts() {
		return allAccounts;
	}
    
    public void setAllAccounts(String allAccounts) {
		this.allAccounts = allAccounts;
	}
	
	public void setSelectedAccounts(String selectedAccounts) {
		this.selectedAccounts = selectedAccounts;
	}
	
	public String getSelectedAccounts() {
		return selectedAccounts;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public DateTime getDob() {
		return dob;
	}

	public void setDob(DateTime dob) {
		this.dob = dob;
	}

	public Long getSalutationTypeTId() {
		return salutationTypeTId;
	}

	public void setSalutationTypeTId(Long salutationTypeTId) {
		this.salutationTypeTId = salutationTypeTId;
	}

	public Long getGenderTypeTDesc() {
		return genderTypeTDesc;
	}

	public void setGenderTypeTDesc(Long genderTypeTDesc) {
		this.genderTypeTDesc = genderTypeTDesc;
	}

	public String getPrimaryContactTypeT() {
		return primaryContactTypeT;
	}

	public void setPrimaryContactTypeT(String primaryContactTypeT) {
		this.primaryContactTypeT = primaryContactTypeT;
	}

	public String getCaptchaResponse() {
		return captchaResponse;
	}

	public void setCaptchaResponse(String captchaResponse) {
		this.captchaResponse = captchaResponse;
	}

	public String getCaptchaValidationRtl() {
		return captchaValidationRtl;
	}

	public void setCaptchaValidationRtl(String captchaValidationRtl) {
		this.captchaValidationRtl = captchaValidationRtl;
	}

	public String getSecurityQuestion1() {
		return securityQuestion1;
	}

	public void setSecurityQuestion1(String securityQuestion1) {
		this.securityQuestion1 = securityQuestion1;
	}

	public String getSecurityQuestion2() {
		return securityQuestion2;
	}

	public void setSecurityQuestion2(String securityQuestion2) {
		this.securityQuestion2 = securityQuestion2;
	}

	public String getAns1() {
		return ans1;
	}

	public void setAns1(String ans1) {
		this.ans1 = ans1;
	}

	public String getAns2() {
		return ans2;
	}

	public void setAns2(String ans2) {
		this.ans2 = ans2;
	}

	public String getEncryptedAnswer1() {
		return encryptedAnswer1;
	}

	public void setEncryptedAnswer1(String encryptedAnswer1) {
		this.encryptedAnswer1 = encryptedAnswer1;
	}

	public String getEncryptedAnswer2() {
		return encryptedAnswer2;
	}

	public void setEncryptedAnswer2(String encryptedAnswer2) {
		this.encryptedAnswer2 = encryptedAnswer2;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getOfficeTelNo() {
		return officeTelNo;
	}

	public void setOfficeTelNo(String officeTelNo) {
		this.officeTelNo = officeTelNo;
	}

	public String getCountryCodeOfficeTelNo() {
		return countryCodeOfficeTelNo;
	}

	public void setCountryCodeOfficeTelNo(String countryCodeOfficeTelNo) {
		this.countryCodeOfficeTelNo = countryCodeOfficeTelNo;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getCountryCodeMobileNo() {
		return countryCodeMobileNo;
	}

	public void setCountryCodeMobileNo(String countryCodeMobileNo) {
		this.countryCodeMobileNo = countryCodeMobileNo;
	}

	public String getModulus() {
		return modulus;
	}

	public void setModulus(String modulus) {
		this.modulus = modulus;
	}

	public String getExponent() {
		return exponent;
	}

	public void setExponent(String exponent) {
		this.exponent = exponent;
	}
	
	
}














































