package com.devalla.grocerieskart.enums;

public enum AddressEnum {
	
	SHIPPING(new Long(100), "SHIPPING"), 
    BILLING(new Long(101), "BILLING");

    private final Long id;  
    private final String code;  

    AddressEnum(Long id, String code){  
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
