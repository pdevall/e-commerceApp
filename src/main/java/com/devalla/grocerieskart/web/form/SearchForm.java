package com.devalla.grocerieskart.web.form;

import java.util.List;

import com.devalla.grocerieskart.dto.Product;

public class SearchForm extends BaseForm{
	
	private List<Product> products;

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	

}
