package com.devalla.grocerieskart.dao;

import java.util.List;

import com.devalla.grocerieskart.common.GroceriesKartException;
import com.devalla.grocerieskart.dto.Product;

public interface HomePageDao extends BasicDao {
	
	List<Product> getHomePageCategories(Long categoryId) throws GroceriesKartException ;
}
