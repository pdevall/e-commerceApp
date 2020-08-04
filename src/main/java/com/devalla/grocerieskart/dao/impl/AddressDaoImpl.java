package com.devalla.grocerieskart.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import com.devalla.grocerieskart.common.GroceriesKartException;
import com.devalla.grocerieskart.dao.AddressDao;
import com.devalla.grocerieskart.dto.AddressDto;
import com.devalla.grocerieskart.dto.impl.AddressDtoImpl;
import com.devalla.grocerieskart.enums.AddressEnum;
import com.devalla.grocerieskart.web.form.AddressForm;

public class AddressDaoImpl extends BasicDaoImpl implements AddressDao {
	
	private static Logger logger = Logger.getLogger(AddressDaoImpl.class);

	private static String SELECT_ADDRESS_BY_ADDRESS_TYPE = "select a.address_id, a.ship_to, a.address_line1, a.address_line2," +
			" a.city, a.postal_code, a.phone_number, at.address_type_id, at.address_type_name, s.state_name, s.state_id" +
			" from address as a, state as s, address_type as at, cust_address as ca where " +
			" ca.customer_id = ? and ca.address_id = a.address_id and a.address_type_id = ? and a.address_type_id = at.address_type_id " +
			" and a.state_id = s.state_id";
	
	private static String DELETE_ADDRESS_ID = "delete from cust_address where address_id = ? and customer_id = ?";
	
	private static String UPDATE_ADDRESS_BY_ID = "update address set ship_to = ?, address_line1 = ? " +
			" address_line2 = ?, city = ?, postal_code = ?, phone_numner = ?, state_id = ? where address_id = ?";
	
	private static String INSERT_ADDRESS = "insert into address(address_id, ship_to, address_line1, address_line2, city, postal_code, state_id, phone_number, address_type_id, create_date_time) " +
			" values(?, ?, ?, ?, ?, ?, ?, ?, ?, utc_timestamp())";
	
	private static String INSERT_CUST_ADDRESS = "insert into cust_address(address_id, customer_id) value(?, ?)";

	public List<AddressDto> findAddresses(Long customerId, Long addressTypeId) throws GroceriesKartException {
		List<AddressDto> addresses = new ArrayList<AddressDto>();
		try {
			addresses = getJdbcTemplate().query(
					SELECT_ADDRESS_BY_ADDRESS_TYPE, new Object[]{customerId, AddressEnum.SHIPPING.id()},
					new RowMapper<AddressDto>() {
						
						public AddressDto mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							AddressDto addressDto = new AddressDtoImpl();
							addressDto.setAddressId(rs.getLong("address_id"));
							addressDto.setShipTo(rs.getString("ship_to"));
							addressDto.setAddress1(rs.getString("address_line1"));
							addressDto.setAddress2(rs.getString("address_line2"));							
							addressDto.setCity(rs.getString("city"));
							addressDto.setPostalCode(rs.getString("postal_code"));
							addressDto.setPhoneNumber(rs.getString("phone_number"));
							addressDto.setStateName(rs.getString("state_name"));
							addressDto.setAddressTypeId(rs.getLong("address_type_id"));
							
							return addressDto;						
						}
					});
			
		} catch (Exception e) {
			logger.error("Error while pulling the Addresses for customer: " + customerId,  e);
			throw new GroceriesKartException("Error while pulling the Addresses for customer: ", e.getCause());
		}
		return addresses;
	}
	
	public int updateAddress(AddressForm addressForm, Long customerId) throws GroceriesKartException {
		int i = 0;
		try {
			i = getJdbcTemplate().update(UPDATE_ADDRESS_BY_ID, new Object[] {addressForm.getShipTo().trim(), addressForm.getAddress1().trim(),
					addressForm.getAddress2().trim(), addressForm.getCity().trim(), addressForm.getPostalCode().trim(), addressForm.getPhoneNumber().trim(),
					addressForm.getAddressId(), addressForm.getAddressId()}, new int[] {Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.BIGINT, Types.BIGINT});
			
		} catch (Exception e) {
			logger.error("Error while updating the Address for customer: " + customerId, e);
			throw new GroceriesKartException("Error while updating the Address for customer: "  , e.getCause());
		}
		return i;
	}
	
	public int deleteAddress(Long addressId, Long customerId) throws GroceriesKartException {
		int i = 0;
		try {
			i = getJdbcTemplate().update(DELETE_ADDRESS_ID, new Object[] {addressId, customerId});
			
		} catch (Exception e) {
			throw new GroceriesKartException("Error while update the Address for customer: " + customerId, e);
		}
		return i;
	}
	
	public Long insertAddress(AddressForm addressForm, Long customerId) throws GroceriesKartException {
		Long addressId = 0L;
		try {
			addressId = getAddressSequence(); 
			int i = getJdbcTemplate().update(INSERT_ADDRESS, new Object[] {addressId, addressForm.getShipTo().trim(), addressForm.getAddress1().trim(),
					addressForm.getAddress2().trim(), addressForm.getCity().trim(), addressForm.getPostalCode().trim(), addressForm.getStateId(), addressForm.getPhoneNumber().trim(),
					AddressEnum.SHIPPING.id()}, new int[] {Types.BIGINT, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.BIGINT, Types.BIGINT, Types.BIGINT});
			getJdbcTemplate().update(INSERT_CUST_ADDRESS, new Object[] {addressId, customerId});
			
		} catch (Exception e) {
			addressId = 0L;
			logger.error("Error while inserting the Address for customer: " + customerId, e);
			throw new GroceriesKartException("Error while inserting the Address for customer: " , e.getCause());
		}
		return addressId;
	}
}
