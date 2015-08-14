package sg.com.fbs.common.form;

import sg.com.fbs.core.techinfra.web.BaseWebFormBean;

public abstract class BusinessWebForm extends BaseWebFormBean{

	private static final long serialVersionUID = 3612919293091849217L;

	public String[] getDefaultOrders(){
		String[] orders = {ID};
		return orders;
	}
}
