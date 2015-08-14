package sg.com.fbs.model.system.transactionlog;

import java.util.Map;

import org.joda.time.DateTime;

/**
 * @Author Frank Xu $
 * @Created 5:26:39 pm 30 Jul, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public interface TransactionLogIF {

	void setId(long id);
	
	long getId();
	
	String getMessage();
	
	String getTransactionType();
	
	void setModifyon(DateTime dt);
	
	public Map<String, String> getTableMap();
	
	public Map<String, String> getColumnsMap();
}



































