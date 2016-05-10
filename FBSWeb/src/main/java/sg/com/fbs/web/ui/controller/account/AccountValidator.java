package sg.com.fbs.web.ui.controller.account;

import java.lang.reflect.Type;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import sg.com.fbs.model.account.AccountStructure;
import sg.com.fbs.web.ui.form.account.AccountForm;

/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu Kun $
 * @Created May 9, 2016 $
 * 
 */
public class AccountValidator implements Validator{

	@Override
	public boolean supports(Class<?> arg0) {
		return true;
	}

	@Override
	public void validate(Object target, Errors errors) {
		if(target instanceof AccountForm){
			AccountForm accountForm = (AccountForm) target;
			
			Gson gson = new Gson();
			Type acctStructureListType = new TypeToken<List<AccountStructure>>(){}.getType();			
			List<AccountStructure> accountStructures = gson.fromJson(accountForm.getAcctStructureJson(), acctStructureListType);
			
			if(accountStructures!=null && accountStructures.size()>0){
				for (AccountStructure accountStructure : accountStructures) {
					if(StringUtils.isEmpty(accountStructure.getColumnName())){
						errors.rejectValue("acctStructureJson", "fbs.common.errors.account.mandatory.column.name");
					}
					if(StringUtils.isEmpty(accountStructure.getColumnSize())){
						errors.rejectValue("acctStructureJson", "fbs.common.errors.account.mandatory.column.size");
					}
					
				}
			}
		}
	}

}









