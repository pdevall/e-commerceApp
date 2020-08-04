package com.devalla.grocerieskart.delegate.impl;

import javax.inject.Inject;

import com.devalla.grocerieskart.common.GroceriesKartException;
import com.devalla.grocerieskart.delegate.UserAccountDelegate;
import com.devalla.grocerieskart.dto.UserAccountInfo;
import com.devalla.grocerieskart.facade.UserAccountFacade;
import com.devalla.grocerieskart.web.form.UserAccountForm;

public class UserAccountDelegateImpl implements UserAccountDelegate {
	
	@Inject
	private UserAccountFacade userAccountFacade;

	
	public UserAccountInfo getUserAccount(UserAccountForm userccountForm)
			throws GroceriesKartException {		
		return userAccountFacade.getUserAccount(userccountForm);
	}
	
	
	public boolean resetUserPassword(String userEmailAddress, String password)
			throws GroceriesKartException {		
		return userAccountFacade.resetUserPassword(userEmailAddress, password);
	}

	
	public boolean createUserAccount(UserAccountForm userAccountForm)
			throws GroceriesKartException {
		return userAccountFacade.createUserAccount(userAccountForm);
	}

	
	public boolean updateUserAccountPasswordOrEmailAddress(String emailAddress,
			String newEmailAddress, String currentPassword, String newPassword,
			boolean updateEmail) throws GroceriesKartException {
		return userAccountFacade.updateUserAccountPasswordOrEmailAddress(emailAddress, newEmailAddress, currentPassword, newPassword, updateEmail);
	}

}
