package com.devalla.grocerieskart.dto.impl;

import java.util.List;

import com.devalla.grocerieskart.dto.BaseCategory;
import com.devalla.grocerieskart.dto.Product;
import com.devalla.grocerieskart.types.StatusType;

public abstract class BaseCategoryImpl extends BaseDtoImpl implements BaseCategory {
	private Long Id;
	private String name;
	private StatusType statusType;
	private String springLabel;
	private List<Product> products;
	
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public StatusType getStatusType() {
		return statusType;
	}
	public void setStatusType(StatusType statusType) {
		this.statusType = statusType;
	}
	public String getSpringLabel() {
		return springLabel;
	}
	public void setSpringLabel(String springLabel) {
		this.springLabel = springLabel;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}	
	
}
