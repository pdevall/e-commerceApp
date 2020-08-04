package com.devalla.grocerieskart.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.devalla.grocerieskart.common.GroceriesKartException;
import com.devalla.grocerieskart.delegate.GListDelegate;
import com.devalla.grocerieskart.delegate.MenuDelegate;
import com.devalla.grocerieskart.dto.Category;
import com.devalla.grocerieskart.dto.FrontendProduct;
import com.devalla.grocerieskart.dto.Product;
import com.devalla.grocerieskart.dto.UserAccountInfo;
import com.devalla.grocerieskart.util.GroceriesKartUtil;
import com.devalla.grocerieskart.web.common.WebConstants;
import com.devalla.grocerieskart.web.form.BaseForm;
import com.devalla.grocerieskart.web.form.GListForm;
import com.devalla.grocerieskart.web.form.ShoppingCartForm;
import com.devalla.grocerieskart.web.validator.GListValidator;

@Controller
@RequestMapping("/glist")
@SessionAttributes("glistForm")
public class GListController {
	
	@Inject
	private GListDelegate glistDelegate;
	
	@Inject
	private MenuDelegate menuDelegate;
	
	@InitBinder("glistForm")
    protected void initBinder(WebDataBinder binder) { 
		binder.setValidator(new GListValidator());
    }
	
	@RequestMapping(value="/addgList/product/{productId}")
    public @ResponseBody Integer addToGList(@PathVariable Long productId, HttpServletRequest request) throws GroceriesKartException  {
		int value = -1;
		HttpSession session = request.getSession(false);
		UserAccountInfo userAccountInfo = (UserAccountInfo) session.getAttribute(WebConstants.USER_ACCOUNT_OBJ);
		if (userAccountInfo != null) {
			value = glistDelegate.insertGList(userAccountInfo.getCustomerId(), productId);
		}
		return new Integer(value);
	}
	
	@RequestMapping(value="/findGList")
    public ModelAndView findGList(HttpServletRequest request) throws GroceriesKartException  {
		GListForm glistForm = new GListForm();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("glistView");
		HttpSession session = request.getSession(false);
		UserAccountInfo userAccountInfo = (UserAccountInfo) session.getAttribute(WebConstants.USER_ACCOUNT_OBJ);
		if (userAccountInfo != null) {
			List<FrontendProduct> fep = glistDelegate.getGListProducts(userAccountInfo.getCustomerId());
			if (fep != null && fep.size() > 0) {
				for (FrontendProduct frontendProduct: fep) {
					GroceriesKartUtil.calculateDiscount((Product)frontendProduct);
				}
				int totalPages = new Double(Math.ceil(new Integer(fep.size()).doubleValue() / WebConstants.GLIST_PER_PAGE.doubleValue())).intValue();
				GroceriesKartUtil.calculatePages(totalPages, (BaseForm)glistForm, 0, true);
				glistForm.setGlistProducts(fep);
			}
			session.setAttribute("glistForm", glistForm);
			mav.addObject("glistForm", glistForm);
			List<Category> categories = menuDelegate.getCategories();
			mav.addObject(WebConstants.LEFT_MENU_SUB_CATEGORY, categories);
		}
		return mav;
	}
	
	@RequestMapping(value="/findGList/nextPrevious")
    public ModelAndView nextPreviousGList(@ModelAttribute("glistForm") GListForm glistForm, HttpServletRequest request) throws GroceriesKartException  {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("glistView");
		String strPageNumber = request.getParameter("pageNumber");
		String pageBoolean = request.getParameter("pageBoolean");
		HttpSession session = request.getSession(false);
		UserAccountInfo userAccountInfo = (UserAccountInfo) session.getAttribute(WebConstants.USER_ACCOUNT_OBJ);
		if (userAccountInfo != null && glistForm != null) {
			if (glistForm.getGlistProducts() != null && glistForm.getGlistProducts().size() > 0) {
				List<Category> categories = menuDelegate.getCategories();
				mav.addObject(WebConstants.LEFT_MENU_SUB_CATEGORY, categories);
				int totalPages = new Double(Math.ceil(new Integer(glistForm.getGlistProducts().size()).doubleValue() / WebConstants.GLIST_PER_PAGE.doubleValue())).intValue();
				GroceriesKartUtil.calculatePages(totalPages, (BaseForm)glistForm, new Integer(strPageNumber), new Boolean(pageBoolean));
			}
		}
		mav.addObject("glistForm", glistForm);
		session.setAttribute("glistForm", glistForm);
		return mav;
	}
	
	@RequestMapping(value="/addOrDelete")
    public ModelAndView addOrDelete(@ModelAttribute("glistForm") @Valid GListForm glistForm, BindingResult result, HttpServletRequest request ) throws GroceriesKartException  {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("glistView");
		String strActionString = request.getParameter("actionString");		
		HttpSession session = request.getSession(false);
		UserAccountInfo userAccountInfo = (UserAccountInfo) session.getAttribute(WebConstants.USER_ACCOUNT_OBJ);
		ShoppingCartForm scf = (ShoppingCartForm) session.getAttribute(WebConstants.SHOPPING_KART_FORM);
		if (result.hasErrors()) {
			List<Category> categories = menuDelegate.getCategories();
			mav.addObject(WebConstants.LEFT_MENU_SUB_CATEGORY, categories);

		} else {
			if (userAccountInfo != null && glistForm != null) {
				if (glistForm.getGlistProducts() != null && glistForm.getGlistProducts().size() > 0) {
					if (strActionString != null && strActionString.equals("add")) {
						addToKart(scf, glistForm.getGlistProducts());
						mav.addObject("successMessage", "Selected Products added to the Cart successfully.");
						List<Category> categories = menuDelegate.getCategories();
						mav.addObject(WebConstants.LEFT_MENU_SUB_CATEGORY, categories);
						mav.addObject("glistForm", glistForm);
					} else if (strActionString != null && strActionString.equals("delete")) {
						List<Long> deleteList = deleteFromGList(glistForm.getGlistProducts());
						if (deleteList != null && deleteList.size() > 0) {
							glistDelegate.deleteGListByIds(deleteList);							
							mav.setViewName("redirect:/glist/findGList");
						}
					}
				}
			}
		}
		return mav;
	}

	private List<Long> deleteFromGList(List<FrontendProduct> glistProducts) {
		List<Long> deleteList = new ArrayList<Long>();
		for (FrontendProduct glistFep : glistProducts) {
			if (glistFep.getSelectedProduct() != null && glistFep.getSelectedProduct()) {
				deleteList.add(glistFep.getCustomerGListId());
			}
		}
		return deleteList;
	}

	private void addToKart(ShoppingCartForm scf,
			List<FrontendProduct> glistProducts) {
		for (FrontendProduct glistFep : glistProducts) {
			if (glistFep.getSelectedProduct() != null && glistFep.getSelectedProduct()) {
				FrontendProduct scFep = scf.findProduct(glistFep.getProductId());
				if (scFep == null) {
					scf.getShoppedProducts().add(glistFep);
					scf.getProductsAddedMap().put(glistFep.getProductId(), glistFep.getProductName());
				} else {
					scFep.setFrontendQuantity(scFep.getFrontendQuantity().intValue() + glistFep.getFrontendQuantity().intValue());
				}
			}
		}
	}


}
