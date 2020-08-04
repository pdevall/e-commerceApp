package com.devalla.grocerieskart.delegate.impl;

import javax.inject.Inject;

import com.devalla.grocerieskart.common.GroceriesKartException;
import com.devalla.grocerieskart.delegate.OrderDelegate;
import com.devalla.grocerieskart.dto.UserAccountInfo;
import com.devalla.grocerieskart.facade.OrderFacade;
import com.devalla.grocerieskart.web.form.ShoppingCartForm;

public class OrderDelegateImpl implements OrderDelegate {
	
	@Inject
	private OrderFacade orderFacade;


	
	public int[] persistOrder(UserAccountInfo userAccountInfo,
			ShoppingCartForm shoppingCartForm) throws GroceriesKartException {
		int[] values = orderFacade.persistOrder(userAccountInfo, shoppingCartForm);
		return values;
	}

}
