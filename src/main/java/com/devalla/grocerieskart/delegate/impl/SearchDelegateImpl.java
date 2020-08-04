package com.devalla.grocerieskart.delegate.impl;

import java.util.List;

import javax.inject.Inject;

import com.devalla.grocerieskart.common.GroceriesKartException;
import com.devalla.grocerieskart.delegate.SearchDelegate;
import com.devalla.grocerieskart.dto.Product;
import com.devalla.grocerieskart.facade.SearchFacade;

public class SearchDelegateImpl implements SearchDelegate {
	
	@Inject
	private SearchFacade searchFacade;
	

	
	public List<Product> searchProducts(Long departmentId, String productName,
			Integer startNumber) throws GroceriesKartException {
		return searchFacade.searchProducts(departmentId, productName, startNumber);
	}


	
	public int searchProductsCount(Long departmentId, String productName)
			throws GroceriesKartException {
		return searchFacade.searchProductsCount(departmentId, productName);
	}

}
