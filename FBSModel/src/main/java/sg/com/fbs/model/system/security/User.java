package sg.com.fbs.model.system.security;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.joda.time.DateTime;
import org.springframework.security.core.GrantedAuthority;

import com.sun.tools.classfile.StackMap_attribute.stack_map_frame;

import sg.com.fbs.model.business.pojo.BaseLogPojo;
import sg.com.fbs.model.system.datalog.DataLogIF;
import sg.com.fbs.model.system.transactionlog.TransactionLogIF;


/**
 * @Author Frank Xu $
 * @Created 5:25:40 pm 30 Jul, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public class User extends BaseLogPojo implements UserDetailsIF, TransactionLogIF,DataLogIF{

	private static final long serialVersionUID = -835444054128876618L;

	public static final String LOGINID = "loginId";

	public static final String NAME = "name";
	
	public static final String SALUTATION = "salutation";
	
	public static final String STATUS = "status";
	
	public static final String LAST_SUCCESS_LOGIN_DATE = "lastSuccessLoginDate";
	
	public static final String LAST_FAILED_LOGIN_DATE = "lastFailedLoginDate";
	
	private long userId;

	private String password;

	private long salutation;

	private String name;
	
	private String loginId;

	private long gender;

	private DateTime dateOfBirth;

	private String officeTel;
	
	private String mobileNum;

	private String status;

	private String preferredContactMode;

	private int failedLoginAttempt;

    private DateTime passwordLastUpdateDate;

	private DateTime lastSuccessLoginDate;
	
	private DateTime lastFailedLoginDate;

	private String salt;

	private String salutationDescription;
	
	private Set<UserSecurityQuestion> UserSecurityQuestion;
	
	private Set<UserAccountMapping> userAcctMappings;
	
	public void setUserAcctMappings(Set<UserAccountMapping> userAcctMappings) {
		this.userAcctMappings = userAcctMappings;
	}
	
	public Set<UserAccountMapping> getUserAcctMappings() {
		return userAcctMappings;
	}
	
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getSalutation() {
		return salutation;
	}

	public void setSalutation(long salutation) {
		this.salutation = salutation;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public long getGender() {
		return gender;
	}

	public void setGender(long gender) {
		this.gender = gender;
	}

	public DateTime getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(DateTime dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getOfficeTel() {
		return officeTel;
	}

	public void setOfficeTel(String officeTel) {
		this.officeTel = officeTel;
	}

	public String getMobileNum() {
		return mobileNum;
	}

	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPreferredContactMode() {
		return preferredContactMode;
	}

	public void setPreferredContactMode(String preferredContactMode) {
		this.preferredContactMode = preferredContactMode;
	}

	public int getFailedLoginAttempt() {
		return failedLoginAttempt;
	}

	public void setFailedLoginAttempt(int failedLoginAttempt) {
		this.failedLoginAttempt = failedLoginAttempt;
	}

	public DateTime getPasswordLastUpdateDate() {
		return passwordLastUpdateDate;
	}

	public void setPasswordLastUpdateDate(DateTime passwordLastUpdateDate) {
		this.passwordLastUpdateDate = passwordLastUpdateDate;
	}

	public DateTime getLastSuccessLoginDate() {
		return lastSuccessLoginDate;
	}

	public void setLastSuccessLoginDate(DateTime lastSuccessLoginDate) {
		this.lastSuccessLoginDate = lastSuccessLoginDate;
	}

	public DateTime getLastFailedLoginDate() {
		return lastFailedLoginDate;
	}

	public void setLastFailedLoginDate(DateTime lastFailedLoginDate) {
		this.lastFailedLoginDate = lastFailedLoginDate;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getSalutationDescription() {
		return salutationDescription;
	}

	public void setSalutationDescription(String salutationDescription) {
		this.salutationDescription = salutationDescription;
	}

	public Set<UserSecurityQuestion> getUserSecurityQuestion() {
		return UserSecurityQuestion;
	}

	public void setUserSecurityQuestion(Set<UserSecurityQuestion> userSecurityQuestion) {
		UserSecurityQuestion = userSecurityQuestion;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 31).append(id).append(name).append(loginId).toHashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj==null){
			return false;
		}		
		if(obj==this){
			return true;
		}
		if(obj.getClass()!=getClass()){
			return false;
		}		
		User rhs = (User) obj;		
		return new EqualsBuilder().append(id, rhs.id).append(name, rhs.name).append(loginId, rhs.loginId).isEquals();
	}
	
	@Override
	public String getObjType() {
		return this.getClass().getName();
	}

	@Override
	public Object getEntityLogDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, String> getTableMap() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, String> getColumnsMap() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

}










































