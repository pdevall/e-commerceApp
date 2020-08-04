package com.devalla.grocerieskart.web.common;

public interface WebConstants {
	
	static String LOCALE_ATTRIBUTE = "locale";
	static String EMPTY_SPACE = "";
	static String STATIC_CONTENT = "staticContent";
	static String STATIC_CONTENT_URL = "http://localhost";
	static String USER_ACCOUNT_LOGIN = "login";
	static String USER_ACCOUNT_REGISTER = "register";
	static String EMAIL_PATTERN = ".+@.+\\.[a-z]+";
	static String USER_ACCOUNT_OBJ = "userAccountObj";
	static Integer PASSWORD_LENGTH = 6;
	static String COOKIE_NAME = "GroceriesKart";
	static String SSID = "ssid";
	static Integer HOME_PAGE_ROWS = new Integer(4);
	static Long HOME_PAGE_REFRESH_TIME = new Long(300000);
	static String HOME_PAGE_CATEGORIES = "home_page_categories";
	static String HOME_PAGE_CALENDAR = "home_page_calendar";
	static Integer PRODUCT_DISPLAY_RECORDS = new Integer(6);
	static String BREAD_CRUMB = "bread_crumb";
	static String LEFT_MENU_SUB_CATEGORY = "left_menu_category";
	static String LEFT_MENU_BRAND = "left_menu_brand";
	static Integer SIMILAR_CATEGORY_PRODS = new Integer(4);
	static String SHOPPING_KART_FORM = "shoppingCartForm";
	static Integer GLIST_PER_PAGE = new Integer(10);
	static Integer SEARCH_PER_PAGE = new Integer(10);
	static String SHIPPING_ADDRESSES = "shippingAddresses";
	static String CITIES = "cities";
	static String STATES = "states";
	static String PAYMENT_TYPES = "payment_types";
	
}
