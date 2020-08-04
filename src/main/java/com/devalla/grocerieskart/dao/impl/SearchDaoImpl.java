package com.devalla.grocerieskart.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import com.devalla.grocerieskart.common.GroceriesKartException;
import com.devalla.grocerieskart.dao.SearchDao;
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
import com.devalla.grocerieskart.types.ProductStatusType;
import com.devalla.grocerieskart.types.impl.ProductStatusTypeImpl;
import com.devalla.grocerieskart.web.common.GroceriesKartDateUtil;
import com.devalla.grocerieskart.web.common.WebConstants;

public class SearchDaoImpl extends BasicDaoImpl implements SearchDao {
	
	private static Logger logger = Logger.getLogger(SearchDaoImpl.class);
	
	private static String SEARCH_SELECT= "select  p.product_id, p.product_name, p.product_desc, p.product_cost, p.small_img_url, p.large_img_url, pd.category_id, pd.sub_category_id, " +
			" scn.spring_label, scn.sub_category_name, prd.product_discount_id, prd.discount_pct, prd.offer_product, prd.all_offers, prd.discount_end_date, " +
			" p1.product_name as offer_product_name, pd.category_id, c.category_name, c.department_type_id, d.dept_type_name, pq.quantity " +
			" from product as p " +
			" left join product_dept as pd on pd.product_id = p.product_id  " + 
			" left join product_discount as prd on prd.product_id = p.product_id " +
			" left join product as p1 on prd.offer_product = p1.product_id,  category as c, sub_category as sc, sub_category_name as scn, department_type as d, product_quantity as pq ";
	
	private static String SEARCH_SELECT_COUNT= "select  count(*) from product as p " +
	" left join product_dept as pd on pd.product_id = p.product_id  " + 
	" left join product_discount as prd on prd.product_id = p.product_id " +
	" left join product as p1 on prd.offer_product = p1.product_id,  category as c, sub_category as sc, sub_category_name as scn, department_type as d, product_quantity as pq ";
	
	private static String WHERE_START = "where p.product_status_type_id = 100 ";
	
	private static String WHERE_END = " and pd.category_id = sc.category_id and pd.sub_category_id = sc.sub_category_id and sc.category_id = c.category_id  " +
			" and sc.sub_category_name_id = scn.sub_category_name_id and c.department_type_id = d.department_type_id and p.product_id = pq.product_id";
	
	
	public List<Product> searchProducts(Long departmentId, String productName, Integer startNumber) throws GroceriesKartException {
		logger.warn("Entering searchProducts method " + System.currentTimeMillis());
		List<Product> products = null;
		StringBuffer sb = new StringBuffer();
		sb.append(SEARCH_SELECT);
		sb.append(WHERE_START);
		if (productName != null && !productName.equals(WebConstants.EMPTY_SPACE)) {
			sb.append(" and upper(p.product_name) like '%" + productName + "%'");
		}
		if (departmentId != null && departmentId > 0) {
			sb.append(" and c.department_type_id = " + departmentId);
		}
		sb.append(WHERE_END);
		sb.append(" limit ");
		sb.append(startNumber);
		sb.append(",");
		sb.append(WebConstants.SEARCH_PER_PAGE);
		try {
		products = getJdbcTemplate().query(
				sb.toString(),
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

	} catch (Exception exp) {
		logger.error("Error while searching Products", exp);
		throw new GroceriesKartException("Error while searching Products.");
	} finally {
		logger.warn("Exit searchProducts method " + System.currentTimeMillis());
	}
	
		return products;
	}
	
	
	
	public int searchProductsCount(Long departmentId, String productName) throws GroceriesKartException {
		logger.warn("Entering searchProducts Count method " + System.currentTimeMillis());
		StringBuffer sb = new StringBuffer();
		sb.append(SEARCH_SELECT_COUNT);
		sb.append(WHERE_START);
		if (productName != null && !productName.equals(WebConstants.EMPTY_SPACE)) {
			sb.append(" and upper(p.product_name) like '%" + productName + "%'");
		}
		if (departmentId != null && departmentId > 0) {
			sb.append(" and c.department_type_id = " + departmentId);
		}
		sb.append(WHERE_END);
		int value = getJdbcTemplate().queryForInt(sb.toString());
		return value;
	}

}
