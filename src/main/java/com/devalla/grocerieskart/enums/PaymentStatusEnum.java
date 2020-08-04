package com.devalla.grocerieskart.enums;

public enum PaymentStatusEnum {
	PAYMENT_INITIATED(new Long(100), "PI"), 
    PAYMENT_RECEIVED(new Long(101), "PR"),
	PAYMNENT_ERROR(new Long(102), "PE"),
	CASH_ON_DELIVERY(new Long(103), "COD");

    private final Long id;  
    private final String code;  

    PaymentStatusEnum(Long id, String code){  
        this.id = id;  
        this.code = code;  
    }  
    public Long id(){  
        return this.id;  
    }  
    public String code(){  
        return this.code;  
    }      
}
