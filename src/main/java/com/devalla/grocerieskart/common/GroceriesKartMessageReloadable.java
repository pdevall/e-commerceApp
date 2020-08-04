package com.devalla.grocerieskart.common;

import java.util.Locale;

import org.springframework.context.support.ReloadableResourceBundleMessageSource;

public class GroceriesKartMessageReloadable extends
		ReloadableResourceBundleMessageSource {

	
	public String resolveCodeWithoutArguments(String code, Locale locale) {		
		return super.resolveCodeWithoutArguments(code, locale);
	}
	
	

}
