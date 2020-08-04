package com.devalla.grocerieskart.dao;

import java.util.List;

import com.devalla.grocerieskart.common.GroceriesKartException;
import com.devalla.grocerieskart.dto.Product;


public interface SearchDao extends BasicDao{
	public List<Product> searchProducts(Long departmentId, String productName, Integer startNumber) throws GroceriesKartException;
	public int searchProductsCount(Long departmentId, String productName) throws GroceriesKartException;
}
