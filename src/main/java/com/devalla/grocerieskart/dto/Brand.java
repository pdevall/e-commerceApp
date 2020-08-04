package com.devalla.grocerieskart.dto;

import java.io.Serializable;

import com.devalla.grocerieskart.types.StatusType;

public interface Brand extends Serializable {
	
	public Long getBrandId();
	public void setBrandId(Long brandId);
	public String getBrandName();
	public void setBrandName(String brandName);
	public StatusType getStatusType();
	public void setStatusType(StatusType statusType);
	public SubCategory getSubCategory();
	public void setSubCategory(SubCategory subCategory);
	public String getSpringLabel();
	public void setSpringLabel(String springLabel);

}
