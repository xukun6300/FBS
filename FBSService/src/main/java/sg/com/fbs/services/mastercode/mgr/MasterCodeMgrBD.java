package sg.com.fbs.services.mastercode.mgr;

import java.util.List;
import java.util.Map;

import sg.com.fbs.core.businfra.businessdelegate.BusinessDelegate;
import sg.com.fbs.core.techinfra.exception.ApplicationCoreException;
import sg.com.fbs.model.domain.mastercode.MasterCode;
import sg.com.fbs.model.domain.mastercode.MasterCodeRequest;
import sg.com.fbs.model.domain.mastercode.MasterCodeType;
import sg.com.fbs.model.domain.mastercode.MasterCodeTypeRequest;
import sg.com.fbs.model.system.persistence.query.Criteria;
import sg.com.fbs.model.system.persistence.query.CriteriaIF;
import sg.com.fbs.model.system.persistence.response.IResponseCRUD;
import sg.com.fbs.model.system.persistence.response.ResponseCRUD;
import sg.com.fbs.services.mastercode.exception.MasterCodeException;


/**
 * @Author Frank Xu $
 * @Created 12:07:55 pm 4 Aug, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public class MasterCodeMgrBD extends BusinessDelegate{

	@SuppressWarnings("unchecked")
	public List<MasterCode> getCodesValuesFilterWithEffectiveDate(String codeKey) throws MasterCodeException{
		Object response;
		try {
			response = new MasterCodeManager().run(codeKey);
		} catch (ApplicationCoreException e) {
			throw new MasterCodeException(e.getMessageCode(), e.getCause());
		}
		return (List<MasterCode>)response;
	}
	
	@SuppressWarnings("unchecked")
	public List<MasterCode> getCodesValues(String codekey) throws MasterCodeException {
		Object response;
		try {
			response = new MasterCodeManager().run(codekey);
		} catch (ApplicationCoreException e) {
			throw new MasterCodeException(e.getMessageCode(), e.getCause());
		}
		return (List<MasterCode>)response;
	}
	
	
	@SuppressWarnings("unchecked")
	public Map<String, String> getSalutation() throws MasterCodeException{
		Object obj;
		try {
			obj = new MasterCodeManager().run();
		} catch (ApplicationCoreException e) {
			throw new MasterCodeException(e.getMessageCode(), e.getCause());
		}
		return (Map<String, String>)obj;
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, String> getGenderType() throws MasterCodeException{
		Object obj;
		try {
			obj = new MasterCodeManager().run();
		} catch (ApplicationCoreException e) {
			throw new MasterCodeException(e.getMessageCode(), e.getCause());
		}
		return (Map<String, String>)obj;
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, String> getPrimaryContactMode() throws MasterCodeException{
		Object obj;
		try {
			obj = new MasterCodeManager().run();
		} catch (ApplicationCoreException e) {
			throw new MasterCodeException(e.getMessageCode(), e.getCause());
		}
		return (Map<String, String>)obj;
	}
	
	public String getMasterCodeValue(long codeId) throws MasterCodeException{
		try {
			Object obj = new MasterCodeManager().run(codeId);
			return (String) obj;
		} catch (ApplicationCoreException e) {
			throw new MasterCodeException(e.getMessageCode(), e);
		}
	}
	
	@SuppressWarnings("rawtypes")
	public ResponseCRUD saveCategoryType(MasterCodeTypeRequest masterCodeTypeRequest) throws MasterCodeException{
		try {
			Object obj = new MasterCodeManager().run(masterCodeTypeRequest);
			return (ResponseCRUD) obj;
		} catch (ApplicationCoreException e) {
			throw new MasterCodeException(e.getMessageCode(), e);
		}
	}
	
	@SuppressWarnings("rawtypes")
	public ResponseCRUD saveCodeValue(MasterCodeRequest masterCodeRequest) throws MasterCodeException{
		try {
			Object obj = new MasterCodeManager().run(masterCodeRequest);
			return (ResponseCRUD) obj;
		} catch (ApplicationCoreException e) {
			throw new MasterCodeException(e.getMessageCode(), e);
		}
	}
	
	@SuppressWarnings("rawtypes")
	public ResponseCRUD searchCategoryTypes(CriteriaIF criteria) throws MasterCodeException{
		try {
			Object obj = new MasterCodeManager().run(criteria);
			return (ResponseCRUD) obj;
		} catch (ApplicationCoreException e) {
			throw new MasterCodeException(e.getMessageCode(), e);
		}
	}
	
	public void updateMasterCodeTypeDetails(MasterCodeType masterCodeType, boolean isInactiveSearch) throws MasterCodeException{
		try {
			new MasterCodeManager().run(masterCodeType, isInactiveSearch);
		} catch (ApplicationCoreException e) {
			throw new MasterCodeException(e.getMessageCode(), e);
		}
	}
	
	@SuppressWarnings("rawtypes")
	public IResponseCRUD searchMasterCodeType(CriteriaIF criteria) throws MasterCodeException{
		try {
			Object obj = new MasterCodeManager().run(criteria);
			return (IResponseCRUD) obj;
		} catch (ApplicationCoreException e) {
			throw new MasterCodeException(e.getMessageCode(), e);
		}
	}
	
	@SuppressWarnings("rawtypes")
	public IResponseCRUD searchCategoryTypeDetails(CriteriaIF criteria) throws MasterCodeException{
		try {
			Object obj = new MasterCodeManager().run(criteria);
			return (IResponseCRUD) obj;
		} catch (ApplicationCoreException e) {
			throw new MasterCodeException(e.getMessageCode(), e);
		}
	}
	
	@SuppressWarnings("rawtypes")
	public IResponseCRUD deleteAndShowCodeKey(CriteriaIF criteria, long codeKeyId) throws MasterCodeException{
		try {
			Object obj = new MasterCodeManager().run(criteria, codeKeyId);
			return (IResponseCRUD) obj;
		} catch (ApplicationCoreException e) {
			throw new MasterCodeException(e.getMessageCode(), e);
		}
	}
	
	@SuppressWarnings("rawtypes")
	public IResponseCRUD deleteAndShowCodeValue(CriteriaIF criteria, long codeId) throws MasterCodeException{
		try {
			Object obj = new MasterCodeManager().run(criteria, codeId);
			return (IResponseCRUD) obj;
		} catch (ApplicationCoreException e) {
			throw new MasterCodeException(e.getMessageCode(), e);
		}
	}
}




























































