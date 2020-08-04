package com.devalla.grocerieskart.facade;

import com.devalla.grocerieskart.common.GroceriesKartException;
import com.devalla.grocerieskart.dto.UserAccountInfo;
import com.devalla.grocerieskart.web.form.UserAccountForm;

public interface UserAccountFacade {
	UserAccountInfo getUserAccount(UserAccountForm userccountForm) throws GroceriesKartException;
	boolean resetUserPassword(String userEmailAddress, String password) throws GroceriesKartException;
	public boolean createUserAccount(UserAccountForm userAccountForm) throws GroceriesKartException;
	public boolean updateUserAccountPasswordOrEmailAddress(String emailAddress, String newEmailAddress, String currentPassword, String newPassword, boolean updateEmail)
	throws GroceriesKartException;
}
