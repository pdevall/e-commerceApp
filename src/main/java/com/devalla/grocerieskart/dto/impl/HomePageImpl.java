package com.devalla.grocerieskart.dto.impl;

import java.util.List;

import com.devalla.grocerieskart.dto.Category;
import com.devalla.grocerieskart.dto.HomePage;

public class HomePageImpl implements HomePage {
	
	private List<Category> homePageCategories;

	public List<Category> getHomePageCategories() {
		return homePageCategories;
	}

	public void setHomePageCategories(List<Category> homePageCategories) {
		this.homePageCategories = homePageCategories;
	}
	
	
}
