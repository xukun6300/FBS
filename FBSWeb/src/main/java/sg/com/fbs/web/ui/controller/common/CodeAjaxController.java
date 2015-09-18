package sg.com.fbs.web.ui.controller.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu $
 * @Created 11:27:18 am 18 Sep, 2015 $
 * 
 */

@Controller
@RequestMapping("/common/codeAjax")
public class CodeAjaxController {

	public @ResponseBody String getGeneratedCode(@RequestParam(value="name",required=true) String name,
			@RequestParam(value="tbName", required=true) String tbName){
		
		String generatedCode = "";
		
		if(name.length()>100){
			return generatedCode;
		}
		
		CodeAjaxAllowedTables tbEnum = CodeAjaxAllowedTables.valueOf(tbName);
		
		if(tbEnum == null){
			return generatedCode;
		}
		
		
		
		return generatedCode;
	}
}
