package com.devalla.grocerieskart.web.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.devalla.grocerieskart.common.GroceriesKartException;
import com.devalla.grocerieskart.delegate.ProductDetailsDelegate;
import com.devalla.grocerieskart.delegate.ProductDisplayDelegate;
import com.devalla.grocerieskart.dto.BaseDto;
import com.devalla.grocerieskart.dto.Category;
import com.devalla.grocerieskart.dto.Product;
import com.devalla.grocerieskart.util.GroceriesKartUtil;
import com.devalla.grocerieskart.web.common.WebConstants;

@Controller
@RequestMapping("/pd")
public class ProductDetailsController {
	
	@Inject
	private ProductDetailsDelegate productDetailsDelegate;
	
	@Inject
	private ProductDisplayDelegate productDisplayDelegate;

		
	@RequestMapping(value="/*/product/{productId}", method = RequestMethod.GET)
    public ModelAndView productsByCategoryId(@PathVariable Long productId, HttpServletRequest request) throws GroceriesKartException {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession(false);
		mav.setViewName("productDetails");
		Product product = productDetailsDelegate.getProductDetailsForProductId(productId);
		if (product.getCategory().getDepartment() != null) {
			mav.addObject(WebConstants.BREAD_CRUMB, product.getCategory().getDepartment().getDepartmentName() + ":" + product.getCategory().getName());
		}
		Category category = productDisplayDelegate.getProductsForCategory(product.getCategory().getId(), new Integer(0), (String) session.getAttribute(WebConstants.LOCALE_ATTRIBUTE), WebConstants.SIMILAR_CATEGORY_PRODS);
		if (category != null ) {
			int totalPages = new Double(Math.ceil(category.getNumOfProductsByCategory().doubleValue() / WebConstants.PRODUCT_DISPLAY_RECORDS.doubleValue())).intValue();
			GroceriesKartUtil.calculatePages(totalPages, (BaseDto)category, 0, true);
			mav.addObject("categoryProductDetails", category);
		}		
		mav.addObject("productDetails", product);
		return mav;
	}
	
	@RequestMapping(value="/ajax/category/{categoryId}", method = RequestMethod.GET)
    public @ResponseBody Category productsByCategoryIdAjax(@PathVariable Long categoryId, HttpServletRequest request) throws GroceriesKartException {
		Category category = null;
		String strPageNumber = request.getParameter("pageNumber");
		String pageBoolean = request.getParameter("pageBoolean");
		HttpSession session = request.getSession(false);
		Integer startNumber = new Integer(0);
		Integer pageNumber = new Integer(0);
		if (strPageNumber != null && !strPageNumber.equals(WebConstants.EMPTY_SPACE)) {
			startNumber = new Integer(strPageNumber);
			pageNumber = new Integer(strPageNumber);
			startNumber = startNumber.intValue() * WebConstants.SIMILAR_CATEGORY_PRODS.intValue();
		}
		if (categoryId == null) {
			//mav.addObject("errorObject", "Category not found.");
		} else {
			category = productDisplayDelegate.getProductsForCategory(categoryId, startNumber, (String) session.getAttribute(WebConstants.LOCALE_ATTRIBUTE), WebConstants.SIMILAR_CATEGORY_PRODS);
			if (category != null && category.getProducts() != null && category.getProducts().size() > 0 ) {
				int totalPages = new Double(Math.ceil(category.getNumOfProductsByCategory().doubleValue() / WebConstants.SIMILAR_CATEGORY_PRODS.doubleValue())).intValue();
				GroceriesKartUtil.calculatePages(totalPages, (BaseDto) category, new Integer(pageNumber), new Boolean(pageBoolean).booleanValue());
		    }
		}
		return category;
	}
	
}
