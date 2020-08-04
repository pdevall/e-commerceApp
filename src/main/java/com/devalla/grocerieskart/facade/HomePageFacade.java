package com.devalla.grocerieskart.facade;

import java.util.List;

import com.devalla.grocerieskart.common.GroceriesKartException;
import com.devalla.grocerieskart.dto.Category;
import com.devalla.grocerieskart.dto.Product;

public interface HomePageFacade {

	public List<Product> getHomePageCategories(List<Category> categories) throws GroceriesKartException;
	public List<Category> getCategories() throws GroceriesKartException;
}
