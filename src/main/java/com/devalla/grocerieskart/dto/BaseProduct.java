package com.devalla.grocerieskart.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.devalla.grocerieskart.types.ProductStatusType;

public interface BaseProduct extends Serializable {
	
	public Long getProductId();
	public void setProductId(Long productId);
	public String getProductName();
	public void setProductName(String productName);
	public String getProductDescription();
	public void setProductDescription(String productDescription);
	public BigDecimal getProductCost();
	public void setProductCost(BigDecimal productCost);
	public String getSmallImageUrl();
	public void setSmallImageUrl(String smallImageUrl);
	public String getBigImageUrl();
	public void setBigImageUrl(String bigImageUrl);
	public ProductStatusType getProductStatusType();
	public void setProductStatusType(ProductStatusType productStatusType);
	public SubCategory getSubCategory();
	public void setSubCategory(SubCategory subCategory);
	public Category getCategory();
	public void setCategory(Category category);
	public Boolean getGroceryProduct();
	public void setGroceryProduct(Boolean groceryProduct);
	public Boolean getMessageTextExist();
	public void setMessageTextExist(Boolean messageTextExist);
	public ProductDiscount getProductDiscount();
	public void setProductDiscount(ProductDiscount productDiscount);
	public BigDecimal getProductCostAfterDiscount();
	public void setProductCostAfterDiscount(BigDecimal productCostAfterDiscount);
	public Integer getQuantity();
	public void setQuantity(Integer quantity);
	

}
