package com.devalla.grocerieskart.dto.impl;

import com.devalla.grocerieskart.dto.Department;

public class DepartmentImpl implements Department {
	
	private Long departmentId;
	private String departmentName;
	private String departmentDescription;
	public Long getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getDepartmentDescription() {
		return departmentDescription;
	}
	public void setDepartmentDescription(String departmentDescription) {
		this.departmentDescription = departmentDescription;
	}

}
