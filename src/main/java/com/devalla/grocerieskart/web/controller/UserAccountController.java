package com.devalla.grocerieskart.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.devalla.grocerieskart.common.GroceriesKartException;
import com.devalla.grocerieskart.delegate.UserAccountDelegate;
import com.devalla.grocerieskart.dto.UserAccountInfo;
import com.devalla.grocerieskart.mail.MailSender;
import com.devalla.grocerieskart.util.GroceriesKartUtil;
import com.devalla.grocerieskart.util.KeyGenerationUtil;
import com.devalla.grocerieskart.web.common.WebConstants;
import com.devalla.grocerieskart.web.form.UserAccountForm;
import com.devalla.grocerieskart.web.validator.UserAccountValidator;

@Controller
@RequestMapping("/useraccount")
public class UserAccountController {
	
	@Inject
	private UserAccountDelegate userAccountDelegate;
	
	@Inject
	private MailSender mailSender;
	
	@InitBinder("userAccountForm")
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new UserAccountValidator());
    }
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
    public ModelAndView login(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
        ModelAndView mav = new ModelAndView();
        String fromCheckout = (String) request.getParameter("fromCheckout");
		UserAccountInfo userAccountInfo = (UserAccountInfo) session.getAttribute(WebConstants.USER_ACCOUNT_OBJ);
		if (userAccountInfo == null) {
	        UserAccountForm userAccountForm = new UserAccountForm();
	        if (fromCheckout != null && !fromCheckout.equals(WebConstants.EMPTY_SPACE)) {
	        	userAccountForm.setFromCheckout(new Integer(fromCheckout));
	        }
	        mav.setViewName("noSideMenuLayout");
	        mav.addObject("userAccountForm", userAccountForm);
		} else {
			if (fromCheckout != null && !fromCheckout.equals(WebConstants.EMPTY_SPACE) && Integer.valueOf(fromCheckout) == 1) {
				mav.setViewName("redirect:/shopkart/showkart/shoppingKart");
			} else {
				mav.setViewName("redirect:/useraccount/myAccount.html");
			}
		}		
        return mav;
    }
	
	@RequestMapping(value="/loginSubmit", method = RequestMethod.POST)
    public ModelAndView loginSubmit(@ModelAttribute("userAccountForm") @Valid UserAccountForm userAccountForm, 
    		BindingResult result, HttpServletRequest request) throws GroceriesKartException {
		HttpSession session = request.getSession(false);
        ModelAndView mav = new ModelAndView();
        if (result.hasErrors()) {
        	mav.setViewName("noSideMenuLayout");
        } else {
	        UserAccountInfo userAccountInfo = userAccountDelegate.getUserAccount(userAccountForm);
	        if (userAccountInfo != null) {
	        	if (userAccountForm.getFromCheckout() != null && userAccountForm.getFromCheckout() == 0) {
					mav.setViewName("redirect:/useraccount/myAccount.html");
	        	} else {
	        		mav.setViewName("redirect:/shopkart/showkart/shoppingKart");
	        	}
	        	session.setAttribute(WebConstants.USER_ACCOUNT_OBJ, userAccountInfo);
	        } else {
	        	mav.setViewName("noSideMenuLayout");
	        	result.reject("useraccount.screen.usernotfound");
	        }
        }
        return mav;
    }
	
	@RequestMapping(value="/forgotPassword")
    public @ResponseBody Map<String, List<String>> resetPassword(@RequestParam String email) throws GroceriesKartException {
		Map<String, List<String>> mapObj = new HashMap<String, List<String>>();
		List<String> message = new ArrayList<String>();
		if (email == null || email.equals(WebConstants.EMPTY_SPACE)) { 
			message.add("Email Address is required.");
			mapObj.put("error", message);
			return mapObj;
		} else {
			boolean match = GroceriesKartUtil.checkEmailPattern(email);
	        if (!match) {
	        	message.add("Email Address does not match the Email pattern.");
	        	mapObj.put("error", message);
	        	return mapObj; 
	        }
		}		
		String passwordReset = KeyGenerationUtil.getPasswordRandom();
		boolean success = userAccountDelegate.resetUserPassword(email, passwordReset);
		
		if (success) {
			mailSender.sendPasswordResetEmail(email, passwordReset);
			message.add("Your password has been reset. Please check your email.");
			mapObj.put("successMsg", message);
			return mapObj;
		} else {
			message.add("Please check your Email Address and try again.");
			mapObj.put("error", message);
			return mapObj;
		}
    }
	
	@RequestMapping(value="/register", method = RequestMethod.GET)
    public ModelAndView register(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
        ModelAndView mav = new ModelAndView();
        String fromCheckout = (String) request.getParameter("fromCheckout");
		UserAccountInfo userAccountInfo = (UserAccountInfo) session.getAttribute(WebConstants.USER_ACCOUNT_OBJ);
		if (userAccountInfo == null) {
	        UserAccountForm userAccountForm = new UserAccountForm();
	        if (fromCheckout != null && !fromCheckout.equals(WebConstants.EMPTY_SPACE)) {
	        	userAccountForm.setFromCheckout(new Integer(fromCheckout));
	        }
	        mav.setViewName("register");
	        mav.addObject("userAccountForm", userAccountForm);
		} else {
			if (fromCheckout != null && !fromCheckout.equals(WebConstants.EMPTY_SPACE) && Integer.valueOf(fromCheckout) == 1) {
				mav.setViewName("redirect:/shopkart/showkart/shoppingKart");
			} else {
				mav.setViewName("redirect:/useraccount/myAccount.html");
			}
		}		
        return mav;
    }
	
	@RequestMapping(value="/registerSubmit", method = RequestMethod.POST)
    public ModelAndView registerSubmit(@ModelAttribute("userAccountForm") @Valid UserAccountForm userAccountForm, 
    		BindingResult result, HttpServletRequest request) throws GroceriesKartException {
		HttpSession session = request.getSession(false);		
        ModelAndView mav = new ModelAndView();
        if (result.hasErrors()) {
        	mav.setViewName("register");
        } else {
        	boolean success = userAccountDelegate.createUserAccount(userAccountForm);
        	if (success) {
        		UserAccountInfo userAccountInfo = userAccountDelegate.getUserAccount(userAccountForm);
        		session.setAttribute(WebConstants.USER_ACCOUNT_OBJ, userAccountInfo);
        		if (userAccountForm.getFromCheckout() != null && userAccountForm.getFromCheckout() == 0) {
    				mav.setViewName("redirect:/useraccount/myAccount.html");
	        	} else {
	        		mav.setViewName("redirect:/shopkart/showkart/shoppingKart");
	        	}
	        	mailSender.sendAccountCreationEmail(userAccountForm.getUserEmailAddress());
        	} else {
        		mav.setViewName("register");
        		result.reject("useraccount.screen.userexist");
        	}
        }
        return mav;
    }
	
	@RequestMapping("/myAccount")
    public ModelAndView myAccount(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("myAccount");
        return mav;
    }
	
	@RequestMapping(value="/changePassword")
    public @ResponseBody Map<String, List<String>> changePassword(@RequestParam String emailAddress, 
    																@RequestParam String currentPassword,
    																@RequestParam String newPassword,
    																@RequestParam String confirmNewPassword, HttpServletRequest request) throws GroceriesKartException {
		HttpSession session = request.getSession(false);
		Map<String, List<String>> mapObj = new HashMap<String, List<String>>();
		List<String> messages = validatePassword(emailAddress, currentPassword, newPassword, confirmNewPassword);
		if (messages.size() <= 0) {		
			boolean success = userAccountDelegate.updateUserAccountPasswordOrEmailAddress(emailAddress, null, currentPassword, newPassword, false);	
			if (success) {
				mailSender.sendPasswordUpdateConfirmationEmail(emailAddress);
				messages.add("Your Password has been updated successfully.");
				mapObj.put("successMsg", messages);
				if (session != null) {
					session.invalidate();
				}
			} else {
				messages.add("Please check your Deatils and try again. If problem persists contact our Customer Service.");
				mapObj.put("error", messages);				
			}
		} else {
			mapObj.put("error", messages);
		}
		return mapObj;
    }
	
	private List<String> validatePassword(String emailAddress, String currentPassword, String newPassword, String confirmNewPassword) {
		List<String> message = new ArrayList<String>();
		if (emailAddress == null || emailAddress.trim().equals(WebConstants.EMPTY_SPACE)) { 
			message.add("Email Address is required.");
		} else {
	        boolean match = GroceriesKartUtil.checkEmailPattern(emailAddress);
	        if (!match) {
	        	message.add("Email Address does not match the Email pattern.");
	        }
		}		
		if (currentPassword == null || currentPassword.trim().equals(WebConstants.EMPTY_SPACE)) {
			message.add("Current Password is required.");
		}
		if (newPassword == null || newPassword.trim().equals(WebConstants.EMPTY_SPACE)) {
			message.add("New Password is required.");
		}
		if (confirmNewPassword == null || confirmNewPassword.trim().equals(WebConstants.EMPTY_SPACE)) {
			message.add("Confirm New Password is required.");
		}
		if (newPassword != null && !newPassword.trim().equals(WebConstants.EMPTY_SPACE) &&
				confirmNewPassword != null && !confirmNewPassword.trim().equals(WebConstants.EMPTY_SPACE)) {
			if (!newPassword.equals(confirmNewPassword)) {
				message.add("New and Confirm New Password's does not match.");
			}
		}
		if (newPassword != null && newPassword.trim().length() < WebConstants.PASSWORD_LENGTH.intValue()) {
			message.add("Password should be atleast 6 characters.");
		}
		return message;
		
	}
	
	@RequestMapping(value="/changeEmailAddress")
    public @ResponseBody Map<String, List<String>> changeEmailAddress(@RequestParam String currentEmailAddress, 
    																@RequestParam String newEmailAddress,
    																@RequestParam String confirmNewEmailAddress,
    																@RequestParam String password) throws GroceriesKartException {
		Map<String, List<String>> mapObj = new HashMap<String, List<String>>();
		List<String> messages = validateEmailAddress(currentEmailAddress, newEmailAddress, confirmNewEmailAddress, password);
		if (messages.size() <= 0) {		
			boolean success = userAccountDelegate.updateUserAccountPasswordOrEmailAddress(currentEmailAddress, newEmailAddress, password, null, true);	
			if (success) {
				mailSender.sendPasswordUpdateConfirmationEmail(currentEmailAddress);
				mailSender.sendPasswordUpdateConfirmationEmail(newEmailAddress);
				messages.add("Your Email Address has been updated successfully.");
				mapObj.put("successMsg", messages);
			} else {
				messages.add("Please check your Deatils and try again. If problem persists contact our Customer Service.");
				mapObj.put("error", messages);				
			}
		} else {
			mapObj.put("error", messages);
		}
		return mapObj;
    }

	private List<String> validateEmailAddress(String currentEmailAddress,
			String newEmailAddress, String confirmNewEmailAddress,
			String password) {
		List<String> message = new ArrayList<String>();
		
		if (currentEmailAddress == null || currentEmailAddress.trim().equals(WebConstants.EMPTY_SPACE)) { 
			message.add("Current Email Address is required.");
		} else {
	        boolean match = GroceriesKartUtil.checkEmailPattern(currentEmailAddress);
	        if (!match) {
	        	message.add("Current Email Address does not match the Email pattern.");
	        }
		}		
		if (newEmailAddress == null || newEmailAddress.trim().equals(WebConstants.EMPTY_SPACE)) { 
			message.add("New Email Address is required.");
		} else {
	        boolean match = GroceriesKartUtil.checkEmailPattern(newEmailAddress);
	        if (!match) {
	        	message.add("New Email Address does not match the Email pattern.");
	        }
		}		
		if (confirmNewEmailAddress == null || confirmNewEmailAddress.trim().equals(WebConstants.EMPTY_SPACE)) { 
			message.add("Confirm Email Address is required.");
		} else {
	        boolean match = GroceriesKartUtil.checkEmailPattern(confirmNewEmailAddress);
	        if (!match) {
	        	message.add("Confirm Email Address does not match the Email pattern.");
	        }
		}		
		if (password == null || password.trim().equals(WebConstants.EMPTY_SPACE)) {
			message.add("Password is required.");
		}
		if (newEmailAddress != null && !newEmailAddress.trim().equals(WebConstants.EMPTY_SPACE) &&
				confirmNewEmailAddress != null && !confirmNewEmailAddress.trim().equals(WebConstants.EMPTY_SPACE)) {
			if (!newEmailAddress.equals(confirmNewEmailAddress)) {
				message.add("New and Confirm New Email Addresses does not match.");
			}
		}
		
		return message;

	}
	
	
	@RequestMapping(value="/logout")
    public ModelAndView logout(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		session.invalidate();
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/index.html");
         return mav;
    }

}
