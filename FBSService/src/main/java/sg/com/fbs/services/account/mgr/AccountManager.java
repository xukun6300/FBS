package sg.com.fbs.services.account.mgr;

import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import sg.com.fbs.core.businfra.facade.CommonFacade;
import sg.com.fbs.core.techinfra.persistence.exception.DataAccessObjectException;
import sg.com.fbs.model.account.Account;
import sg.com.fbs.model.account.AccountRequest;
import sg.com.fbs.model.account.AccountStructure;
import sg.com.fbs.model.system.persistence.response.ResponseCRUD;
import sg.com.fbs.services.account.dao.AccountDao;
import sg.com.fbs.services.account.exception.AccountException;

/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu Kun $
 * @Created May 6, 2016 10:22:31 AM $
 * 
 */
public class AccountManager extends CommonFacade{

	@SuppressWarnings("rawtypes")
	public ResponseCRUD saveAccount(AccountRequest accountRequest) throws AccountException{
		try {
			ResponseCRUD response = new ResponseCRUD();

			AccountDao accountDao = new AccountDao();

			Account account = new Account();
			account.setAccountCode(accountRequest.getAccountCode());
			account.setAccountDesc(accountRequest.getAccountDesc());
			account.setRequisitionForm(accountRequest.isNeedRequisitionForm() ? "Y" : "N");
			account.setFinancialYear("2016");
			account.setSpendPeriod(Integer.valueOf(accountRequest.getAcctSpendingPeriod()));

			account = accountDao.insert(account);
			
			Gson gson = new Gson();			
			Type acctStructureListType = new TypeToken<List<AccountStructure>>(){}.getType();			
			List<AccountStructure> accountStructures = gson.fromJson(accountRequest.getAcctStructureJson(), acctStructureListType);
            System.out.println("JSON value:---->"+accountRequest.getAcctStructureJson());
            
			if(accountStructures!=null && accountStructures.size()>0){
				account.setAcctStructures(new HashSet<AccountStructure>(accountStructures));
				for (AccountStructure accountStructure : accountStructures) {
					accountStructure.setAccount(account);
					accountStructure = accountDao.insert(accountStructure);
				}
			}
			
			if(account.getId()>0){
				accountRequest.setId(account.getId());
				response.setCrudResult(account);
			}
			return response;
		} catch (DataAccessObjectException e) {
			throw new AccountException(e.getMessageCode(), e);
		}
	}
}
