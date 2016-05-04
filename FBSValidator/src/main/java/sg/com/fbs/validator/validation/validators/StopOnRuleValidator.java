package sg.com.fbs.validator.validation.validators;

import lombok.Getter;
import lombok.Setter;
import sg.com.fbs.validator.validation.FieldValidator;
import sg.com.fbs.validator.validation.ValidatorMessage;
import sg.com.fbs.validator.validation.ValidatorMessageHolder;

/**
 * @Author Frank Xu $
 * @Created 5:03:55 pm 9 Jul, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public class StopOnRuleValidator implements FieldValidator{

	private static final long serialVersionUID = 498247418765148543L;

	private String ruleName;
	
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}
	
	public String getRuleName() {
		return ruleName;
	}
	
	@Override
	public ValidatorMessageHolder validate(Object fieldValue, String fieldLabel) {
		return new ValidatorMessage();
	}

}
