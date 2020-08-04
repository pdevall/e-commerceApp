package com.devalla.grocerieskart.facade.impl;

import javax.inject.Inject;

import org.springframework.transaction.annotation.Transactional;

import com.devalla.grocerieskart.common.GroceriesKartException;
import com.devalla.grocerieskart.dao.ProductDisplayDao;
import com.devalla.grocerieskart.dto.Category;
import com.devalla.grocerieskart.dto.SubCategory;
import com.devalla.grocerieskart.facade.ProductDisplayFacade;

public class ProductDisplayFacadeImpl implements ProductDisplayFacade {
	
	@Inject
	private ProductDisplayDao productDisplayDao;

	
	@Transactional (readOnly= true)
	public Category getProductsForCategory(Long categoryId,
			Integer startNumber, Integer limit) throws GroceriesKartException {
		Category category = productDisplayDao.getProductsForCategory(categoryId, startNumber, limit);
		return category;
	}

	
	@Transactional (readOnly= true)
	public SubCategory getProductsForSubCategory(Long subCategoryId,
			Long categoryId) throws GroceriesKartException {
		return productDisplayDao.getProductsForSubCategory(subCategoryId, categoryId);
	}

	
	@Transactional (readOnly= true)
	public SubCategory getProductsForBrand(Long brandId, Long subCategoryId,
			Long categoryId) throws GroceriesKartException {
		return productDisplayDao.getProductsForBrand(brandId, subCategoryId, categoryId);
	}
	
	

}
