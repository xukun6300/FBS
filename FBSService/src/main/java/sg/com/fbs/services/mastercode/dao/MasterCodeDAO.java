package sg.com.fbs.services.mastercode.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sg.com.fbs.core.techinfra.persistence.exception.DataAccessObjectException;
import sg.com.fbs.model.domain.mastercode.MasterCodeType;
import sg.com.fbs.techinfra.persistence.dao.BaseDao;

/**
 * @Author Frank Xu $
 * @Created 11:48:54 am 4 Aug, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public class MasterCodeDAO extends BaseDao{

	
	public MasterCodeType getMasterCodeType(String codeType) throws DataAccessObjectException{
		Map<String, Object> params = new HashMap<String, Object>();	
		params.put("codeType", codeType);
		List<?> result = super.executeNamedQuery("findMasterCodeType", params, true);
		
		if(result.size()>0){
			return (MasterCodeType)result.get(0);
		}
		return null;
	}
}












































