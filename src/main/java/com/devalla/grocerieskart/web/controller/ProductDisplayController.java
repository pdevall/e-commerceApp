package com.devalla.grocerieskart.web.controller;

import java.util.List;

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
import com.devalla.grocerieskart.delegate.MenuDelegate;
import com.devalla.grocerieskart.delegate.ProductDisplayDelegate;
import com.devalla.grocerieskart.dto.Brand;
import com.devalla.grocerieskart.dto.Category;
import com.devalla.grocerieskart.dto.SubCategory;
import com.devalla.grocerieskart.web.common.WebConstants;

@Controller
@RequestMapping("/gk")
public class ProductDisplayController {
	
	@Inject
	private ProductDisplayDelegate productDisplayDelegate;
	
	@Inject
	private MenuDelegate menuDelegate;

		
	@RequestMapping(value="/*/category/{categoryId}", method = RequestMethod.GET)
    public ModelAndView productsByCategoryId(@PathVariable Long categoryId, HttpServletRequest request) throws GroceriesKartException {
		Category category = null;
		ModelAndView mav = new ModelAndView();
		String strStartNumber = request.getParameter("startNumber");
		HttpSession session = request.getSession(false);
		Integer startNumber = new Integer(0);
		if (strStartNumber != null && !strStartNumber.equals(WebConstants.EMPTY_SPACE)) {
			startNumber = new Integer(strStartNumber);
		}
		mav.setViewName("productDisplay");
		if (categoryId == null) {
			mav.addObject("errorObject", "Category not found.");
		} else {
			category = productDisplayDelegate.getProductsForCategory(categoryId, startNumber, (String) session.getAttribute(WebConstants.LOCALE_ATTRIBUTE), WebConstants.PRODUCT_DISPLAY_RECORDS);
			if (category != null) {
				Integer newStartNumber = startNumber + WebConstants.PRODUCT_DISPLAY_RECORDS;
				if (newStartNumber < category.getNumOfProductsByCategory())
					mav.addObject("startNumber", newStartNumber);				
				if (category.getDepartment() != null) {
					mav.addObject(WebConstants.BREAD_CRUMB, category.getDepartment().getDepartmentName() + ":" + category.getName());
				}
			}
			mav.addObject("category", category);
			
			List<SubCategory> leftMenu = menuDelegate.getSubCategoriesByCategoryId(categoryId);
			mav.addObject(WebConstants.LEFT_MENU_SUB_CATEGORY, leftMenu);
		}
		return mav;
	}
	
	@RequestMapping(value="/ajax/*/category/{categoryId}", method = RequestMethod.GET)
    public @ResponseBody Category productsByCategoryIdAjax(@PathVariable Long categoryId, HttpServletRequest request) throws GroceriesKartException {
		Category category = null;
		String strStartNumber = request.getParameter("startNumber");
		HttpSession session = request.getSession(false);
		Integer startNumber = new Integer(0);
		if (strStartNumber != null && !strStartNumber.equals(WebConstants.EMPTY_SPACE)) {
			startNumber = new Integer(strStartNumber);
		}
		if (categoryId == null) {
			//mav.addObject("errorObject", "Category not found.");
		} else {
			category = productDisplayDelegate.getProductsForCategory(categoryId, startNumber, (String) session.getAttribute(WebConstants.LOCALE_ATTRIBUTE), WebConstants.PRODUCT_DISPLAY_RECORDS);
			if (category != null) {
				Integer newStartNumber = startNumber + WebConstants.PRODUCT_DISPLAY_RECORDS;
				if (newStartNumber < category.getNumOfProductsByCategory())
					category.setStartNumber(newStartNumber);				
			}
		}
		return category;
	}
	
