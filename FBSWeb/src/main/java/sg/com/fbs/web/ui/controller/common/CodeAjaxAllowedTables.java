package sg.com.fbs.web.ui.controller.common;

/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu $
 * @Created 12:22:49 pm 18 Sep, 2015 $
 * 
 */
public enum CodeAjaxAllowedTables {

	MASTERCODE_TYPE("MASTER_CODETYPE_TB");
	
	private String tbName;
	
	private CodeAjaxAllowedTables(String tbName){
		this.tbName = tbName;
	}
	
	public String getTableName(){
		return this.tbName;
	}
}
