package com.devalla.grocerieskart.common;

public class GroceriesKartException extends Exception {
	public GroceriesKartException() {		
	}	
	
    public GroceriesKartException(String msg) { 
    	super(msg); 
    }  
    public GroceriesKartException(Throwable cause) { 
    	super(cause); 
    }  
    public GroceriesKartException(String msg, Throwable cause) { 
    	super(msg, cause); 
    }
}