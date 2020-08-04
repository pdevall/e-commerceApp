package com.devalla.grocerieskart.facade.impl;

import javax.inject.Inject;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.devalla.grocerieskart.common.GroceriesKartException;
import com.devalla.grocerieskart.dao.UserAccountDao;
import com.devalla.grocerieskart.dto.UserAccountInfo;
import com.devalla.grocerieskart.facade.UserAccountFacade;
import com.devalla.grocerieskart.web.form.UserAccountForm;

public class UserAccountFacadeImpl implements UserAccountFacade {
	
	@Inject
	private UserAccountDao userAccountDao;

	
	@Transactional (readOnly= true)
	public UserAccountInfo getUserAccount(UserAccountForm userAccountForm)
			throws GroceriesKartException {
		
		return userAccountDao.getUserAccount(userAccountForm);
	}

	
	@Transactional (readOnly= false, rollbackFor= Exception.class, propagation= Propagation.REQUIRED)
	public boolean resetUserPassword(String userEmailAddress, String password)
			throws GroceriesKartException {

		return userAccountDao.resetUserPassword(userEmailAddress, password);
	}

	
	@Transactional (readOnly= false, rollbackFor= Exception.class, propagation= Propagation.REQUIRED)
	public boolean createUserAccount(UserAccountForm userAccountForm)
			throws GroceriesKartException {
		return userAccountDao.createUserAccount(userAccountForm);
	}

	
	@Transactional (readOnly= false, rollbackFor= Exception.class, propagation= Propagation.REQUIRED)
	public boolean updateUserAccountPasswordOrEmailAddress(String emailAddress,
			String newEmailAddress, String currentPassword, String newPassword,
			boolean updateEmail) throws GroceriesKartException {
		return userAccountDao.updateUserAccountPasswordOrEmailAddress(emailAddress, newEmailAddress, currentPassword, newPassword, updateEmail);
	}

}
