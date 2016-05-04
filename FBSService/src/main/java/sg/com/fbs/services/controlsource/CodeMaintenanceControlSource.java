package sg.com.fbs.services.controlsource;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import sg.com.fbs.core.techinfra.exception.ApplicationCoreException;
import sg.com.fbs.model.domain.mastercode.MasterCode;
import sg.com.fbs.model.domain.mastercode.MasterCodeType;
import sg.com.fbs.services.mastercode.mgr.MasterCodeMgrBD;
import sg.com.fbs.services.mastercode.mgr.MasterCodeUtil;

/**
 * @Author Frank Xu $
 * @Created 11:41:01 am 4 Aug, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public class CodeMaintenanceControlSource extends BaseControlSource{

	public static final int ORDER_BY_DESCRIPTION = 1;
	
	@Override
	public Map getControlSourceValues(Map params) throws ApplicationCoreException {
		String codeKey = (String)params.get(CODE_KEY);
		String type = (String)params.get(TYPE);
		String filterEffectiveDate = (String)params.get(FILTEREFFECTIVEDATE);

		Map<String, String> values = new LinkedHashMap<String, String>();
		
		MasterCodeMgrBD bd = new MasterCodeMgrBD();
		
		if(codeKey==null){
			return values;
		}
		
		MasterCodeType masterCodeType = MasterCodeUtil.getMasterCodeType(codeKey);
		if(masterCodeType==null){
			return values;
		}
		
		List<MasterCode> result = null;
		
		if(filterEffectiveDate!=null && STR_Y.equals(filterEffectiveDate)){
			result = bd.getCodesValuesFilterWithEffectiveDate(codeKey);
		}else {
			result = bd.getCodesValues(codeKey);
		}
		
		if (result != null && result.size() > 0) {
			for (MasterCode masterCode : result) {
				if(masterCode.isActive()){
					if(type!=null && type.equals(ID)){
						if(!values.containsValue(masterCode.getCodeValue())){
							values.put(String.valueOf(masterCode.getId()), masterCode.getCodeValue());
						}
					}else {
						if(!values.containsValue(masterCode.getCodeValue())){
							values.put(masterCode.getCodeValue(), masterCode.getCodeValue());
						}
					}
				}
			}
		}
		
		return sort(values, masterCodeType);
	}
	
	private <K extends Comparable<V>, V extends Comparable<V>> Map<K, V> sort(Map<K, V> map, MasterCodeType masterCodeType){
		
		if(masterCodeType.getSortOrder()==ORDER_BY_DESCRIPTION){
			List<Map.Entry<K, V>> entries = new LinkedList<Map.Entry<K,V>>(map.entrySet());
			
			Collections.sort(entries, new Comparator<Map.Entry<K, V>>() {
				@Override
				public int compare(Entry<K, V> o1, Entry<K, V> o2) {
					return o1.getValue().compareTo(o2.getValue());
				}
			});
			
			Map<K, V> sortedMap = new LinkedHashMap<K, V>();
			for (Map.Entry<K, V> entry : entries) {
				sortedMap.put(entry.getKey(), entry.getValue());
			}
			
			return sortedMap;
		}
		
		return map;
	}
	


}




































