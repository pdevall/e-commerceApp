package com.devalla.grocerieskart.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.devalla.grocerieskart.common.GroceriesKartException;
import com.devalla.grocerieskart.dao.OrderDao;
import com.devalla.grocerieskart.dto.FrontendProduct;
import com.devalla.grocerieskart.dto.UserAccountInfo;
import com.devalla.grocerieskart.enums.OrderStatusEnum;
import com.devalla.grocerieskart.enums.PaymentStatusEnum;
import com.devalla.grocerieskart.enums.PaymentTypeEnum;
import com.devalla.grocerieskart.web.form.ShoppingCartForm;

public class OrderDaoImpl extends BasicDaoImpl implements OrderDao {
	
	private static Logger logger = Logger.getLogger(OrderDaoImpl.class);
	
	private String SELECT_PRODUCT_QUANTITY = "select quantity from product_quantity where product_id = ?";
	
	
	public int[] persistOrder(UserAccountInfo userAccountInfo, ShoppingCartForm shoppingCartForm) throws GroceriesKartException {
		
		int[] value = null;
		try {
		
			String ORDERS_SQL  = "INSERT INTO orders (ORDER_ID, CUSTOMER_ID, ORDER_NUM, ORDER_AMOUNT, SHIP_BILLING_ADDRESS, DISCOUNT_APPLIED, CURRENT_ORDER_STATUS_ID, GLOBAL_DISCOUNT_ID, ORDER_ADDL_DISCOUNT, PAYMENT_TYPE_ID, PAYMENT_TRAN_NUM, PAYMENT_STATUS_ID, CREATE_DATE_TIME, UPDATE_DATE_TIME) VALUES (";
			String CUSTOMER_ORDER = "INSERT INTO customer_order (CUSTOMER_ORDER_ID, CUSTOMER_ID, ORDER_ID, PRODUCT_ID, PRODUCT_DISCOUNT_ID, ORDER_STATUS_ID, QUANTITY, PROD_ORG_COST, PROD_ADDL_DISCOUNT, PROD_COST_AFTER_DISC, TOTAL_PROD_COST, CREATE_DATE_TIME, UPDATED_BY, UPDATE_DATE_TIME) VALUES (";
			String ORDER_STATUS = "INSERT INTO order_status (ORDER_STATUS_ID, ORDER_ID, ORDER_STATUS_TYPE_ID, CREATE_DATE_TIME) VALUES (";
			String ORDER_COMMENTS = "INSERT INTO order_comments (ORDER_COMMENTS_ID, ORDER_ID, COMMENT_TEXT, UPDATE_BY, CREATE_DATE_TIME) VALUES (";
			String UPDATE_PRODUCT_QUANTITY = "UPDATE product_quantity SET QUANTITY = ";
					
			List<String> sqlList = new ArrayList<String>();			
			long orderId = getOrderSequence();
			long orderStatusId = getGlobalSequence();
			StringBuffer orderSB = new StringBuffer();
			orderSB.append(ORDERS_SQL);
			orderSB.append(orderId);
			orderSB.append(",");
			orderSB.append(userAccountInfo.getCustomerId());
			orderSB.append(",");
			orderSB.append("'"+ shoppingCartForm.getOrderNumber() + "'");
			orderSB.append(",");
			orderSB.append(shoppingCartForm.getTotalShoppingCostWithGlobalDiscount());
			orderSB.append(",");
			orderSB.append(true);
			orderSB.append(",");
			orderSB.append(shoppingCartForm.discountApplied());
			orderSB.append(",");
			orderSB.append(orderStatusId);
			orderSB.append(",");
			if (shoppingCartForm.getGlobalDiscount() != null)
				orderSB.append(shoppingCartForm.getGlobalDiscount().getGlobalDiscountId());
			else 
				orderSB.append("null");
			
			orderSB.append(",");
			orderSB.append(new BigDecimal("0"));
			orderSB.append(",");
			orderSB.append(shoppingCartForm.getPaymentTypeId());
			orderSB.append(",");
			if (shoppingCartForm.getPaymentTypeId().longValue() == PaymentTypeEnum.COD.id().longValue())
				orderSB.append("null");
			else 
				orderSB.append(shoppingCartForm.getTransactionNumber());
			orderSB.append(",");
			if (shoppingCartForm.getPaymentTypeId() != null) {
				if (shoppingCartForm.getPaymentTypeId().longValue() == PaymentTypeEnum.COD.id().longValue()) {
					orderSB.append(PaymentStatusEnum.CASH_ON_DELIVERY.id());
				} else if ((shoppingCartForm.getPaymentTypeId().longValue() == PaymentTypeEnum.CRD.id().longValue()) ||
						(shoppingCartForm.getPaymentTypeId() == PaymentTypeEnum.NB.id())) {
					orderSB.append(PaymentStatusEnum.PAYMENT_INITIATED.id());
				}
			}		
			orderSB.append(",");
			orderSB.append("utc_timestamp()");
			orderSB.append(",");
			orderSB.append("utc_timestamp()");
			orderSB.append(")");
			
			StringBuffer osBuffer = new StringBuffer();
			osBuffer.append(ORDER_STATUS);
			osBuffer.append(orderStatusId);
			osBuffer.append(",");
			osBuffer.append(orderId);
			osBuffer.append(",");
			osBuffer.append(OrderStatusEnum.ORDER_RECEIVED.id());
			osBuffer.append(",");
			osBuffer.append("utc_timestamp()");
			osBuffer.append(")");
			
			StringBuffer ocBuffer = new StringBuffer();
			ocBuffer.append(ORDER_COMMENTS);
			ocBuffer.append(getGlobalSequence());
			ocBuffer.append(",");
			ocBuffer.append(orderId);
			ocBuffer.append(",");
			ocBuffer.append("'Initial Order By Customer'");
			ocBuffer.append(",");
			ocBuffer.append("'" + userAccountInfo.getCustomerFirstname() + "'");
			ocBuffer.append(",");
			ocBuffer.append("utc_timestamp()");
			ocBuffer.append(")");
	
			List<String> customerOrderSql = new ArrayList<String>();
			for (FrontendProduct fp : shoppingCartForm.getShoppedProducts()) {
				StringBuffer customerOrder = new StringBuffer();
				customerOrder.append(CUSTOMER_ORDER);
				customerOrder.append(getGlobalSequence());
				customerOrder.append(",");
				customerOrder.append(userAccountInfo.getCustomerId());
				customerOrder.append(",");
				customerOrder.append(orderId);
				customerOrder.append(",");
				customerOrder.append(fp.getProductId());
				customerOrder.append(",");
				if (fp.getProductDiscount() != null) {
					customerOrder.append(fp.getProductDiscount().getProductDiscountId());
				} else 
					customerOrder.append("null");
				
				customerOrder.append(",");
				customerOrder.append(orderStatusId);
				customerOrder.append(",");
				customerOrder.append(fp.getFrontendQuantity());
				customerOrder.append(",");
				customerOrder.append(fp.getProductCost());
				customerOrder.append(",");
				customerOrder.append(new BigDecimal("0"));
				customerOrder.append(",");
				if (fp.getProductCostAfterDiscount() == null)
					customerOrder.append(fp.getProductCost());
				else {
					customerOrder.append(fp.getProductCostAfterDiscount());
				}
				customerOrder.append(",");
				customerOrder.append(fp.getFrontEndTotalCost());
				customerOrder.append(",");
				customerOrder.append("utc_timestamp()");
				customerOrder.append(",");
				customerOrder.append("'"+userAccountInfo.getCustomerFirstname()+"'");
				customerOrder.append(",");
				customerOrder.append("utc_timestamp()");
				customerOrder.append(")");
				
				customerOrderSql.add(customerOrder.toString());
				
				Integer prodQuantity = getProductQuantity(fp.getProductId());
				if (prodQuantity != null && prodQuantity > 0) {
					StringBuffer updateBuffer = new StringBuffer();				 
					updateBuffer.append(UPDATE_PRODUCT_QUANTITY);
					updateBuffer.append(prodQuantity - fp.getFrontendQuantity());
					updateBuffer.append(", CREATE_DATE_TIME = utc_timestamp() WHERE product_id =");
					updateBuffer.append(fp.getProductId());
					customerOrderSql.add(updateBuffer.toString());
				}
				
			}
			
			sqlList.add(orderSB.toString());
			sqlList.add(osBuffer.toString());
			sqlList.add(ocBuffer.toString());
			sqlList.addAll(customerOrderSql);
			
			value = getJdbcTemplate().batchUpdate(sqlList.toArray(new String[sqlList.size()]));
			
		} catch (Exception e) {
			logger.error("Error while persisting the Order " + e);
			throw new GroceriesKartException("Error while persisting the Order ", e);			
		}
		
		return value;
	}
	
	private Integer getProductQuantity(Long productId) {
		int value = getJdbcTemplate().queryForInt(SELECT_PRODUCT_QUANTITY, new Object[] {productId});
		return value;
	}

}
