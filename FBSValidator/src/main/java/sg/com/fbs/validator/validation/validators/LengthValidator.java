package sg.com.fbs.validator.validation.validators;


import sg.com.fbs.validator.annotations.design.Implements;
import sg.com.fbs.validator.validation.FieldValidator;
import sg.com.fbs.validator.validation.ValidatorMessage;
import sg.com.fbs.validator.validation.ValidatorMessageHolder;

/**
 * @Author Frank Xu $
 * @Created 10:48:29 am 14 Jul, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 * 
 * LongRangeValidator works with all integer ranges. 
 */
public class LengthValidator extends AbstractValidator{

	private static final long serialVersionUID = 5127961954452667948L;

	private Long min = 0L;
	
	private Long max = Long.MAX_VALUE;
	
	public void setMin(Long min) {
		this.min = min;
	}
	
	public void setMax(Long max) {
		this.max = max;
	}
	
	@Implements(interfaceClass=FieldValidator.class)
	@Override
	public ValidatorMessageHolder validate(Object fieldValue, String fieldLabel) {

		ValidatorMessage validatorMessage = new ValidatorMessage();
		if(fieldValue == null){
			return validatorMessage;
		}
		
		String value = fieldValue.toString();
		if(!(value.length()>=min && value.length()<=max)){
			populateMessage(validatorMessage, fieldLabel, min,max);
		}
		
		return validatorMessage;
	}

}
