package com.devalla.grocerieskart.facade.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.devalla.grocerieskart.common.GroceriesKartException;
import com.devalla.grocerieskart.dao.GListDao;
import com.devalla.grocerieskart.dto.FrontendProduct;
import com.devalla.grocerieskart.facade.GListFacade;

public class GListFacadeImpl implements GListFacade {
	
	@Inject
	private GListDao glistDao;

	
	@Transactional(readOnly= false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public int insertGList(Long customerId, Long productId)
			throws GroceriesKartException {
		return glistDao.insertGList(customerId, productId);
	}

	
	@Transactional(readOnly= false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteGListByCustProdId(Long customerGListId)
			throws GroceriesKartException {
		glistDao.deleteGListByCustProdId(customerGListId);
	}

	
	@Transactional(readOnly= false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteGListByIds(List<Long> customerGListIds)
			throws GroceriesKartException {
		glistDao.deleteGListByIds(customerGListIds);
	}

	
	@Transactional(readOnly= true)
	public List<FrontendProduct> getGListProducts(Long customerId)
			throws GroceriesKartException {
		return glistDao.getGListProducts(customerId);
	}

}
