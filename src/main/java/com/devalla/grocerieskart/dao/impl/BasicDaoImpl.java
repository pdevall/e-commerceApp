package com.devalla.grocerieskart.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.devalla.grocerieskart.common.GroceriesKartException;
import com.devalla.grocerieskart.dao.BasicDao;
import com.devalla.grocerieskart.dto.Category;
import com.devalla.grocerieskart.dto.Department;
import com.devalla.grocerieskart.dto.GlobalDiscount;
import com.devalla.grocerieskart.dto.State;
import com.devalla.grocerieskart.dto.SubCategory;
import com.devalla.grocerieskart.dto.impl.CategoryImpl;
import com.devalla.grocerieskart.dto.impl.DepartmentImpl;
import com.devalla.grocerieskart.dto.impl.GlobalDiscountImpl;
import com.devalla.grocerieskart.dto.impl.StateImpl;
import com.devalla.grocerieskart.dto.impl.SubCategoryImpl;
import com.devalla.grocerieskart.enums.StatusEnum;
import com.devalla.grocerieskart.types.PaymentType;
import com.devalla.grocerieskart.types.StatusType;
import com.devalla.grocerieskart.types.impl.PaymentTypeImpl;
import com.devalla.grocerieskart.types.impl.StatusTypeImpl;
import com.devalla.grocerieskart.web.common.GroceriesKartDateUtil;

public abstract class BasicDaoImpl implements BasicDao {
	
	private static Logger logger = Logger.getLogger(BasicDaoImpl.class);
	
	private static String CUSTOMER_SEQUENCE_QUERY = "SELECT ID FROM CUSTOMER_SEQUENCE FOR UPDATE";
	private static String CUSTOMER_SEQUENCE_UPDATE_QUERY = "UPDATE CUSTOMER_SEQUENCE SET ID = ID + 1";
	private static String ALL_SUB_CATEGORIES_CATEGORY_ID = "select sc.sub_category_id, sc.category_id, scn.sub_category_name, scn.spring_label, gst.status_name, gst.gen_status_type_id " +
			" from sub_category as sc, sub_category_name as scn, gen_status_type as gst where sc.category_id = ? and sc.sub_cat_status_type_id = ? and " +
			" sc.sub_category_name_id = scn.sub_category_name_id and sc.sub_cat_status_type_id = gst.gen_status_type_id order by scn.sub_category_name desc";
	private static String GET_ALL_CATEGORIES = "select c.category_id, c.category_name, c.spring_label, c.department_type_id, dt.dept_type_name, "
		+ "  gst.gen_status_type_id, gst.status_name from category as c, gen_status_type as gst, department_type as dt "
		+ " where c.category_status_id = ? and c.category_status_id = gst.gen_status_type_id and c.department_type_id = dt.department_type_id";
	
	private static String ORDER_SEQUENCE_QUERY = "SELECT ID FROM ORDER_SEQUENCE FOR UPDATE";
	private static String ORDER_SEQUENCE_UPDATE_QUERY = "UPDATE ORDER_SEQUENCE SET ID = ID + 1";

	private static String ADDRESS_SEQUENCE_QUERY = "SELECT ID FROM ADDRESS_SEQUENCE FOR UPDATE";
	private static String ADDRESS_SEQUENCE_UPDATE_QUERY = "UPDATE ADDRESS_SEQUENCE SET ID = ID + 1";

	
	private static String CUST_ORDER_SEQUENCE_QUERY = "SELECT ID FROM CUST_ORD_SEQUENCE FOR UPDATE";
	private static String CUST_ORDER_SEQUENCE_UPDATE_QUERY = "UPDATE CUST_ORD_SEQUENCE SET ID = ID + 1";


	private static String GLOBAL_SEQUENCE_QUERY = "SELECT ID FROM GLOBAL_SEQUENCE FOR UPDATE";
	private static String GLOBAL_SEQUENCE_UPDATE_QUERY = "UPDATE GLOBAL_SEQUENCE SET ID = ID + 1";
	
	private static String GLOBAL_DISCOUNTS = "select global_discount_id, discount_pct, discount_start_date, discount_end_date from global_discount where utc_timestamp() > discount_start_date and discount_end_date is null or discount_end_date > utc_timestamp()";
	
