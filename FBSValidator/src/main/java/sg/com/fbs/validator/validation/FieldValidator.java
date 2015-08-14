package sg.com.fbs.validator.validation;

import java.io.Serializable;

/**
 * @Author Frank Xu $
 * @Created 2:03:51 pm 6 Jul, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 * 
 * Validator interface for crank validation.
 * This would get used by commons and our other validation mechanism.
 */
public interface FieldValidator extends Serializable{
    /**
     * Validates a single field.
     * @param object object to validate
     * @return A messages whose hasError is set to true if there was an error.
     */
	ValidatorMessageHolder validate(Object fieldValue, String fieldLabel);
}
