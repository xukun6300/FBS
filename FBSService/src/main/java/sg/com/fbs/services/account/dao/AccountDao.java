package sg.com.fbs.services.account.dao;

import sg.com.fbs.core.techinfra.persistence.exception.DataAccessObjectException;
import sg.com.fbs.model.account.Account;
import sg.com.fbs.model.account.AccountStructure;
import sg.com.fbs.model.system.persistence.query.CriteriaIF;
import sg.com.fbs.model.system.persistence.response.IResponseCRUD;
import sg.com.fbs.techinfra.persistence.dao.BaseDao;

/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu Kun $
 * @Created May 6, 2016 10:38:04 AM $
 * 
 */
public class AccountDao extends BaseDao{

	public Account insert(Account account) throws DataAccessObjectException{
		account = (Account) super.insert(account);
		return account;
	}
	
	public AccountStructure insert(AccountStructure accountStructure) throws DataAccessObjectException{
		accountStructure = (AccountStructure) super.insert(accountStructure);
		return accountStructure;
	}
	
	@SuppressWarnings("rawtypes")
	public IResponseCRUD searchAccount(CriteriaIF criteria) throws DataAccessObjectException{
		IResponseCRUD response = search(Account.class, criteria);
		return response;
	}
	
}
