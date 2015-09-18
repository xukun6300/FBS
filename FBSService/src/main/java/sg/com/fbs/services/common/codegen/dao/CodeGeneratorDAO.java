package sg.com.fbs.services.common.codegen.dao;

import java.util.Collection;
import java.util.Map;

import sg.com.fbs.core.techinfra.persistence.exception.DataAccessObjectException;
import sg.com.fbs.techinfra.persistence.dao.BaseDao;

/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu $
 * @Created 12:27:58 pm 18 Sep, 2015 $
 * 
 */
public class CodeGeneratorDAO extends BaseDao{

	public Collection findRecordsByCode(String tbName, String code) throws DataAccessObjectException{
		String query = "SELECT CODE FROM "+ tbName + " WHERE CODE='"+ code +"'";
		return super.findByProperties(query);
	}
	
	public Collection findRecordsByCode(String tbName, Map<String, String> additionlWhere, String code) throws DataAccessObjectException{
		if(additionlWhere!null && additionlWhere.size()==0){
			return findRecordsByCode(tbName, code);
		}
		
		StringBuffer query = new StringBuffer();
		query.append("SELECT CODE FROM "+tbName);
		query.append(" ");
		query.append("WHERE CODE = '"+code+"'");
		query.append("");
		query.append("AND");
		query.append(" ");
		
		for (String colName : additionlWhere.keySet()) {
			String colVal = additionlWhere.get(colName);
			query.append(colName);
			query.append("=");
			query.append("'"+colVal+"'");
			query.append(" ");
		}
		
		query.append("ORDER BY CODE DESC");
		return super.findByProperties(query.toString());
	}
	
	public Collection findRecordsLikeCode(String tbName, String code) throws DataAccessObjectException{
		String query = "SELECT CODE FROM " + tbName + " WHERE CODE LIKE '" +code +"+_%' ESCAPE '+' ORDER BY CODE DESC";
		return super.findByProperties(query);
	}
    
	public Collection findRecordsLikeCode(String tbName, Map<String, String> additionlWhere, String code) throws DataAccessObjectException{
		if(additionlWhere !=null && additionlWhere.size()==0){
			return findRecordsLikeCode(tbName, code);
		}
		
		StringBuffer query = new StringBuffer();
		query.append("SELECT CODE FROM "+tbName);
		query.append(" ");
		query.append("WHERE CODE LIKE '"+code+"+_%' ESCAPE '+'");
		query.append(" ");
		query.append("AND");
		query.append(" ");
		
		// and?
		for (String colName : additionlWhere.keySet()) {
			String colVal = additionlWhere.get(colName);
			query.append(colName);
			query.append("=");
			query.append("'"+colVal+"'");
			query.append(" ");
		}
		
		query.append("ORDER BY CODE DESC");
		return super.findByProperties(query.toString());
	}
}


















