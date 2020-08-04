package com.devalla.grocerieskart.dto.impl;

import com.devalla.grocerieskart.dto.Brand;
import com.devalla.grocerieskart.dto.SubCategory;
import com.devalla.grocerieskart.types.StatusType;

public class BrandImpl implements Brand {
	
	private Long brandId;
	private String brandName;
	private StatusType statusType;
	private SubCategory subCategory;
	private String springLabel;
	
	public Long getBrandId() {
		return brandId;
	}
	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public StatusType getStatusType() {
		return statusType;
	}
	public void setStatusType(StatusType statusType) {
		this.statusType = statusType;
	}
	public SubCategory getSubCategory() {
		return subCategory;
	}
	public void setSubCategory(SubCategory subCategory) {
		this.subCategory = subCategory;
	}
	public String getSpringLabel() {
		return springLabel;
	}
	public void setSpringLabel(String springLabel) {
		this.springLabel = springLabel;
	}
	
	
}
