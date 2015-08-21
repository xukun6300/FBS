package sg.com.fbs.common.logging;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.springframework.aop.framework.Advised;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.mvc.multiaction.InternalPathMethodNameResolver;
import org.springframework.web.servlet.mvc.multiaction.MethodNameResolver;

import sg.com.fbs.model.system.activity.ActivityLog;
import sg.com.fbs.techinfra.persistence.dao.BaseDao;

/**
 * @Author Frank Xu $
 * @Created 2:43:42 pm 29 Jul, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public class ActivityInterceptor extends HandlerInterceptorAdapter{

	private static final MethodNameResolver METHOD_RESOLVER = new InternalPathMethodNameResolver();
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		//if(PrincipalSecUtil.getUserId()!=0L){
			final ActivityLog activity = new ActivityLog();
			
			if(handler instanceof HandlerMethod){
				activity.setController(((HandlerMethod)handler).getMethod().getDeclaringClass().getSimpleName());
			}else if (handler instanceof Advised) {
			//	activity.setController(((Advised)handler).getTargetClass().getSimpleName());
			}else {
				activity.setController(handler.getClass().getSimpleName());
			}
			
			activity.setAction(ActivityInterceptor.METHOD_RESOLVER.getHandlerMethodName(request));
			//activity.setCreatedby(PrincipalSecUtil.getUserId());
			activity.setCreateon(DateTime.now());
			
			new BaseDao().saveOrUpdate(activity);
		//}
		return true;
	}
}




















