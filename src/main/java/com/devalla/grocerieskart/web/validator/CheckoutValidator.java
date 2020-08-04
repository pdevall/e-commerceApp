package com.devalla.grocerieskart.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.devalla.grocerieskart.web.common.WebConstants;
import com.devalla.grocerieskart.web.form.AddressForm;
import com.devalla.grocerieskart.web.form.CheckoutForm;

public class CheckoutValidator implements Validator {
	
	
	public boolean supports(Class<?> clazz) {
		return CheckoutForm.class.equals(clazz);
	}

	
	public void validate(Object obj, Errors errors) {
		CheckoutForm checkoutForm = (CheckoutForm) obj;
		
		if(checkoutForm.getAddressForm() != null) {
			AddressForm addressForm = checkoutForm.getAddressForm();
			if (addressForm.getShipTo() == null || addressForm.getShipTo().equals(WebConstants.EMPTY_SPACE)) {
				errors.reject("addressform.shipto.required");
			}
			if (addressForm.getAddress1() == null || addressForm.getAddress1().equals(WebConstants.EMPTY_SPACE)) {
				errors.reject("addressform.address1.required");
			}
			if (addressForm.getCity() == null || addressForm.getCity().equals(WebConstants.EMPTY_SPACE)) {
				errors.reject("addressform.city.required");
			}
			if (addressForm.getPostalCode() == null || addressForm.getPostalCode().equals(WebConstants.EMPTY_SPACE)) {
				errors.reject("addressform.postalcode.required");
			} else if (addressForm.getPostalCode() != null && addressForm.getPostalCode().length() != 6 ) {
				errors.reject("addressform.postalcode.length");
			}
			if (addressForm.getStateId() == null || addressForm.getStateId().longValue() == 0) {
				errors.reject("addressform.state.required");
			}
			if (addressForm.getPhoneNumber() == null || addressForm.getPhoneNumber().equals(WebConstants.EMPTY_SPACE)) {
				errors.reject("addressform.phonenumber.required");
			} else if (addressForm.getPhoneNumber() != null && addressForm.getPhoneNumber().length() != 10 ) {
				errors.reject("addressform.phonenumber.length");
			}
		} else {
			errors.reject("addressform.null");
		}

	}
}
