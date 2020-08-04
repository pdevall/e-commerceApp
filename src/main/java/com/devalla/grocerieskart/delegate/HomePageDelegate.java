package com.devalla.grocerieskart.delegate;

import com.devalla.grocerieskart.common.GroceriesKartException;
import com.devalla.grocerieskart.dto.HomePage;

public interface HomePageDelegate {
	
	HomePage getHomePageCategories(String localeString) throws GroceriesKartException ;
}
