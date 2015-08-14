package sg.com.fbs.services.test.mgr;

import java.util.Collection;

import sg.com.fbs.core.businfra.facade.CommonFacade;
import sg.com.fbs.core.techinfra.persistence.exception.DataAccessObjectException;
import sg.com.fbs.model.system.persistence.query.Criteria;
import sg.com.fbs.model.system.persistence.query.CriteriaIF;
import sg.com.fbs.model.system.persistence.query.Criterion;
import sg.com.fbs.model.system.persistence.query.Order;
import sg.com.fbs.model.system.persistence.query.OrderIF;
import sg.com.fbs.model.system.persistence.response.IResponseCRUD;
import sg.com.fbs.model.system.persistence.response.ResponseCRUD;
import sg.com.fbs.model.testuser.TestUserRequest;
import sg.com.fbs.techinfra.persistence.dao.BaseDao;
import sg.com.fbs.user.User;


public class TestManager extends CommonFacade {
	
	public ResponseCRUD saveUser(TestUserRequest testUserRequest){
		ResponseCRUD responseCRUD = new ResponseCRUD();
		User user = new User();
		user.setId(testUserRequest.getId());
		user.setName(testUserRequest.getName());
		user.setPassword(testUserRequest.getPassword());
		
		/*user.setName("jones");
		user.setPassword("james");
		*/
		
		try {
			new BaseDao().insert(user);
		} catch (DataAccessObjectException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		responseCRUD.setCrudResult(user);
		return responseCRUD;
	}
	
	
	@SuppressWarnings("rawtypes")
	public ResponseCRUD searchUsers(CriteriaIF criteria){
		ResponseCRUD responseCRUD = new ResponseCRUD();
		try {
			responseCRUD = (ResponseCRUD) new BaseDao().search(User.class, criteria);
		} catch (DataAccessObjectException e) {
		}

		return responseCRUD;
	}
	
	


}
