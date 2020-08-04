package com.devalla.grocerieskart.dao;

import com.devalla.grocerieskart.common.GroceriesKartException;
import com.devalla.grocerieskart.dto.UserAccountInfo;
import com.devalla.grocerieskart.web.form.ShoppingCartForm;

public interface OrderDao extends BasicDao {
	
	public int[] persistOrder(UserAccountInfo userAccountInfo, ShoppingCartForm shoppingCartForm) throws GroceriesKartException;

}
