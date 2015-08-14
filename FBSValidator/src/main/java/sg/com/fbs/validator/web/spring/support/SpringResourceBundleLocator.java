package sg.com.fbs.validator.web.spring.support;

import java.util.ResourceBundle;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.MessageSourceResourceBundle;

import sg.com.fbs.validator.core.ResourceBundleLocator;

/**
 * @Author Frank Xu $
 * @Created 11:13:27 am 14 Jul, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public class SpringResourceBundleLocator implements ResourceBundleLocator, ApplicationContextAware {

	private ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}

	@Override
	public ResourceBundle getBundle() {
		return new MessageSourceResourceBundle(applicationContext, LocaleContextHolder.getLocale());
	}

}


























