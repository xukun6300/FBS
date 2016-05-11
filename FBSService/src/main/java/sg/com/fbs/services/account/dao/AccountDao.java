package sg.com.fbs.services.account.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import sg.com.fbs.core.techinfra.persistence.exception.DataAccessObjectException;
import sg.com.fbs.model.account.Account;
import sg.com.fbs.model.account.AccountStructure;
import sg.com.fbs.model.system.persistence.query.Criteria;
import sg.com.fbs.model.system.persistence.query.CriteriaIF;
import sg.com.fbs.model.system.persistence.query.Criterion;
import sg.com.fbs.model.system.persistence.query.Order;
import sg.com.fbs.model.system.persistence.query.Projection;
import sg.com.fbs.model.system.persistence.query.ProjectionIF;
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
	
	public List<String> getDistinctFinancialYearList() throws DataAccessObjectException{
		
		List<String> fyList = new ArrayList<String>();
		
		CriteriaIF criteria = new Criteria();
	    Projection[] projection = { //will only get FY column
	    		new Projection(ProjectionIF.DISTINCT, Account.FINANCIAL_YEAR)
	    };
	    
	    Criterion[] criterion = {
	    		new Criterion(Account.ACT_IND, "Y")
	    };
	    
	    Order[] orders = {
	    		new Order(Account.FINANCIAL_YEAR, false)
	    };
	    
	    criteria.setCriterion(criterion);
	    criteria.setProjection(projection);
	    criteria.setOrder(orders);
	    criteria.setFetchAll(true);
	    
	    IResponseCRUD response = search(Account.class, criteria);
	    
	    Collection<Integer> fys = response.getQueryResult();
	    
	    for (Integer fy : fys) {
	    	fyList.add(String.valueOf(fy));
		}
	    	    
	    return fyList;
	}
	
}









