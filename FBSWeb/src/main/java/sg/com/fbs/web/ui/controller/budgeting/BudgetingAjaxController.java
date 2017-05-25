package sg.com.fbs.web.ui.controller.budgeting;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import sg.com.fbs.model.account.AccountStructure;
import sg.com.fbs.model.budgeting.LineItem;
import sg.com.fbs.services.account.mgr.AccountManagerBD;
import sg.com.fbs.services.budgeting.exception.BudgetingException;
import sg.com.fbs.services.budgeting.mgr.BudgetingManagerBD;
import sg.com.fbs.techinfra.web.xss.JSONResponse;

/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu Kun $
 * @Created Apr 15, 2017 $
 * 
 */
@Controller
@RequestMapping("/budgetingAjax")
public class BudgetingAjaxController {

	protected Logger logger = Logger.getLogger(BudgetingAjaxController.class);
	
	private BudgetingManagerBD budgetingManagerBD = new BudgetingManagerBD();
	private AccountManagerBD accountManagerBD = new AccountManagerBD();
	
	public static final String COLUMN_TYPE_NUMERIC = "N";
	public static final String COLUMN_TYPE_DATE = "D";
	public static final String COLUMN_TYPE_TEXT = "T";
	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	
	@RequestMapping(value="/saveLineItem", method=RequestMethod.POST)
	public @ResponseBody String saveLineItem(@RequestParam(value = "json", required = true) String json) {
		JSONResponse jsonResponse = new JSONResponse();
		String errorMessage = "";
		Gson gson = new Gson();
		Type lineitemType = new TypeToken<LineItem>() {}.getType();
		LineItem lineItem = gson.fromJson(json, lineitemType);
		try {
			List<AccountStructure> accountStructures = accountManagerBD.getAccountStructuresByAccount(lineItem.getAccount());
			for (int i = 0; i < accountStructures.size(); i++) {
				String getterName = "getColumn" + (++i);
				String value = (String) lineItem.getClass().getMethod(getterName).invoke(lineItem);
				String columnType = accountStructures.get(i).getColumnType();
				String columnName = accountStructures.get(i).getColumnName();

				if (COLUMN_TYPE_NUMERIC.equalsIgnoreCase(columnType)) {
					if(!StringUtils.isNumeric(value)){					
						errorMessage = errorMessage.concat(columnName+" Should Be Numeric Value.<br>");
					}
				} else if (COLUMN_TYPE_DATE.equalsIgnoreCase(columnType)) {                
                        try {
                        	dateFormat.parse(value);
						} catch (ParseException e) {
							errorMessage = errorMessage.concat(columnName+" Should Be Date Value.<br>");
						}
				}
			}
			if(errorMessage!=""){
				jsonResponse.setData(null);
				jsonResponse.setErrorCode(1);
				jsonResponse.setErrorMessage(errorMessage);
			}else{
				LineItem savedLineitem = budgetingManagerBD.saveLineItem(lineItem);
				System.out.println("savedLineitem" + savedLineitem.getId());
				jsonResponse.setData(savedLineitem.getId());
				jsonResponse.setErrorCode(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonResponse.setData(null);
			jsonResponse.setErrorCode(1);
			jsonResponse.setErrorMessage("System Internal Error");
		}
		String result = gson.toJson(jsonResponse);
		return result;
	}
	
	@RequestMapping(value="/deleteLineItem", method=RequestMethod.POST)
	public @ResponseBody String deleteLineItem(@RequestParam(value="lineitemId", required=true) Long lineItemId){
		JSONResponse jsonResponse = new JSONResponse();
		Gson gson = new Gson();
		try {
			LineItem deletedLineitem = budgetingManagerBD.deleteLineItem(lineItemId);
			jsonResponse.setData(true);
			jsonResponse.setErrorCode(0);
			String result = gson.toJson(jsonResponse);
			System.out.println(result);
			return result;
		} catch (BudgetingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			jsonResponse.setData(null);
			jsonResponse.setErrorCode(1);
			String result = gson.toJson(jsonResponse);
			return result;
		}		
	}
	
}
