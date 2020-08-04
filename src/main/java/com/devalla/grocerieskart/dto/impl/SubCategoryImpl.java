package com.devalla.grocerieskart.dto.impl;

import com.devalla.grocerieskart.dto.Category;
import com.devalla.grocerieskart.dto.SubCategory;
import com.devalla.grocerieskart.dto.SubCategoryName;

public class SubCategoryImpl extends BaseCategoryImpl implements SubCategory {
	private SubCategoryName subCategoryName;
	private Category category;
	public SubCategoryName getSubCategoryName() {
		return subCategoryName;
	}
	public void setSubCategoryName(SubCategoryName subCategoryName) {
		this.subCategoryName = subCategoryName;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
}
