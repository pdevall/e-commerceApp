package com.devalla.grocerieskart.dto.impl;

import java.math.BigDecimal;
import java.util.Calendar;

import com.devalla.grocerieskart.dto.GlobalDiscount;

public class GlobalDiscountImpl implements GlobalDiscount {
	
	private Long globalDiscountId;
	private BigDecimal discountPercent;
	private Calendar discountStartDate;
	private Calendar discountEndDate;
	public Long getGlobalDiscountId() {
		return globalDiscountId;
	}
	public void setGlobalDiscountId(Long globalDiscountId) {
		this.globalDiscountId = globalDiscountId;
	}
	public BigDecimal getDiscountPercent() {
		return discountPercent;
	}
	public void setDiscountPercent(BigDecimal discountPercent) {
		this.discountPercent = discountPercent;
	}
	public Calendar getDiscountStartDate() {
		return discountStartDate;
	}
	public void setDiscountStartDate(Calendar discountStartDate) {
		this.discountStartDate = discountStartDate;
	}
	public Calendar getDiscountEndDate() {
		return discountEndDate;
	}
	public void setDiscountEndDate(Calendar discountEndDate) {
		this.discountEndDate = discountEndDate;
	}
	
	

}


