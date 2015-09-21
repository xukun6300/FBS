package sg.com.fbs.core.techinfra.persistence.dao;

/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu $
 * @Created 5:10:53 pm 16 Sep, 2015 $
 * 
 */
public enum DaoErrorCodesEnum {

	DAO_RECORD_ALREADY_EXIST {
		@Override
		public String toString() {
			return "SYS0008";
		}
	},
	
	DAO_RECORD_NOT_FOUND {
		@Override
		public String toString() {
			return "core.dao.error.record.not.found";
		}
	},
}
