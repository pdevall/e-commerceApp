package com.devalla.grocerieskart.delegate.impl;

import java.util.List;

import javax.inject.Inject;

import com.devalla.grocerieskart.common.GroceriesKartException;
import com.devalla.grocerieskart.delegate.GListDelegate;
import com.devalla.grocerieskart.dto.FrontendProduct;
import com.devalla.grocerieskart.facade.GListFacade;

public class GListDelegateImpl implements GListDelegate {
	
	@Inject
	private GListFacade glistFacade;

	
	public int insertGList(Long customerId, Long productId)
			throws GroceriesKartException {
		return glistFacade.insertGList(customerId, productId);
	}

	
	public void deleteGListByCustProdId(Long customerGListId)
			throws GroceriesKartException {
		glistFacade.deleteGListByCustProdId(customerGListId);
	}

	
	public void deleteGListByIds(List<Long> customerGListIds)
			throws GroceriesKartException {
		glistFacade.deleteGListByIds(customerGListIds);
	}

	
	public List<FrontendProduct> getGListProducts(Long customerId)
			throws GroceriesKartException {
		return glistFacade.getGListProducts(customerId);
	}

}
