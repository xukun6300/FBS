package sg.com.fbs.services.controlsource;

import java.util.Map;

import sg.com.fbs.core.techinfra.exception.ApplicationCoreException;
import sg.com.fbs.services.mastercode.mgr.MasterCodeMgrBD;

/**
 * @Author Frank Xu $
 * @Created 12:09:28 pm 20 Aug, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public class PrimaryContactModeControlSource extends BaseControlSource{

	@Override
	public Map getControlSourceValues(Map params) throws ApplicationCoreException {
		MasterCodeMgrBD masterCodeMgrBD = new MasterCodeMgrBD();
		return masterCodeMgrBD.getPrimaryContactMode();
	}

}
