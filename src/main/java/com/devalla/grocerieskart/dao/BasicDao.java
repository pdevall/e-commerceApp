package com.devalla.grocerieskart.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.devalla.grocerieskart.common.GroceriesKartException;
import com.devalla.grocerieskart.dto.Category;
import com.devalla.grocerieskart.dto.GlobalDiscount;
import com.devalla.grocerieskart.dto.State;
import com.devalla.grocerieskart.dto.SubCategory;
import com.devalla.grocerieskart.types.PaymentType;


public interface BasicDao {	
	
	JdbcTemplate getJdbcTemplate();	
	NamedParameterJdbcTemplate getNamedParameterJdbcTemplate();
	public long getCustomerSequence();
	public List<SubCategory> getSubCategoriesByCategory(Long categoryId) throws GroceriesKartException;
	public List<Category> getCategories() throws GroceriesKartException;
	public long getAddressSequence();
	public long getGlobalSequence();
	public long getOrderSequence();
	public long getCustomerOrderSequence();
	public GlobalDiscount getGlobalDiscounts() throws GroceriesKartException;
	public List<State> getStates() throws GroceriesKartException;
	public List<PaymentType> getPaymentTypes() throws GroceriesKartException;
	
}
