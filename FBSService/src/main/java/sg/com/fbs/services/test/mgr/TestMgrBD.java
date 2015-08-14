package sg.com.fbs.services.test.mgr;

import sg.com.fbs.core.businfra.businessdelegate.BusinessDelegate;
import sg.com.fbs.core.techinfra.exception.ApplicationCoreException;
import sg.com.fbs.model.system.persistence.query.CriteriaIF;
import sg.com.fbs.model.system.persistence.response.ResponseCRUD;
import sg.com.fbs.model.testuser.TestUserRequest;

public class TestMgrBD extends BusinessDelegate{

	public ResponseCRUD saveUser(TestUserRequest testUserRequest) throws ApplicationCoreException{
		return (ResponseCRUD) new TestManager().run(testUserRequest);
	}
	
	public ResponseCRUD searchUsers(CriteriaIF criteria) throws ApplicationCoreException{
		return (ResponseCRUD) new TestManager().run(criteria);
	}
}
