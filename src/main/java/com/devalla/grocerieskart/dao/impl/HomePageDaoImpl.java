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
import com.devalla.grocerieskart.dao.HomePageDao;
import com.devalla.grocerieskart.dto.Category;
import com.devalla.grocerieskart.dto.Product;
import com.devalla.grocerieskart.dto.ProductDiscount;
import com.devalla.grocerieskart.dto.SubCategory;
import com.devalla.grocerieskart.dto.impl.CategoryImpl;
import com.devalla.grocerieskart.dto.impl.ProductDiscountImpl;
import com.devalla.grocerieskart.dto.impl.ProductImpl;
import com.devalla.grocerieskart.dto.impl.SubCategoryImpl;
import com.devalla.grocerieskart.enums.ProductStatusEnum;
import com.devalla.grocerieskart.web.common.GroceriesKartDateUtil;

public class HomePageDaoImpl extends BasicDaoImpl implements HomePageDao {

	private static String GET_ALL_PRODUCTS_BY_CATEGORYIDS = "select  p.product_id, p.product_name, p.product_desc, p.product_cost, p.small_img_url, p.large_img_url, pview.category_id, pview.sub_category_id," +
			" scn.spring_label, pview.product_discount_id, pview.discount_pct, pview.offer_product, pview.all_offers, pview.discount_end_date," +
			" p1.product_name as offer_product_name " +
			" from " +
			" 	(select pd.product_id, pd.category_id, pd.sub_category_id, prd.product_discount_id, prd.discount_pct, prd.offer_product, prd.all_offers, prd.discount_end_date" +
			" 	from product_dept as pd " +
			" 	left join product_discount as prd on pd.product_id = prd.product_id and utc_timestamp() > prd.discount_start_date and prd.discount_end_date is null or prd.discount_end_date > utc_timestamp()) as pview" +
			" left join product as p1 on pview.offer_product = p1.product_id, product as p, category as c, sub_category as sc, sub_category_name as scn" +
			" where pview.category_id in (:categoryId) and pview.product_id = p.product_id and p.product_status_type_id = :productStatusTypeId" +
			" and pview.category_id = c.category_id and pview.sub_category_id = sc.sub_category_id and sc.sub_category_name_id = scn.sub_category_name_id";

	private static Logger logger = Logger.getLogger(HomePageDaoImpl.class);

	
	public List<Product> getHomePageCategories(Long categoryId)
			throws GroceriesKartException {
		logger.warn("Entering getHomePageCategories method " + System.currentTimeMillis());
		List<Product> products = null;
		try {
			if (categoryId != null) {
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("categoryId", categoryId);
				paramMap.put("productStatusTypeId", ProductStatusEnum.IN_STOCK.id());
				products = getNamedParameterJdbcTemplate().query(
						GET_ALL_PRODUCTS_BY_CATEGORYIDS, paramMap,
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
		} catch (Exception exp) {
			logger.error("Error while pulling Home Page Categories", exp);
			throw new GroceriesKartException("Error while pulling Home Page Categories.");
		} finally {
			logger.warn("Exit getHomePageCategories method " + System.currentTimeMillis());
		}

		return products;
	}
}
