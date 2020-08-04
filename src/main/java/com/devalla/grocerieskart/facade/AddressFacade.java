package com.devalla.grocerieskart.facade;

import java.util.List;

import com.devalla.grocerieskart.common.GroceriesKartException;
import com.devalla.grocerieskart.dto.AddressDto;
import com.devalla.grocerieskart.web.form.AddressForm;

public interface AddressFacade {
	public List<AddressDto> findAddresses(Long customerId, Long addressTypeId) throws GroceriesKartException;
	public int updateAddress(AddressForm addressForm, Long customerId) throws GroceriesKartException;
	public int deleteAddress(Long addressId, Long customerId) throws GroceriesKartException;
	public Long insertAddress(AddressForm addressForm, Long customerId) throws GroceriesKartException;
}
