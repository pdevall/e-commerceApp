package com.devalla.grocerieskart.dto.impl;

import java.util.List;

import com.devalla.grocerieskart.dto.Category;
import com.devalla.grocerieskart.dto.Department;
import com.devalla.grocerieskart.dto.SubCategory;

public class CategoryImpl extends BaseCategoryImpl implements Category {
	
	private Department department;
	private List<SubCategory> subCategories;
	private Integer numOfProductsByCategory;
	private int startNumber;
	
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public List<SubCategory> getSubCategories() {
		return subCategories;
	}
	public void setSubCategories(List<SubCategory> subCategories) {
		this.subCategories = subCategories;
	}
	public Integer getNumOfProductsByCategory() {
		return numOfProductsByCategory;
	}
	public void setNumOfProductsByCategory(Integer numOfProductsByCategory) {
		this.numOfProductsByCategory = numOfProductsByCategory;
	}
	
	public int getProductLength() {		
		if (getProducts() != null) {
			return getProducts().size();
		}		
		return 0;
	}
	public int getStartNumber() {
		return startNumber;
	}
	public void setStartNumber(int startNumber) {
		this.startNumber = startNumber;
	}
	
}
