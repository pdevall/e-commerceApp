package com.devalla.grocerieskart.dto.impl;

import com.devalla.grocerieskart.dto.SubCategoryName;

public class SubCategoryNameImpl implements SubCategoryName {
	private Long subCategoryNameId;
	private String subCategoryName;
	private String springLabel;
	public Long getSubCategoryNameId() {
		return subCategoryNameId;
	}
	public void setSubCategoryNameId(Long subCategoryNameId) {
		this.subCategoryNameId = subCategoryNameId;
	}
	public String getSubCategoryName() {
		return subCategoryName;
	}
	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}
	public String getSpringLabel() {
		return springLabel;
	}
	public void setSpringLabel(String springLabel) {
		this.springLabel = springLabel;
	}
	
	
}
