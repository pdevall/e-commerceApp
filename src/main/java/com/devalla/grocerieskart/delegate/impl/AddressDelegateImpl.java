package com.devalla.grocerieskart.delegate.impl;

import java.util.List;

import javax.inject.Inject;

import com.devalla.grocerieskart.common.GroceriesKartException;
import com.devalla.grocerieskart.delegate.AddressDelegate;
import com.devalla.grocerieskart.dto.AddressDto;
import com.devalla.grocerieskart.facade.AddressFacade;
import com.devalla.grocerieskart.web.form.AddressForm;

public class AddressDelegateImpl implements AddressDelegate {

	@Inject
	private AddressFacade addressFacade;

	public List<AddressDto> findAddresses(Long customerId, Long addressTypeId)
			throws GroceriesKartException {
		return addressFacade.findAddresses(customerId, addressTypeId);
	}

	public int updateAddress(AddressForm addressForm, Long customerId)
			throws GroceriesKartException {
		return addressFacade.updateAddress(addressForm, customerId);
	}

	public int deleteAddress(Long addressId, Long customerId)
			throws GroceriesKartException {
		return addressFacade.deleteAddress(addressId, customerId);
	}

	public Long insertAddress(AddressForm addressForm, Long customerId)
			throws GroceriesKartException {
		return addressFacade.insertAddress(addressForm, customerId);
	}

	
	
}
