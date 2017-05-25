package sg.com.fbs.services.account.mgr;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

//import org.hibernate.criterion.Criterion;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import sg.com.fbs.core.businfra.facade.CommonFacade;
import sg.com.fbs.core.techinfra.persistence.exception.DataAccessObjectException;
import sg.com.fbs.core.techinfra.security.util.PrincipalSecUtil;
import sg.com.fbs.core.techinfra.util.DateUtil;
import sg.com.fbs.model.account.Account;
import sg.com.fbs.model.account.AccountRequest;
import sg.com.fbs.model.account.AccountStructure;
import sg.com.fbs.model.budgetconfig.BudgetConfig;
import sg.com.fbs.model.domain.enumeration.ActiveStatusEnum;
import sg.com.fbs.model.system.persistence.query.Criteria;
import sg.com.fbs.model.system.persistence.query.CriteriaIF;
import sg.com.fbs.model.system.persistence.query.Criterion;
import sg.com.fbs.model.system.persistence.query.CriterionIF;
import sg.com.fbs.model.system.persistence.query.Order;
import sg.com.fbs.model.system.persistence.query.RestrictionType;
import sg.com.fbs.model.system.persistence.response.IResponseCRUD;
import sg.com.fbs.model.system.persistence.response.ResponseCRUD;
import sg.com.fbs.model.system.security.UserAccountMapping;
import sg.com.fbs.services.account.dao.AccountDao;
import sg.com.fbs.services.account.exception.AccountException;
import sg.com.fbs.services.budgetconfig.exception.BudgetConfigException;
import sg.com.fbs.services.budgetconfig.mgr.BudgetConfigManager;
import sg.com.fbs.services.business.financialyear.FinancialYearUtil;

/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu Kun $
 * @Created May 6, 2016 10:22:31 AM $
 * 
 */
public class AccountManager extends CommonFacade{
	
	private BudgetConfigManager budgetConfigManager = new BudgetConfigManager();
	
	private AccountDao accountDao = new AccountDao();
	
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
	public IResponseCRUD updateAccount(AccountRequest accountRequest) throws AccountException {

		ResponseCRUD response = new ResponseCRUD();
		try {
			Account account = getAccountById(accountRequest.getId());
			
			if(account!=null){
				account.setAccountDesc(accountRequest.getAccountDesc());
				account.setRequisitionForm(accountRequest.isNeedRequisitionForm() ? "Y" : "N");
				account.setSpendPeriod(Integer.valueOf(accountRequest.getAcctSpendingPeriod()));

				account = (Account) accountDao.update(account);

				if(account.getAcctStructures()!=null && account.getAcctStructures().size()>0){
					for (AccountStructure accountStructure : account.getAcctStructures()) {
						accountDao.softDelete(accountStructure);
					}
				}
				
				Gson gson = new Gson();			
				Type acctStructureListType = new TypeToken<List<AccountStructure>>(){}.getType();
				List<AccountStructure> accountStructures = gson.fromJson(accountRequest.getAcctStructureJson(), acctStructureListType);		
				
				if(accountStructures!=null && accountStructures.size()>0){
					for (AccountStructure accountStructure : accountStructures) {
						accountStructure.setAccount(account);
						accountStructure = accountDao.insert(accountStructure);
					}				
					account.setAcctStructures(accountStructures);
				}			
				
				response.setCrudResult(account);
			}
			
			
			
		} catch (DataAccessObjectException e) {
			e.printStackTrace();
			throw new AccountException(e.getMessageCode(), e);
		}
		return response;
	}
	
	
	@SuppressWarnings("rawtypes")
	public IResponseCRUD searchAccount(CriteriaIF criteria) throws AccountException{
		try {
			AccountDao accountDao = new AccountDao();
			IResponseCRUD response = accountDao.searchAccount(criteria);
			return response;
		} catch (DataAccessObjectException e) {
			e.printStackTrace();
			throw new AccountException(e.getMessageCode(), e);
		}
		
	}
	
