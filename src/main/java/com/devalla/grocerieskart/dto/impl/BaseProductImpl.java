package com.devalla.grocerieskart.dto.impl;

import java.math.BigDecimal;

import com.devalla.grocerieskart.dto.BaseProduct;
import com.devalla.grocerieskart.dto.Category;
import com.devalla.grocerieskart.dto.ProductDiscount;
import com.devalla.grocerieskart.dto.SubCategory;
import com.devalla.grocerieskart.types.ProductStatusType;

public abstract class BaseProductImpl implements BaseProduct {
	
	private Long productId;
	private String productName;
	private String productDescription;
	private BigDecimal productCost;
	private String smallImageUrl;
	private String bigImageUrl;
	private ProductStatusType productStatusType;
	private SubCategory subCategory;
	private Category category;
	private Boolean groceryProduct = false;
	private Boolean messageTextExist;
	private ProductDiscount productDiscount;
	private BigDecimal productCostAfterDiscount;
	private Integer quantity;
	
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public BigDecimal getProductCost() {
		return productCost;
	}
	public void setProductCost(BigDecimal productCost) {
		this.productCost = productCost;
	}
	public String getSmallImageUrl() {
		return smallImageUrl;
	}
	public void setSmallImageUrl(String smallImageUrl) {
		this.smallImageUrl = smallImageUrl;
	}
	public String getBigImageUrl() {
		return bigImageUrl;
	}
	public void setBigImageUrl(String bigImageUrl) {
		this.bigImageUrl = bigImageUrl;
	}
	public ProductStatusType getProductStatusType() {
		return productStatusType;
	}
	public void setProductStatusType(ProductStatusType productStatusType) {
		this.productStatusType = productStatusType;
	}
	public SubCategory getSubCategory() {
		return subCategory;
	}
	public void setSubCategory(SubCategory subCategory) {
		this.subCategory = subCategory;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Boolean getGroceryProduct() {
		return groceryProduct;
	}
	public void setGroceryProduct(Boolean groceryProduct) {
		this.groceryProduct = groceryProduct;
	}
	public Boolean getMessageTextExist() {
		return messageTextExist;
	}
	public void setMessageTextExist(Boolean messageTextExist) {
		this.messageTextExist = messageTextExist;
	}
	public ProductDiscount getProductDiscount() {
		return productDiscount;
	}
	public void setProductDiscount(ProductDiscount productDiscount) {
		this.productDiscount = productDiscount;
	}
	public BigDecimal getProductCostAfterDiscount() {
		return productCostAfterDiscount;
	}
	public void setProductCostAfterDiscount(BigDecimal productCostAfterDiscount) {
		this.productCostAfterDiscount = productCostAfterDiscount;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}
