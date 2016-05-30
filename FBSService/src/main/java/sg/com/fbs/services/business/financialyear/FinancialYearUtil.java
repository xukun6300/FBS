package sg.com.fbs.services.business.financialyear;

import java.util.Calendar;

/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu Kun $
 * @Created May 30, 2016 4:14:10 PM $
 * 
 */
public class FinancialYearUtil {

	public static int getCurrentFinancialYear(){
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		return year;	
	}
}