	public Map<String, String> getFinancialYears() throws AccountException{
		AccountDao accountDao = new AccountDao();
		Map<String, String> fyMap = new LinkedHashMap<String, String>();
		try {
			List<String> fyList = accountDao.getDistinctFinancialYearList();
			
			if(fyList!=null && fyList.size()>0){
				for (String fy : fyList) {
					fyMap.put(fy, fy);
				}
			}
			
			return fyMap;
		} catch (DataAccessObjectException e) {
			e.printStackTrace();
			throw new AccountException(e.getMessageCode(), e);
		}		
	}
	
	public void loadAccountDetails(AccountRequest accountRequest) throws AccountException{
		long accountId = accountRequest.getId();
		Account account = getAccountById(accountId);
		if(account!=null){
			accountRequest.setAccountCode(account.getAccountCode());
			accountRequest.setAccountDesc(account.getAccountDesc());
			accountRequest.setAcctSpendingPeriod(String.valueOf(account.getSpendPeriod()));
			accountRequest.setAcctStructures(account.getAcctStructures());
			accountRequest.setFinancialYear(String.valueOf(account.getFinancialYear()));
			accountRequest.setNeedRequisitionForm("Y".equalsIgnoreCase(account.getRequisitionForm()));
		}
	}
	
	public Account getAccountById(long accountId) throws AccountException{
		try {
			Account account = (Account) accountDao.findObject(Account.class, Account.ID, accountId);
			return account;
		} catch (DataAccessObjectException e) {			
			e.printStackTrace();
			throw new AccountException(e.getMessageCode(), e);
		}
	}
	
