package com.devalla.grocerieskart.web.form;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.devalla.grocerieskart.dto.AddressDto;
import com.devalla.grocerieskart.dto.FrontendProduct;
import com.devalla.grocerieskart.dto.GlobalDiscount;
import com.devalla.grocerieskart.web.common.WebConstants;

public class ShoppingCartForm extends BaseForm {
	
	private List<FrontendProduct> shoppedProducts;
	private Map<Long, String> productsAddedMap;
	private GlobalDiscount globalDiscount;
	private AddressDto selectedAddressDto;
	private Long paymentTypeId;
	private String orderNumber;
	private String transactionNumber;

	public List<FrontendProduct> getShoppedProducts() {
		if (shoppedProducts == null) {
			shoppedProducts = new ArrayList<FrontendProduct>();
		}
		return shoppedProducts;
	}
	public String getTransactionNumber() {
		return transactionNumber;
	}
	public void setTransactionNumber(String transactionNumber) {
		this.transactionNumber = transactionNumber;
	}
	public void setShoppedProducts(List<FrontendProduct> shoppedProducts) {
		this.shoppedProducts = shoppedProducts;
	}
	public Map<Long, String> getProductsAddedMap() {
		if (productsAddedMap == null) {
			productsAddedMap = new HashMap<Long, String>();
		}
		return productsAddedMap;
	}

	public void setProductsAddedMap(Map<Long, String> productsAddedMap) {
		this.productsAddedMap = productsAddedMap;
	}
	
	public BigDecimal getTotalShoppingCost() {
		BigDecimal totalShoppingCost = new BigDecimal(0);
		for (FrontendProduct fp: getShoppedProducts()) {
			if (fp.getProductDiscount() != null && fp.getProductCostAfterDiscount() != null) {
				totalShoppingCost = totalShoppingCost.add(fp.getProductCostAfterDiscount().multiply(new BigDecimal(fp.getFrontendQuantity())));
			} else {
				totalShoppingCost = totalShoppingCost.add(fp.getProductCost().multiply(new BigDecimal(fp.getFrontendQuantity())));
			}
		}
		return totalShoppingCost;
	}
	
	public BigDecimal getTotalShoppingCostWithGlobalDiscount() {
		BigDecimal totalShoppingCost = new BigDecimal(0);
		for (FrontendProduct fp: getShoppedProducts()) {
			if (fp.getProductDiscount() != null && fp.getProductCostAfterDiscount() != null) {
				totalShoppingCost = totalShoppingCost.add(fp.getProductCostAfterDiscount().multiply(new BigDecimal(fp.getFrontendQuantity())));
			} else {
				totalShoppingCost = totalShoppingCost.add(fp.getProductCost().multiply(new BigDecimal(fp.getFrontendQuantity())));
			}
		}
		if (globalDiscount != null) {
			totalShoppingCost = totalShoppingCost.subtract(totalShoppingCost.multiply(globalDiscount.getDiscountPercent()));
			totalShoppingCost.setScale(2);
		}
		return totalShoppingCost;
	}
	
	public boolean findProductAdded(Long productId) {
		boolean productAdded = false;
		if (productId != null) {
			String productName = getProductsAddedMap().get(productId);
			if (productName != null && !productName.equals(WebConstants.EMPTY_SPACE)) {
				productAdded = true;
			}
		}
		return productAdded;
	}
	
	public int getProductIndex(Long productId) {
		int index = -1;
		for (int i = 0; i < getShoppedProducts().size(); i++) {
			FrontendProduct fp = getShoppedProducts().get(i);
			if (fp.getProductId().longValue() == productId.longValue()) {
				index = i;
				break;
			}
		}
		return index;
	}
	
	public FrontendProduct findProduct(Long productId) {
		FrontendProduct frontendProduct = null;
		for (int i = 0; i < getShoppedProducts().size(); i++) {
			FrontendProduct fp = getShoppedProducts().get(i);
			if (fp.getProductId().longValue() == productId.longValue()) {
				frontendProduct = fp;
				break;
			}
		}
		return frontendProduct;
	}
	
	public void updateProductQuantity(Long productId) {
		boolean productAdded = findProductAdded(productId);
		if (productAdded) {
			FrontendProduct fp = findProduct(productId);
			if (fp != null) {
				Integer quantity = fp.getFrontendQuantity();
				if (quantity != null) {
					if (quantity.intValue() <= 0) {
						int index = getProductIndex(productId);
						getShoppedProducts().remove(index);
						getProductsAddedMap().remove(productId);
					}
				} 
			}
		}
	}
	
	public void removeProduct(Long productId) {
		boolean productAdded = findProductAdded(productId);
		if (productAdded) {
			FrontendProduct fp = findProduct(productId);
			if (fp != null) {
				int index = getProductIndex(productId);
				getShoppedProducts().remove(index);
				getProductsAddedMap().remove(productId);
			}
		}
	}
	public GlobalDiscount getGlobalDiscount() {
		return globalDiscount;
	}
	public void setGlobalDiscount(GlobalDiscount globalDiscount) {
		this.globalDiscount = globalDiscount;
	}
	public AddressDto getSelectedAddressDto() {
		return selectedAddressDto;
	}
	public void setSelectedAddressDto(AddressDto selectedAddressDto) {
		this.selectedAddressDto = selectedAddressDto;
	}
	
	public boolean discountApplied() {
		boolean discountApplied = false;
		if (getGlobalDiscount() != null) {
			discountApplied = true;
		}
		if (!discountApplied) {
			for (FrontendProduct fp : getShoppedProducts()) {
				if (fp.getProductDiscount() != null) {
					discountApplied = true;
					break;
				}
			}
		}
		
		return discountApplied;
	}
	public void setPaymentTypeId(Long paymentTypeId) {
		this.paymentTypeId = paymentTypeId;		
	}
	
	public Long getPaymentTypeId() {
		return this.paymentTypeId;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;		
	}
	public String getOrderNumber() {
		return orderNumber;		
	}	
}
