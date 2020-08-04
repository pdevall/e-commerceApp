package com.devalla.grocerieskart.dto.impl;

import java.math.BigDecimal;
import java.util.Calendar;

import com.devalla.grocerieskart.dto.ProductDiscount;

public class ProductDiscountImpl implements ProductDiscount {
	
	private Long productDiscountId;
	private Long productId;
	private BigDecimal discountPercent;
	private Long productOffer;
	private Calendar discountStartDate;
	private Calendar discountEndDate;
	private Boolean allOffers;
	private String productOfferName;
	public Long getProductDiscountId() {
		return productDiscountId;
	}
	public void setProductDiscountId(Long productDiscountId) {
		this.productDiscountId = productDiscountId;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public BigDecimal getDiscountPercent() {
		return discountPercent;
	}
	public void setDiscountPercent(BigDecimal discountPercent) {
		this.discountPercent = discountPercent;
	}
	public Long getProductOffer() {
		return productOffer;
	}
	public void setProductOffer(Long productOffer) {
		this.productOffer = productOffer;
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
	public Boolean getAllOffers() {
		return allOffers;
	}
	public void setAllOffers(Boolean allOffers) {
		this.allOffers = allOffers;
	}
	public String getProductOfferName() {
		return productOfferName;
	}
	public void setProductOfferName(String productOfferName) {
		this.productOfferName = productOfferName;
	}
	
}
