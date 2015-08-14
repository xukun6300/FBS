package sg.com.fbs.validator.core;

import java.util.ResourceBundle;

import sg.com.fbs.validator.annotations.design.ExtentionPoint;

/**
 * @Author Frank Xu $
 * @Created 4:16:08 pm 6 Jul, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
@ExtentionPoint
public interface ResourceBundleLocator {

	ResourceBundle getBundle();
}
