package com.devalla.grocerieskart.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import com.devalla.grocerieskart.common.GroceriesKartException;
import com.devalla.grocerieskart.dao.UserAccountDao;
import com.devalla.grocerieskart.dto.UserAccountInfo;
import com.devalla.grocerieskart.dto.impl.UserAccountinfoImpl;
import com.devalla.grocerieskart.web.form.UserAccountForm;

public class UserAccountDaoImpl extends BasicDaoImpl implements UserAccountDao {
	
	private static String GET_USER_ACCOUNT = "select customer_id, cust_first_name, cust_last_name, cust_email_address from customer where cust_email_address = ? and cust_password = password(?)";
	private static String RESET_PASSWORD_USER_ACCOUNT = "update customer set cust_password = password(?), update_date_time = utc_timestamp() where cust_email_address = ?";
	private static String INSERT_USER_ACCOUNT = "insert into customer(customer_id, cust_first_name, cust_last_name, cust_email_address, cust_phone_number, cust_password, create_date_time, update_date_time) values (?, ?, ?, ?, ?, password(?), utc_timestamp(), utc_timestamp())";
	private static String FIND_USER_EXIST = "SELECT COUNT(*) FROM CUSTOMER WHERE CUST_EMAIL_ADDRESS = ?";
	private static String UPDATE_PASSWORD_USER_ACCOUNT = "update customer set cust_password = password(?), update_date_time = utc_timestamp() where cust_email_address = ? and cust_password = password(?)";
	private static String UPDATE_EMAIL_USER_ACCOUNT = "update customer set cust_email_address = ?, update_date_time = utc_timestamp() where cust_email_address = ? and cust_password = password(?)";
	
	private static Logger logger = Logger.getLogger(UserAccountDaoImpl.class);
	
	public UserAccountInfo getUserAccount(UserAccountForm userAccountForm) throws GroceriesKartException {
		try {
			logger.warn("Entering getUserAccount method " + System.currentTimeMillis());
			List<UserAccountInfo> userAccountInfoList = (List<UserAccountInfo>) getJdbcTemplate().query(GET_USER_ACCOUNT, new Object[]{userAccountForm.getUserEmailAddress().trim(), userAccountForm.getPassword().trim()}, new RowMapper<UserAccountInfo>() {
		        public UserAccountInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
		        	UserAccountInfo userAccountInfo = new UserAccountinfoImpl();
		        	userAccountInfo.setCustomerId(rs.getLong("customer_id"));
		        	userAccountInfo.setCustomerFirstname(rs.getString("cust_first_name"));
		        	userAccountInfo.setCustomerLastname(rs.getString("cust_last_name"));
		        	userAccountInfo.setCustomerEmail(rs.getString("cust_email_address"));
		            return userAccountInfo;
		        }
		    });
			
			if (userAccountInfoList != null && userAccountInfoList.size() == 1) 
				return userAccountInfoList.get(0);
		} catch (Exception exp) {
			logger.error("Error while pulling User Account", exp);
			throw new GroceriesKartException("Error while pulling User Account");
		} finally {
			logger.warn("Exit getUserAccount method " + System.currentTimeMillis());
		}
		
		return null;
	}

	
	public boolean resetUserPassword(String userEmailAddress, String password)
			throws GroceriesKartException {
		try {
			logger.warn("Entering resetUserPassword method " + System.currentTimeMillis());
			boolean success = false; 
			int value = getJdbcTemplate().update(RESET_PASSWORD_USER_ACCOUNT, new Object[] {password, userEmailAddress});
			if (value > 0) {
				success = true;
			} else {
				success = false;
			}

			return success;
		} catch (Exception e) {
			logger.error("Error while Reset User Password ", e);
			throw new GroceriesKartException("Error while Reset User Password");
		} finally {
			logger.warn("Exit resetUserPassword method " + System.currentTimeMillis());
		}
	}
	
	
	public boolean createUserAccount(UserAccountForm userAccountForm)
			throws GroceriesKartException {
		try {
			logger.warn("Entering createUserAccount method " + System.currentTimeMillis());
			boolean success = findUserExist(userAccountForm.getUserEmailAddress().trim());
			if (!success) {
				long customerId = getCustomerSequence();
				success = false;
				int value = getJdbcTemplate().update(INSERT_USER_ACCOUNT, new Object[] {customerId, 
						userAccountForm.getCustomerFirstName().trim(),
						userAccountForm.getCustomerLastName().trim(),
						userAccountForm.getUserEmailAddress().trim(),
						userAccountForm.getPhoneNumber().trim(),
						userAccountForm.getPassword().trim()});
				if (value > 0) {
					success = true;
				} else {
					success = false;
				}
			} else {
				success = false;
			}

			return success;
		} catch (Exception e) {
			logger.error("Error while Creating User Account ", e);
			throw new GroceriesKartException("Error while Creating User Account");
		} finally {
			logger.warn("Exit createUserAccount method " + System.currentTimeMillis());
		}
	}
	
	
	public boolean findUserExist(String emailAddress) {
		int value = getJdbcTemplate().queryForInt(FIND_USER_EXIST, new Object[] {emailAddress}, new int[] {Types.VARCHAR});		
		if (value > 0) {
			return true;
		}
		return false;
	}
	
	
	public boolean updateUserAccountPasswordOrEmailAddress(String emailAddress, String newEmailAddress, String currentPassword, String newPassword, boolean updateEmail)
			throws GroceriesKartException {
		try {
			logger.warn("Entering updateUserAccountPassword method " + System.currentTimeMillis());
			boolean success = false;
			int value = 0;
			if (!updateEmail) {
				value = getJdbcTemplate().update(UPDATE_PASSWORD_USER_ACCOUNT, new Object[] {newPassword.trim(), 
						emailAddress.trim(),
						currentPassword.trim()});
			} else {
				
				value = getJdbcTemplate().update(UPDATE_EMAIL_USER_ACCOUNT, new Object[] {newEmailAddress.trim(), 
						emailAddress.trim(),
						currentPassword.trim()});
			}
			if (value > 0) {
				success = true;
			} else {
				success = false;
			}

			return success;
		} catch (Exception e) {
			logger.error("Error while Updating User Account password ", e);
			throw new GroceriesKartException("Error while Updating User Account password");
		} finally {
			logger.warn("Exit updateUserAccountPassword method " + System.currentTimeMillis());
		}
	}

}
