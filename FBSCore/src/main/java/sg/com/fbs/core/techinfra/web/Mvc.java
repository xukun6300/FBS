package sg.com.fbs.core.techinfra.web;

import org.springframework.web.servlet.ModelAndView;

import sg.com.fbs.model.business.pojo.BasePojoRequest;

public class Mvc extends ModelAndView {

	public Mvc(){
		super();
	}
	
	public Mvc(String view){
		super(view);
	}
	
	public Mvc(BaseWebFormIF form, String view){
		super(view);
		addForm(form);
	}
	
	public Mvc(BaseWebFormIF form, String view, BasePojoRequest pojo){
		super(view);
		addForm(form);
		addPojoRequest(pojo);
	}
	
	public void addForm(BaseWebFormIF obj){
		super.addObject(BaseWebEnum.COMMAND_FORM.toString(), obj); // form
	}
	
	public void addPojoRequest(BasePojoRequest obj){
		super.addObject(BaseWebEnum.MODEL_BEAN.toString(), obj); // business model (pojo request)
	}
	
	public BaseWebFormIF getForm(){
		return (BaseWebFormIF) super.getModelMap().get(BaseWebEnum.COMMAND_FORM.toString());
	}
}
