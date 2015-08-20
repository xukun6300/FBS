package sg.com.fbs.model.system.security.uam;

import org.apache.commons.lang.StringUtils;

/**
 * @Author Frank Xu $
 * @Created 3:29:00 pm 20 Aug, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public enum PrimaryContactModeEnum {
	// to be used by other module, code need to be consistent with code table
	OFFICE("OFF","Office Tel No."),
	MOBILE("MBL","Mobile No.");
	
	private String contactMode;
	private String description;
	
	private PrimaryContactModeEnum(String contactMode, String description){
		this.contactMode = contactMode;
		this.description = description;
	}
	
	public String getContactMode() {
		return contactMode;
	}
	
	public String getDescription() {
		return description;
	}
	
	public static PrimaryContactModeEnum getPrimaryContactModeEnum(String primaryContactMode){
		if(StringUtils.isEmpty(primaryContactMode)){
			return null;
		}
		
		for (PrimaryContactModeEnum contactMode : PrimaryContactModeEnum.values()) {
			if(primaryContactMode.equals(contactMode.getContactMode())){
				return contactMode;
			}
		}
		
		return null;
	}
}











