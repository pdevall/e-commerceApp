package com.devalla.grocerieskart.web.controller;

import java.util.Calendar;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.devalla.grocerieskart.common.GroceriesKartException;
import com.devalla.grocerieskart.delegate.HomePageDelegate;
import com.devalla.grocerieskart.delegate.MenuDelegate;
import com.devalla.grocerieskart.dto.GlobalDiscount;
import com.devalla.grocerieskart.dto.HomePage;
import com.devalla.grocerieskart.web.common.WebConstants;
import com.devalla.grocerieskart.web.form.ShoppingCartForm;

@Controller
@RequestMapping("/index")
public class IndexController {
	
	@Inject
	private HomePageDelegate homePageDelegate;
	
	@Inject
	private MenuDelegate menuDelegate;
	
	@RequestMapping(method = RequestMethod.GET)
    public ModelAndView welcome(HttpServletRequest request) throws GroceriesKartException {
		HomePage homePage = null;
		HttpSession session = request.getSession(false);
		boolean fetchAgain = false;
		session.setAttribute(WebConstants.STATIC_CONTENT, WebConstants.STATIC_CONTENT_URL);
		String localeString = (String) session.getAttribute(WebConstants.LOCALE_ATTRIBUTE);
        ModelAndView mav = new ModelAndView();
        ShoppingCartForm shoppingCartForm = (ShoppingCartForm) session.getAttribute(WebConstants.SHOPPING_KART_FORM);
        if (shoppingCartForm == null) {
        	shoppingCartForm = new ShoppingCartForm();
        	session.setAttribute(WebConstants.SHOPPING_KART_FORM, shoppingCartForm);
    		GlobalDiscount gb = menuDelegate.getGlobalDiscounts();
    		shoppingCartForm.setGlobalDiscount(gb);
        }
        
        Calendar initialCal = (Calendar) session.getAttribute(WebConstants.HOME_PAGE_CALENDAR);
        if (initialCal != null) {
        	Calendar refreshCal = Calendar.getInstance();
        	long diff = refreshCal.getTimeInMillis() - initialCal.getTimeInMillis();
        	if (diff > WebConstants.HOME_PAGE_REFRESH_TIME.longValue()) {
        		homePage = homePageDelegate.getHomePageCategories(localeString);
        		fetchAgain = true;
        	}
        } else {
        	fetchAgain = true;
        	homePage = homePageDelegate.getHomePageCategories(localeString);
        }
        if (fetchAgain) {
        	session.setAttribute(WebConstants.HOME_PAGE_CATEGORIES, homePage);
        	Calendar cal = Calendar.getInstance();
            session.setAttribute(WebConstants.HOME_PAGE_CALENDAR, cal);
        }
        mav.setViewName("index");
        mav.addObject(WebConstants.BREAD_CRUMB, "Home");
        return mav;
    }

}
