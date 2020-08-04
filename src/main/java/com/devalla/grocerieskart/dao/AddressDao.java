package com.devalla.grocerieskart.dao;

import java.util.List;

import com.devalla.grocerieskart.common.GroceriesKartException;
import com.devalla.grocerieskart.dto.AddressDto;
import com.devalla.grocerieskart.web.form.AddressForm;


public interface AddressDao extends BasicDao {
	
	public List<AddressDto> findAddresses(Long customerId, Long addressTypeId) throws GroceriesKartException;
	public int updateAddress(AddressForm addressForm, Long customerId) throws GroceriesKartException;
	public int deleteAddress(Long addressId, Long customerId) throws GroceriesKartException;
	public Long insertAddress(AddressForm addressForm, Long customerId) throws GroceriesKartException;

}
