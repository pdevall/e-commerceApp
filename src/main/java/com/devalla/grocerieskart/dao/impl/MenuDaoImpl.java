package com.devalla.grocerieskart.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import com.devalla.grocerieskart.common.GroceriesKartException;
import com.devalla.grocerieskart.dao.MenuDao;
import com.devalla.grocerieskart.dto.Brand;
import com.devalla.grocerieskart.dto.Category;
import com.devalla.grocerieskart.dto.SubCategory;
import com.devalla.grocerieskart.dto.impl.BrandImpl;
import com.devalla.grocerieskart.dto.impl.CategoryImpl;
import com.devalla.grocerieskart.dto.impl.SubCategoryImpl;
import com.devalla.grocerieskart.enums.StatusEnum;

public class MenuDaoImpl extends BasicDaoImpl implements MenuDao {
	
	private static Logger logger = Logger.getLogger(MenuDaoImpl.class);

	private static String GET_BRANDS_BY_SUB_CATEGORY_ID = "select b.brand_id, b.sub_category_id, sc.category_id, bn.brand_name, bn.spring_label " +
			" from  brand as b, brand_name as bn, gen_status_type as gst, sub_category as sc where b.sub_category_id = ? and b.brand_status_id = ? and" +
			" b.sub_category_id = sc.sub_category_id and b.brand_name_id = bn.brand_name_id and b.brand_status_id = gst.gen_status_type_id order by bn.brand_name desc";
	
	
	public List<Brand> getBrandsBySubCategoryId(Long subCategoryId) throws GroceriesKartException {
		logger.warn("Entering getBrandsBySubCategoryId method "
				+ System.currentTimeMillis());
		try {
		List<Brand> brands = (List<Brand>) getJdbcTemplate()
				.query(GET_BRANDS_BY_SUB_CATEGORY_ID,
						new Object[] {subCategoryId, StatusEnum.ACTIVE.id() },
						new RowMapper<Brand>() {
							public Brand mapRow(ResultSet rs, int rowNum)
									throws SQLException {
								Brand brand = new BrandImpl();
								brand.setBrandId(rs.getLong("brand_id"));
								brand.setBrandName(rs.getString("brand_name"));
								brand.setSpringLabel(rs
										.getString("spring_label"));
								SubCategory subCategory = new SubCategoryImpl();
								subCategory.setId(rs.getLong("sub_category_id"));
								
								Category category = new CategoryImpl();
								category.setId(rs.getLong("category_id"));
								
								subCategory.setCategory(category);
								
								brand.setSubCategory(subCategory);


								return brand;
							}
						});
		return brands;
		} catch (Exception exp) {
			logger.error("Error while pulling Brands", exp);
			throw new GroceriesKartException("Error while fetching Brand");
		} finally {
			logger.warn("Exit getBrandsBySubCategoryId method "
					+ System.currentTimeMillis());
		}

	}
	
}
