package com.devalla.grocerieskart.dto;

import java.math.BigDecimal;

public interface Product extends BaseProduct {

	public String getSpringLabelValue();
	public void setSpringLabelValue(String springLabelValue);
	public BigDecimal getDiscountCost();
	public void setDiscountCost(BigDecimal discountCost);
}
