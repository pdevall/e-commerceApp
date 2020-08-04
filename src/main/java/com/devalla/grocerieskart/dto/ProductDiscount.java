package com.devalla.grocerieskart.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

public interface ProductDiscount extends Serializable {
	public Long getProductDiscountId();
	public void setProductDiscountId(Long productDiscountId);
	public Long getProductId();
	public void setProductId(Long productId);
	public BigDecimal getDiscountPercent();
	public void setDiscountPercent(BigDecimal discountPercent);
	public Long getProductOffer();
	public void setProductOffer(Long productOffer);
	public Calendar getDiscountStartDate();
	public void setDiscountStartDate(Calendar discountStartDate);
	public Calendar getDiscountEndDate();
	public void setDiscountEndDate(Calendar discountEndDate);
	public Boolean getAllOffers();
	public void setAllOffers(Boolean allOffers);
	public String getProductOfferName();
	public void setProductOfferName(String productOfferName);
}
