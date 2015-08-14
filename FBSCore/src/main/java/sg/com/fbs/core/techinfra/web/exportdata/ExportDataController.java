package sg.com.fbs.core.techinfra.web.exportdata;

import java.lang.reflect.Type;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;


/**
 * @Author Frank Xu $
 * @Created 11:55:41 am 1 Jul, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
@Controller
@RequestMapping("/gridexportdata")
public class ExportDataController {

	private static final String PDF_TYPE = "PDF";
	
	private static final String EXCEL_TYPE = "EXCEL";
	
	protected Logger logger = Logger.getLogger(ExportDataController.class);
	
	@RequestMapping(value="/exportdata", method=RequestMethod.POST)
	public ModelAndView exportData(@RequestParam("type") String output, @RequestParam("exportBody") String body){  //@RequestParam is hidden field in submitted form
		ModelAndView mvc = new ModelAndView();
		
		logger.debug("type>>>"+output);
		logger.debug("exportBody>>>"+body);
		
		Gson gson =new Gson();
	    Type exportDataType = new TypeToken<List<List<String>>>() {}.getType();
	    List<List<String>> exportData = gson.fromJson(body, exportDataType);
	    logger.debug("exportData >>> " + exportData);
	    
	    if(exportData!=null){
	    	if(PDF_TYPE.equals(output.toUpperCase())){
	    		mvc = new ModelAndView("exportPdf", "result", exportData);  // will go through xmlViewResolver
	    	}else if (EXCEL_TYPE.equals(output.toUpperCase())) {
	    		mvc = new ModelAndView("exportExcel", "result", exportData);
			}
	    }
	    
		return mvc;
	}
}
