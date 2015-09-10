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
	
	@Setter
	@Getter
	private long userId;
	@Setter
	@Getter
	private String password;
	@Setter
	@Getter
	private long salutation;
	@Setter
	@Getter
	private String name;
	@Setter
	@Getter
	private String loginId;
	@Setter
	@Getter
	private long gender;
	@Setter
	@Getter
	private DateTime dateOfBirth;
	@Setter
	@Getter
	private String officeTel;
	@Setter
	@Getter
	private String mobileNum;
	@Setter
	@Getter
	private String status;
	@Setter
	@Getter
	private String preferredContactMode;
	@Setter
	@Getter
	private int failedLoginAttempt;
	@Setter
	@Getter
    private DateTime passwordLastUpdateDate;
	@Setter
	@Getter
	private DateTime lastSuccessLoginDate;
	@Setter
	@Getter
	private DateTime lastFailedLoginDate;
	@Setter
	@Getter
	private String salt;
	@Setter
	@Getter
	private String salutationDescription;
	@Setter
	@Getter
	private Set<UserSecurityQuestion> UserSecurityQuestion;
	
	
	
	
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










































