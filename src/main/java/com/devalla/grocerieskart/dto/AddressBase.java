package com.devalla.grocerieskart.dto;

import java.io.Serializable;

public interface AddressBase extends Serializable {

	public Long getAddressId();
	public void setAddressId(Long addressId);
	public String getShipTo();
	public void setShipTo(String shipTo);
	public String getAddress1();
	public void setAddress1(String address1);
	public String getAddress2();
	public void setAddress2(String address2);
	public String getCity();
	public void setCity(String city);
	public String getPostalCode();
	public void setPostalCode(String postalCode);
	public Long getStateId();
	public void setStateId(Long stateId);
	public String getStateName();
	public void setStateName(String stateName);
	public String getPhoneNumber();
	public void setPhoneNumber(String phoneNumber);
	public Long getAddressTypeId();
	public void setAddressTypeId(Long addressTypeId);
}
