package com.devalla.grocerieskart.dto.impl;

import java.math.BigDecimal;

import com.devalla.grocerieskart.dto.Product;

public class ProductImpl extends BaseProductImpl implements Product {
	
	private String springLabelValue;
    private BigDecimal discountCost;
	
	public String getSpringLabelValue() {
		return springLabelValue;
	}

	public void setSpringLabelValue(String springLabelValue) {
		this.springLabelValue = springLabelValue;
	}

	public BigDecimal getDiscountCost() {
		return discountCost;
	}

	public void setDiscountCost(BigDecimal discountCost) {
		this.discountCost = discountCost;
	}
	
	

}
