package com.devalla.grocerieskart.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import com.devalla.grocerieskart.common.GroceriesKartException;
import com.devalla.grocerieskart.dao.GListDao;
import com.devalla.grocerieskart.dto.Category;
import com.devalla.grocerieskart.dto.Department;
import com.devalla.grocerieskart.dto.FrontendProduct;
import com.devalla.grocerieskart.dto.ProductDiscount;
import com.devalla.grocerieskart.dto.SubCategory;
import com.devalla.grocerieskart.dto.impl.CategoryImpl;
import com.devalla.grocerieskart.dto.impl.DepartmentImpl;
import com.devalla.grocerieskart.dto.impl.FrontendProductImpl;
import com.devalla.grocerieskart.dto.impl.ProductDiscountImpl;
import com.devalla.grocerieskart.dto.impl.SubCategoryImpl;
import com.devalla.grocerieskart.enums.ProductStatusEnum;
import com.devalla.grocerieskart.types.ProductStatusType;
import com.devalla.grocerieskart.types.impl.ProductStatusTypeImpl;
import com.devalla.grocerieskart.web.common.GroceriesKartDateUtil;

public class GListDaoImpl extends BasicDaoImpl implements GListDao {
	
	private static Logger logger = Logger.getLogger(GListDaoImpl.class);
	
	private static String INSERT_GLIST_QUERY = "insert into customer_glist(customer_glist_id, customer_id, product_id, created_date_time) values(?, ?, ?, utc_timestamp())";
	private static String SELECT_GLIST_CUST_PROD_ID_QUERY = "select count(*) from customer_glist where customer_id = ? and product_id = ?";
	private static String DELETE_GLIST_BY_ID_QUERY = "delete from customer_glist where customer_glist_id = ?";
	private static String DELETE_GLIST_BY_IDS_QUERY = "delete from customer_glist where customer_glist_id in (:gListIds)";
	
	private static String GET_PRODUCT_BY_PRODUCT_ID = "select  p.product_id, p.product_name, p.product_desc, p.product_cost, p.small_img_url, p.large_img_url, pd.category_id, pd.sub_category_id, " +
			" scn.spring_label, scn.sub_category_name, prd.product_discount_id, prd.discount_pct, prd.offer_product, prd.all_offers, prd.discount_end_date, " +
			" p1.product_name as offer_product_name, pd.category_id, c.category_name, c.department_type_id, d.dept_type_name, pq.quantity, custView.customer_glist_id " +
			" from ( select cust.customer_id, glist.customer_glist_id, glist.product_id from customer as cust, customer_glist as glist where cust.customer_id = ? and " +
			" cust.customer_id = glist.customer_id) as custView " +
			" left join product_dept as pd on pd.product_id = custView.product_id " +
			" left join product_discount as prd on (prd.product_id = custView.product_id and utc_timestamp() > prd.discount_start_date and prd.discount_end_date is null or prd.discount_end_date > utc_timestamp()) " +
			" left join product as p on p.product_id = custView.product_id " +
			" left join product as p1 on prd.offer_product = p1.product_id,  category as c, sub_category as sc, sub_category_name as scn, department_type as d, product_quantity as pq " +
			" where p.product_status_type_id = ?  and pd.category_id = sc.category_id and pd.sub_category_id = sc.sub_category_id and sc.category_id = c.category_id " +
			" and sc.sub_category_name_id = scn.sub_category_name_id and c.department_type_id = d.department_type_id and p.product_id = pq.product_id";

	
	public int insertGList(Long customerId, Long productId) throws GroceriesKartException {
		logger.info("insertGList in " + BasicDaoImpl.class.getName());
		int value = getJdbcTemplate().queryForInt(SELECT_GLIST_CUST_PROD_ID_QUERY, new Object[] {customerId, productId});
		int insertValue = -1;
		if (value <= 0) {
			insertValue = getJdbcTemplate().update(INSERT_GLIST_QUERY, new Object[] {getGlobalSequence(), customerId, productId}, new int[] {Types.BIGINT, Types.BIGINT, Types.BIGINT});
		} else {
			insertValue = 0;
		}
		return insertValue;
	}
	
