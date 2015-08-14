package sg.com.fbs.model.system.datalog;

import org.joda.time.DateTime;

/**
 * @Author Frank Xu $
 * @Created 5:23:21 pm 30 Jul, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public interface DataLogIF {

	void setId(long id);
	
	long getId();
	
	String getTransactionType();
	
	String getObjType();
	
	void setModifyon(DateTime dt);
	
	Object getEntityLogDetails();
}
