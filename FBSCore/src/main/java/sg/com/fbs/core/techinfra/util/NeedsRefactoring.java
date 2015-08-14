package sg.com.fbs.core.techinfra.util;

/**
 * @Author Frank Xu $
 * @Created 12:03:56 pm 3 Jul, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public @interface NeedsRefactoring {
	String value();
	String item1() default "";
	String item2() default "";
	String item3() default "";
	String item4() default "";
	String item5() default "";
	String item6() default "";
}
