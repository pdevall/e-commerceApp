package com.devalla.grocerieskart.facade.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import org.springframework.transaction.annotation.Transactional;

import com.devalla.grocerieskart.common.GroceriesKartException;
import com.devalla.grocerieskart.dao.HomePageDao;
import com.devalla.grocerieskart.dto.Category;
import com.devalla.grocerieskart.dto.Product;
import com.devalla.grocerieskart.facade.HomePageFacade;
import com.devalla.grocerieskart.web.common.WebConstants;

public class HomePageFacadeImpl implements HomePageFacade {
	
	@Inject
	private HomePageDao homePageDao;

	public List<Category> getCategories() throws GroceriesKartException {
		return homePageDao.getCategories();
	}

	
	@Transactional (readOnly= true)
	public List<Product> getHomePageCategories(List<Category> categories) throws GroceriesKartException {	
		List<Product> products = new ArrayList<Product>();
		if (categories != null && categories.size() > 0) {
			for (Category category: categories)  {
				List<Product> productsByCategory = homePageDao.getHomePageCategories(category.getId());
				Collections.shuffle(productsByCategory);
				if (productsByCategory.size() > WebConstants.HOME_PAGE_ROWS.intValue()) {
					for(int i = 0; i < WebConstants.HOME_PAGE_ROWS; i++) {
						products.add((Product)productsByCategory.get(i));
					}
				} else {
					products.addAll(productsByCategory);
				}
			}
		}
	 
		return products;
	}

}