	@RequestMapping(value="/*/category/{categoryId}/subCategory/{subCategoryId}", method = RequestMethod.GET)
    public ModelAndView productsBySubCategoryId(@PathVariable Long categoryId, @PathVariable Long subCategoryId, HttpServletRequest request) throws GroceriesKartException {
		SubCategory subCategory = null;
		ModelAndView mav = new ModelAndView();
		mav.setViewName("productDisplaySubCategory");
		if (categoryId == null || subCategoryId == null) {
			mav.addObject("errorObject", "Category or Sub Category not found.");
		} else {
			subCategory = productDisplayDelegate.getProductsForSubCategory(subCategoryId, categoryId);
			if (subCategory != null) {
				if (subCategory.getCategory() != null && subCategory.getCategory().getDepartment() != null) {
					mav.addObject(WebConstants.BREAD_CRUMB, subCategory.getCategory().getDepartment().getDepartmentName() + ":" + subCategory.getName());
				}
			}
			mav.addObject("subCategory", subCategory);
			
			List<SubCategory> leftMenu = menuDelegate.getSubCategoriesByCategoryId(categoryId);
			moveSelectedSubCategoryToTop(leftMenu, subCategoryId);
			mav.addObject(WebConstants.LEFT_MENU_SUB_CATEGORY, leftMenu);
			
			List<Brand> brands = menuDelegate.getBrandsBySubCategoryId(subCategoryId);
			mav.addObject(WebConstants.LEFT_MENU_BRAND, brands);
			
		}
		return mav;
	}
	
	@RequestMapping(value="/*/category/{categoryId}/subCategory/{subCategoryId}/*/{brandId}", method = RequestMethod.GET)
    public ModelAndView productsByBrandId(@PathVariable Long brandId, @PathVariable Long categoryId, @PathVariable Long subCategoryId, HttpServletRequest request) throws GroceriesKartException {
		SubCategory subCategory = null;
		ModelAndView mav = new ModelAndView();
		mav.setViewName("productDisplaySubCategory");
		if (categoryId == null || subCategoryId == null || brandId == null) {
			mav.addObject("errorObject", "Category or Sub Category or Brand not found.");
		} else {
			subCategory = productDisplayDelegate.getProductsForBrand(brandId, subCategoryId, categoryId);
			if (subCategory != null) {
				if (subCategory.getCategory() != null && subCategory.getCategory().getDepartment() != null) {
					mav.addObject(WebConstants.BREAD_CRUMB, subCategory.getCategory().getDepartment().getDepartmentName() + ":" + subCategory.getName());
				}
			}
			mav.addObject("subCategory", subCategory);
			
			List<SubCategory> leftMenu = menuDelegate.getSubCategoriesByCategoryId(categoryId);
			moveSelectedSubCategoryToTop(leftMenu, subCategoryId);
			mav.addObject(WebConstants.LEFT_MENU_SUB_CATEGORY, leftMenu);
			
			List<Brand> brands = menuDelegate.getBrandsBySubCategoryId(subCategoryId);
			moveSelectedBrandToTop(brands, brandId);
			mav.addObject(WebConstants.LEFT_MENU_BRAND, brands);
			
		}
		return mav;
	}

	
	private void moveSelectedSubCategoryToTop(List<SubCategory> subCategories, Long subCategoryId) {
		if (subCategories != null && subCategories.size() > 0 ) {
			int j = -1;
			SubCategory selectedSubCategory = null;
			for (int i = 0; i < subCategories.size(); i++) {
				SubCategory subCategory = subCategories.get(i);
				if (subCategory.getId().longValue() == subCategoryId.longValue()) {
					selectedSubCategory = subCategory;
					j = i;
					break;
				}
			}
			if (j > -1) {
				subCategories.remove(j);
				subCategories.add(0, selectedSubCategory);
			}
		}
	}
	
	private void moveSelectedBrandToTop(List<Brand> brands, Long brandId) {
		if (brands != null && brands.size() > 0 ) {
			int j = -1;
			Brand selectedBrand = null;
			for (int i = 0; i < brands.size(); i++) {
				Brand brand = brands.get(i);
				if (brand.getBrandId().longValue() == brandId.longValue()) {
					selectedBrand = brand;
					j = i;
					break;
				}
			}
			if (j > -1) {
				brands.remove(j);
				brands.add(0, selectedBrand);
			}
		}
	}
}
