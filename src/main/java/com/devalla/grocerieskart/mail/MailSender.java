package com.devalla.grocerieskart.mail;

import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.devalla.grocerieskart.common.SpringContext;
import com.devalla.grocerieskart.dto.UserAccountInfo;
import com.devalla.grocerieskart.util.MenuUtil;
import com.devalla.grocerieskart.web.common.GroceriesKartDateUtil;
import com.devalla.grocerieskart.web.form.ShoppingCartForm;

public class MailSender extends GroceriesKartMail {	
	
	public void sendPasswordResetEmail(final String email, final String passwordReset)  {
	      MimeMessagePreparator preparator = new MimeMessagePreparator() {
	         public void prepare(MimeMessage mimeMessage) throws Exception {
	            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true);
	            message.setTo(email);
	            message.setFrom(MenuUtil.getSmtpProperty("smtp.from"));
	            message.setSubject(MenuUtil.getSmtpProperty("smtp.email.reset.password.subject"));
	            Map model = new HashMap();
	            model.put("email", email);
	            model.put("reset", passwordReset);
	            model.put("date", GroceriesKartDateUtil.getUTCCalenderNoTimeString());
	            String text = VelocityEngineUtils.mergeTemplateIntoString(getVelocityEngine(), "passwordResetEmail.vm", model);
	            message.setText(text, true);
	            ApplicationContext ctx = SpringContext.getSpringContext();
	            Resource resource  = ctx.getResource(MenuUtil.getCommonProperty("static.content.url")+ "/images/gcart.png");
	            message.addInline("logoImage", resource);	            
	         }
	      };
	      getMailSender().send(preparator);		
	}

	public void sendPasswordUpdateConfirmationEmail(final String emailAddress) {
	      MimeMessagePreparator preparator = new MimeMessagePreparator() {
		         public void prepare(MimeMessage mimeMessage) throws Exception {
		            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true);
		            message.setTo(emailAddress);
		            message.setFrom(MenuUtil.getSmtpProperty("smtp.from"));
		            message.setSubject(MenuUtil.getSmtpProperty("smtp.email.update.password.subject"));
		            Map model = new HashMap();
		            model.put("email", emailAddress);
		            model.put("date", GroceriesKartDateUtil.getUTCCalenderNoTimeString());
		            String text = VelocityEngineUtils.mergeTemplateIntoString(getVelocityEngine(), "accountInformationUpdateEmail.vm", model);
		            message.setText(text, true);
		            ApplicationContext ctx = SpringContext.getSpringContext();
		            Resource resource  = ctx.getResource(MenuUtil.getCommonProperty("static.content.url")+ "/images/gcart.png");
		            message.addInline("logoImage", resource);	            

		         }
		      };
		      getMailSender().send(preparator);		
		
	}
	
	public void sendAccountCreationEmail(final String emailAddress) {
	      MimeMessagePreparator preparator = new MimeMessagePreparator() {
		         public void prepare(MimeMessage mimeMessage) throws Exception {
		            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true);
		            message.setTo(emailAddress);
		            message.setFrom(MenuUtil.getSmtpProperty("smtp.from"));
		            message.setSubject(MenuUtil.getSmtpProperty("smtp.email.new.account.subject"));
		            Map model = new HashMap();
		            model.put("email", emailAddress);
		            model.put("date", GroceriesKartDateUtil.getUTCCalenderNoTimeString());
		            String text = VelocityEngineUtils.mergeTemplateIntoString(getVelocityEngine(), "accountCreationEmail.vm", model);
		            message.setText(text, true);
		            ApplicationContext ctx = SpringContext.getSpringContext();
		            Resource resource  = ctx.getResource(MenuUtil.getCommonProperty("static.content.url")+ "/images/gcart.png");
		            message.addInline("logoImage", resource);	            

		         }
		      };
		      getMailSender().send(preparator);		
		
	}

	public void sendOrderReceiptEmail(final UserAccountInfo userAccountInfo,
			final ShoppingCartForm shoppingCartForm) {
	      MimeMessagePreparator preparator = new MimeMessagePreparator() {
		         public void prepare(MimeMessage mimeMessage) throws Exception {
		            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true);
		            message.setTo(userAccountInfo.getCustomerEmail());
		            message.setFrom(MenuUtil.getSmtpProperty("smtp.from"));
		            message.setSubject(MenuUtil.getSmtpProperty("smtp.email.order.receipt.subject"));
		            Map model = new HashMap();
		            model.put("userAccountInfo", userAccountInfo);
		            model.put("date", GroceriesKartDateUtil.getUTCCalenderNoTimeString());
		            model.put("shoppingCartForm", shoppingCartForm);
		            String text = VelocityEngineUtils.mergeTemplateIntoString(getVelocityEngine(), "orderReceipt.vm", model);
		            message.setText(text, true);
		            ApplicationContext ctx = SpringContext.getSpringContext();
		            Resource resource  = ctx.getResource(MenuUtil.getCommonProperty("static.content.url")+ "/images/gcart.png");
		            message.addInline("logoImage", resource);	            

		         }
		      };
		      getMailSender().send(preparator);	
		
	}	
}