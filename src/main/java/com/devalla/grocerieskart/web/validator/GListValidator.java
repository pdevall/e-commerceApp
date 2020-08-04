package com.devalla.grocerieskart.web.validator;

import java.util.List;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.devalla.grocerieskart.dto.FrontendProduct;
import com.devalla.grocerieskart.web.form.GListForm;

public class GListValidator implements Validator {

	
	public boolean supports(Class<?> clazz) {
		return GListForm.class.equals(clazz);
	}

	
	public void validate(Object obj, Errors errors) {
		GListForm glistForm = (GListForm) obj;
		List<FrontendProduct> feps = glistForm.getGlistProducts();
		if (glistForm.getActionString() != null && glistForm.getActionString().equals("add")) {
			addGlistValidation(feps, errors);
		} else if(glistForm.getActionString() != null && glistForm.getActionString().equals("delete")){
			deleteGlistValidation(feps, errors);
		}
	}

	private void deleteGlistValidation(List<FrontendProduct> feps, Errors errors) {
		boolean selectedProduct = false;
		if (feps != null && feps.size() > 0) {
			for (FrontendProduct fep : feps) {
				if (fep.getSelectedProduct() != null && fep.getSelectedProduct()) {					
					selectedProduct = true;
				}
			}
			if (!selectedProduct) {
				errors.reject("glist.select.product.delete");
			}
		}		
	}

	private void addGlistValidation(List<FrontendProduct> feps, Errors errors) {
		boolean selectedProduct = false;
		boolean errorMessage = true;
		if (feps != null && feps.size() > 0) {
			for (FrontendProduct fep : feps) {
				if (fep.getSelectedProduct() != null && fep.getSelectedProduct().booleanValue()) {
					selectedProduct = true;
				}
				if (selectedProduct) {
					if (fep.getFrontendQuantity() != null && fep.getFrontendQuantity().intValue() > 0) {
						errorMessage = false;
					} else {
						selectedProduct = false;
						errorMessage = true;
					}
				}
			}
			if (errorMessage) {
				errors.reject("glist.select.product");
			}
		}
	}

}
