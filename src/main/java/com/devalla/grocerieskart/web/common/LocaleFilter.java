package com.devalla.grocerieskart.web.common;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.devalla.grocerieskart.web.form.ShoppingCartForm;

public class LocaleFilter implements Filter {

		
		public void doFilter(ServletRequest request, ServletResponse response,
				FilterChain chain) throws IOException, ServletException {
			try {
				//String cookieValue = WebConstants.EMPTY_SPACE;
				HttpServletRequest req = (HttpServletRequest) request;
				HttpSession session = req.getSession(false);
				if (session == null) {
					session = req.getSession();
					//session.setAttribute(WebConstants.SSID, session.getId());
				}
				//String jsessionId = (String) session.getAttribute(WebConstants.SSID);
				String langParam = request.getParameter("lang");
				String language = (String) session.getAttribute(WebConstants.LOCALE_ATTRIBUTE);
				if (langParam != null && !langParam.equals(WebConstants.EMPTY_SPACE)) {
					session.setAttribute(WebConstants.LOCALE_ATTRIBUTE, langParam);
				} else {
					if (language == null || language.equals(WebConstants.EMPTY_SPACE))
						session.setAttribute(WebConstants.LOCALE_ATTRIBUTE, "te");
				}
/*				Cookie[] cookies = req.getCookies();
				if (cookies != null && cookies.length > 0 ) {
					for (int i = 0; i < cookies.length; i++) {
						Cookie cookie = cookies[i];
						if ((WebConstants.COOKIE_NAME+"_"+jsessionId).equals(cookie.getName())) {
							cookieValue = cookie.getValue();
						}
					}					
				}
				if (cookieValue.equals(WebConstants.EMPTY_SPACE) || !cookieValue.equals(jsessionId)) {
					Cookie newCookie = new Cookie(WebConstants.COOKIE_NAME+"_"+jsessionId, jsessionId);
					HttpServletResponse res = (HttpServletResponse) response;
					res.addCookie(newCookie);
					res.sendRedirect(req.getContextPath() + "/index.html");
				}*/
				chain.doFilter(request, response);

			} catch (Exception se) {
				HttpServletRequest req = (HttpServletRequest) request;
				HttpSession session = req.getSession(false);
				RequestDispatcher rd  = request.getRequestDispatcher("/error.html");
				HttpServletResponse res = (HttpServletResponse) response;
				int statusCode  = res.getStatus();
				if (se.getCause() != null) {
					req.setAttribute("servletError", se.getCause().getMessage());
				}
				if (session == null) {
					statusCode = 2000;
				} else {
					ShoppingCartForm scForm = (ShoppingCartForm) session.getAttribute(WebConstants.SHOPPING_KART_FORM);
					if (scForm == null) {
						session.invalidate();
						statusCode = 2000;
					}
				}
				req.setAttribute("statusCode", statusCode);
				rd.forward(request, response);
			}
		}
		
		
		public void destroy() {

		}

		
		public void init(FilterConfig arg0) throws ServletException {

		}

}
