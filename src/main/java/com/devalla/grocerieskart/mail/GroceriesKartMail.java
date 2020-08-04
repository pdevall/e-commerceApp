package com.devalla.grocerieskart.mail;

import javax.inject.Inject;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.mail.javamail.JavaMailSender;

public abstract class GroceriesKartMail {
	
	@Inject
	private JavaMailSender mailSender;
	@Inject
	private VelocityEngine velocityEngine;
	
	public JavaMailSender getMailSender() {
		return mailSender;
	}
	public VelocityEngine getVelocityEngine() {
		return velocityEngine;
	}
	
	


}
