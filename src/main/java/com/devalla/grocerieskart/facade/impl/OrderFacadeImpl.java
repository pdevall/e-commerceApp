package com.devalla.grocerieskart.facade.impl;

import javax.inject.Inject;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.devalla.grocerieskart.common.GroceriesKartException;
import com.devalla.grocerieskart.dao.OrderDao;
import com.devalla.grocerieskart.dto.UserAccountInfo;
import com.devalla.grocerieskart.facade.OrderFacade;
import com.devalla.grocerieskart.web.form.ShoppingCartForm;

public class OrderFacadeImpl implements OrderFacade {
	
	@Inject
	private OrderDao orderDao;
	
	
	@Transactional(readOnly = false, propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public int[] persistOrder(UserAccountInfo userAccountInfo, ShoppingCartForm shoppingCartForm) throws GroceriesKartException {
		return orderDao.persistOrder(userAccountInfo, shoppingCartForm);
	}

}
