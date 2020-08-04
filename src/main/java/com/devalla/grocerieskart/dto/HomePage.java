package com.devalla.grocerieskart.dto;

import java.io.Serializable;
import java.util.List;

public interface HomePage extends Serializable {
	public List<Category> getHomePageCategories();
	public void setHomePageCategories(List<Category> homePageCategories);
	
}
