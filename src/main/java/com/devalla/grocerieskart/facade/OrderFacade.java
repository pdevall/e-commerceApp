package com.devalla.grocerieskart.facade;

import com.devalla.grocerieskart.common.GroceriesKartException;
import com.devalla.grocerieskart.dto.UserAccountInfo;
import com.devalla.grocerieskart.web.form.ShoppingCartForm;

public interface OrderFacade {

	public int[] persistOrder(UserAccountInfo userAccountInfo, ShoppingCartForm shoppingCartForm) throws GroceriesKartException;
}
