package com.devalla.grocerieskart.facade.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.transaction.annotation.Transactional;

import com.devalla.grocerieskart.common.GroceriesKartException;
import com.devalla.grocerieskart.dao.MenuDao;
import com.devalla.grocerieskart.dto.Brand;
import com.devalla.grocerieskart.dto.Category;
import com.devalla.grocerieskart.dto.GlobalDiscount;
import com.devalla.grocerieskart.dto.State;
import com.devalla.grocerieskart.dto.SubCategory;
import com.devalla.grocerieskart.facade.MenuFacade;
import com.devalla.grocerieskart.types.PaymentType;

public class MenuFacadeImpl implements MenuFacade {
	
	@Inject
	private MenuDao menuDao;

	
	@Transactional(readOnly= true)
	public List<Brand> getBrandsBySubCategoryId(Long subCategoryId)
			throws GroceriesKartException {
		return menuDao.getBrandsBySubCategoryId(subCategoryId);
	}

	
	@Transactional(readOnly= true)
	public List<SubCategory> getSubCategoriesByCategoryId(Long categoryId)
			throws GroceriesKartException {
		return menuDao.getSubCategoriesByCategory(categoryId);
	}

	
	@Transactional(readOnly= true)
	public List<Category> getCategories() throws GroceriesKartException {
		return menuDao.getCategories();
	}

	
	@Transactional(readOnly= true)
	public GlobalDiscount getGlobalDiscounts() throws GroceriesKartException {
		return menuDao.getGlobalDiscounts();
	}

	
	@Transactional(readOnly= true)
	public List<State> getStates() throws GroceriesKartException {
		return menuDao.getStates();
	}
	
	
	@Transactional(readOnly= true)
	public List<PaymentType> getPaymentTypes() throws GroceriesKartException {
		return menuDao.getPaymentTypes();
	}

}
