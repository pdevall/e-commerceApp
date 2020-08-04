package com.devalla.grocerieskart.facade.impl;

import javax.inject.Inject;

import org.springframework.transaction.annotation.Transactional;

import com.devalla.grocerieskart.common.GroceriesKartException;
import com.devalla.grocerieskart.dao.ProductDetailsDao;
import com.devalla.grocerieskart.dto.FrontendProduct;
import com.devalla.grocerieskart.dto.Product;
import com.devalla.grocerieskart.facade.ProductDetailsFacade;

public class ProductDetailsFacadeImpl implements ProductDetailsFacade {
	
	@Inject
	private ProductDetailsDao productDetailsDao;

	
	@Transactional (readOnly = true)
	public Product getProductDetailsForProductId(Long productId)
			throws GroceriesKartException {
		return productDetailsDao.getProductDetailsForProductId(productId);
	}

	
	@Transactional (readOnly = true)
	public FrontendProduct getFrontendProductDetailsForProductId(Long productId)
			throws GroceriesKartException {
		return productDetailsDao.getFrontendProductDetailsForProductId(productId);
	}

}
