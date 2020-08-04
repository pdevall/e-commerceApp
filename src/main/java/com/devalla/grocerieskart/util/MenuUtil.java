package com.devalla.grocerieskart.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.devalla.grocerieskart.dto.SubCategory;

public final class MenuUtil {

	private static final Logger log = Logger.getLogger(MenuUtil.class);
	private static final String SMTP_PROPERTIES_NAME = "smtp.properties";
	private static final String COMMON_PROPERTIES_NAME = "common.properties";
	private static Properties smtpProperties = new Properties();
	private static Properties commonProperties = new Properties();

	static {
		InputStream smtp = Thread.currentThread().getContextClassLoader()
		.getResourceAsStream(SMTP_PROPERTIES_NAME);
		
		InputStream common = Thread.currentThread().getContextClassLoader()
		.getResourceAsStream(COMMON_PROPERTIES_NAME);		

		if (smtp != null && common != null) {
			// property file found
			try {
				smtpProperties.load(smtp);
				commonProperties.load(common);
			} catch (IOException ex) {
				log.error(
						"Unable to load smtp menu property file from classpath. File name:"
								+ SMTP_PROPERTIES_NAME, ex);

			}
		}
	}

	public static String getSmtpProperty(String key) {
		String value = null;
		if (smtpProperties != null) {
			value = smtpProperties.getProperty(key);
		}
		return value;
	}
	
	public static String getCommonProperty(String key) {
		String value = null;
		if (commonProperties != null) {
			value = commonProperties.getProperty(key);
		}
		return value;
	}
	
	public static List<SubCategory> moveTheSlectedCategoryToTop(List<SubCategory> subCategories, Long selectedSubCategoryId) {
		//SubCategory selected
		return null;
	}
}
