package com.devalla.grocerieskart.delegate;

import com.devalla.grocerieskart.common.GroceriesKartException;
import com.devalla.grocerieskart.dto.Category;
import com.devalla.grocerieskart.dto.SubCategory;

public interface ProductDisplayDelegate {
	
	public Category getProductsForCategory(Long categoryId, Integer startNumber, String localeString, Integer limit)
	throws GroceriesKartException;
	
	public SubCategory getProductsForSubCategory(Long subCategoryId, Long categoryId)
	throws GroceriesKartException;
	
	public SubCategory getProductsForBrand(Long brandId, Long subCategoryId, Long categoryId)
	throws GroceriesKartException;

}
