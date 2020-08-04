package com.devalla.grocerieskart.dao;

import com.devalla.grocerieskart.common.GroceriesKartException;
import com.devalla.grocerieskart.dto.Category;
import com.devalla.grocerieskart.dto.SubCategory;

public interface ProductDisplayDao extends BasicDao {

	public Category getCategoryById(Long categoryId) throws GroceriesKartException;
	public Category getProductsForCategory(Long categoryId, Integer startNumber, Integer limit)
	throws GroceriesKartException;
	public SubCategory getProductsForSubCategory(Long subCategoryId, Long categoryId)
	throws GroceriesKartException;
	public SubCategory getProductsForBrand(Long brandId, Long subCategoryId, Long categoryId)
	throws GroceriesKartException;
}
