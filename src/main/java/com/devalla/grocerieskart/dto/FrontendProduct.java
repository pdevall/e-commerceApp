package com.devalla.grocerieskart.dto;

import java.math.BigDecimal;

public interface FrontendProduct extends Product {

	public Integer getFrontendQuantity();
	public void setFrontendQuantity(Integer frontendQuantity);
	public BigDecimal getFrontEndTotalCost();
	public Boolean getSelectedProduct();
	public void setSelectedProduct(Boolean selectedProduct);
	public Long getCustomerGListId();
	public void setCustomerGListId(Long customerGListId);
}
