package com.devalla.grocerieskart.facade;

import java.util.List;

import com.devalla.grocerieskart.common.GroceriesKartException;
import com.devalla.grocerieskart.dto.FrontendProduct;

public interface GListFacade {

	public int insertGList(Long customerId, Long productId) throws GroceriesKartException;
	public void deleteGListByCustProdId(Long customerGListId) throws GroceriesKartException;
	public void deleteGListByIds(List<Long> customerGListIds) throws GroceriesKartException;
	public List<FrontendProduct> getGListProducts(Long customerId)
	throws GroceriesKartException;

}
