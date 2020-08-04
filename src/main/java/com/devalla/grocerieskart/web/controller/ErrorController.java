package com.devalla.grocerieskart.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.devalla.grocerieskart.web.common.WebConstants;

@Controller
public class ErrorController {
	
	@RequestMapping("/error")
    public ModelAndView processError(HttpServletRequest request)  {
		ModelAndView mav = new ModelAndView("error");
		String servletError = (String) request.getAttribute("servletError");
		Integer stCode = (Integer ) request.getAttribute("statusCode");
		
		
		mav.addObject("servletError", servletError);
		if(stCode != null && !stCode.equals(WebConstants.EMPTY_SPACE)) {
			if (Integer.valueOf(stCode) == 2000) {
				mav.setViewName("redirect:/index.html?sessionExpired=Session Expired.");
			} else {
				mav.addObject("statusCode", new Integer(stCode));
			}
		}
		
		return mav;
	}

}
