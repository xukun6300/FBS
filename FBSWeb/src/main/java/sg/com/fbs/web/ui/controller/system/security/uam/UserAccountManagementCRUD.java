package sg.com.fbs.web.ui.controller.system.security.uam;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import sg.com.fbs.core.techinfra.exception.CRUDException;
import sg.com.fbs.core.techinfra.web.WebCRUDIF;
import sg.com.fbs.model.business.pojo.BasePojoRequest;
import sg.com.fbs.model.system.persistence.query.Criteria;
import sg.com.fbs.model.system.persistence.response.IResponseCRUD;
import sg.com.fbs.model.system.security.uam.RegisterUserRequest;
import sg.com.fbs.services.system.security.uam.exception.UserAccountManagementException;
import sg.com.fbs.services.system.security.uam.mgr.UserAccountManagerBD;
import sg.com.fbs.web.ui.form.system.security.uam.RegisterUserForm;
import sg.com.fbs.web.ui.form.system.security.uam.UserSearchForm;

/**
 * @Author Frank Xu $
 * @Created 9:38:30 am 30 Jul, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public class UserAccountManagementCRUD implements WebCRUDIF{

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public IResponseCRUD<?> runQuery(BasePojoRequest pojoRequest, Object form, HttpServletRequest request) throws CRUDException {
		
		IResponseCRUD response = null;
		UserAccountManagerBD userAccountManagerBD = new UserAccountManagerBD();
		
		if(form instanceof UserSearchForm){
			UserSearchForm userSearchForm = (UserSearchForm) form;
			try {
				response = userAccountManagerBD.searchUsers(userSearchForm.getSearchCriteria(request));			
				Collection pojoRequestResults = (Collection) userSearchForm.getCrudQueryResultMapper().mapQueryResult(response.getQueryResult());				
				response.setQueryResult(pojoRequestResults);
			} catch (UserAccountManagementException e) {
				throw new CRUDException(e);
			}
		}

		return response;
	}

	@Override
	public IResponseCRUD<?> runDetails(BasePojoRequest pojoRequest,
			Object form, HttpServletRequest request) throws CRUDException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IResponseCRUD<?> insert(BasePojoRequest pojoRequest, Object form,
			HttpServletRequest request) throws CRUDException {
		IResponseCRUD<?> response = null;
		UserAccountManagerBD userAccountManagerBD = new UserAccountManagerBD();
		
		if(pojoRequest instanceof RegisterUserRequest){
			//RegisterUserForm formBean = (RegisterUserForm)form;
			RegisterUserRequest registerUserRequest = (RegisterUserRequest) pojoRequest;
			try {
				response = userAccountManagerBD.saveNewUser(registerUserRequest);
			} catch (UserAccountManagementException e) {
				throw new CRUDException(e.getMessageCode(), e);
			}
		}
		
		return response;
	}

	@Override
	public IResponseCRUD<?> update(BasePojoRequest pojoRequest, Object form,
			HttpServletRequest request) throws CRUDException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IResponseCRUD<?> delete(BasePojoRequest pojoRequest, Object form,
			HttpServletRequest request) throws CRUDException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IResponseCRUD<?> dynamic(BasePojoRequest pojoRequest, Object form,
			HttpServletRequest request) throws CRUDException {
		// TODO Auto-generated method stub
		return null;
	}

}
