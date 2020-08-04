package com.devalla.grocerieskart.dto;

public interface SubCategory extends BaseCategory {
	public SubCategoryName getSubCategoryName();
	public void setSubCategoryName(SubCategoryName subCategoryName);
	public Category getCategory();
	public void setCategory(Category category);
}
