package sg.com.fbs.validator.validation.validators;

import sg.com.fbs.model.system.web.ValueLabelPair;
import sg.com.fbs.validator.validation.FieldValidator;
import sg.com.fbs.validator.validation.ValidatorMessage;
import sg.com.fbs.validator.validation.ValidatorMessageHolder;

/**
 * @Author Frank Xu $
 * @Created 3:34:21 pm 9 Jul, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public class RequiredValidator extends AbstractValidator {

	private static final long serialVersionUID = 5539616141850007519L;

	@Override
	public void init() {
		this.setDetailMessage("{validator.required.detail}");
		this.setSummaryMessage("{validator.required.summary}");
		this.setLabelFields("{validator.required.labelfields}");
	}
	
	@Override
	public ValidatorMessageHolder validate(Object fieldValue, String fieldLabel) {

		ValidatorMessage message = new ValidatorMessage();
		
		if(fieldValue instanceof String){
			
			String string = (String)fieldLabel;
			boolean valid = string!=null && !string.trim().equals("");
			if(!valid){ //if fieldLabel is null or ""
				populateMessage(message, fieldLabel);
			}
		}else if (fieldValue instanceof ValueLabelPair) {
			
			ValueLabelPair dropdown = (ValueLabelPair) fieldValue;
			if(!dropdown.isValidRequired()){
				populateMessage(message, fieldLabel);
			}
		}else {
			if(fieldValue==null){
				populateMessage(message, fieldLabel);
			}
		}

		return message;
	}

	
}







































