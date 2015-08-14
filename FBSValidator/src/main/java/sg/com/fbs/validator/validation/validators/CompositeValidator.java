package sg.com.fbs.validator.validation.validators;

import java.util.ArrayList;
import java.util.List;

import lombok.Setter;
import sg.com.fbs.validator.validation.FieldValidator;
import sg.com.fbs.validator.validation.ValidatorMessage;
import sg.com.fbs.validator.validation.ValidatorMessageHolder;
import sg.com.fbs.validator.validation.ValidatorMessages;

/**
 * @Author Frank Xu $
 * @Created 10:59:00 am 9 Jul, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 * 
 * <p>
 * Combines a bunch of validators into one.
 * </p>
 */
public class CompositeValidator implements FieldValidator{

	private static final long serialVersionUID = 2018509213007603836L;

	private List<FieldValidator> validatorList = new ArrayList<FieldValidator>();
	
	private RequiredValidator requiredValidator = null;
	
	@Setter
	private List<String> detailArgs;
	
	@Setter
	private List<String> summaryArgs;
	
	private String stopOnRule = "";
	
	private boolean stopOnFirstRule = false;
	
	@Setter
	private boolean stopOnBlank = true;
	
	@Override
	public ValidatorMessageHolder validate(Object fieldValue, String fieldLabel) {
		
		ValidatorMessages messages = new ValidatorMessages(); //hold error messages
		
		ValidatorMessage requiredMessage = validateWithRequriedIfPresent(fieldValue, fieldLabel, messages);
		
		boolean proceed = !(stopOnBlank && (fieldValue == null || fieldValue.toString().trim().length() == 0));
		
		/* If the requiredMessage from the requiredValidator is null, then there was not a required validator present. */
        /* If the requiredMessage is present then check to see if it has errors, only validate further if
         * the requiredMessage has no error. */
		if(requiredMessage==null || !requiredMessage.isHasError()){
			if(proceed){
				runValidationRules(fieldValue, fieldLabel, messages);
			}
		}
		
		return messages;
	}
	
	public void setValidatorList(List<FieldValidator> list) {
		this.validatorList = list;
		
		// StopOnRuleValidator and RequiredValidator is the placeholder to indicate require and stopOnRule
        // to be remove from ruleset
		StopOnRuleValidator stopOnRuleValidator = null;
		for (FieldValidator fieldValidator : list) {
			if(fieldValidator instanceof StopOnRuleValidator){
				stopOnRuleValidator = (StopOnRuleValidator) fieldValidator;
			}
			
			if(fieldValidator instanceof RequiredValidator){
				requiredValidator = (RequiredValidator) fieldValidator;		
			}
		}
		
		if(requiredValidator!=null){
			validatorList.remove(requiredValidator);
		}
		
		if(stopOnRuleValidator!=null){
			validatorList.remove(stopOnRuleValidator);
			String ruleName = stopOnRuleValidator.getRuleName();
			if("first".equals(ruleName) || ruleName==null){
				stopOnFirstRule = true;
			}else {
				stopOnRule = ruleName;
			}
			
		}
	}

	
	
	private void runValidationRules(Object object, String fieldLabel, ValidatorMessages messages){
		for(FieldValidator validator: validatorList){
			putArgs(validator);
			ValidatorMessage message = (ValidatorMessage) validator.validate(object, fieldLabel);   //will go to LengthValidator
			
			if(message.isHasError()){
				messages.add(message);
				if(this.stopOnFirstRule){
					break;
				}else if (validator.getClass().getSimpleName().equalsIgnoreCase(stopOnRule)) {
					break;
				}
			}
		}
	}
	
	private ValidatorMessage validateWithRequriedIfPresent(Object object, String fieldLabel, ValidatorMessages messages){
		ValidatorMessage requiredMessage = null;
		
		if(requiredValidator!=null){
			putArgs(requiredValidator);
			requiredMessage = (ValidatorMessage) requiredValidator.validate(object, fieldLabel);
			if(requiredMessage.isHasError()){
				messages.add(requiredMessage);
			}
		}
		return requiredMessage;
	}
	
	private void putArgs(FieldValidator validator){
		if(validator instanceof AbstractValidator){
			AbstractValidator abstractValidator = (AbstractValidator) validator;
			abstractValidator.setSummaryArgs(this.summaryArgs);
			abstractValidator.setDetailArgs(this.detailArgs);
		}
	}
	
	
	
}
























