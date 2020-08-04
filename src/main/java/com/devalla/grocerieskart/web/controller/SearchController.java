package com.devalla.grocerieskart.web.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.devalla.grocerieskart.common.GroceriesKartException;
import com.devalla.grocerieskart.delegate.MenuDelegate;
import com.devalla.grocerieskart.delegate.SearchDelegate;
import com.devalla.grocerieskart.dto.Category;
import com.devalla.grocerieskart.dto.Product;
import com.devalla.grocerieskart.util.GroceriesKartUtil;
import com.devalla.grocerieskart.web.common.WebConstants;
import com.devalla.grocerieskart.web.form.BaseForm;
import com.devalla.grocerieskart.web.form.SearchForm;

@Controller
@RequestMapping("/search")
public class SearchController {
	
	@Inject
	private MenuDelegate menuDelegate;
	
	@Inject
	private SearchDelegate searchDelegate;
	
	@RequestMapping(value="/searchProducts/dept/{departmentId}/searchString/{searchstring}")
    public ModelAndView search(@PathVariable Long departmentId, @PathVariable String searchstring, HttpServletRequest request) throws GroceriesKartException  {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("search");
		SearchForm searchForm = new SearchForm();
		Integer pageNumber = new Integer(0);
		String strPageNumber = request.getParameter("pageNumber");
		String pageBoolean = request.getParameter("pageBoolean");
		boolean next = true;
		if (strPageNumber != null && !strPageNumber.equals(WebConstants.EMPTY_SPACE)) {
			pageNumber = new Integer(strPageNumber);
		}
		if (pageBoolean != null && !pageBoolean.equals(WebConstants.EMPTY_SPACE)) {
			next = new Boolean(pageBoolean).booleanValue();
		}
		Integer startNumber = pageNumber * 	WebConstants.SEARCH_PER_PAGE;
		List<Product> products = searchDelegate.searchProducts(departmentId, searchstring.trim().toUpperCase(), startNumber);
			if (products != null && products.size() > 0) {
				int count = searchDelegate.searchProductsCount(departmentId, searchstring.trim().toUpperCase());
				request.setAttribute("department", departmentId);
				request.setAttribute("searchString", searchstring);
				searchForm.setProducts(products);
				mav.addObject("searchForm", searchForm);
				for (Product product: products) {
					GroceriesKartUtil.calculateDiscount(product);
				}
				List<Category> categories = menuDelegate.getCategories();
				mav.addObject(WebConstants.LEFT_MENU_SUB_CATEGORY, categories);
				int totalPages = new Double(Math.ceil(new Integer(count).doubleValue() / WebConstants.SEARCH_PER_PAGE.doubleValue())).intValue();
				GroceriesKartUtil.calculatePagesWithOutStartEndNumber(totalPages, (BaseForm)searchForm, pageNumber, next);
			}
		return mav;
	}

}
