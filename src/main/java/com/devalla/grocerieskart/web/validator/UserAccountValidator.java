package com.devalla.grocerieskart.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.devalla.grocerieskart.util.GroceriesKartUtil;
import com.devalla.grocerieskart.web.common.WebConstants;
import com.devalla.grocerieskart.web.form.UserAccountForm;

public class UserAccountValidator implements Validator{

	
	public boolean supports(Class<?> clazz) {
		return UserAccountForm.class.equals(clazz);
	}

	
	public void validate(Object obj, Errors errors) {
		
		UserAccountForm userAccountForm = (UserAccountForm) obj;
		if (userAccountForm.getActionString() != null) {
			if (userAccountForm.getActionString().equals(WebConstants.USER_ACCOUNT_LOGIN)) {
				loginValidation(errors, userAccountForm);
			}else if (userAccountForm.getActionString().equals(WebConstants.USER_ACCOUNT_REGISTER)) {				
				registrationValidation(errors, userAccountForm);
			} 
		} else {
			errors.reject("grocerieskart.screen.action");
		}

		
	}



	private void loginValidation(Errors errors,
			UserAccountForm userAccountForm) {
		
		if (userAccountForm.getUserEmailAddress() == null || userAccountForm.getUserEmailAddress().trim().equals(WebConstants.EMPTY_SPACE)) {
			errors.reject("useraccount.screen.useremailaddress");
		} else {
			boolean email = emailValidation(userAccountForm.getUserEmailAddress().trim());		
			if (!email) {
				errors.reject("useraccount.screen.useremailaddress.pattern");
			}
		}

		if (userAccountForm.getPassword() == null || userAccountForm.getPassword().trim().equals(WebConstants.EMPTY_SPACE)) {
			errors.reject("useraccount.screen.password");
		}
	}

	private void registrationValidation(Errors errors,
			UserAccountForm userAccountForm) {
		if (userAccountForm.getCustomerFirstName() == null || userAccountForm.getCustomerFirstName().trim().equals(WebConstants.EMPTY_SPACE)) {
			errors.reject("useraccount.screen.firstname");
		}
		if (userAccountForm.getCustomerLastName() == null || userAccountForm.getCustomerLastName().trim().equals(WebConstants.EMPTY_SPACE)) {
			errors.reject("useraccount.screen.lastname");
		}
		if (userAccountForm.getPassword() == null || userAccountForm.getPassword().trim().equals(WebConstants.EMPTY_SPACE)) {
			errors.reject("useraccount.screen.password");
		}
		if (userAccountForm.getConfirmPassword() == null || userAccountForm.getConfirmPassword().trim().equals(WebConstants.EMPTY_SPACE)) {
			errors.reject("useraccount.screen.confirm.password");
		}
		
		if (userAccountForm.getPassword() != null && !userAccountForm.getPassword().trim().equals(WebConstants.EMPTY_SPACE) &&
				userAccountForm.getConfirmPassword() != null && !userAccountForm.getConfirmPassword().trim().equals(WebConstants.EMPTY_SPACE)) {
			if (!userAccountForm.getPassword().equals(userAccountForm.getConfirmPassword())) {
				errors.reject("useraccount.screen.password.match");
			}
		}
		
		if (userAccountForm.getPassword() != null && userAccountForm.getPassword().trim().length() < WebConstants.PASSWORD_LENGTH.intValue()) {
			errors.reject("useraccount.screen.password.length");
		}
		
		if (userAccountForm.getUserEmailAddress() == null || userAccountForm.getUserEmailAddress().trim().equals(WebConstants.EMPTY_SPACE)) {
			errors.reject("useraccount.screen.useremailaddress");
		} else {
			boolean email = emailValidation(userAccountForm.getUserEmailAddress());		
			if (!email) {
				errors.reject("useraccount.screen.useremailaddress.pattern");
			}
		}
		
		if (userAccountForm.getPhoneNumber() == null || userAccountForm.getPhoneNumber().trim().equals(WebConstants.EMPTY_SPACE)) {
			errors.reject("useraccount.screen.phonenumber");
		} else {
			if (userAccountForm.getPhoneNumber() != null && !userAccountForm.getPhoneNumber().trim().equals(WebConstants.EMPTY_SPACE)) {
				if (userAccountForm.getPhoneNumber().trim().length() == 8 || userAccountForm.getPhoneNumber().trim().length() == 10) {
					//Nothing
				} else {
					errors.reject("useraccount.screen.phonenumber.invalid");
				}
			}
		}

	}

	private boolean emailValidation(String email) {
        return GroceriesKartUtil.checkEmailPattern(email);
 
	}

}
