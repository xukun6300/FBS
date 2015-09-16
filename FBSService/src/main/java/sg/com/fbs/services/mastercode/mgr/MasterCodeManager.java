package sg.com.fbs.services.mastercode.mgr;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sg.com.fbs.core.businfra.facade.CommonFacade;
import sg.com.fbs.core.techinfra.exception.ApplicationCoreException;
import sg.com.fbs.core.techinfra.persistence.dao.DaoErrorCodesEnum;
import sg.com.fbs.core.techinfra.persistence.exception.DataAccessObjectException;
import sg.com.fbs.core.techinfra.util.ControlSourceIF;
import sg.com.fbs.model.domain.mastercode.MasterCode;
import sg.com.fbs.model.domain.mastercode.MasterCodeType;
import sg.com.fbs.model.domain.mastercode.MasterCodeTypeEnum;
import sg.com.fbs.model.domain.mastercode.MasterCodeTypeRequest;
import sg.com.fbs.model.system.persistence.response.ResponseCRUD;
import sg.com.fbs.services.controlsource.CodeMaintenanceControlSource;
import sg.com.fbs.services.mastercode.dao.MasterCodeDAO;
import sg.com.fbs.services.mastercode.exception.MasterCodeException;

/**
 * @Author Frank Xu $
 * @Created 12:09:48 pm 4 Aug, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public class MasterCodeManager extends CommonFacade{

	public List<MasterCode> getCodesValuesFilterWithEffectiveDate(String codeKey) throws MasterCodeException{
		return MasterCodeUtil.getMasterCodesWithEffectiveDate(codeKey);
	}
	
	public List<MasterCode> getCodesValues(String codekey) throws MasterCodeException{
		return MasterCodeUtil.getMasterCodes(codekey);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<Object, String> getSalutation() throws ApplicationCoreException{
		Map params = new HashMap();
		params.put(ControlSourceIF.CODE_KEY, MasterCodeTypeEnum.SALUTATION.toString());
		params.put(ControlSourceIF.TYPE, ControlSourceIF.ID);
		CodeMaintenanceControlSource codeMaintenanceControlSource = new CodeMaintenanceControlSource();
		return (Map<Object, String>)codeMaintenanceControlSource.getControlSourceValues(params);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<Object, String> getGenderType() throws ApplicationCoreException{
		Map params = new HashMap();
		params.put(ControlSourceIF.CODE_KEY, MasterCodeTypeEnum.GENDER.toString());
		params.put(ControlSourceIF.TYPE, ControlSourceIF.ID);
		CodeMaintenanceControlSource codeMaintenanceControlSource = new CodeMaintenanceControlSource();
		return (Map<Object,String>)codeMaintenanceControlSource.getControlSourceValues(params);		
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<Object, String> getPrimaryContactMode() throws ApplicationCoreException{
		Map params = new HashMap();
		params.put(ControlSourceIF.CODE_KEY, MasterCodeTypeEnum.PRIMARY_CONTACT_MODE.toString());
		CodeMaintenanceControlSource codeMaintenanceControlSource = new CodeMaintenanceControlSource();
		return (Map<Object, String>)codeMaintenanceControlSource.getControlSourceValues(params);
	}
	
	public String getMasterCodeValue(long codeId) throws DataAccessObjectException{
		MasterCode masterCode = new MasterCodeDAO().getMasterCodeValue(codeId);
		String codeValue = null;
		if(masterCode!=null){
			codeValue = masterCode.getCodeValue();
		}		
		return codeValue;
	}
	
	@SuppressWarnings("rawtypes")
	public ResponseCRUD saveCategoryType(MasterCodeTypeRequest masterCodeTypeRequest) throws MasterCodeException{
		try {
			if(checkIfExistsByCodeKey(masterCodeTypeRequest)){
				throw new DataAccessObjectException(DaoErrorCodesEnum.DAO_RECORD_ALREADY_EXIST.toString());
			}
			
			ResponseCRUD response = new ResponseCRUD();
			MasterCodeDAO masterCodeDAO = new MasterCodeDAO();
			
			MasterCodeType masterCodeType = new MasterCodeType();
			
			masterCodeType.setVersion(1);
			masterCodeType.setCodeKey(masterCodeTypeRequest.getCodeKey());
			masterCodeType.setRemarks(masterCodeTypeRequest.getRemarks());
			masterCodeType.setEffectiveDate(masterCodeTypeRequest.getEffectiveDate());
			masterCodeType.setSortOrder(masterCodeTypeRequest.getSortOrder());
			
			masterCodeType = masterCodeDAO.insert(masterCodeType);
			
			if(masterCodeType.getId()>0){
				masterCodeTypeRequest.setId(masterCodeType.getId());
				response.setCrudResult(masterCodeTypeRequest);
			}
			
			return response;
		} catch (DataAccessObjectException e) {
			throw new MasterCodeException(e.getMessageCode(), e.getCause());
		}
	}
	
	
	private boolean checkIfExistsByCodeKey(MasterCodeTypeRequest masterCodeTypeRequest) throws DataAccessObjectException{
		boolean exists = false;
	    MasterCodeDAO masterCodeDAO = new MasterCodeDAO();
	    MasterCodeType masterCodeType = (MasterCodeType) masterCodeDAO.findObject(MasterCodeType.class, MasterCodeType.CATEGORY_TYPE, masterCodeTypeRequest.getCodeKey());
		
	    if(masterCodeType!=null && masterCodeType.getId()!=masterCodeTypeRequest.getId()){
	    	exists = true;
	    }
	    
		return exists;
	}
	
	
	
}


























