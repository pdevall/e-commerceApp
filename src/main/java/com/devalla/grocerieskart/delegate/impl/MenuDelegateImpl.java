package com.devalla.grocerieskart.delegate.impl;

import java.util.List;

import javax.inject.Inject;

import com.devalla.grocerieskart.common.GroceriesKartException;
import com.devalla.grocerieskart.delegate.MenuDelegate;
import com.devalla.grocerieskart.dto.Brand;
import com.devalla.grocerieskart.dto.Category;
import com.devalla.grocerieskart.dto.GlobalDiscount;
import com.devalla.grocerieskart.dto.State;
import com.devalla.grocerieskart.dto.SubCategory;
import com.devalla.grocerieskart.facade.MenuFacade;
import com.devalla.grocerieskart.types.PaymentType;

public class MenuDelegateImpl implements MenuDelegate {
	
	@Inject
	private MenuFacade menuFacade;

	
	public List<Brand> getBrandsBySubCategoryId(Long subCategoryId)
			throws GroceriesKartException {

		return menuFacade.getBrandsBySubCategoryId(subCategoryId);
	}
	
	
	public List<SubCategory> getSubCategoriesByCategoryId(Long categoryId)
			throws GroceriesKartException {

		return menuFacade.getSubCategoriesByCategoryId(categoryId);
	}

	
	public List<Category> getCategories() throws GroceriesKartException {
		return menuFacade.getCategories();
	}

	
	public GlobalDiscount getGlobalDiscounts() throws GroceriesKartException {
		return menuFacade.getGlobalDiscounts();
	}

	
	public List<State> getStates() throws GroceriesKartException {
		return menuFacade.getStates();
	}
	
	
	public List<PaymentType> getPaymentTypes() throws GroceriesKartException {
		return menuFacade.getPaymentTypes();
	}

}
