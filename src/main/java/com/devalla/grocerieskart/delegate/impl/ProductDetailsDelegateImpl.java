package com.devalla.grocerieskart.delegate.impl;

import javax.inject.Inject;

import com.devalla.grocerieskart.common.GroceriesKartException;
import com.devalla.grocerieskart.delegate.ProductDetailsDelegate;
import com.devalla.grocerieskart.dto.FrontendProduct;
import com.devalla.grocerieskart.dto.Product;
import com.devalla.grocerieskart.facade.ProductDetailsFacade;
import com.devalla.grocerieskart.util.GroceriesKartUtil;

public class ProductDetailsDelegateImpl implements ProductDetailsDelegate {
	
	@Inject
	private ProductDetailsFacade productDetailsFacade;

	
	public Product getProductDetailsForProductId(Long productId)
			throws GroceriesKartException {
		
		Product product = productDetailsFacade.getProductDetailsForProductId(productId);
		if (product != null) {
			GroceriesKartUtil.calculateDiscount(product);
		}		
		return product;
	}

	
	public FrontendProduct getFrontendProductDetailsForProductId(Long productId)
			throws GroceriesKartException {
		return productDetailsFacade.getFrontendProductDetailsForProductId(productId);
	}

}
