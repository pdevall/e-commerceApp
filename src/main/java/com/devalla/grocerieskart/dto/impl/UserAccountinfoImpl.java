package com.devalla.grocerieskart.dto.impl;

import com.devalla.grocerieskart.dto.UserAccountInfo;
import com.devalla.grocerieskart.web.common.WebConstants;

public class UserAccountinfoImpl implements UserAccountInfo {
	
	private Long customerId;
	private String customerFirstname;
	private String customerLastname;
	private String customerEmail;
	
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public String getCustomerFirstname() {
		return customerFirstname;
	}
	public void setCustomerFirstname(String customerFirstname) {
		this.customerFirstname = customerFirstname;
	}
	public String getCustomerLastname() {
		return customerLastname;
	}
	public void setCustomerLastname(String customerLastname) {
		this.customerLastname = customerLastname;
	}
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	public String getMaskEmail() {
		if (customerEmail != null && !customerEmail.equals(WebConstants.EMPTY_SPACE)) {
			return customerEmail.substring(0, 5) + "...@...";
		}
		return null;
	}

}
