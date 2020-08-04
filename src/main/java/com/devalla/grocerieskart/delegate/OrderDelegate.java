package com.devalla.grocerieskart.delegate;

import com.devalla.grocerieskart.common.GroceriesKartException;
import com.devalla.grocerieskart.dto.UserAccountInfo;
import com.devalla.grocerieskart.web.form.ShoppingCartForm;

public interface OrderDelegate {
	public int[] persistOrder(UserAccountInfo userAccountInfo, ShoppingCartForm shoppingCartForm) throws GroceriesKartException;

}
