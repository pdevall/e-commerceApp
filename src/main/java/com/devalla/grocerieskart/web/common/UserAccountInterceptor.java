package com.devalla.grocerieskart.web.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.devalla.grocerieskart.dto.UserAccountInfo;


public class UserAccountInterceptor extends HandlerInterceptorAdapter  {

	
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession(false);
		String context = request.getContextPath();
		String url = context+"/useraccount/login.html";
		String fromCheckout = request.getParameter("fromCheckout");
		if (fromCheckout != null && !fromCheckout.equals(WebConstants.EMPTY_SPACE)) {
			url = url + "?fromCheckout=" + fromCheckout;
		}
		UserAccountInfo userAccountInfoObj = (UserAccountInfo) session.getAttribute(WebConstants.USER_ACCOUNT_OBJ);
		if (userAccountInfoObj == null) {			
			response.sendRedirect(url);
			return false;
		}
		
		return true;
	}

	

}
