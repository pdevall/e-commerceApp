package com.devalla.grocerieskart.types.impl;

import com.devalla.grocerieskart.types.ProductStatusType;

public class ProductStatusTypeImpl implements ProductStatusType {
	
	private Long statusTypeId;
	private String status;
	public Long getStatusTypeId() {
		return statusTypeId;
	}
	public void setStatusTypeId(Long statusTypeId) {
		this.statusTypeId = statusTypeId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
