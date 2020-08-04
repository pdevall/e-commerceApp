package com.devalla.grocerieskart.web.form;

import java.util.ArrayList;
import java.util.List;

import com.devalla.grocerieskart.dto.FrontendProduct;

public class GListForm extends BaseForm {
	
	private List<FrontendProduct> glistProducts;

	public List<FrontendProduct> getGlistProducts() {
		if (glistProducts == null) {
			glistProducts = new ArrayList<FrontendProduct>();
		}
		return glistProducts; 
	}

	public void setGlistProducts(List<FrontendProduct> glistProducts) {
		this.glistProducts = glistProducts;
	}
	
	

}
