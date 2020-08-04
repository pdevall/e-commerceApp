package com.devalla.grocerieskart.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

public interface GlobalDiscount extends Serializable {
	
	public Long getGlobalDiscountId();
	public void setGlobalDiscountId(Long globalDiscountId);
	public BigDecimal getDiscountPercent();
	public void setDiscountPercent(BigDecimal discountPercent);
	public Calendar getDiscountStartDate();
	public void setDiscountStartDate(Calendar discountStartDate);
	public Calendar getDiscountEndDate();
	public void setDiscountEndDate(Calendar discountEndDate);

}
