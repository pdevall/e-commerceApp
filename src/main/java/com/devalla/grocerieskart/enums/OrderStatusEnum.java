package com.devalla.grocerieskart.enums;

public enum OrderStatusEnum {
	ORDER_RECEIVED(new Long(100), "OR"), 
    IN_PROCESS(new Long(101), "IP"),
	OUT_FOR_DELIVERY(new Long(102), "OD"),
	CANCELLED(new Long(103), "C"),
	DELIVERED(new Long(104), "D");

    private final Long id;  
    private final String code;  

    OrderStatusEnum(Long id, String code){  
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
