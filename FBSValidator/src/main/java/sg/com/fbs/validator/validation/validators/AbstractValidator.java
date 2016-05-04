package sg.com.fbs.validator.validation.validators;

import lombok.Getter;
import lombok.Setter;
import sg.com.fbs.validator.core.NameAware;
import sg.com.fbs.validator.message.MessageSpecification;
import sg.com.fbs.validator.validation.FieldValidator;
import sg.com.fbs.validator.validation.ValidatorMessage;

/**
 * @Author Frank Xu $
 * @Created 3:27:13 pm 9 Jul, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public abstract class AbstractValidator extends MessageSpecification implements NameAware, FieldValidator{
	
	private static final long serialVersionUID = 2774996045359666016L;

	public boolean noMessages = false;

	public boolean isNoMessages() {
		return noMessages;
	}

	public void setNoMessages(boolean noMessages) {
		this.noMessages = noMessages;
	}

	protected void populateMessage(ValidatorMessage message, String fieldLabel, Object... args) {
		populateMessage(null, message, fieldLabel, args);
	}
	
    protected void populateMessage(MessageSpecification ms, ValidatorMessage message, String fieldLabel, Object... args) {
		if(ms==null){
			ms = this;
		}
		
		if(!noMessages){
			message.setSummary(ms.createSummaryMessage(args));
			message.setDetail(ms.createDetailMessage(args));
		}
		ms.setCurrentSubject(null);
		message.setHasError(true);
	}
}






















