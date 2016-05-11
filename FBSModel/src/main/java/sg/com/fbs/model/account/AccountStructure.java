package sg.com.fbs.model.account;

import sg.com.fbs.model.business.pojo.BaseLogPojo;

/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu Kun $
 * @Created May 6, 2016 2:40:56 PM $
 * 
 */
public class AccountStructure extends BaseLogPojo{

	private static final long serialVersionUID = -7145608437246771389L;
	
	private Account account;
	
	private String columnName;
	
	private String columnSize;
	
	private String columnType;
	
	private int sequence;
	
	private String defaultColumn;
	
	public void setDefaultColumn(String defaultColumn) {
		this.defaultColumn = defaultColumn;
	}
	
	public String getDefaultColumn() {
		return defaultColumn;
	}

	public Account getAccount() {
		return account;
	}
	
	public void setAccount(Account account) {
		this.account = account;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getColumnSize() {
		return columnSize;
	}

	public void setColumnSize(String columnSize) {
		this.columnSize = columnSize;
	}

	public String getColumnType() {
		return columnType;
	}

	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	
	 

}
