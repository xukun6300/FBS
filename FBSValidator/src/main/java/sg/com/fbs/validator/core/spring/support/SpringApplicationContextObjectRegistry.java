package sg.com.fbs.validator.core.spring.support;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.thoughtworks.xstream.io.binary.Token.Value;

import sg.com.fbs.validator.core.ObjectNotFound;
import sg.com.fbs.validator.core.ObjectRegistry;
import sg.com.fbs.validator.web.util.ServletContextUtils;

/**
 * @Author Frank Xu $
 * @Created 9:56:34 am 6 Jul, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public class SpringApplicationContextObjectRegistry implements ObjectRegistry{

	private ApplicationContext applicationContext;

	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}
	
	private void initIfNeeded(){
		if(applicationContext == null){           //use ServletContext to find web application context
			applicationContext = WebApplicationContextUtils.getWebApplicationContext(ServletContextUtils.context());
		}
	}


	@Override
	public Object getObjectReturnNullIfMissing(String name) {
		initIfNeeded();
		try {
			return applicationContext.getBean(name);
		} catch (BeansException e) {
			return null;
		}
		
	}

	@Override
	public void resolveCollaborators(Object object) {
		initIfNeeded();
		ConfigurableApplicationContext configurableApplicationContext= (ConfigurableApplicationContext) applicationContext;
		configurableApplicationContext.getBeanFactory().autowireBeanProperties(object, AutowireCapableBeanFactory.AUTOWIRE_BY_NAME, false);
	}

	@Override
	public Object[] getObjectsByType(Class<?> clazz) {
		initIfNeeded();
		return applicationContext.getBeansOfType(clazz).values().toArray();
	}

	@Override
	public Object convertObject(Object object, Class<?> clazz) {
		if(clazz==String.class && object.getClass() == String.class){
			return object;
		}else if (clazz == String.class && object.getClass() == Integer.class) {
			return Integer.valueOf((String)object);
		}else if (clazz == String.class && object.getClass() == Long.class) {
			return Long.valueOf((String)object);
		}else if (clazz == String.class && object.getClass() == Short.class) {
			return Short.valueOf((String)object);
		}else if (clazz == String.class && object.getClass() == Byte.class) {
			return Byte.valueOf((String)object);
		}else if (clazz == String.class && object.getClass() == Double.class) {
			return Double.valueOf((String)object);
		}else if (clazz == String.class && object.getClass() == Float.class) {
			return Float.valueOf((String)object);
		}else if (clazz == String.class && object.getClass() == BigDecimal.class) {
			return new BigDecimal((String)object);
		}else if (clazz == String.class && object.getClass() == BigInteger.class) {
			return new BigInteger((String)object);
		}else {
			return null;
		}
	}

	@Override
	public Object getObject(String name) {
		initIfNeeded();
		try {
			return applicationContext.getBean(name);
		} catch (BeansException e) {
			throw new ObjectNotFound(name, e);
		}
	}

	@Override
	public Object getObject(String string, Class<?> clazz) {
		initIfNeeded();
		return applicationContext.getBean(string, clazz);
	}
	
}











































