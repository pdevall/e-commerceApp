package com.devalla.grocerieskart.dao;

import com.devalla.grocerieskart.common.GroceriesKartException;
import com.devalla.grocerieskart.dto.FrontendProduct;
import com.devalla.grocerieskart.dto.Product;

public interface ProductDetailsDao  extends BasicDao {

	public Product getProductDetailsForProductId(Long productId)
	throws GroceriesKartException;
	public FrontendProduct getFrontendProductDetailsForProductId(Long productId)
	throws GroceriesKartException;
}
