package sg.com.fbs.services.mastercode.mgr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sg.com.fbs.core.businfra.facade.CommonFacade;
import sg.com.fbs.core.techinfra.exception.ApplicationCoreException;
import sg.com.fbs.core.techinfra.persistence.dao.DaoErrorCodesEnum;
import sg.com.fbs.core.techinfra.persistence.exception.DataAccessObjectException;
import sg.com.fbs.core.techinfra.util.ControlSourceIF;
import sg.com.fbs.model.business.pojo.BasePojo;
import sg.com.fbs.model.domain.enumeration.ActiveStatusEnum;
import sg.com.fbs.model.domain.mastercode.MasterCode;
import sg.com.fbs.model.domain.mastercode.MasterCodeRequest;
import sg.com.fbs.model.domain.mastercode.MasterCodeType;
import sg.com.fbs.model.domain.mastercode.MasterCodeTypeEnum;
import sg.com.fbs.model.domain.mastercode.MasterCodeTypeRequest;
import sg.com.fbs.model.system.persistence.query.Criteria;
import sg.com.fbs.model.system.persistence.query.CriteriaIF;
import sg.com.fbs.model.system.persistence.query.Criterion;
import sg.com.fbs.model.system.persistence.query.CriterionIF;
import sg.com.fbs.model.system.persistence.response.IResponseCRUD;
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
	
	@SuppressWarnings("unchecked")
	private boolean checkIfExistsByCodeKeyAndValue(MasterCodeRequest masterCodeRequest) throws DataAccessObjectException{
		boolean exists = false;
		MasterCodeDAO masterCodeDAO = new MasterCodeDAO();
		String searchCTVal = masterCodeRequest.getCategoryType().getValue(); //categoryType for what???
		
		MasterCodeType masterCodeType = (MasterCodeType) masterCodeDAO.findObject(MasterCodeType.class, MasterCodeType.CATEGORY_TYPE, searchCTVal);	
		MasterCode existingRecord = null;	
		List<MasterCode> masterCodeList = (List<MasterCode>) masterCodeDAO.find(MasterCode.class, MasterCode.MASTERCODE_TYPE_ID,masterCodeType.getId(), MasterCode.CODE_VALUE,
						masterCodeRequest.getCodeValue(), MasterCode.ACT_IND,ActiveStatusEnum.YES.toString());
		if(masterCodeList!=null && masterCodeList.size()>0){
			existingRecord = masterCodeList.iterator().next();
		}
		
		if(existingRecord!=null && existingRecord.getId() != masterCodeRequest.getId()){
			exists = true;
		}
		
		return exists;
	}
	
	@SuppressWarnings("rawtypes")
	public ResponseCRUD saveCodeValue(MasterCodeRequest masterCodeRequest) throws MasterCodeException{
		try {
			if(checkIfExistsByCodeKeyAndValue(masterCodeRequest)){
				throw new DataAccessObjectException(DaoErrorCodesEnum.DAO_RECORD_ALREADY_EXIST.toString());
			}
			
			ResponseCRUD response = new ResponseCRUD();		
			MasterCodeDAO masterCodeDAO = new MasterCodeDAO();		
			MasterCode masterCode = new MasterCode();
			String searchCTVal = masterCodeRequest.getCategoryType().getValue();
			MasterCodeType masterCodeType = (MasterCodeType) masterCodeDAO.findObject(MasterCodeType.class, MasterCodeType.CATEGORY_TYPE, searchCTVal);
			if(masterCodeType==null){
				throw new DataAccessObjectException(DaoErrorCodesEnum.DAO_RECORD_NOT_FOUND.toString());
			}
			
			masterCodeType = updateMasterCodeTypeVersion(masterCodeType);
			
			masterCode.setMasterCodeType(masterCodeType);
			masterCode.setCodeValue(masterCodeRequest.getCodeValue());
			masterCode.setRemarks(masterCodeRequest.getRemarks());
			masterCode.setDescription(masterCodeRequest.getDescription());
			masterCode.setEffectiveDate(masterCodeRequest.getEffectiveDate());
			masterCode.setExpiryDate(masterCodeRequest.getExpiryDate());
			
			masterCode = masterCodeDAO.insert(masterCode);
			if(masterCode.getId()>0){
				masterCodeRequest.getCategoryType().setLabel(masterCodeType.getName()); 
				response.setCrudResult(masterCodeRequest);	
			}
			
			return response;			
		} catch (DataAccessObjectException e) {
			throw new MasterCodeException(e.getMessageCode(), e.getCause());
		}
	}
	
	private MasterCodeType updateMasterCodeTypeVersion(MasterCodeType masterCodeType) throws DataAccessObjectException{
		MasterCodeDAO masterCodeDAO = new MasterCodeDAO();
		masterCodeType.setVersion(masterCodeType.getVersion() + 1);
		masterCodeDAO.update(masterCodeType);
		return masterCodeType;
	}
	
	@SuppressWarnings("rawtypes")
	public IResponseCRUD searchCategoryTypes(CriteriaIF criteria) throws MasterCodeException{
		addActiveStatusCriterion(criteria);
		MasterCodeDAO masterCodeDAO = new MasterCodeDAO();
		IResponseCRUD response;
		try {
			response = masterCodeDAO.searchMasterCodeType(criteria);
		} catch (DataAccessObjectException e) {
			throw new MasterCodeException(e.getMessageCode(), e.getCause());
		}		
		return response;
	}
	
	public void updateMasterCodeTypeDetails(MasterCodeType masterCodeType, boolean isInactiveSearch) throws MasterCodeException{
		
		if(isInactiveSearch){//for inactive search, all code types cannot delete, so no worry getActiveMasterCodesId not set
			List<MasterCode> inactiveCodes = getAllInactiveMasterCode(masterCodeType);
			for (MasterCode masterCode : inactiveCodes) {
				masterCodeType.getInactiveMasterCodesId().add(masterCode.getId());
			}
		}else {			
			for (MasterCode masterCode : masterCodeType.getMasterCodes()) {
				
				masterCodeType.getActiveMasterCodesId().add(masterCode.getId());
			}
		}
		
		if((masterCodeType.getActiveMasterCodesId()==null || masterCodeType.getActiveMasterCodesId().size()==0) 
				&& masterCodeType.getActiveStatus().equals(ActiveStatusEnum.YES.toString())){
			
			masterCodeType.setDeletable(true);
		}
		
	}
	
	
	@SuppressWarnings("unchecked")
	public List<MasterCode> getAllInactiveMasterCode(MasterCodeType masterCodeType) throws MasterCodeException{
		MasterCodeDAO masterCodeDAO = new MasterCodeDAO();
		List<MasterCode> inactiveCodes = new ArrayList<MasterCode>();
		try {
			inactiveCodes = (List<MasterCode>)masterCodeDAO.find(MasterCode.class, MasterCode.MASTERCODE_TYPE_ID, masterCodeType.getId(), MasterCode.ACT_IND, ActiveStatusEnum.NO.toString());
		} catch (DataAccessObjectException e) {
			e.printStackTrace();
			throw new MasterCodeException(e.getMessageCode(),e);
		}
		return inactiveCodes;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public IResponseCRUD searchCategoryTypeDetails(CriteriaIF criteria) throws MasterCodeException{
		IResponseCRUD res = null;
		
		CriteriaIF mctCriteria = new Criteria();
		mctCriteria.setCriterion(criteria.getCriterion());
		mctCriteria.setFetchAll(false);
		mctCriteria.setRequestedPage(1);
		
		res = searchMasterCodeType(mctCriteria); //first search against MasterCodeType
		
		MasterCodeType masterCodeType = (MasterCodeType) res.getCrudResult();
		if(masterCodeType!=null){
			CriteriaIF searchCriteria = new Criteria();
			CriterionIF[] criterions = {new Criterion(MasterCode.MASTERCODE_TYPE_ID, masterCodeType.getId())};
			
			for (Criterion criterion : criteria.getCriterion()) {
				if(criterion.getPropertyName().equalsIgnoreCase(BasePojo.ACT_IND)){
					List<CriterionIF> criterionList = new ArrayList<CriterionIF>();
					criterionList.addAll(Arrays.asList(criterions));
					criterionList.add(criterion);
					criterions = criterionList.toArray(new CriterionIF[criterionList.size()]);
					break;
				}
			}
			
			searchCriteria.setCriterion(criterions);
			searchCriteria.setFetchAll(criteria.isFetchAll());
			searchCriteria.setOrder(criteria.getOrder());
			searchCriteria.setRequestedPage(criteria.getRequestedPage());
			
			IResponseCRUD detailsResult = searchMasterCodes(searchCriteria);
			res.setCrudResult(masterCodeType);
			res.setQueryResult(detailsResult.getQueryResult());
			res.setTotalPages(detailsResult.getTotalPages());
			res.setTotalRecords(detailsResult.getTotalRecords());
			res.getCriteria().setOrder(criteria.getOrder());
			res.getCriteria().setFetchAll(criteria.isFetchAll());
			res.getCriteria().setRequestedPage(criteria.getRequestedPage());
			
		}
		
		return res;
	}
	
	@SuppressWarnings("rawtypes")
	public IResponseCRUD searchMasterCode(CriteriaIF criteria) throws MasterCodeException{
		IResponseCRUD response = searchMasterCodes(criteria);
		
		if(response.getQueryResult().size()==1){
			MasterCode masterCode = (MasterCode) response.getQueryResult().iterator().next();
			response.setCrudResult(masterCode);
		}
		return response;
	}
	

	@SuppressWarnings("rawtypes")
	public IResponseCRUD searchMasterCodes(CriteriaIF criteria) throws MasterCodeException{
		addActiveStatusCriterion(criteria);
		MasterCodeDAO masterCodeDAO = new MasterCodeDAO();
		IResponseCRUD response;
		try {
			response = masterCodeDAO.searchMasterCode(criteria);
			
			if(response!=null && response.getTotalRecords()>0){
				MasterCode masterCode = (MasterCode) response.getQueryResult().iterator().next();
				MasterCodeType masterCodeType = masterCode.getMasterCodeType(); 
				response.setCrudResult(masterCodeType);
			}
	
		} catch (DataAccessObjectException e) {
			throw new MasterCodeException(e.getMessageCode(), e);
		}
		
		return response;
	}
	
	@SuppressWarnings("rawtypes")
	public IResponseCRUD searchMasterCodeType(CriteriaIF criteria) throws MasterCodeException{
		IResponseCRUD response = searchCategoryTypes(criteria);
		if(response.getQueryResult().size()==1){
			MasterCodeType masterCodeType = (MasterCodeType) response.getQueryResult().iterator().next();
			response.setCrudResult(masterCodeType);
		}
		return response;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public IResponseCRUD deleteAndShowCodeKey(CriteriaIF criteria, long codeKeyId) throws MasterCodeException{
		ResponseCRUD response = new ResponseCRUD();
		MasterCodeDAO masterCodeDAO = new MasterCodeDAO();
		MasterCodeType masterCodeType;
		try {
			masterCodeType = (MasterCodeType) masterCodeDAO.findObject(MasterCodeType.class, MasterCodeType.ID, codeKeyId);
			masterCodeType = (MasterCodeType) masterCodeDAO.softDelete(masterCodeType);
			masterCodeDAO.flush();//flush the delete operation to db then do search
			
			if(masterCodeType.getId()>0){	
				ResponseCRUD res = (ResponseCRUD) searchCategoryTypes(criteria);
				if(res!=null && res.getQueryResult()!=null){
					response.setQueryResult(res.getQueryResult());
					response.setTotalRecords(res.getTotalRecords());
					response.setTotalPages(res.getTotalPages());
					response.setCriteria(criteria);
					
					Map<String, Object> moreQueryResult = new HashMap<String, Object>();
					moreQueryResult.put("successMsg", true);
					moreQueryResult.put("deletedCodeKey", masterCodeType.getCodeKey());
					response.setMoreQueryResult(moreQueryResult);
				}			
			}
			
		} catch (DataAccessObjectException e) {
			throw new MasterCodeException(e.getMessageCode(), e);
		}
		
		return response;
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public IResponseCRUD deleteAndShowCodeValue(CriteriaIF criteria, long codeId) throws MasterCodeException{
		ResponseCRUD response = new ResponseCRUD();		
		MasterCodeDAO masterCodeDAO = new MasterCodeDAO();
		try {
			MasterCode masterCode = (MasterCode) masterCodeDAO.findObject(MasterCode.class, MasterCode.ID, codeId);
			
			if(masterCode!=null){
				masterCode = (MasterCode) masterCodeDAO.softDelete(masterCode);
				masterCodeDAO.flush();
				
				if(masterCode.getId()>0){
					ResponseCRUD res = (ResponseCRUD) searchCategoryTypeDetails(criteria);
					if(res!=null && res.getQueryResult()!=null){
						response.setCrudResult(masterCode.getMasterCodeType());//command obj
						response.setQueryResult(res.getQueryResult());
						response.setTotalRecords(res.getTotalRecords());
						response.setTotalPages(res.getTotalPages());
						response.setCriteria(criteria);
						
						Map<String, Object> moreQueryResult = new HashMap<String, Object>();
						moreQueryResult.put("successMsg", true);
						moreQueryResult.put("deletedCodeValue", masterCode.getCodeValue());
						response.setMoreQueryResult(moreQueryResult);
					}			
				}
				
			}
			
		} catch (DataAccessObjectException e) {
			throw new MasterCodeException(e.getMessageCode(), e);
		}
		
		return response;
	}
	

}


























