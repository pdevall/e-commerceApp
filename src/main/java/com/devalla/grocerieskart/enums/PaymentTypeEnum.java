package com.devalla.grocerieskart.enums;

public enum PaymentTypeEnum {
	
	COD(new Long(1000), "COD"), 
    CRD(new Long(1001), "CRD"),
	NB(new Long(1002), "NB");

    private final Long id;  
    private final String code;  

    PaymentTypeEnum(Long id, String code){  
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
