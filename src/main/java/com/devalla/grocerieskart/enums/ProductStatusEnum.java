package com.devalla.grocerieskart.enums;

public enum ProductStatusEnum {
	
	IN_STOCK(new Long(100), "In Stock"), 
    SOLD_OUT(new Long(101), "Sold Out"),
	DISCONTINUED(new Long(102), "Discontinued"),
	OUT_OF_STOCK(new Long(103), "Out Of Stock");


    private final Long id;  
    private final String code;  

    ProductStatusEnum(Long id, String code){  
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
