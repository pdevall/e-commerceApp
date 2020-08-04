package com.devalla.grocerieskart.dao;

import java.util.List;

import com.devalla.grocerieskart.common.GroceriesKartException;
import com.devalla.grocerieskart.dto.Brand;


public interface MenuDao extends BasicDao {
	
	public List<Brand> getBrandsBySubCategoryId(Long subCategoryId) throws GroceriesKartException;
	
}
