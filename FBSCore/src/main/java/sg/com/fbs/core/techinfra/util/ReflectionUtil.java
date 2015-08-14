package sg.com.fbs.core.techinfra.util;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtilsBean;

import sg.com.fbs.core.techinfra.exception.ApplicationSystemException;


public class ReflectionUtil {

	public static void copyProperties(Object dest, Object orig){
		
		try {
			ConvertUtilsBean convertUtilsBean = BeanUtilsBean.getInstance().getConvertUtils();
			//convertUtilsBean.register(converter, clazz);
			BeanUtils.copyProperties(dest, orig);
		} catch (IllegalAccessException e) {
			throw new ApplicationSystemException(e);
		} catch (InvocationTargetException e) {
			throw new ApplicationSystemException(e);
		}
	}
	
	public static void setProperty(Object target, String property, Object value){
		try {
			BeanUtilsBean.getInstance().getPropertyUtils().setNestedProperty(target, property, value);
		} catch (IllegalAccessException e) {
			throw new ApplicationSystemException(e);
		} catch (InvocationTargetException e) {
			throw new ApplicationSystemException(e);
		} catch (NoSuchMethodException e) {
			throw new ApplicationSystemException(e);
		}
	}
	
	public static Object getClassObject(String clazzName) {
		try {
			Object object = Class.forName(clazzName).newInstance();		
			return object;
			
		} catch (InstantiationException e) {
			throw new ApplicationSystemException(e);
		} catch (IllegalAccessException e) {
			throw new ApplicationSystemException(e);
		} catch (ClassNotFoundException e) {
			throw new ApplicationSystemException(e);
		}
	}
}
