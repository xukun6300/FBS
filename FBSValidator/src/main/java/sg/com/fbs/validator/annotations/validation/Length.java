package sg.com.fbs.validator.annotations.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author Frank Xu $
 * @Created 4:40:49 pm 13 Jul, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.FIELD,ElementType.TYPE})
public @interface Length {

	long min() default 1L;
	
	long max() default Long.MAX_VALUE;
	
	String detailMessage() default "";
	
	String summaryMessage() default "";
	
	String labelFields() default "";
	
	String dynaData() default "";
}
