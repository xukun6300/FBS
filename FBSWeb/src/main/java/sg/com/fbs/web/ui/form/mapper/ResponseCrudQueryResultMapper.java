package sg.com.fbs.web.ui.form.mapper;

import java.io.Serializable;

/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu $
 * @Created 2:55:06 pm 9 Sep, 2015 $
 * 
 */
public interface ResponseCrudQueryResultMapper extends Serializable {
	
	Object mapQueryResult(Object results);
}
