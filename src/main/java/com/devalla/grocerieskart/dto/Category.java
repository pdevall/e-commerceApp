package com.devalla.grocerieskart.dto;

import java.util.List;

public interface Category  extends BaseCategory {

	public Department getDepartment();
	public void setDepartment(Department department);
	public List<SubCategory> getSubCategories();
	public void setSubCategories(List<SubCategory> subCategories);
	public Integer getNumOfProductsByCategory();
	public void setNumOfProductsByCategory(Integer numOfProductsByCategory);
	
	public int getProductLength();
	public int getStartNumber();
	public void setStartNumber(int startNumber);
}
