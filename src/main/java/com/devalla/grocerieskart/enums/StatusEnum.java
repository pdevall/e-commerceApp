package com.devalla.grocerieskart.enums;

public enum StatusEnum {

	ACTIVE(new Long(200), "Active"), 
    IN_ACTIVE(new Long(201), "In Active");

    private final Long id;  
    private final String code;  

    StatusEnum(Long id, String code){  
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
