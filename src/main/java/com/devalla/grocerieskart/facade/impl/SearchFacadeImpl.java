package com.devalla.grocerieskart.facade.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.transaction.annotation.Transactional;

import com.devalla.grocerieskart.common.GroceriesKartException;
import com.devalla.grocerieskart.dao.SearchDao;
import com.devalla.grocerieskart.dto.Product;
import com.devalla.grocerieskart.facade.SearchFacade;

public class SearchFacadeImpl implements SearchFacade {
	
	@Inject
	private SearchDao searchDao;

	
	@Transactional(readOnly = true)
	public List<Product> searchProducts(Long departmentId, String productName,
			Integer startNumber) throws GroceriesKartException {
		return searchDao.searchProducts(departmentId, productName, startNumber);
	}

	
	public int searchProductsCount(Long departmentId, String productName)
			throws GroceriesKartException {
		return searchDao.searchProductsCount(departmentId, productName);
	}

}
