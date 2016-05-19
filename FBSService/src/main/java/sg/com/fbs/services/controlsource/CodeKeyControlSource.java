package sg.com.fbs.services.controlsource;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import sg.com.fbs.core.techinfra.exception.ApplicationCoreException;
import sg.com.fbs.model.domain.enumeration.ActiveStatusEnum;
import sg.com.fbs.model.domain.mastercode.MasterCodeType;
import sg.com.fbs.model.system.persistence.query.Criteria;
import sg.com.fbs.model.system.persistence.query.CriteriaIF;
import sg.com.fbs.model.system.persistence.query.Criterion;
import sg.com.fbs.model.system.persistence.query.Order;
import sg.com.fbs.model.system.persistence.response.ResponseCRUD;
import sg.com.fbs.services.mastercode.mgr.MasterCodeMgrBD;

/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu Kun $
 * @Created May 19, 2016 10:44:14 AM $
 * 
 */
public class CodeKeyControlSource extends BaseControlSource{

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Map<String, String> getControlSourceValues(Map params) throws ApplicationCoreException {
		Map<String, String> results = new LinkedHashMap<String, String>();
		
		MasterCodeMgrBD masterCodeMgrBD = new MasterCodeMgrBD();		
        CriteriaIF searchCriteria = new Criteria();		
		searchCriteria.appendCriterion(new Criterion(MasterCodeType.ACT_IND, ActiveStatusEnum.YES.toString()));		
		Order order = new Order(MasterCodeType.CODE, true);		
		Order[] orders = {order};
		searchCriteria.setOrder(orders);
		searchCriteria.setFetchAll(true);
		
		ResponseCRUD response = masterCodeMgrBD.searchCategoryTypes(searchCriteria);
		
		List<MasterCodeType> codeKeys = (List<MasterCodeType>) response.getQueryResult();
		
		if(codeKeys!=null && codeKeys.size()>0){
			for (MasterCodeType masterCodeType : codeKeys) {
				results.put(masterCodeType.getCode(), masterCodeType.getName());
			}
		}
		
		return results;
	}

}


















