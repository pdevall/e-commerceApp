package com.devalla.grocerieskart.delegate.impl;

import java.util.Locale;

import javax.inject.Inject;

import com.devalla.grocerieskart.common.GroceriesKartException;
import com.devalla.grocerieskart.common.GroceriesKartMessageReloadable;
import com.devalla.grocerieskart.delegate.ProductDisplayDelegate;
import com.devalla.grocerieskart.dto.Category;
import com.devalla.grocerieskart.dto.Product;
import com.devalla.grocerieskart.dto.SubCategory;
import com.devalla.grocerieskart.facade.ProductDisplayFacade;
import com.devalla.grocerieskart.util.GroceriesKartUtil;
import com.devalla.grocerieskart.web.common.WebConstants;

public class ProductDisplayDelegateImpl implements ProductDisplayDelegate {

	@Inject
	private ProductDisplayFacade productDisplayFacade;

	@Inject
	private GroceriesKartMessageReloadable messageSource;

	
	public Category getProductsForCategory(Long categoryId,
			Integer startNumber, String localeString, Integer limit)
			throws GroceriesKartException {
		Category category = productDisplayFacade.getProductsForCategory(
				categoryId, startNumber, limit);
		Locale locale = new Locale(localeString);
		if (category != null) {
			if (category.getProducts() != null
					&& category.getProducts().size() > 0) {
				for (Product product : category.getProducts()) {
					if (product.getSubCategory() != null
							&& product.getSubCategory().getSpringLabel() != null) {
						String messageText = messageSource
								.resolveCodeWithoutArguments(product
										.getSubCategory().getSpringLabel(),
										locale);
						if (messageText != null
								&& !messageText
										.equals(WebConstants.EMPTY_SPACE)) {
							product.setMessageTextExist(true);
							product.setSpringLabelValue(messageText);
						}
						GroceriesKartUtil.calculateDiscount(product);
					}
				}
			}
		}
		return category;
	}

	
	public SubCategory getProductsForSubCategory(Long subCategoryId,
			Long categoryId) throws GroceriesKartException {
		SubCategory subCategory = productDisplayFacade
				.getProductsForSubCategory(subCategoryId, categoryId);
		if (subCategory.getProducts() != null
				&& subCategory.getProducts().size() > 0) {
			for (Product product : subCategory.getProducts()) {
				GroceriesKartUtil.calculateDiscount(product);
			}
		}
		return subCategory;
	}

	
	public SubCategory getProductsForBrand(Long brandId, Long subCategoryId,
			Long categoryId) throws GroceriesKartException {		
		SubCategory subCategory = productDisplayFacade
		.getProductsForBrand(brandId, subCategoryId, categoryId);
		if (subCategory.getProducts() != null
				&& subCategory.getProducts().size() > 0) {
			for (Product product : subCategory.getProducts()) {
				GroceriesKartUtil.calculateDiscount(product);
			}
		}
		return subCategory;
	}

}
