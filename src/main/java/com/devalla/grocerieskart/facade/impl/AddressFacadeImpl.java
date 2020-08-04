package com.devalla.grocerieskart.facade.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.devalla.grocerieskart.common.GroceriesKartException;
import com.devalla.grocerieskart.dao.AddressDao;
import com.devalla.grocerieskart.dto.AddressDto;
import com.devalla.grocerieskart.facade.AddressFacade;
import com.devalla.grocerieskart.web.form.AddressForm;

public class AddressFacadeImpl implements AddressFacade {
	
	@Inject
	private AddressDao addressDao;

	
	@Transactional(readOnly = true)
	public List<AddressDto> findAddresses(Long customerId, Long addressTypeId)
			throws GroceriesKartException {
		return addressDao.findAddresses(customerId, addressTypeId);
	}

	
	@Transactional(readOnly= false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public int updateAddress(AddressForm addressForm, Long customerId)
			throws GroceriesKartException {
		return addressDao.updateAddress(addressForm, customerId);
	}

	
	@Transactional(readOnly= false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public int deleteAddress(Long addressId, Long customerId)
			throws GroceriesKartException {		
		return addressDao.deleteAddress(addressId, customerId);
	}

	
	@Transactional(readOnly= false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Long insertAddress(AddressForm addressForm, Long customerId)
			throws GroceriesKartException {
		return addressDao.insertAddress(addressForm, customerId);
	}

}
