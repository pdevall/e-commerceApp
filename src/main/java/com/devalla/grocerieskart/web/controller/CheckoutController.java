package com.devalla.grocerieskart.web.controller;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.devalla.grocerieskart.common.GroceriesKartException;
import com.devalla.grocerieskart.delegate.AddressDelegate;
import com.devalla.grocerieskart.delegate.MenuDelegate;
import com.devalla.grocerieskart.delegate.OrderDelegate;
import com.devalla.grocerieskart.dto.AddressDto;
import com.devalla.grocerieskart.dto.State;
import com.devalla.grocerieskart.dto.UserAccountInfo;
import com.devalla.grocerieskart.enums.AddressEnum;
import com.devalla.grocerieskart.enums.PaymentTypeEnum;
import com.devalla.grocerieskart.mail.MailSender;
import com.devalla.grocerieskart.types.PaymentType;
import com.devalla.grocerieskart.util.GroceriesKartUtil;
import com.devalla.grocerieskart.util.KeyGenerationUtil;
import com.devalla.grocerieskart.web.common.WebConstants;
import com.devalla.grocerieskart.web.form.AddressForm;
import com.devalla.grocerieskart.web.form.CheckoutForm;
import com.devalla.grocerieskart.web.form.ShoppingCartForm;
import com.devalla.grocerieskart.web.validator.CheckoutValidator;

@Controller
@RequestMapping("/checkout")
@SessionAttributes("checkoutForm")
public class CheckoutController {
	
	private static Logger logger = Logger.getLogger(CheckoutController.class);
	
	@Inject
	private AddressDelegate addressDelegate;
	
	@Inject
	private MenuDelegate menuDelegate;
	
	@Inject
	private OrderDelegate orderDelegate;
	
	@Inject
	private MailSender mailSender;
	
	@InitBinder("checkoutForm")
    protected void initBinder(WebDataBinder binder) { 
		binder.setValidator(new CheckoutValidator());
    }
	
