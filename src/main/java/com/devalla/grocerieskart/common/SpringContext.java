package com.devalla.grocerieskart.common;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringContext {
	
	private static ApplicationContext springContext;
	
	private SpringContext() {
		
	}
	
	public static synchronized ApplicationContext getSpringContext() {
		if (springContext == null) {
			springContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		}
		
		return springContext;
	}
}
