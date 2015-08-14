package sg.com.fbs.model.domain.mastercode;

/**
 * @Author Frank Xu $
 * @Created 11:37:32 am 4 Aug, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public enum MasterCodeTypeEnum {

	SALUTATION {
		public String toString(){
			return "SALUTATION";
		}
	},
	
	GENDER {
		@Override
		public String toString() {
			return "GENDER";
		}
	},
	
	PRIMARY_CONTACT_MODE {
		@Override
		public String toString() {
			return "PRIMARY_CONTACT_MODE";
		}
	}
	
	
}
