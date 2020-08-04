package com.devalla.grocerieskart.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.devalla.grocerieskart.dto.BaseDto;
import com.devalla.grocerieskart.dto.Product;
import com.devalla.grocerieskart.web.common.WebConstants;
import com.devalla.grocerieskart.web.form.BaseForm;

public class GroceriesKartUtil {	

	public static boolean checkEmailPattern(String emailAddress) {
		Pattern p = Pattern.compile(WebConstants.EMAIL_PATTERN);
        Matcher m = p.matcher(emailAddress.trim());
        return m.matches();
	}
	
	
	public static void calculatePages(Integer totalPages,
			BaseDto basDto, int pageNumber, boolean next) {
		int nextPage = 0;
		int previousPage = -1;
		if (next) {
			if (pageNumber < totalPages + 1 ) {
				nextPage = pageNumber + 1;
			} 
			if (pageNumber > 0) {
				previousPage = pageNumber - 1;
			}
		} else {
			if (pageNumber < totalPages) {
				nextPage = pageNumber + 1;
			}
			if (pageNumber > -1) {
				previousPage = pageNumber - 1;
			}
		}
		
		basDto.setNextPage(nextPage);
		basDto.setPreviousPage(previousPage);
		basDto.setTotalNumberOfPages(totalPages);
		
	}
	
	public static void calculatePages(Integer totalPages,
			BaseForm baseForm, int pageNumber, boolean next) {
		int nextPage = 0;
		int previousPage = -1;
		int startNumber = 0;
		int endNumber = 0;
		if (next) {
			if (pageNumber < totalPages + 1 ) {
				nextPage = pageNumber + 1;
			} 
			if (pageNumber > 0) {
				previousPage = pageNumber - 1;
			}
		} else {
			if (pageNumber < totalPages) {
				nextPage = pageNumber + 1;
			}
			if (pageNumber > -1) {
				previousPage = pageNumber - 1;
			}
		}
		if (pageNumber == -1 || pageNumber == 0) {
			baseForm.setStartNumber(new Integer(0));
			baseForm.setEndNumber(new Integer(WebConstants.GLIST_PER_PAGE) - 1);
		} else {
			startNumber = pageNumber * WebConstants.GLIST_PER_PAGE.intValue();
			endNumber = startNumber + WebConstants.GLIST_PER_PAGE.intValue() - 1;
			baseForm.setStartNumber(startNumber);
			baseForm.setEndNumber(endNumber);
		}
		baseForm.setNextPage(nextPage);
		baseForm.setPreviousPage(previousPage);
		baseForm.setTotalNumberOfPages(totalPages);
		
	}
	
	public static void calculatePagesWithOutStartEndNumber(Integer totalPages,
			BaseForm baseForm, int pageNumber, boolean next) {
		int nextPage = 0;
		int previousPage = -1;
		if (next) {
			if (pageNumber < totalPages + 1 ) {
				nextPage = pageNumber + 1;
			} 
			if (pageNumber > 0) {
				previousPage = pageNumber - 1;
			}
		} else {
			if (pageNumber < totalPages) {
				nextPage = pageNumber + 1;
			}
			if (pageNumber > -1) {
				previousPage = pageNumber - 1;
			}
		}
		baseForm.setNextPage(nextPage);
		baseForm.setPreviousPage(previousPage);
		baseForm.setTotalNumberOfPages(totalPages);
		
	}
	
	public static void calculateDiscount(Product product) {		
		if (product.getProductDiscount() != null) {
			if (product.getProductDiscount().getDiscountPercent() != null) {
				BigDecimal discountCost = (product.getProductCost()
						.multiply(product.getProductDiscount()
								.getDiscountPercent())).setScale(2).divide(
						new BigDecimal(100.00), RoundingMode.CEILING);
				product.setDiscountCost(discountCost);
				BigDecimal newProductCost = product.getProductCost().subtract(
						discountCost);
				newProductCost.setScale(2);
				product.setProductCostAfterDiscount(newProductCost);
			}
		}		
	}
	
	public static Map<String, String> getCities() {
		Map<String, String> cities = new HashMap<String, String>();		
		cities.put("Hyderabad", "Hyderabad");
		cities.put("Secunderabad", "Secunderabad");
		
		return cities;
	}
}
