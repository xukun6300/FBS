package sg.com.fbs.services.mastercode.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sg.com.fbs.core.techinfra.persistence.exception.DataAccessObjectException;
import sg.com.fbs.model.domain.mastercode.MasterCode;
import sg.com.fbs.model.domain.mastercode.MasterCodeType;
import sg.com.fbs.model.system.persistence.query.CriteriaIF;
import sg.com.fbs.model.system.persistence.response.IResponseCRUD;
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
	
	public MasterCode getMasterCodeValue(long codeId) throws DataAccessObjectException{
		MasterCode masterCode = (MasterCode) super.getByPrimaryKey(MasterCode.class, codeId);
		return masterCode;
	}
	
	public MasterCodeType insert(MasterCodeType masterCodeType) throws DataAccessObjectException{
		masterCodeType = (MasterCodeType) super.insert(masterCodeType);
		return masterCodeType;
	}
	
	public MasterCode insert(MasterCode masterCode) throws DataAccessObjectException{
		//add remove cache ,will implement later
		masterCode = (MasterCode) super.insert(masterCode);
		return masterCode;
	}
	
	@SuppressWarnings("rawtypes")
	public IResponseCRUD searchMasterCodeType(CriteriaIF criteria) throws DataAccessObjectException{
		return search(MasterCodeType.class, criteria, true);
	}
	
	@SuppressWarnings("rawtypes")
	public IResponseCRUD searchMasterCode(CriteriaIF criteria) throws DataAccessObjectException{
		return search(MasterCode.class, criteria, true);
	}
}












































