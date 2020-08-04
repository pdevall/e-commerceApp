package com.devalla.grocerieskart.dto;

import java.io.Serializable;

public interface SubCategoryName extends Serializable {

	public Long getSubCategoryNameId();
	public void setSubCategoryNameId(Long subCategoryNameId);
	public String getSubCategoryName();
	public void setSubCategoryName(String subCategoryName);
	public String getSpringLabel();
	public void setSpringLabel(String springLabel);
}
