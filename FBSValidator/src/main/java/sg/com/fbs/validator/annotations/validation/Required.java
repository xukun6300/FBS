package sg.com.fbs.validator.annotations.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author Frank Xu $
 * @Created 9:13:40 am 15 Jul, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.TYPE })
public @interface Required {

	String detailMessage() default "";
	
	String summaryMessage() default "";
	
	String labelFields() default "";
	
	String dynaData() default "";
}
