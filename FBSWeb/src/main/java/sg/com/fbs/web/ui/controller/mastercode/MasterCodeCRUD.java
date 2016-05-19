package sg.com.fbs.web.ui.controller.mastercode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import sg.com.fbs.core.techinfra.exception.CRUDException;
import sg.com.fbs.core.techinfra.web.WebCRUDIF;
import sg.com.fbs.model.business.pojo.BasePojo;
import sg.com.fbs.model.business.pojo.BasePojoRequest;
import sg.com.fbs.model.domain.enumeration.ActiveStatusEnum;
import sg.com.fbs.model.domain.mastercode.MasterCodeRequest;
import sg.com.fbs.model.domain.mastercode.MasterCodeType;
import sg.com.fbs.model.domain.mastercode.MasterCodeTypeRequest;
import sg.com.fbs.model.system.persistence.query.CriteriaIF;
import sg.com.fbs.model.system.persistence.query.Criterion;
import sg.com.fbs.model.system.persistence.response.IResponseCRUD;
import sg.com.fbs.services.mastercode.exception.MasterCodeException;
import sg.com.fbs.services.mastercode.mgr.MasterCodeMgrBD;
import sg.com.fbs.web.ui.form.mastercode.MasterCodeTypeListForm;
import sg.com.fbs.web.ui.form.mastercode.MasterCodeTypeSearchForm;


/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu $
 * @Created 3:22:33 pm 16 Sep, 2015 $
 * 
 */
public class MasterCodeCRUD implements WebCRUDIF{

	private MasterCodeMgrBD masterCodeMgrBD = new MasterCodeMgrBD();
	
	@Override
	public IResponseCRUD<?> runQuery(BasePojoRequest pojoRequest, Object form, HttpServletRequest request) throws CRUDException {
		IResponseCRUD<?> response = null;
		try {
			if (form instanceof MasterCodeTypeSearchForm) {
				MasterCodeTypeSearchForm formBean = (MasterCodeTypeSearchForm) form;
				
				if (formBean.isSearchInactiveMasterCodes()) {
                    CriteriaIF searchCriteria = addInActiveStatusCriterion(formBean.getSearchCriteria(request));
					response = masterCodeMgrBD.searchCategoryTypes(searchCriteria);
				}else{
					response = masterCodeMgrBD.searchCategoryTypes(formBean.getSearchCriteria(request));
				}
				
				//update master code size and deleteable
				if(response!=null && response.getQueryResult().size()>0){
					for (Object masterCodeType : response.getQueryResult()) {
						if(masterCodeType instanceof MasterCodeType){
							masterCodeMgrBD.updateMasterCodeTypeDetails((MasterCodeType)masterCodeType, formBean.isSearchInactiveMasterCodes());
						}
					}
				}

			}
		} catch (MasterCodeException e) {		
			e.printStackTrace();
			throw new CRUDException(e);
		}
		return response;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public IResponseCRUD<?> runDetails(BasePojoRequest pojoRequest, Object form, HttpServletRequest request) throws CRUDException {
		IResponseCRUD response = null;
		try {
			if (form instanceof MasterCodeTypeListForm) {
				MasterCodeTypeListForm formBean = (MasterCodeTypeListForm) form;
				CriteriaIF criteria = formBean.getSearchCriteria(request);
				if (formBean.isSearchInactiveMasterCodes()) {
					criteria = addInActiveStatusCriterion(criteria);
				} else {
					criteria = addActiveStatusCriterionForCodeValues(criteria);
				}

				response = masterCodeMgrBD.searchCategoryTypeDetails(criteria);

			}
		} catch (MasterCodeException e) {
			throw new CRUDException(e.getMessageCode(), e);
		}
		return response;
	}

	@Override
	public IResponseCRUD<?> insert(BasePojoRequest pojoRequest, Object form,
			HttpServletRequest request) throws CRUDException {
		
		IResponseCRUD<?> response = null;		
		try {
			if (pojoRequest instanceof MasterCodeTypeRequest) {
				
				MasterCodeTypeRequest masterCodeTypeRequest = (MasterCodeTypeRequest) pojoRequest;
				response = masterCodeMgrBD.saveCategoryType(masterCodeTypeRequest);
			}else if (pojoRequest instanceof MasterCodeRequest) {
				MasterCodeRequest masterCodeRequest = (MasterCodeRequest) pojoRequest;
				response = masterCodeMgrBD.saveCodeValue(masterCodeRequest);
			}
		} catch (MasterCodeException e) {
			throw new CRUDException(e.getMessageCode(), e);
		}
		return response;
	}

	@Override
	public IResponseCRUD<?> update(BasePojoRequest pojoRequest, Object form,
			HttpServletRequest request) throws CRUDException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IResponseCRUD<?> delete(BasePojoRequest pojoRequest, Object form,
			HttpServletRequest request) throws CRUDException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IResponseCRUD<?> dynamic(BasePojoRequest pojoRequest, Object form,
			HttpServletRequest request) throws CRUDException {
		// TODO Auto-generated method stub
		return null;
	}

	protected CriteriaIF addInActiveStatusCriterion(CriteriaIF criteria) {
		List<Criterion> listCriterion = new ArrayList<Criterion>();
		Criterion[] criterion = criteria.getCriterion();
		List<Criterion> origListCriterion = Arrays.asList(criterion);
		listCriterion.addAll(origListCriterion);
		listCriterion.add(new Criterion(BasePojo.ACT_IND, ActiveStatusEnum.NO.toString()));
		criterion = (Criterion[]) listCriterion.toArray(new Criterion[listCriterion.size()]);
		criteria.setCriterion(criterion);
		return criteria;
	}

	protected CriteriaIF addActiveStatusCriterionForCodeValues(CriteriaIF criteria) {
		List<Criterion> listCriterion = new ArrayList<Criterion>();
		Criterion[] criterion = criteria.getCriterion();
		List<Criterion> origListCriterion = Arrays.asList(criterion);
		listCriterion.addAll(origListCriterion);
		listCriterion.add(new Criterion(BasePojo.ACT_IND, ActiveStatusEnum.YES.toString()));
		criterion = (Criterion[]) listCriterion.toArray(new Criterion[listCriterion.size()]);
		criteria.setCriterion(criterion);
		return criteria;
	}
}
