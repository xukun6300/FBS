package sg.com.fbs.services.controlsource;

import java.util.Map;

import sg.com.fbs.core.techinfra.exception.ApplicationCoreException;
import sg.com.fbs.services.mastercode.mgr.MasterCodeMgrBD;

/**
 * @Author Frank Xu $
 * @Created 3:29:53 pm 4 Aug, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public class SalutationControlSource extends BaseControlSource{

	@Override
	public Map<String, String> getControlSourceValues(Map params) throws ApplicationCoreException {
		return new MasterCodeMgrBD().getSalutation();
	}

}
