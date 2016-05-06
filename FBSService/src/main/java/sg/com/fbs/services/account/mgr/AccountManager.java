package sg.com.fbs.services.account.mgr;

import sg.com.fbs.core.businfra.facade.CommonFacade;
import sg.com.fbs.model.account.AccountRequest;
import sg.com.fbs.model.system.persistence.response.ResponseCRUD;
import sg.com.fbs.services.account.exception.AccountException;

/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu Kun $
 * @Created May 6, 2016 10:22:31 AM $
 * 
 */
public class AccountManager extends CommonFacade{

	public ResponseCRUD saveAccount(AccountRequest accountRequest) throws AccountException{
		
		ResponseCRUD response = new ResponseCRUD();
		
		
		return response;
	}
}
