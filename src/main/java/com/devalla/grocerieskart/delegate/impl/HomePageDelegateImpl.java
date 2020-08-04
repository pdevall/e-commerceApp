package com.devalla.grocerieskart.delegate.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import com.devalla.grocerieskart.common.GroceriesKartException;
import com.devalla.grocerieskart.common.GroceriesKartMessageReloadable;
import com.devalla.grocerieskart.delegate.HomePageDelegate;
import com.devalla.grocerieskart.dto.Category;
import com.devalla.grocerieskart.dto.HomePage;
import com.devalla.grocerieskart.dto.Product;
import com.devalla.grocerieskart.dto.impl.HomePageImpl;
import com.devalla.grocerieskart.enums.DepartmentEnum;
import com.devalla.grocerieskart.facade.HomePageFacade;
import com.devalla.grocerieskart.web.common.WebConstants;

public class HomePageDelegateImpl implements HomePageDelegate {
	
	@Inject
	private HomePageFacade homePageFacade;
	
	@Inject
	private GroceriesKartMessageReloadable messageSource;

	
	public HomePage getHomePageCategories(String localeString) throws GroceriesKartException {
		HomePage homePage = null;
		List<Category> categories = homePageFacade.getCategories();
		List<Category> categoriesForQuery = new ArrayList<Category>();
		Locale locale = new Locale(localeString);
		if (categories != null && categories.size() > 0 ) {
			Collections.shuffle(categories);
			if (categories.size() > WebConstants.HOME_PAGE_ROWS.intValue()) {
				for(int i = 0; i < WebConstants.HOME_PAGE_ROWS; i++) {
					categoriesForQuery.add((Category)categories.get(i));
				}
			} else {
				categoriesForQuery.addAll(categories);
			}
			List<Product> products = homePageFacade.getHomePageCategories(categoriesForQuery);
			if (products != null && products.size() > 0) {
				for (Product product1 : products) {
					if (product1.getSubCategory() != null && product1.getSubCategory().getSpringLabel() != null) {
						String messageText = messageSource.resolveCodeWithoutArguments(product1.getSubCategory().getSpringLabel(), locale);
						if (messageText != null && !messageText.equals(WebConstants.EMPTY_SPACE)) {
							product1.setMessageTextExist(true);
						}
					}
					if (product1.getProductDiscount() != null) {
						if (product1.getProductDiscount().getDiscountPercent() != null) {
							BigDecimal discountCost = (product1.getProductCost().multiply(product1.getProductDiscount().getDiscountPercent())).setScale(2).divide(new BigDecimal(100.00), RoundingMode.CEILING);
							BigDecimal newProductCost = product1.getProductCost().subtract(discountCost);
							newProductCost.setScale(2);
							product1.setProductCostAfterDiscount(newProductCost);
						}
					}
				} 
				for (Category category : categoriesForQuery) {
					for (Product product : products) {
						if (category.getDepartment() != null
								&& category.getDepartment().getDepartmentId() != null
								&& category.getDepartment().getDepartmentId()
										.longValue() == DepartmentEnum.GROCERY
										.id().longValue()) {
							product.setGroceryProduct(true);
	
						}
						if (product.getCategory().getId().longValue() == category
								.getId().longValue()) {
							List<Product> categoryProduct = category
									.getProducts();
							if (categoryProduct == null) {
								category.setProducts(new ArrayList<Product>());
								category.getProducts().add(product);
							} else {
								categoryProduct.add(product);
							}
						}
					}
				}
				homePage = new HomePageImpl();
				homePage.setHomePageCategories(categoriesForQuery);
			}
		} else {
			throw new GroceriesKartException("Categories are empty.");
		}
		return homePage;
	}


}
