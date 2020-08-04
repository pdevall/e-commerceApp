package com.devalla.grocerieskart.dto;

import java.io.Serializable;

public interface Department extends Serializable {
	
	public Long getDepartmentId();
	public void setDepartmentId(Long departmentId);
	public String getDepartmentName();
	public void setDepartmentName(String departmentName);
	public String getDepartmentDescription();
	public void setDepartmentDescription(String departmentDescription);

}