	public void deleteGListByCustProdId(Long customerGListId) throws GroceriesKartException {
		logger.info("deleteGListByCustProdId in " + BasicDaoImpl.class.getName());
		getJdbcTemplate().update(DELETE_GLIST_BY_ID_QUERY, new Object[] {customerGListId}, new int[] {Types.BIGINT});
	}

	public void deleteGListByIds(List<Long> customerGListIds) throws GroceriesKartException {
		logger.info("deleteGListByIds in " + BasicDaoImpl.class.getName());
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("gListIds", customerGListIds);
			getNamedParameterJdbcTemplate().update(DELETE_GLIST_BY_IDS_QUERY, paramMap);
		} catch (Exception e){
			throw new GroceriesKartException("Error while deleting GList " + e.getCause());
		}
	}
	
	
	public List<FrontendProduct> getGListProducts(Long customerId)
		throws GroceriesKartException {
	logger.warn("Entering getGListProducts method " + System.currentTimeMillis());
	List<FrontendProduct> products = null;
	try {
		products = getJdbcTemplate().query(
				GET_PRODUCT_BY_PRODUCT_ID, new Object[]{customerId, ProductStatusEnum.IN_STOCK.id()},
				new RowMapper<FrontendProduct>() {
					
					public FrontendProduct mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						FrontendProduct product = new FrontendProductImpl();
						product.setProductId(rs.getLong("product_id"));
						product.setProductName(rs.getString("product_name"));
						product.setProductDescription("product_desc");
						product.setProductCost(rs
								.getBigDecimal("product_cost"));
						product.setSmallImageUrl(rs
								.getString("small_img_url"));
						product.setBigImageUrl(rs
								.getString("large_img_url"));
						
						SubCategory subCategory = new SubCategoryImpl();
						subCategory.setId(rs.getLong("sub_category_id"));
						subCategory.setSpringLabel(rs.getString("spring_label"));
						subCategory.setName(rs.getString("sub_category_name"));
						product.setSubCategory(subCategory);							
						
						Category category = new CategoryImpl();
						category.setId(rs.getLong("category_id"));
						category.setName(rs.getString("category_name"));
						
						Department department = new DepartmentImpl();
						department.setDepartmentName(rs.getString("dept_type_name"));
						department.setDepartmentId(rs.getLong("department_type_id"));
						
						category.setDepartment(department);
						subCategory.setCategory(category);
						product.setCategory(category);
						
						ProductDiscount productDiscount = new ProductDiscountImpl();
						productDiscount.setProductDiscountId(rs.getLong("product_discount_id"));
						productDiscount.setDiscountPercent(rs.getBigDecimal("discount_pct"));
						productDiscount.setProductOffer(rs.getLong("offer_product"));
						Timestamp ts = rs.getTimestamp("discount_end_date");
						if (ts != null) {
							productDiscount.setDiscountEndDate(GroceriesKartDateUtil.getCalenderByTimestamp(ts));
						} else {
							productDiscount.setDiscountEndDate(null);
						}
						productDiscount.setProductOfferName(rs.getString("offer_product_name"));
						productDiscount.setAllOffers(rs.getBoolean("all_offers"));
						
						product.setProductDiscount(productDiscount);
						if (((Integer)rs.getInt("quantity") == null) && ((Integer)rs.getInt("quantity")).longValue() <= 0) {
							ProductStatusType pst = new ProductStatusTypeImpl();
							pst.setStatusTypeId(ProductStatusEnum.OUT_OF_STOCK.id());
							pst.setStatus(ProductStatusEnum.OUT_OF_STOCK.code());
							product.setProductStatusType(pst);
						}
						product.setCustomerGListId(rs.getLong("customer_glist_id"));
						product.setFrontendQuantity(new Integer(1));
						return product;
					}

				});

	} catch (Exception exp) {
		logger.error("Error while pulling GList Products", exp);
		throw new GroceriesKartException("Error while pulling GList Products.");
	} finally {
		logger.warn("Exit getGListProducts method " + System.currentTimeMillis());
	}
	
		return products;
	}


	
}
