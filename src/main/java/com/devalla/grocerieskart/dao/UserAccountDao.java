package com.devalla.grocerieskart.dao;

import com.devalla.grocerieskart.common.GroceriesKartException;
import com.devalla.grocerieskart.dto.UserAccountInfo;
import com.devalla.grocerieskart.web.form.UserAccountForm;

public interface UserAccountDao extends BasicDao {
	
	public UserAccountInfo getUserAccount(UserAccountForm userAccountForm) throws GroceriesKartException;
	public boolean resetUserPassword(String userEmailAddress, String password)throws GroceriesKartException;
	public boolean createUserAccount(UserAccountForm userAccountForm) throws GroceriesKartException;
	public boolean updateUserAccountPasswordOrEmailAddress(String emailAddress, String newEmailAddress, String currentPassword, String newPassword, boolean updateEmail)
	throws GroceriesKartException;

}
