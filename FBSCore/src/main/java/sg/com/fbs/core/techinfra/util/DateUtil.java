package sg.com.fbs.core.techinfra.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

public class DateUtil {

	protected static Logger logger = Logger.getLogger(DateUtil.class);
	
	public static final String DEFAULT_DATE_FORMAT = "dd/MM/yyyy";
	
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

}
