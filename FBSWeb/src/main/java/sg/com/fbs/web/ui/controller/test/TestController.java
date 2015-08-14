package sg.com.fbs.web.ui.controller.test;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.Validator;
import org.springframework.web.servlet.ModelAndView;

import sg.com.fbs.core.techinfra.web.BaseWebController;
import sg.com.fbs.core.techinfra.web.Mvc;
import sg.com.fbs.core.techinfra.web.WebCRUDEnum;
import sg.com.fbs.intranet.web.ui.form.testuser.TestUserForm;
import sg.com.fbs.model.testuser.TestUserRequest;


public class TestController extends BaseWebController {
	@Override
	public String getModuleWebContext() {
		return "test/";
	}

	@Override
	public void preLoad(Map<String, Object> map, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ModelAndView postLoad(Map<String, Object> map,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Validator getCustomValidator() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ModelAndView hello(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("--------------in controller-------------");
	
		
		TestUserForm form = new TestUserForm();
		TestUserRequest pojoRequest = new TestUserRequest();
		setCrudMode(WebCRUDEnum.DETAILS_MODE);
		setCRUDOperation(TestUserCRUD.class);	
		setValidationErrorPage("hello");
		Mvc mvc = new Mvc(form, "hello", pojoRequest);	
		return mvc;
		
	}

	public ModelAndView add(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("--------------in controller-------------");

		TestUserForm form = new TestUserForm();
		TestUserRequest pojoRequest = new TestUserRequest();
		setCrudMode(WebCRUDEnum.INSERT_MODE);
		setCRUDOperation(TestUserCRUD.class);	
		setValidationErrorPage("hello");
		Mvc mvc = new Mvc(form, "hello", pojoRequest);	
		return mvc;
		
	}
	

}
