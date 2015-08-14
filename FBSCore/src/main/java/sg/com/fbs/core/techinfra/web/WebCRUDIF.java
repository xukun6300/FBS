package sg.com.fbs.core.techinfra.web;

import javax.servlet.http.HttpServletRequest;

import sg.com.fbs.core.techinfra.exception.CRUDException;
import sg.com.fbs.model.business.pojo.BasePojoRequest;
import sg.com.fbs.model.system.persistence.response.IResponseCRUD;

public interface WebCRUDIF {

	public IResponseCRUD<?> runQuery(BasePojoRequest pojoRequest, Object form, HttpServletRequest request) throws CRUDException;
	
	public IResponseCRUD<?> runDetails(BasePojoRequest pojoRequest, Object form, HttpServletRequest request) throws CRUDException;
	
	public IResponseCRUD<?> insert(BasePojoRequest pojoRequest, Object form, HttpServletRequest request) throws CRUDException;
	
	public IResponseCRUD<?> update(BasePojoRequest pojoRequest, Object form, HttpServletRequest request) throws CRUDException;
	
	public IResponseCRUD<?> delete(BasePojoRequest pojoRequest, Object form, HttpServletRequest request) throws CRUDException;
	
	public IResponseCRUD<?> dynamic(BasePojoRequest pojoRequest, Object form, HttpServletRequest request) throws CRUDException;
	
	
}
