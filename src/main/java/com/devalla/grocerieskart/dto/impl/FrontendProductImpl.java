package com.devalla.grocerieskart.dto.impl;

import java.math.BigDecimal;

import com.devalla.grocerieskart.dto.FrontendProduct;

public class FrontendProductImpl extends ProductImpl implements FrontendProduct {
	
	private Integer frontendQuantity;
	private Boolean selectedProduct;
    private Long customerGListId;
	
	public Integer getFrontendQuantity() {
		return frontendQuantity;
	}

	public void setFrontendQuantity(Integer frontendQuantity) {
		this.frontendQuantity = frontendQuantity;
	}

	public BigDecimal getFrontEndTotalCost() {
		BigDecimal frontEndTotalCost = new BigDecimal(0);
		if (frontendQuantity != null) {
			if (getProductCostAfterDiscount() != null) {
				frontEndTotalCost =	getProductCostAfterDiscount().multiply(new BigDecimal(frontendQuantity)).setScale(2);
			} else {
				frontEndTotalCost =	getProductCost().multiply(new BigDecimal(frontendQuantity)).setScale(2);
			}
		}
		
		return frontEndTotalCost;
	}


	public Boolean getSelectedProduct() {
		return selectedProduct;
	}

	public void setSelectedProduct(Boolean selectedProduct) {
		this.selectedProduct = selectedProduct;
	}

	public Long getCustomerGListId() {
		return customerGListId;
	}

	public void setCustomerGListId(Long customerGListId) {
		this.customerGListId = customerGListId;
	}

}
