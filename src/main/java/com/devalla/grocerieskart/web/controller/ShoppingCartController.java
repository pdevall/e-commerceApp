package com.devalla.grocerieskart.web.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.devalla.grocerieskart.common.GroceriesKartException;
import com.devalla.grocerieskart.delegate.MenuDelegate;
import com.devalla.grocerieskart.delegate.ProductDetailsDelegate;
import com.devalla.grocerieskart.dto.Category;
import com.devalla.grocerieskart.dto.FrontendProduct;
import com.devalla.grocerieskart.dto.Product;
import com.devalla.grocerieskart.util.GroceriesKartUtil;
import com.devalla.grocerieskart.web.common.WebConstants;
import com.devalla.grocerieskart.web.form.BaseForm;
import com.devalla.grocerieskart.web.form.ShoppingCartForm;

@Controller
@RequestMapping("/shopkart")
@SessionAttributes("shoppingCartForm")
public class ShoppingCartController {
	
	@Inject
	private ProductDetailsDelegate productDetailsDelegate;
	
	@Inject
	private MenuDelegate menuDelegate;
	
	@InitBinder("shoppingCartForm")
    protected void initBinder(WebDataBinder binder) {        
    }
	
	@RequestMapping(value="/add2kart/product/{productId}")
    public ModelAndView addToKart(@PathVariable Long productId, HttpServletRequest request) throws GroceriesKartException  {
		ModelAndView mav = new ModelAndView("redirect:/shopkart/showkart/shoppingKart");
		String strQuantity = request.getParameter("productQuantity");
		processAdd(productId, request, strQuantity, false);
		return mav;
	}
	
	@RequestMapping(value="/showkart/shoppingKart")
    public ModelAndView showKart(HttpServletRequest request) throws GroceriesKartException  {
		ModelAndView mav = new ModelAndView("shoppingKart");
		List<Category> categories = menuDelegate.getCategories();
		mav.addObject(WebConstants.LEFT_MENU_SUB_CATEGORY, categories);
		HttpSession session = request.getSession(false);
		ShoppingCartForm sForm = (ShoppingCartForm) session.getAttribute(WebConstants.SHOPPING_KART_FORM);
		if (sForm != null && sForm.getShoppedProducts().size() > 0) {
			int totalPages = new Double(Math.ceil(new Integer(sForm.getShoppedProducts().size()).doubleValue() / WebConstants.GLIST_PER_PAGE.doubleValue())).intValue();
			GroceriesKartUtil.calculatePages(totalPages, (BaseForm)sForm, 0, true);
		}
		return mav;
	}
	
	@RequestMapping(value="/ajax/add2kart/product/{productId}")
    public @ResponseBody Integer addToKartAjax(@PathVariable Long productId, HttpServletRequest request) throws GroceriesKartException  {
		HttpSession session = request.getSession(false);
		String strQuantity = request.getParameter("productQuantity");
		processAdd(productId, request, strQuantity, false);
		ShoppingCartForm shoppingCartForm = (ShoppingCartForm) session.getAttribute(WebConstants.SHOPPING_KART_FORM);		
		return new Integer(shoppingCartForm.getShoppedProducts().size());
	}
	
	@RequestMapping(value="/update2kart/product/{productId}")
    public ModelAndView updateToKart(@PathVariable Long productId, HttpServletRequest request) throws GroceriesKartException  {
		ModelAndView mav = new ModelAndView("redirect:/shopkart/showkart/shoppingKart");
		String strQuantity = request.getParameter("productQuantity");
		processAdd(productId, request, strQuantity, true);
		return mav;
	}
	
	@RequestMapping(value="/delete2kart/product/{productId}")
    public ModelAndView deleteToKart(@PathVariable Long productId, HttpServletRequest request) throws GroceriesKartException  {
		ModelAndView mav = new ModelAndView("redirect:/shopkart/showkart/shoppingKart");
		HttpSession session = request.getSession(false);
		ShoppingCartForm shoppingCartForm = (ShoppingCartForm) session.getAttribute(WebConstants.SHOPPING_KART_FORM);
		removeProduct(productId, shoppingCartForm);
		return mav;
	}

	private void processAdd(Long productId, HttpServletRequest request,
			String strQuantity, boolean update) throws GroceriesKartException {
		FrontendProduct fep = null;
		boolean productFound = false;
		boolean deleteProduct = false;
		HttpSession session = request.getSession(false);		
		Integer quantity = new Integer(1);
		if (strQuantity != null && !strQuantity.equals(WebConstants.EMPTY_SPACE)) {
			quantity = new Integer(strQuantity);
		} else {
			if (update) {
				quantity = new Integer(-1);
			}
		}
		ShoppingCartForm shoppingCartForm = (ShoppingCartForm) session.getAttribute(WebConstants.SHOPPING_KART_FORM);
		if (shoppingCartForm.findProductAdded(productId)) {
			fep = shoppingCartForm.findProduct(productId);
			productFound = true;
		} else {
			if (!update) {
				fep = productDetailsDelegate.getFrontendProductDetailsForProductId(productId);
				shoppingCartForm.getProductsAddedMap().put(fep.getProductId(), fep.getProductName());
				productFound = false;
			}
		}
		if (fep != null) {
			if (fep.getFrontendQuantity() != null) {
				if (!update) {
					Integer newQuantity = fep.getFrontendQuantity().intValue() + quantity.intValue();
					fep.setFrontendQuantity(newQuantity);
				} else {
					if (quantity.intValue() <= 0) {
						deleteProduct = true;
					} else {
						fep.setFrontendQuantity(quantity);
					}
				}
			} else {
				fep.setFrontendQuantity(quantity);
			}			
			calculation(fep);
			if (!productFound)  {
				shoppingCartForm.getShoppedProducts().add(fep);
			} else {
				if (deleteProduct) {
					removeProduct(productId, shoppingCartForm);
				}
			}
			session.setAttribute(WebConstants.SHOPPING_KART_FORM, shoppingCartForm);
		}
	}

	private void removeProduct(Long productId, ShoppingCartForm shoppingCartForm) {
		int index = shoppingCartForm.getProductIndex(productId);
		shoppingCartForm.getShoppedProducts().remove(index);
		shoppingCartForm.getProductsAddedMap().remove(productId);
	}
	
	private void calculation(FrontendProduct fp) {
		GroceriesKartUtil.calculateDiscount((Product)fp);
	}
	
	@RequestMapping(value="/nextPrevious")
    public ModelAndView nextPreviousGList(HttpServletRequest request) throws GroceriesKartException  {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("shoppingKart");
		String strPageNumber = request.getParameter("pageNumber");
		String pageBoolean = request.getParameter("pageBoolean");
		HttpSession session = request.getSession(false);
		ShoppingCartForm shoppingCartForm = (ShoppingCartForm) session.getAttribute(WebConstants.SHOPPING_KART_FORM);
		if (shoppingCartForm != null && shoppingCartForm.getShoppedProducts().size() > 0) {
			List<Category> categories = menuDelegate.getCategories();
			mav.addObject(WebConstants.LEFT_MENU_SUB_CATEGORY, categories);
			int totalPages = new Double(Math.ceil(new Integer(shoppingCartForm.getShoppedProducts().size()).doubleValue() / WebConstants.GLIST_PER_PAGE.doubleValue())).intValue();
			GroceriesKartUtil.calculatePages(totalPages, (BaseForm)shoppingCartForm, new Integer(strPageNumber), new Boolean(pageBoolean));
		}
		return mav;
	}

}
