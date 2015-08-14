package sg.com.fbs.services.mastercode.mgr;

import java.util.List;
import java.util.Map;

import sg.com.fbs.core.businfra.businessdelegate.BusinessDelegate;
import sg.com.fbs.core.techinfra.exception.ApplicationCoreException;
import sg.com.fbs.model.domain.mastercode.MasterCode;
import sg.com.fbs.services.mastercode.exception.MasterCodeException;


/**
 * @Author Frank Xu $
 * @Created 12:07:55 pm 4 Aug, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public class MasterCodeMgrBD extends BusinessDelegate{

	public List<MasterCode> getCodesValuesFilterWithEffectiveDate(String codeKey) throws MasterCodeException{
		Object response;
		try {
			response = new MasterCodeManager().run(codeKey);
		} catch (ApplicationCoreException e) {
			throw new MasterCodeException(e.getMessageCode(), e.getCause());
		}
		return (List<MasterCode>)response;
	}
	
	public List<MasterCode> getCodesValues(String codekey) throws MasterCodeException {
		Object response;
		try {
			response = new MasterCodeManager().run(codekey);
		} catch (ApplicationCoreException e) {
			throw new MasterCodeException(e.getMessageCode(), e.getCause());
		}
		return (List<MasterCode>)response;
	}
	
	
	public Map<String, String> getSalutation() throws MasterCodeException{
		Object obj;
		try {
			obj = new MasterCodeManager().run();
		} catch (ApplicationCoreException e) {
			throw new MasterCodeException(e.getMessageCode(), e.getCause());
		}
		return (Map<String, String>)obj;
	}
	
	public Map<String, String> getGenderType() throws MasterCodeException{
		Object obj;
		try {
			obj = new MasterCodeManager().run();
		} catch (ApplicationCoreException e) {
			throw new MasterCodeException(e.getMessageCode(), e.getCause());
		}
		return (Map<String, String>)obj;
	}
	
	public Map<String, String> getPrimaryContactMode() throws MasterCodeException{
		Object obj;
		try {
			obj = new MasterCodeManager().run();
		} catch (ApplicationCoreException e) {
			throw new MasterCodeException(e.getMessageCode(), e.getCause());
		}
		return (Map<String, String>)obj;
	}
	
	
}




























































