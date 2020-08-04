package com.devalla.grocerieskart.types.impl;

import com.devalla.grocerieskart.types.PaymentType;

public class PaymentTypeImpl implements PaymentType {
	private static final long serialVersionUID = 1L;
	private Long paymentTypeId;
	private String paymentType;
	private String paymentTypeCode;
	public Long getPaymentTypeId() {
		return paymentTypeId;
	}
	public void setPaymentTypeId(Long paymentTypeId) {
		this.paymentTypeId = paymentTypeId;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public String getPaymentTypeCode() {
		return paymentTypeCode;
	}
	public void setPaymentTypeCode(String paymentTypeCode) {
		this.paymentTypeCode = paymentTypeCode;
	}
	
	
}
