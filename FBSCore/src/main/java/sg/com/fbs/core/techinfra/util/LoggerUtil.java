package sg.com.fbs.core.techinfra.util;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;

/**
 * @author Frank Xu
 *
 */
public class LoggerUtil {

	public static void logError(Logger logger, Throwable cause){
		if(logger!=null && cause!=null){
			logger.error(ExceptionUtils.getFullStackTrace(cause));
		}
	}
}
	
