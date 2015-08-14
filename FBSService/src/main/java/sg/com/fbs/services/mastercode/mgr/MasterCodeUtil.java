package sg.com.fbs.services.mastercode.mgr;

import java.util.ArrayList;
import java.util.List;

import sg.com.fbs.core.techinfra.persistence.exception.DataAccessObjectException;
import sg.com.fbs.model.domain.mastercode.MasterCode;
import sg.com.fbs.model.domain.mastercode.MasterCodeType;
import sg.com.fbs.services.mastercode.dao.MasterCodeDAO;
import sg.com.fbs.services.mastercode.exception.MasterCodeException;

/**
 * @Author Frank Xu $
 * @Created 12:04:49 pm 4 Aug, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public class MasterCodeUtil {

	public static MasterCodeType getMasterCodeType(String code) throws MasterCodeException{
		try {
			return new MasterCodeDAO().getMasterCodeType(code);
		} catch (DataAccessObjectException e) {
			throw new MasterCodeException("Failed to get MasterCodeType from DB", e);
		}
	}
	
	public static List<MasterCode> getMasterCodes(String code) throws MasterCodeException{
		MasterCodeType codeType = getMasterCodeType(code);
		if(codeType==null){
			return null;
		}
		return new ArrayList<MasterCode>(codeType.getMasterCodes());
	}
	
	public static List<MasterCode> getMasterCodesWithEffectiveDate(String code) throws MasterCodeException{
		List<MasterCode> ret = new ArrayList<MasterCode>();
		List<MasterCode> masterCodes = getMasterCodes(code);
		
		for (MasterCode masterCode : masterCodes) {
			if(masterCode.getEffectiveDate()==null||masterCode.getExpiryDate()==null){
				ret.add(masterCode);
			}else if (masterCode.getEffectiveDate()==null && masterCode.getExpiryDate()!=null && masterCode.getExpiryDate().isAfterNow()) {
				ret.add(masterCode);
			}else if (masterCode.getEffectiveDate()!=null && masterCode.getEffectiveDate().isBeforeNow() && masterCode.getExpiryDate()==null) {
				ret.add(masterCode);
			}else if (masterCode.getEffectiveDate().isBeforeNow() && masterCode.getExpiryDate().isAfterNow()) {
				ret.add(masterCode);
			}
		}
		return ret;
	}
	
}
