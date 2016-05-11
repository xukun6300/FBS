package sg.com.fbs.core.techinfra.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.sun.tools.classfile.StackMap_attribute.stack_map_frame;

/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu $
 * @Created 4:14:32 pm 10 Sep, 2015 $
 * 
 */
public class DateUtil {

	protected static Logger logger = Logger.getLogger(DateUtil.class);
	
	public static final String DEFAULT_DATE_FORMAT = "dd/MM/yyyy";
	
	public static final String DATE_FORMAT1 = "dd MMM yyyy";
	
	public static final String DEFAULT_DATETIME_FORMAT = "dd MMM yyyy HH:mm:ss";
	
	public static DateTime convertDateStringToDate(String dateStr, String format){
		return DateTimeFormat.forPattern(format).parseDateTime(dateStr);
	}
	
	public static String convertDateToDateString(DateTime date, String format) {
		return DateTimeFormat.forPattern(format).print(date);
	}
	
	public static String convertDateToDateString(DateTime date){
		try { 
			return new SimpleDateFormat(DEFAULT_DATE_FORMAT).format(date);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	public static String dateToString(Date date, String outPattern) {
		try {
			return new SimpleDateFormat(outPattern).format(date);
		} catch (Exception e) {
			return null;
		}
	}
	
	public static int getCurrentYear(){		
		return Calendar.getInstance().get(Calendar.YEAR);
	}

}