	private static String FIND_ALL_STATES = "select state_id, state_name from state";
	
	private static String FIND_ALL_PAYMENT_TYPES = "select payment_type_id, payment_card, payment_code from payment_type";
	
	@Inject
	private JdbcTemplate jdbcTemplate;
	
	@Inject
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		return namedParameterJdbcTemplate;
	}
	
	@Transactional( propagation = Propagation.REQUIRES_NEW)
	public long getCustomerSequence() {

		logger.info("getSequence in " + BasicDaoImpl.class.getName());
		long value = getJdbcTemplate().queryForLong(CUSTOMER_SEQUENCE_QUERY);
		getJdbcTemplate().update(CUSTOMER_SEQUENCE_UPDATE_QUERY);		

		return value;
	}
	
	@Transactional( propagation = Propagation.REQUIRES_NEW)
	public long getGlobalSequence() {

		logger.info("getGlobalSequence in " + BasicDaoImpl.class.getName());
		long value = getJdbcTemplate().queryForLong(GLOBAL_SEQUENCE_QUERY);
		getJdbcTemplate().update(GLOBAL_SEQUENCE_UPDATE_QUERY);		

		return value;
	}
	
	@Transactional( propagation = Propagation.REQUIRES_NEW)
	public long getOrderSequence() {

		logger.info("getOrderSequence in " + BasicDaoImpl.class.getName());
		long value = getJdbcTemplate().queryForLong(ORDER_SEQUENCE_QUERY);
		getJdbcTemplate().update(ORDER_SEQUENCE_UPDATE_QUERY);		

		return value;
	}
	
	@Transactional( propagation = Propagation.REQUIRES_NEW)
	public long getAddressSequence() {

		logger.info("getAddressSequence in " + BasicDaoImpl.class.getName());
		long value = getJdbcTemplate().queryForLong(ADDRESS_SEQUENCE_QUERY);
		getJdbcTemplate().update(ADDRESS_SEQUENCE_UPDATE_QUERY);		

		return value;
	}
	
	@Transactional( propagation = Propagation.REQUIRES_NEW)
	public long getCustomerOrderSequence() {

		logger.info("getCustomerOrderSequence in " + BasicDaoImpl.class.getName());
		long value = getJdbcTemplate().queryForLong(CUST_ORDER_SEQUENCE_QUERY);
		getJdbcTemplate().update(CUST_ORDER_SEQUENCE_UPDATE_QUERY);		

		return value;
	}
	
	
	public List<SubCategory> getSubCategoriesByCategory(final Long categoryId) throws GroceriesKartException {
		try {
			logger.warn("Entering getSubCategoriesByCategory method "
					+ System.currentTimeMillis());
			List<SubCategory> subCategories = (List<SubCategory>) getJdbcTemplate()
					.query(ALL_SUB_CATEGORIES_CATEGORY_ID,
							new Object[] {categoryId, StatusEnum.ACTIVE.id() },
							new RowMapper<SubCategory>() {
								public SubCategory mapRow(ResultSet rs, int rowNum)
										throws SQLException {
									SubCategory subCategory = new SubCategoryImpl();
									subCategory.setId(rs.getLong("sub_category_id"));
									subCategory.setName(rs
											.getString("sub_category_name"));
									subCategory.setSpringLabel(rs
											.getString("spring_label"));

									StatusType statusType = new StatusTypeImpl();
									statusType.setStatus(rs
											.getString("status_name"));
									statusType.setStatusTypeId(rs
											.getLong("gen_status_type_id"));
									subCategory.setStatusType(statusType);
									
									Category category = new CategoryImpl();
									category.setId(categoryId);
									
									subCategory.setCategory(category);

									return subCategory;
								}
							});
			return subCategories;
		} catch (Exception exp) {
			logger.error("Error while pulling Sub Categories", exp);
			throw new GroceriesKartException("Error while fetching Sub Categories");
		} finally {
			logger.warn("Exit getSubCategoriesByCategory method "
					+ System.currentTimeMillis());
		}
	}
	
	
	public List<Category> getCategories() throws GroceriesKartException {
		try {
			logger.warn("Entering getCategories method "
					+ System.currentTimeMillis());
			List<Category> categories = (List<Category>) getJdbcTemplate()
					.query(GET_ALL_CATEGORIES,
							new Object[] { StatusEnum.ACTIVE.id() },
							new RowMapper<Category>() {
								public Category mapRow(ResultSet rs, int rowNum)
										throws SQLException {
									Category category = new CategoryImpl();
									category.setId(rs.getLong("category_id"));
									category.setName(rs
											.getString("category_name"));
									category.setSpringLabel(rs
											.getString("spring_label"));

									StatusType statusType = new StatusTypeImpl();
									statusType.setStatus(rs
											.getString("status_name"));
									statusType.setStatusTypeId(rs
											.getLong("gen_status_type_id"));
									category.setStatusType(statusType);

									Department department = new DepartmentImpl();
									department.setDepartmentId(rs
											.getLong("department_type_id"));
									department.setDepartmentName(rs.getString("dept_type_name"));
									category.setDepartment(department);

									return category;
								}
							});
			return categories;
		} catch (Exception exp) {
			logger.error("Error while pulling Categories", exp);
			throw new GroceriesKartException("Error while fetching Categories");
		} finally {
			logger.warn("Exit getCategories method "
					+ System.currentTimeMillis());
		}
	}
	
	
	public GlobalDiscount getGlobalDiscounts() throws GroceriesKartException {
		try {
			GlobalDiscount globalDiscount = null;
			logger.warn("Entering getGlobalDiscounts method "
					+ System.currentTimeMillis());
			List<GlobalDiscount> globalDiscounts = (List<GlobalDiscount>) getJdbcTemplate()
					.query(GLOBAL_DISCOUNTS,
							new RowMapper<GlobalDiscount>() {
								public GlobalDiscount mapRow(ResultSet rs, int rowNum)
										throws SQLException {
									GlobalDiscount globalDiscount = new GlobalDiscountImpl();
									globalDiscount.setGlobalDiscountId(rs.getLong("global_discount_id"));
									globalDiscount.setDiscountPercent(rs.getBigDecimal("discount_pct"));
									globalDiscount.setDiscountStartDate(GroceriesKartDateUtil.getCalenderByTimestamp(rs.getTimestamp("discount_start_date")));
									if (rs.getTimestamp("discount_end_date") != null) {
										globalDiscount.setDiscountEndDate(GroceriesKartDateUtil.getCalenderByTimestamp(rs.getTimestamp("discount_end_date")));
									}
									return globalDiscount;
								}
							});
			if (globalDiscounts != null && globalDiscounts.size() > 0) {
				globalDiscount = (GlobalDiscount) globalDiscounts.get(0);
			}
			return globalDiscount;
		} catch (Exception exp) {
			logger.error("Error while pulling Global Discounts", exp);
			throw new GroceriesKartException("Error while fetching Global Discounts");
		} finally {
			logger.warn("Exit getGlobalDiscounts method "
					+ System.currentTimeMillis());
		}
	}
	
	
	public List<State> getStates() throws GroceriesKartException {
		List<State> states = null;
		try {
			states = (List<State>) getJdbcTemplate()
			.query(FIND_ALL_STATES,
					new RowMapper<State>() {
						public State mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							State state = new StateImpl();
							state.setStateId(rs.getLong("state_id"));
							state.setStateName(rs.getString("state_name"));
							return state;
						}
					});
		} catch (Exception exp) {
			logger.error("Error while pulling states", exp);
			throw new GroceriesKartException("Error while pulling states");

		}
		
		return states;
	}
	
	
	public List<PaymentType> getPaymentTypes() throws GroceriesKartException {
		List<PaymentType> paymentTypes = null;
		try {
			paymentTypes = (List<PaymentType>) getJdbcTemplate()
			.query(FIND_ALL_PAYMENT_TYPES,
					new RowMapper<PaymentType>() {
						public PaymentType mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							PaymentType paymentType = new PaymentTypeImpl();
							paymentType.setPaymentTypeId(rs.getLong("payment_type_id"));
							paymentType.setPaymentType(rs.getString("payment_card"));
							paymentType.setPaymentTypeCode(rs.getString("payment_code"));
							return paymentType;
						}
					});
		} catch (Exception exp) {
			logger.error("Error while pulling payment types", exp);
			throw new GroceriesKartException("Error while pulling payment types");

		}
		
		return paymentTypes;
	}

}
