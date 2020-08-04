package com.devalla.grocerieskart.dto;

import java.io.Serializable;

public interface UserAccountInfo extends Serializable {
	
	public Long getCustomerId();
	public void setCustomerId(Long customerId);
	public String getCustomerFirstname();
	public void setCustomerFirstname(String customerFirstname);
	public String getCustomerLastname();
	public void setCustomerLastname(String customerLastname);
	public String getCustomerEmail();
	public void setCustomerEmail(String customerEmail);

}
