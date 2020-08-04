package com.devalla.grocerieskart.dto;

import java.util.List;

import com.devalla.grocerieskart.types.StatusType;

public interface BaseCategory extends BaseDto {
	
	public Long getId() ;
	public void setId(Long id);
	public String getName();
	public void setName(String name);
	public StatusType getStatusType();
	public void setStatusType(StatusType statusType);
	public String getSpringLabel();
	public void setSpringLabel(String springLabel);
	public List<Product> getProducts();
	public void setProducts(List<Product> products);

}
