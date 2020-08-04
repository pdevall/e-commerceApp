package com.devalla.grocerieskart.web.form;

public class CheckoutForm extends BaseForm {
	
	private AddressForm addressForm;
	private Long selectedShippingAddrId;
	private Long paymentType;
	
	public AddressForm getAddressForm() {
		return addressForm;
	}
	public void setAddressForm(AddressForm addressForm) {
		this.addressForm = addressForm;
	}
	public Long getSelectedShippingAddrId() {
		return selectedShippingAddrId;
	}
	public void setSelectedShippingAddrId(Long selectedShippingAddrId) {
		this.selectedShippingAddrId = selectedShippingAddrId;
	}
	public Long getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(Long paymentType) {
		this.paymentType = paymentType;
	}

}
