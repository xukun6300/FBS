package sg.com.fbs.web.ui.controller.mastercode;

import javax.servlet.http.HttpServletRequest;

import sg.com.fbs.core.techinfra.exception.CRUDException;
import sg.com.fbs.core.techinfra.web.WebCRUDIF;
import sg.com.fbs.model.business.pojo.BasePojoRequest;
import sg.com.fbs.model.domain.mastercode.MasterCodeTypeRequest;
import sg.com.fbs.model.system.persistence.response.IResponseCRUD;
import sg.com.fbs.services.mastercode.mgr.MasterCodeMgrBD;

/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu $
 * @Created 3:22:33 pm 16 Sep, 2015 $
 * 
 */
public class MasterCodeCRUD implements WebCRUDIF{

	@Override
	public IResponseCRUD<?> runQuery(BasePojoRequest pojoRequest, Object form,
			HttpServletRequest request) throws CRUDException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IResponseCRUD<?> runDetails(BasePojoRequest pojoRequest,
			Object form, HttpServletRequest request) throws CRUDException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IResponseCRUD<?> insert(BasePojoRequest pojoRequest, Object form,
			HttpServletRequest request) throws CRUDException {
		IResponseCRUD<?> response = null;
		MasterCodeMgrBD masterCodeMgrBD = new MasterCodeMgrBD();
		
		if(pojoRequest instanceof MasterCodeTypeRequest){
			
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

}
