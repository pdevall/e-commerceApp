package com.devalla.grocerieskart.enums;

public enum DepartmentEnum {
	
	GROCERY(new Long(100), "GROCERY"), 
    BABY_KIDS(new Long(101), "BABY & KIDS"),
	BATH_BODY(new Long(102), "BATH & BODY");



    private final Long id;  
    private final String code;  

    DepartmentEnum(Long id, String code){  
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
