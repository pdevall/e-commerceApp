package com.devalla.grocerieskart.dto.impl;

import com.devalla.grocerieskart.dto.AddressBase;

public abstract class AddressBaseImpl implements AddressBase {
	
	private Long addressId;
	private String shipTo;
	private String address1;
	private String address2;
	private String city;
	private String postalCode;
	private Long stateId;
	private String stateName;
	private String phoneNumber;
	private Long addressTypeId;
	
	public Long getAddressId() {
		return addressId;
	}
	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}
	public String getShipTo() {
		return shipTo;
	}
	public void setShipTo(String shipTo) {
		this.shipTo = shipTo;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public Long getStateId() {
		return stateId;
	}
	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Long getAddressTypeId() {
		return addressTypeId;
	}
	public void setAddressTypeId(Long addressTypeId) {
		this.addressTypeId = addressTypeId;
	}

}
