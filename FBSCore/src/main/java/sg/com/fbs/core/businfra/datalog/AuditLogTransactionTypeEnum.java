package sg.com.fbs.core.businfra.datalog;

import lombok.Getter;

/**
 * @Author Frank Xu $
 * @Created 9:40:38 am 29 Jul, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public enum AuditLogTransactionTypeEnum {
	INSERT("Insert"),
	UPDATE("Update"),
	DELETE("Delete");
	
	private String formalName;
	
	public String getFormalName() {
		return formalName;
	}
	
	private AuditLogTransactionTypeEnum(final String formalName){
		this.formalName = formalName;
	}
}
