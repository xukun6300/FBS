package sg.com.fbs.web.ui.controller.budgeting;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu Kun $
 * @Created Apr 15, 2017 $
 * 
 */
public class BudgetingAjaxController {

	protected Logger logger = Logger.getLogger(BudgetingAjaxController.class);
	
	@RequestMapping(value="/saveLineItem", method=RequestMethod.POST)
	public @ResponseBody String saveLineItem(@RequestParam(value="json", required=true) String json){
		
		
		return "";
	}
	
}
