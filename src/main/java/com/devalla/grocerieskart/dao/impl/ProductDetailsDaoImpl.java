package com.devalla.grocerieskart.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import com.devalla.grocerieskart.common.GroceriesKartException;
import com.devalla.grocerieskart.dao.ProductDetailsDao;
import com.devalla.grocerieskart.dto.Category;
import com.devalla.grocerieskart.dto.Department;
import com.devalla.grocerieskart.dto.FrontendProduct;
import com.devalla.grocerieskart.dto.Product;
import com.devalla.grocerieskart.dto.ProductDiscount;
import com.devalla.grocerieskart.dto.SubCategory;
import com.devalla.grocerieskart.dto.impl.CategoryImpl;
import com.devalla.grocerieskart.dto.impl.DepartmentImpl;
import com.devalla.grocerieskart.dto.impl.FrontendProductImpl;
import com.devalla.grocerieskart.dto.impl.ProductDiscountImpl;
import com.devalla.grocerieskart.dto.impl.ProductImpl;
import com.devalla.grocerieskart.dto.impl.SubCategoryImpl;
import com.devalla.grocerieskart.enums.ProductStatusEnum;
import com.devalla.grocerieskart.types.ProductStatusType;
import com.devalla.grocerieskart.types.impl.ProductStatusTypeImpl;
import com.devalla.grocerieskart.web.common.GroceriesKartDateUtil;

public class ProductDetailsDaoImpl extends BasicDaoImpl implements
		ProductDetailsDao {
	
	private static Logger logger = Logger.getLogger(ProductDetailsDaoImpl.class);
	
	private static String GET_PRODUCT_BY_PRODUCT_ID = "select p.product_id, p.product_name, p.product_desc, p.product_cost, p.small_img_url, p.large_img_url, pview.category_id, pview.sub_category_id, " +
	" scn.spring_label, scn.sub_category_name, pview.product_discount_id, pview.discount_pct, pview.offer_product, pview.all_offers, pview.discount_end_date, " +
	" p1.product_name as offer_product_name, pview.category_id, c.category_name, c.department_type_id, d.dept_type_name, pq.quantity " +
	" from (select pd.product_id, pd.category_id, pd.sub_category_id, pd.brand_id, prd.product_discount_id, prd.discount_pct, prd.offer_product, prd.all_offers, prd.discount_end_date " +
	" from product_dept as pd " +
	" left join product_discount as prd on pd.product_id = prd.product_id and utc_timestamp() > prd.discount_start_date and prd.discount_end_date is null or prd.discount_end_date > utc_timestamp()) as pview " +
	" left join product as p1 on pview.offer_product = p1.product_id, product as p, category as c, sub_category as sc, sub_category_name as scn, department_type as d, product_quantity as pq " +
	" where pview.product_id = ? and p.product_id = ? and pview.product_id = p.product_id and p.product_status_type_id = ? " +
	" and pview.category_id = sc.category_id and pview.sub_category_id = sc.sub_category_id and sc.category_id = c.category_id and sc.sub_category_name_id = scn.sub_category_name_id and " +
	" c.department_type_id = d.department_type_id and p.product_id = pq.product_id";

	
	
	public Product getProductDetailsForProductId(Long productId)
		throws GroceriesKartException {
	logger.warn("Entering getProductDetailsForProductId method " + System.currentTimeMillis());
	List<Product> products = null;
	Product product = null;
	try {
		if (productId != null) {			
			products = getJdbcTemplate().query(
					GET_PRODUCT_BY_PRODUCT_ID, new Object[]{productId, productId, ProductStatusEnum.IN_STOCK.id()},
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
							if (((Integer)rs.getInt("quantity") == null) && ((Integer)rs.getInt("quantity")).longValue() <= 0) {
								ProductStatusType pst = new ProductStatusTypeImpl();
								pst.setStatusTypeId(ProductStatusEnum.OUT_OF_STOCK.id());
								pst.setStatus(ProductStatusEnum.OUT_OF_STOCK.code());
								product.setProductStatusType(pst);
							}
							
							return product;
						}
	
					});
			
			if (products != null && products.size() == 1) {
				product = products.get(0);
			}

		}

	} catch (Exception exp) {
		logger.error("Error while pulling Product Details for Product Id " + productId, exp);
		throw new GroceriesKartException("Error while pulling Product Details for Product Id.");
	} finally {
		logger.warn("Exit getProductDetailsForProductId method " + System.currentTimeMillis());
	}
	
		return product;
	}
	
	
	
	public FrontendProduct getFrontendProductDetailsForProductId(Long productId)
		throws GroceriesKartException {
	logger.warn("Entering getFrontendProductDetailsForProductId method " + System.currentTimeMillis());
	List<FrontendProduct> products = null;
	FrontendProduct product = null;
	try {
		if (productId != null) {			
			products = getJdbcTemplate().query(
					GET_PRODUCT_BY_PRODUCT_ID, new Object[]{productId, productId, ProductStatusEnum.IN_STOCK.id()},
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
							
							return product;
						}
	
					});
			
			if (products != null && products.size() == 1) {
				product = products.get(0);
			}

		}

	} catch (Exception exp) {
		logger.error("Error while pulling Product Details for Product Id " + productId, exp);
		throw new GroceriesKartException("Error while pulling Product Details for Product Id.");
	} finally {
		logger.warn("Exit getFrontendProductDetailsForProductId method " + System.currentTimeMillis());
	}
	
		return product;
	}


}
