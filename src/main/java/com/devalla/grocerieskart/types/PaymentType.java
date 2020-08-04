package com.devalla.grocerieskart.types;

import java.io.Serializable;

public interface PaymentType extends Serializable {
	public Long getPaymentTypeId();
	public void setPaymentTypeId(Long paymentTypeId);
	public String getPaymentType();
	public void setPaymentType(String paymentType);
	public String getPaymentTypeCode();
	public void setPaymentTypeCode(String paymentTypeCode);
}