	// can extract one method?
	public AccountStructure getAccountStructureById(long acctStructureId) throws AccountException{
		try {
			AccountStructure accountStructure = (AccountStructure) accountDao.findObject(AccountStructure.class, AccountStructure.ID, acctStructureId);
			return accountStructure;
		} catch (DataAccessObjectException e) {			
			e.printStackTrace();
			throw new AccountException(e.getMessageCode(), e);
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ResponseCRUD deleteAndShowAccount(CriteriaIF criteria, long accountId) throws AccountException{
		try {
			ResponseCRUD response = new ResponseCRUD();
			AccountDao accountDao = new AccountDao();
			Account account = (Account) accountDao.findObject(Account.class, Account.ID, accountId);
			account = (Account) accountDao.softDelete(account);
			accountDao.flush();
			
			IResponseCRUD res = searchAccount(criteria);
			
			response.setQueryResult(res.getQueryResult());
			response.setTotalPages(res.getTotalPages());
			response.setTotalRecords(res.getTotalRecords());
			response.setCriteria(criteria);
			
			Map<String, Object> moreQueryResult = new HashMap<String, Object>();
			moreQueryResult.put("successMsg", true);
			moreQueryResult.put("deletedAccount", account);
			response.setMoreQueryResult(moreQueryResult);
			
			return response;
		} catch (DataAccessObjectException e) {
			throw new AccountException(e.getMessageCode(), e);
		}
	}
		
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, String> getAllAccountsForCurrentFY() throws AccountException{		
		try {
			Map<String, String> accountMap = new LinkedHashMap<String, String>();
			int fy = FinancialYearUtil.getCurrentFinancialYear();			
			AccountDao accountDao = new AccountDao();
			
			CriteriaIF criteria = new Criteria();
			criteria.appendCriterion(new Criterion(Account.FINANCIAL_YEAR, fy));
			criteria.appendCriterion(new Criterion(Account.ACT_IND, ActiveStatusEnum.YES.toString()));
			Order order = new Order(Account.ACCOUNT_CODE, true);
			Order[] orders = {order};
			criteria.setOrder(orders);			
			IResponseCRUD response = accountDao.search(Account.class, criteria);
			
			Collection<Account> accounts = response.getQueryResult();
			
			if(accounts!=null && accounts.size()>0){
				for (Account account : accounts) {
					accountMap.put(account.getAccountCode(), "("+account.getAccountCode()+")"+account.getAccountDesc());
				}
			}
			
			return accountMap;
		} catch (DataAccessObjectException e) {
			throw new AccountException(e.getMessageCode(), e);
		}
	}
	
	/**
	 * Get a list of accounts for logged in user for current budgeting phase.
	 * @return
	 * @throws AccountException
	 * @throws  
	 */
	@SuppressWarnings("unchecked")
	public List<Account> getAccountsForBudgetingByUser() throws AccountException {
		List<Account> accounts = new ArrayList<Account>();
		try {						
			BudgetConfig budgetConfig = budgetConfigManager.getBudgetConfigForNow();
			if(budgetConfig!=null){
				Integer budgetingFY = budgetConfig.getBudgetConfigFY();
				List<String> accoutCodeList = getAccountCodesByUser();
				CriteriaIF criteria = new Criteria();
				List<CriterionIF> criterions = new ArrayList<CriterionIF>();
				criterions.add(new Criterion(Account.ACT_IND, RestrictionType.EQUAL, ActiveStatusEnum.YES.toString()));
				criterions.add(new Criterion(Account.ACCOUNT_CODE, RestrictionType.IN, accoutCodeList.toArray(new String[accoutCodeList.size()])));
				criterions.add(new Criterion(Account.FINANCIAL_YEAR, RestrictionType.EQUAL, budgetingFY));
				
				criteria.setCriterion(criterions.toArray(new CriterionIF[criterions.size()]));
				criteria.setFetchAll(true);
				IResponseCRUD<Account> response = accountDao.search(Account.class, criteria);
				accounts = new ArrayList<Account>(response.getQueryResult());
			}
				
		} catch (DataAccessObjectException e) {
			e.printStackTrace();
			throw new AccountException(e.getMessageCode(), e);
		} catch (BudgetConfigException e) {
			e.printStackTrace();
			throw new AccountException(e.getMessageCode(), e);
		}
			
		return accounts;
	}
	
	/**
	 * Get a list of account code for logged in user
	 * @return
	 * @throws AccountException
	 */
	@SuppressWarnings("unchecked")
	public List<String> getAccountCodesByUser() throws AccountException{
		List<String> accountCodes = new ArrayList<String>();
		
		try {			
			long userid = PrincipalSecUtil.getUserId();
			if(userid!=0L){
				CriteriaIF criteria = new Criteria();
				List<CriterionIF> criterions = new ArrayList<CriterionIF>();
				criterions.add(new Criterion(UserAccountMapping.USER_ID, RestrictionType.EQUAL, userid));
				criterions.add(new Criterion(UserAccountMapping.ACT_IND, RestrictionType.EQUAL, ActiveStatusEnum.YES.toString()));
				criteria.setCriterion(criterions.toArray(new CriterionIF[criterions.size()]));
				criteria.setFetchAll(true);
				IResponseCRUD<UserAccountMapping> responseCRUD = accountDao.search(UserAccountMapping.class, criteria);
				Collection<UserAccountMapping> userAccountMappings = responseCRUD.getQueryResult();
				
				for(Iterator<UserAccountMapping> iterator =userAccountMappings.iterator(); iterator.hasNext();){
					accountCodes.add(iterator.next().getAccountCode());
				}
				
				return accountCodes;
			}
		} catch (DataAccessObjectException e) {
			e.printStackTrace();
			throw new AccountException(e.getMessageCode(), e);
		}		
		return accountCodes;
	}
	
	@SuppressWarnings("unchecked")
	public List<AccountStructure> getAccountStructuresByAccount(Account account) throws AccountException{
		try {
			List<AccountStructure> accountStructures = (List<AccountStructure>) accountDao.find(AccountStructure.class, AccountStructure.ACT_IND, 
					ActiveStatusEnum.YES.toString(), AccountStructure.ACCT_ID, account.getId());
	        return accountStructures;
		} catch (DataAccessObjectException e) {
			e.printStackTrace();
			throw new AccountException(e.getMessageCode(), e);
		}
	}
	
}


















