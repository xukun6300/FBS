package sg.com.fbs.core.techinfra.util;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;

public class ResponseUtils {

	public static void write(PageContext pageContext, String text) throws JspException{
		JspWriter writer = pageContext.getOut();
		try {
			writer.print(text);
		} catch (IOException e) {
			throw new JspException(e.getMessage());
		}
	}
}
