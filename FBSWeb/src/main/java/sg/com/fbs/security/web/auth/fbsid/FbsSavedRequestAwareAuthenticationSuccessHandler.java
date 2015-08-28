package sg.com.fbs.security.web.auth.fbsid;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

/**
 * @Author Frank Xu $
 * @Created 11:33:20 am 27 Aug, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public class FbsSavedRequestAwareAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		System.out.println("**************************Authentication Successful!!!  ****************************");
		super.onAuthenticationSuccess(request, response, authentication);
	}
}
