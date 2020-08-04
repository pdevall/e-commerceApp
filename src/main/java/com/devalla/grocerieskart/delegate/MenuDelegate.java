package com.devalla.grocerieskart.delegate;

import java.util.List;

import com.devalla.grocerieskart.common.GroceriesKartException;
import com.devalla.grocerieskart.dto.Brand;
import com.devalla.grocerieskart.dto.Category;
import com.devalla.grocerieskart.dto.GlobalDiscount;
import com.devalla.grocerieskart.dto.State;
import com.devalla.grocerieskart.dto.SubCategory;
import com.devalla.grocerieskart.types.PaymentType;

public interface MenuDelegate {

	public List<Brand> getBrandsBySubCategoryId(Long subCategoryId) throws GroceriesKartException;
	public List<SubCategory> getSubCategoriesByCategoryId(Long categoryId)
	throws GroceriesKartException;
	public List<Category> getCategories() throws GroceriesKartException;
	public GlobalDiscount getGlobalDiscounts() throws GroceriesKartException;
	public List<State> getStates() throws GroceriesKartException;
	public List<PaymentType> getPaymentTypes() throws GroceriesKartException ;
	
}
