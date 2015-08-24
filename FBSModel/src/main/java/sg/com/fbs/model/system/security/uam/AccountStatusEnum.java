package sg.com.fbs.model.system.security.uam;

/**
 * @Author Frank Xu $
 * @Created 11:08:15 am 24 Aug, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public enum AccountStatusEnum {

	ACTIVE("ACTIVE","ACTIVE"),
	INACTIVE("INACTIVE","INACTIVE"),
	PENDINGACTIVATION("PENDINGACTIVATION","PENDINGACTIVATION"),
	REJECTED("REJECTED","REJECTED");
	
	private String accountStatus;
	
	private String description;
	
	private AccountStatusEnum(String accountStatus, String description){
		this.accountStatus = accountStatus;
		this.description = description;
	}
	
	public String getAccountStatus() {
		return accountStatus;
	}
	
	public String getDescription() {
		return description;
	}
	
	public static AccountStatusEnum[] getAccountTypeEnum(){
		return AccountStatusEnum.values();
	}
	
	public static AccountStatusEnum getEnumFromValue(String value){
		return AccountStatusEnum.valueOf(value);
	}
}

