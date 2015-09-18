package sg.com.fbs.services.common.codegen.mgr;

import sg.com.fbs.core.businfra.businessdelegate.BusinessDelegate;
import sg.com.fbs.core.techinfra.exception.ApplicationCoreException;

/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu $
 * @Created 5:49:31 pm 18 Sep, 2015 $
 * 
 */
public class CodeGeneratorManagerBD extends BusinessDelegate{

	public String getGeneratedCode(String tbName, String name) throws ApplicationCoreException{
		return (String) new CodeGeneratorManager().run(tbName, name);
	}
}