	@RequestMapping(value="/selectShippingAddress")
	@SuppressWarnings("unchecked")
    public ModelAndView shippingAddress(HttpServletRequest request) throws GroceriesKartException  {
		ModelAndView mav = new ModelAndView("shippingAddress");
		HttpSession session = request.getSession(false);
		UserAccountInfo userAccountInfo = (UserAccountInfo) session.getAttribute(WebConstants.USER_ACCOUNT_OBJ);
		CheckoutForm checkoutForm = (CheckoutForm) session.getAttribute("checkoutForm");
		if (checkoutForm == null) {
			checkoutForm = new CheckoutForm();
			AddressForm addressForm = new AddressForm();
			checkoutForm.setAddressForm(addressForm);
			session.setAttribute("checkoutForm", checkoutForm);
			mav.addObject("checkoutForm", checkoutForm);
		}
		Map<String, String> cities = (Map<String, String>) session.getAttribute(WebConstants.CITIES);
		List<AddressDto> shippingAddresses = addressDelegate.findAddresses(userAccountInfo.getCustomerId(), AddressEnum.SHIPPING.id());
		session.setAttribute(WebConstants.SHIPPING_ADDRESSES, shippingAddresses);
		if (cities == null) {
			cities = GroceriesKartUtil.getCities();
			session.setAttribute(WebConstants.CITIES, cities);
		}
		List<State> states = (List<State>) session.getAttribute(WebConstants.STATES);
		if (states == null) {
			states = menuDelegate.getStates();
			session.setAttribute(WebConstants.STATES, states);
		}
		return mav;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/selectShipAddress")
	public ModelAndView selectedAddress(@ModelAttribute("checkoutForm") CheckoutForm checkoutForm, HttpServletRequest request) throws GroceriesKartException  {
		HttpSession session = (HttpSession) request.getSession(false);
		ModelAndView mav = new ModelAndView("preview");
		List<AddressDto> shippingAddress = (List<AddressDto>) session.getAttribute(WebConstants.SHIPPING_ADDRESSES);
		AddressDto addressDto = findSelectedAddressDto(checkoutForm.getSelectedShippingAddrId(), shippingAddress);
		ShoppingCartForm scForm = (ShoppingCartForm) session.getAttribute(WebConstants.SHOPPING_KART_FORM);
		scForm.setSelectedAddressDto(addressDto);
		List<PaymentType> paymentTypes = menuDelegate.getPaymentTypes();
		mav.addObject(WebConstants.PAYMENT_TYPES, paymentTypes);

		return mav;
	}
	
	private AddressDto findSelectedAddressDto(Long selectedShippingAddrId,
			List<AddressDto> shippingAddress) {
		AddressDto addressDto = null;
		if (selectedShippingAddrId != null && shippingAddress != null) {
			for (AddressDto addDto : shippingAddress) {
				if (addDto.getAddressId().longValue() == selectedShippingAddrId.longValue()) {
					addressDto = addDto;
					break;
				}
			}
 		}
		return addressDto;
	}

	@RequestMapping(value="/addShipAddress")
	public ModelAndView addShipAddress(@ModelAttribute("checkoutForm") @Valid CheckoutForm checkoutForm, BindingResult result, HttpServletRequest request) throws GroceriesKartException  {
		HttpSession session = (HttpSession) request.getSession(false);
		UserAccountInfo userAccountInfo = (UserAccountInfo) session.getAttribute(WebConstants.USER_ACCOUNT_OBJ);
		ModelAndView mav = new ModelAndView("preview");	
		if (result.hasErrors()) {
			mav.setViewName("shippingAddress");
		} else {
			Long addressId = addressDelegate.insertAddress(checkoutForm.getAddressForm(), userAccountInfo.getCustomerId());
			List<AddressDto> shippingAddresses = addressDelegate.findAddresses(userAccountInfo.getCustomerId(), AddressEnum.SHIPPING.id());
			session.setAttribute(WebConstants.SHIPPING_ADDRESSES, shippingAddresses);
			AddressDto addressDto = findSelectedAddressDto(addressId, shippingAddresses);
			ShoppingCartForm scForm = (ShoppingCartForm) session.getAttribute(WebConstants.SHOPPING_KART_FORM);
			scForm.setSelectedAddressDto(addressDto);
			checkoutForm = (CheckoutForm) session.getAttribute("checkoutForm");
			if (checkoutForm != null) {
				checkoutForm = new CheckoutForm();
				AddressForm addressForm = new AddressForm();
				checkoutForm.setAddressForm(addressForm);
				session.setAttribute("checkoutForm", checkoutForm);
				mav.addObject("checkoutForm", checkoutForm);
			}
			List<PaymentType> paymentTypes = menuDelegate.getPaymentTypes();
			mav.addObject(WebConstants.PAYMENT_TYPES, paymentTypes);
		}
		return mav;
	}
	
	@RequestMapping(value="/pay")
	public @ResponseBody int payment(@RequestBody CheckoutForm checkoutForm, HttpServletRequest request) throws GroceriesKartException  {
		int value = 0;
		try {
			HttpSession session = (HttpSession) request.getSession(false);
			UserAccountInfo userAccountInfo = (UserAccountInfo) session.getAttribute(WebConstants.USER_ACCOUNT_OBJ);
			ShoppingCartForm scForm = (ShoppingCartForm) session.getAttribute(WebConstants.SHOPPING_KART_FORM);
			scForm.setPaymentTypeId(checkoutForm.getPaymentType());
			String orderNumber = KeyGenerationUtil.getTransactionNumber();
			scForm.setOrderNumber(orderNumber);
			int[] values = orderDelegate.persistOrder(userAccountInfo, scForm);
			if (values != null && values.length > 0) {
				if (scForm.getPaymentTypeId().longValue() == PaymentTypeEnum.COD.id().longValue()) {
					value = 1;
					if (values != null && values.length > 0) {
						mailSender.sendOrderReceiptEmail(userAccountInfo, scForm);
					}
				} else if (scForm.getPaymentTypeId().longValue() == PaymentTypeEnum.NB.id().longValue()) {
					value = 2;
				} else if (scForm.getPaymentTypeId().longValue() == PaymentTypeEnum.CRD.id().longValue()) {
					value = 3;
				}
			} else {
				value = 0;
			}
		} catch (Exception e) {
			logger.error("Error while persisting the order", e);
		}
		return value;
	}

}
