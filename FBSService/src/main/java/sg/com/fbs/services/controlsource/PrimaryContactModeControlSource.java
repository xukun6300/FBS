	public Map getControlSourceValues(Map params) throws ApplicationCoreException {
		MasterCodeMgrBD masterCodeMgrBD = new MasterCodeMgrBD();
		return masterCodeMgrBD.getPrimaryContactMode();
