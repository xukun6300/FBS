package sg.com.fbs.web.ui.controller.test;

import javax.servlet.http.HttpServletRequest;

import sg.com.fbs.core.techinfra.exception.ApplicationCoreException;
import sg.com.fbs.core.techinfra.exception.CRUDException;
import sg.com.fbs.core.techinfra.web.WebCRUDIF;
import sg.com.fbs.intranet.web.ui.form.testuser.TestUserForm;
import sg.com.fbs.model.business.pojo.BasePojoRequest;
import sg.com.fbs.model.system.persistence.query.Criteria;
import sg.com.fbs.model.system.persistence.query.CriteriaIF;
import sg.com.fbs.model.system.persistence.query.Criterion;
import sg.com.fbs.model.system.persistence.query.Order;
import sg.com.fbs.model.system.persistence.query.OrderIF;
import sg.com.fbs.model.system.persistence.response.IResponseCRUD;
import sg.com.fbs.model.system.persistence.response.ResponseCRUD;
import sg.com.fbs.model.testuser.TestUserRequest;
import sg.com.fbs.services.test.mgr.TestManager;
import sg.com.fbs.services.test.mgr.TestMgrBD;
import sg.com.fbs.techinfra.persistence.dao.BaseDao;
import sg.com.fbs.user.User;

public class TestUserCRUD implements WebCRUDIF{

	@Override
	public IResponseCRUD<?> runQuery(BasePojoRequest pojoRequest, Object form,
			HttpServletRequest request) throws CRUDException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IResponseCRUD<?> runDetails(BasePojoRequest pojoRequest,
			Object form, HttpServletRequest request) throws CRUDException {
		ResponseCRUD responseCRUD = new ResponseCRUD();
		TestUserForm testUserForm = (TestUserForm) form;
		if(pojoRequest instanceof TestUserRequest){
			TestUserRequest testUserRequest = (TestUserRequest) pojoRequest;
			
			TestMgrBD bd = new TestMgrBD();
			try {

				responseCRUD = (ResponseCRUD) bd.searchUsers(testUserForm.getSearchCriteria(request));
			} catch (ApplicationCoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//responseCRUD = mgr.saveUser(testUserRequest);
		}
		return responseCRUD;
	}

	@Override
	public IResponseCRUD<?> insert(BasePojoRequest pojoRequest, Object form,
			HttpServletRequest request) throws CRUDException {
		ResponseCRUD responseCRUD = new ResponseCRUD();
		
		TestUserForm testUserForm = (TestUserForm) form;
		if(pojoRequest instanceof TestUserRequest){
			TestUserRequest testUserRequest = (TestUserRequest) pojoRequest;
			
			TestMgrBD bd = new TestMgrBD();
			try {

				responseCRUD = (ResponseCRUD) bd.saveUser(testUserRequest);
			} catch (ApplicationCoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//responseCRUD = mgr.saveUser(testUserRequest);
		}
		return responseCRUD;
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
