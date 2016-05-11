package sg.com.fbs.services.account.mgr;

import java.lang.reflect.Type;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

//import org.hibernate.criterion.Criterion;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import sg.com.fbs.core.businfra.facade.CommonFacade;
import sg.com.fbs.core.techinfra.persistence.exception.DataAccessObjectException;
import sg.com.fbs.core.techinfra.util.DateUtil;
import sg.com.fbs.model.account.Account;
import sg.com.fbs.model.account.AccountRequest;
import sg.com.fbs.model.account.AccountStructure;
import sg.com.fbs.model.system.persistence.query.Criteria;
import sg.com.fbs.model.system.persistence.query.CriteriaIF;
import sg.com.fbs.model.system.persistence.query.Criterion;
import sg.com.fbs.model.system.persistence.response.IResponseCRUD;
import sg.com.fbs.model.system.persistence.response.ResponseCRUD;
import sg.com.fbs.services.account.dao.AccountDao;
import sg.com.fbs.services.account.exception.AccountException;

/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu Kun $
 * @Created May 6, 2016 10:22:31 AM $
 * 
 */
public class AccountManager extends CommonFacade{
	
	@Autowired
	private AccountDao accountDao;
	
	@SuppressWarnings("rawtypes")
	public Boolean checkAccountCodeExist(String accountCode) throws AccountException{
		
		CriteriaIF searchCriteria = new Criteria();
		Criterion[] criterions = {new Criterion(Account.ACCOUNT_CODE, accountCode, true), new Criterion(Account.ACT_IND, "Y", true)};
		searchCriteria.setCriterion(criterions);
		searchCriteria.setFetchAll(true);
		
		try {
			IResponseCRUD responseCRUD = accountDao.searchAccount(searchCriteria);
			
			return responseCRUD.getQueryResult().size()>0;
		} catch (DataAccessObjectException e) {
			throw new AccountException(e.getMessageCode(), e);
		}

	}

	@SuppressWarnings("rawtypes")
	public ResponseCRUD saveAccount(AccountRequest accountRequest) throws AccountException{
		try {
			ResponseCRUD response = new ResponseCRUD();

			int currentFY = DateUtil.getCurrentYear();
			
			Account account = new Account();
			account.setAccountCode(accountRequest.getAccountCode());
			account.setAccountDesc(accountRequest.getAccountDesc());
			account.setRequisitionForm(accountRequest.isNeedRequisitionForm() ? "Y" : "N");
			account.setFinancialYear(currentFY);
			account.setSpendPeriod(Integer.valueOf(accountRequest.getAcctSpendingPeriod()));

			account = accountDao.insert(account);
			
			Gson gson = new Gson();			
			Type acctStructureListType = new TypeToken<List<AccountStructure>>(){}.getType();			
			List<AccountStructure> accountStructures = gson.fromJson(accountRequest.getAcctStructureJson(), acctStructureListType);
            System.out.println("JSON value:---->"+accountRequest.getAcctStructureJson());
            
			if(accountStructures!=null && accountStructures.size()>0){
				account.setAcctStructures(accountStructures);
				for (AccountStructure accountStructure : accountStructures) {
					accountStructure.setAccount(account);
					accountStructure = accountDao.insert(accountStructure);
				}
			}
			
			if(account.getId()>0){
				accountRequest.setId(account.getId());
				accountRequest.setAcctStructures(accountStructures);
				response.setCrudResult(account);
			}
			return response;
		} catch (DataAccessObjectException e) {
			e.printStackTrace();
			throw new AccountException(e.getMessageCode(), e);
		}
	}
	
	@SuppressWarnings("rawtypes")
	public IResponseCRUD searchAccount(CriteriaIF criteria) throws AccountException{
		try {
			IResponseCRUD response = accountDao.searchAccount(criteria);
			return response;
		} catch (DataAccessObjectException e) {
			e.printStackTrace();
			throw new AccountException(e.getMessageCode(), e);
		}
		
	}
}





