package sg.com.fbs.web.ui.controller.budgeting;

import java.lang.reflect.Type;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import sg.com.fbs.model.budgeting.LineItem;
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
	
	@RequestMapping(value="/saveLineItem", method=RequestMethod.POST)
	public @ResponseBody String saveLineItem(@RequestParam(value="json", required=true) String json){
		Gson gson = new Gson();
		Type lineitemType = new TypeToken<LineItem>(){}.getType();
		LineItem lineItem = gson.fromJson(json, lineitemType);
		JSONResponse jsonResponse = new JSONResponse();
		try {
			budgetingManagerBD.saveLineItem(lineItem);
			jsonResponse.setData(true);
			jsonResponse.setErrorCode(0);
			String result = gson.toJson(jsonResponse);
			System.out.println(result);
			return result;
		} catch (BudgetingException e) {
			e.printStackTrace();
			jsonResponse.setData(false);
			jsonResponse.setErrorCode(1);
			String result = gson.toJson(jsonResponse);
			return result;
		}
		
	}
	
}
