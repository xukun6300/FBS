package sg.com.fbs.core.techinfra.util;

import java.beans.PropertyEditorSupport;

import org.joda.time.DateTime;
import org.springframework.util.StringUtils;

public class JodaDatetimeEditor extends PropertyEditorSupport{

	public String DEFAULT_DATE_FORMAT = "dd/MM/yyyy";
	
	public JodaDatetimeEditor(){
	
	}
	
	public JodaDatetimeEditor(String dateFormat){
		DEFAULT_DATE_FORMAT = dateFormat;
	}
	
	@Override
	public void setAsText(String text){
		if (!StringUtils.hasText(text)) {
			setValue(null);
		} else {
			setValue(DateUtil.convertDateStringToDate(text, DEFAULT_DATE_FORMAT));
		}
	}
	
	@Override
	public String getAsText() {
		DateTime value = (DateTime) getValue();
		return (value != null ? DateUtil.convertDateToDateString(value, DEFAULT_DATE_FORMAT) : "");
	}
}
