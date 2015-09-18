package sg.com.fbs.services.common.codegen.mgr;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import sg.com.fbs.core.businfra.facade.CommonFacade;
import sg.com.fbs.core.techinfra.exception.ApplicationCoreException;
import sg.com.fbs.model.system.persistence.response.IResponseCRUD;
import sg.com.fbs.model.system.persistence.response.ResponseCRUD;
import sg.com.fbs.services.common.codegen.dao.CodeGeneratorDAO;

/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu $
 * @Created 12:29:18 pm 18 Sep, 2015 $
 * 
 */
public class CodeGeneratorManager extends CommonFacade{

	private static final int MAX_DIGIT = 3;
	
	private static final int MAX_LENGTH = 60; 
	
	private static final String APPENDER = "__";
	
	private Map<String, Set<String>> codeMap;
	
	private static ThreadLocal<HashMap<String, Set<String>>> localCodeMap = new ThreadLocal<HashMap<String,Set<String>>>();
	
	public CodeGeneratorManager(){
		codeMap = new HashMap<String, Set<String>>();
	}
	
	private String returnCode(String tbName, String code){
		if(!codeMap.containsKey(tbName)){
			Set<String> newSet = new HashSet<String>();
			codeMap.put(tbName, newSet);
		}
		
		Set<String> currentSet = codeMap.get(tbName);
		if(!currentSet.contains(code)){
			currentSet.add(code);
		}
		return code;
	}
	
	private boolean checkExistCodeMap(String tbName, String code){
		return codeMap.containsKey(tbName) && codeMap.get(tbName).contains(code);
	}
	
	private Map<String, Set<String>> getCurrentCodeMap(){
		if(localCodeMap.get()==null){
			localCodeMap.set(new HashMap<String, Set<String>>());
		}
		
		return localCodeMap.get();
	}
	
	private boolean isCurrentCodeExist(String tbName, String code){
		boolean exist = false;
		
		if(getCurrentCodeMap().containsKey(tbName)){
			exist = getCurrentCodeMap().get(tbName).contains(code);
		}	
		return exist;
	}
	
	private void addCurrentCode(String tbName, String code){
		if(!getCurrentCodeMap().containsKey(tbName)){
			getCurrentCodeMap().put(tbName, new HashSet<String>());
		}
		
		getCurrentCodeMap().get(tbName).add(code);
	}
	
	private String getCurrentGeneratedCode(String tbName, String name) throws ApplicationCoreException{
		String code = getGeneratedCode(tbName, new HashMap<String, String>(), name, null);
		int runningSequence = 2;
		while(isCurrentCodeExist(tbName, code)){
			code = getRunningCode(getCodeFromName(name, null), runningSequence++);
		}
		
		addCurrentCode(tbName, code);
		return code;	
	}
	
	public String getGeneratedCode(String tbName, Map<String, String> additionlWhere, String name, String allowedCharacters) throws ApplicationCoreException{
		String baseCode = getCodeFromName(name, allowedCharacters);
		IResponseCRUD crudEqual = findRecordsByCode(tbName, additionlWhere, baseCode);
		Collection sameRecords = crudEqual.getQueryResult();
		
		if(sameRecords.isEmpty() &&!checkExistCodeMap(tbName, baseCode)){
			return returnCode(tbName, baseCode);
		}else{
			IResponseCRUD crudLike = findRecordsLikeCode(tbName, additionlWhere, baseCode);
		    Collection likeRecords = crudLike.getQueryResult();
		    
		    String runningCode1 = getRunningCode(baseCode, 1);
		    if(likeRecords.isEmpty() && !checkExistCodeMap(tbName, runningCode1)){
		    	return returnCode(tbName, runningCode1);
		    }else{
		    	int running = 2;
		    	String retCode = getRunningCode(baseCode, running++);
		    	while(likeRecords.contains(retCode) || checkExistCodeMap(tbName, retCode)){
		    		retCode = getRunningCode(baseCode, running++);
		    	}
		    	
		    	return returnCode(tbName, retCode);
		    }
		    
		}
			
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private IResponseCRUD findRecordsByCode(String tbName, Map<String, String> additionlWhere, String code) throws ApplicationCoreException{
		CodeGeneratorDAO codeGeneratorDAO = new CodeGeneratorDAO();
		Collection records = codeGeneratorDAO.findRecordsByCode(tbName, additionlWhere, code);
		IResponseCRUD response = new ResponseCRUD();
		response.setQueryResult(records);
		return response;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private IResponseCRUD findRecordsLikeCode(String tbName, Map<String, String> additionlWhere, String code) throws ApplicationCoreException{
		CodeGeneratorDAO codeGeneratorDAO = new CodeGeneratorDAO();
		Collection records = codeGeneratorDAO.findRecordsLikeCode(tbName, additionlWhere, code);
		IResponseCRUD response = new ResponseCRUD();
		response.setQueryResult(records);
		return response;
	}
	
	public String getCodeFromName(String name, String allowedCharacters){
		if(name==null){
			name = "";
		}
		
		char[] chars = name.toCharArray();
		String code = "";
		
		for (int i = 0; i < chars.length; i++) {
			boolean baseCondition = (chars[i]>='a' && chars[i]<='z') || (chars[i]>='A' && chars[i]<='Z') || (chars[i]=='_');
			
			if(allowedCharacters!=null && allowedCharacters.length()>0){
				String[] allowedChars = allowedCharacters.split("\\|");
				boolean condition = false;
				for (String s : allowedChars) {
					condition = condition || chars[i]==s.charAt(0);
				}
				
				if(baseCondition || condition){
					code += chars[i];
				}
			}else {
				if(baseCondition){
					code += chars[i];
				}
			}
			
			if(code.length()>MAX_LENGTH){
				break;
			}
		}
		
		if(code.equals("")){
			return "UNKNOWNCODE";
		}else {
			return code.toUpperCase();
		}		
	}
	
	private String getRunningCode(String baseCode, int num) {
		if (num >= Math.pow(10, MAX_DIGIT)) {
			return baseCode + APPENDER + num;
		}else{
			String numStr = "" + num;
			while(numStr.length()<MAX_DIGIT){ //001 002
				numStr = "0"+numStr;
			}		
			return baseCode + APPENDER + numStr;
		}		
	}
	
	
}
























