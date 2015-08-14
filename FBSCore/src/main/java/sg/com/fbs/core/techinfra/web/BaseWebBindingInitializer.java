package sg.com.fbs.core.techinfra.web;

import org.joda.time.DateTime;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

import sg.com.fbs.core.techinfra.util.JodaDatetimeEditor;

public class BaseWebBindingInitializer implements WebBindingInitializer{

	@Override
	public void initBinder(WebDataBinder binder, WebRequest request) {
		binder.registerCustomEditor(DateTime.class, new JodaDatetimeEditor());
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
	}

}
