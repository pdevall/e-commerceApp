package com.devalla.grocerieskart.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import com.devalla.grocerieskart.common.GroceriesKartException;
import com.devalla.grocerieskart.dao.ProductDisplayDao;
import com.devalla.grocerieskart.dto.Category;
import com.devalla.grocerieskart.dto.Department;
import com.devalla.grocerieskart.dto.Product;
import com.devalla.grocerieskart.dto.ProductDiscount;
import com.devalla.grocerieskart.dto.SubCategory;
import com.devalla.grocerieskart.dto.impl.CategoryImpl;
import com.devalla.grocerieskart.dto.impl.DepartmentImpl;
import com.devalla.grocerieskart.dto.impl.ProductDiscountImpl;
import com.devalla.grocerieskart.dto.impl.ProductImpl;
import com.devalla.grocerieskart.dto.impl.SubCategoryImpl;
import com.devalla.grocerieskart.enums.ProductStatusEnum;
import com.devalla.grocerieskart.enums.StatusEnum;
import com.devalla.grocerieskart.types.StatusType;
import com.devalla.grocerieskart.types.impl.StatusTypeImpl;
import com.devalla.grocerieskart.web.common.GroceriesKartDateUtil;

public class ProductDisplayDaoImpl extends BasicDaoImpl implements
		ProductDisplayDao {
	
	private static String GET_CATEGORY_BY_ID = "select c.category_id, c.category_name, c.spring_label, c.department_type_id, dt.dept_type_name, "
		+ "  gst.gen_status_type_id, gst.status_name from category as c, gen_status_type as gst, department_type as dt "
		+ " where c.category_id = ? and c.category_status_id = ? and c.category_status_id = gst.gen_status_type_id and c.department_type_id = dt.department_type_id";

	private static String GET_ALL_PRODUCTS_BY_CATEGORYID = "select  p.product_id, p.product_name, p.product_desc, p.product_cost, p.small_img_url, p.large_img_url, pview.category_id, pview.sub_category_id," +
	" scn.spring_label, pview.product_discount_id, pview.discount_pct, pview.offer_product, pview.all_offers, pview.discount_end_date," +
	" p1.product_name as offer_product_name " +
	" from " +
	" 	(select pd.product_id, pd.category_id, pd.sub_category_id, prd.product_discount_id, prd.discount_pct, prd.offer_product, prd.all_offers, prd.discount_end_date" +
	" 	from product_dept as pd " +
	" 	left join product_discount as prd on pd.product_id = prd.product_id and utc_timestamp() > prd.discount_start_date and prd.discount_end_date is null or prd.discount_end_date > utc_timestamp()) as pview" +
	" left join product as p1 on pview.offer_product = p1.product_id, product as p, category as c, sub_category as sc, sub_category_name as scn" +
	" where pview.category_id in (:categoryId) and pview.product_id = p.product_id and p.product_status_type_id = :productStatusTypeId" +
	" and pview.category_id = c.category_id and pview.sub_category_id = sc.sub_category_id and sc.sub_category_name_id = scn.sub_category_name_id limit ";
	
	private static String GET_ALL_PRODUCTS_BY_SUB_CATEGORYID = "select  p.product_id, p.product_name, p.product_desc, p.product_cost, p.small_img_url, p.large_img_url, pview.category_id, pview.sub_category_id, " +
			" scn.spring_label, scn.sub_category_name, pview.product_discount_id, pview.discount_pct, pview.offer_product, pview.all_offers, pview.discount_end_date, " +
			" p1.product_name as offer_product_name, pview.category_id, c.category_name, c.department_type_id, d.dept_type_name " +
			" from (select pd.product_id, pd.category_id, pd.sub_category_id, prd.product_discount_id, prd.discount_pct, prd.offer_product, prd.all_offers, prd.discount_end_date " +
			" from product_dept as pd " +
			" left join product_discount as prd on pd.product_id = prd.product_id and utc_timestamp() > prd.discount_start_date and prd.discount_end_date is null or prd.discount_end_date > utc_timestamp()) as pview " +
			" left join product as p1 on pview.offer_product = p1.product_id, product as p, category as c, sub_category as sc, sub_category_name as scn, department_type as d " +
			" where pview.sub_category_id in (:subCategoryId) and pview.category_id = :categoryId and pview.product_id = p.product_id and p.product_status_type_id = :productStatusTypeId " +
			" and pview.category_id = sc.category_id and pview.sub_category_id = sc.sub_category_id and sc.category_id = c.category_id and sc.sub_category_name_id = scn.sub_category_name_id and c.department_type_id = d.department_type_id";

	private static String GET_ALL_PRODUCTS_BY_BRANDID = "select  p.product_id, p.product_name, p.product_desc, p.product_cost, p.small_img_url, p.large_img_url, pview.category_id, pview.sub_category_id, " +
	" scn.spring_label, scn.sub_category_name, pview.product_discount_id, pview.discount_pct, pview.offer_product, pview.all_offers, pview.discount_end_date, " +
	" p1.product_name as offer_product_name, pview.category_id, c.category_name, c.department_type_id, d.dept_type_name " +
	" from (select pd.product_id, pd.category_id, pd.sub_category_id, pd.brand_id, prd.product_discount_id, prd.discount_pct, prd.offer_product, prd.all_offers, prd.discount_end_date " +
	" from product_dept as pd " +
	" left join product_discount as prd on pd.product_id = prd.product_id and utc_timestamp() > prd.discount_start_date and prd.discount_end_date is null or prd.discount_end_date > utc_timestamp()) as pview " +
	" left join product as p1 on pview.offer_product = p1.product_id, product as p, category as c, sub_category as sc, sub_category_name as scn, department_type as d " +
	" where pview.brand_id = :brandId and pview.sub_category_id in (:subCategoryId) and pview.category_id = :categoryId and pview.product_id = p.product_id and p.product_status_type_id = :productStatusTypeId " +
	" and pview.category_id = sc.category_id and pview.sub_category_id = sc.sub_category_id and sc.category_id = c.category_id and sc.sub_category_name_id = scn.sub_category_name_id and c.department_type_id = d.department_type_id";
	
	private static String NUM_OF_ROWS_BY_CATEGORY = "SELECT count(*) from product_dept where category_id = ";
	
	private static Logger logger = Logger.getLogger(ProductDisplayDaoImpl.class);
	
	
	public Category getCategoryById(Long categoryId) throws GroceriesKartException {
		try {
			logger.warn("Entering getCategoryById method "
					+ System.currentTimeMillis());
			List<Category> categories = (List<Category>) getJdbcTemplate()
					.query(GET_CATEGORY_BY_ID,
							new Object[] { categoryId, StatusEnum.ACTIVE.id() },
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
			if (categories != null && categories.size() == 1) 
				return (Category) categories.get(0);
			
			return null;
			
		} catch (Exception exp) {
			logger.error("Error while pulling Category By Id" + categoryId , exp);
			throw new GroceriesKartException("Error while pulling Category.");
		} finally {
			logger.warn("Exit getCategoryById method "
					+ System.currentTimeMillis());
		}
	}

	
	public Category getProductsForCategory(Long categoryId, Integer startNumber, Integer limit)
		throws GroceriesKartException {
	logger.warn("Entering getHomePageCategories method " + System.currentTimeMillis());
	Category category = new CategoryImpl();
	List<Product> products = null;
	try {
		if (categoryId != null) {
			category = getCategoryById(categoryId);
			if (category != null) {
				StringBuffer sb = new StringBuffer();
				sb.append(GET_ALL_PRODUCTS_BY_CATEGORYID);
				sb.append(startNumber);
				sb.append(",");
				sb.append(limit);
				
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("categoryId", categoryId);
				paramMap.put("productStatusTypeId", ProductStatusEnum.IN_STOCK.id());
				products = getNamedParameterJdbcTemplate().query(
						sb.toString(), paramMap,
						new RowMapper<Product>() {
							
							public Product mapRow(ResultSet rs, int rowNum)
									throws SQLException {
								Product product = new ProductImpl();
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
								product.setSubCategory(subCategory);							
								
								Category category = new CategoryImpl();
								category.setId(rs.getLong("category_id"));
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
								
								return product;
							}
		
						});
			}
		}
		String sql = NUM_OF_ROWS_BY_CATEGORY + categoryId;
		Integer count = new Integer(getJdbcTemplate().queryForInt(sql));
		category.setNumOfProductsByCategory(count);
		category.setProducts(products);
	} catch (Exception exp) {
		logger.error("Error while pulling Home Page Categories", exp);
		throw new GroceriesKartException("Error while pulling Home Page Categories.");
	} finally {
		logger.warn("Exit getHomePageCategories method " + System.currentTimeMillis());
	}
	
	return category;
	}
	
	
	public SubCategory getProductsForSubCategory(Long subCategoryId, Long categoryId)
		throws GroceriesKartException {
	logger.warn("Entering getProductsForSubCategory method " + System.currentTimeMillis());
	SubCategory subCategory = new SubCategoryImpl();
	List<Product> products = null;
	try {
		if (subCategoryId != null) {			
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("subCategoryId", subCategoryId);
			paramMap.put("categoryId", categoryId);			
			paramMap.put("productStatusTypeId", ProductStatusEnum.IN_STOCK.id());
			products = getNamedParameterJdbcTemplate().query(
					GET_ALL_PRODUCTS_BY_SUB_CATEGORYID, paramMap,
					new RowMapper<Product>() {
						
						public Product mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							Product product = new ProductImpl();
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
							
							return product;
						}
	
					});
			
		}
		if (products != null && products.size() > 0 ) {
			Product product = (Product) products.get(0);
			subCategory = product.getSubCategory();
			subCategory.setProducts(products);
		}
	} catch (Exception exp) {
		logger.error("Error while pulling Products by Sub Category Id " + subCategoryId, exp);
		throw new GroceriesKartException("Error while pulling Products by Sub Category Id.");
	} finally {
		logger.warn("Exit getProductsForSubCategory method " + System.currentTimeMillis());
	}
	
	return subCategory;
	}
	
	
	public SubCategory getProductsForBrand(Long brandId, Long subCategoryId, Long categoryId)
		throws GroceriesKartException {
	logger.warn("Entering getProductsForBrand method " + System.currentTimeMillis());
	SubCategory subCategory = new SubCategoryImpl();
	List<Product> products = null;
	try {
		if (subCategoryId != null) {			
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("brandId", brandId);
			paramMap.put("subCategoryId", subCategoryId);
			paramMap.put("categoryId", categoryId);			
			paramMap.put("productStatusTypeId", ProductStatusEnum.IN_STOCK.id());
			products = getNamedParameterJdbcTemplate().query(
					GET_ALL_PRODUCTS_BY_BRANDID, paramMap,
					new RowMapper<Product>() {
						
						public Product mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							Product product = new ProductImpl();
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
							
							return product;
						}
	
					});
			
		}
		if (products != null && products.size() > 0 ) {
			Product product = (Product) products.get(0);
			subCategory = product.getSubCategory();
			subCategory.setProducts(products);
		}
	} catch (Exception exp) {
		logger.error("Error while pulling Products by Brand Id " + subCategoryId, exp);
		throw new GroceriesKartException("Error while pulling Products by Brand Id.");
	} finally {
		logger.warn("Exit getProductsForBrand method " + System.currentTimeMillis());
	}
	
	return subCategory;
	}
}
