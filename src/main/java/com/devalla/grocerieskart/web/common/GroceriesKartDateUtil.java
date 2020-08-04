package com.devalla.grocerieskart.web.common;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.TimeZone;

public class GroceriesKartDateUtil {
	
	public static Calendar getUTCCalender() {
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		return calendar;
	}
	
	public static String getUTCCalenderNoTimeString() {
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		String cal = calendar.get(Calendar.DATE) + "/" + (calendar.get(Calendar.MONTH) + 1) + "/" + calendar.get(Calendar.YEAR);
		return cal;
	}
	
	public static Calendar getCalenderByTimestamp(Timestamp timestamp) {
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		calendar.setTimeInMillis(timestamp.getTime());
		return calendar;
	}
	
	public static String getCalenderByTimeZoneWithNoTimeInString(String timeZone) {
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(timeZone));
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		String cal = calendar.get(Calendar.YEAR) + "/" + (calendar.get(Calendar.MONTH) +1) + "/"  + calendar.get(Calendar.DATE);
		return cal; 
	}
	
	public static String getCalenderString(Integer month, Integer year, boolean endDate) {
		String cal;
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		calendar.set(Calendar.DATE, 1);
		calendar.set(Calendar.MONTH, month - 1);
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		if (endDate)
			cal = calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) +1) + "-"  + calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		else 
			cal = calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) +1) + "-"  + calendar.get(Calendar.DATE);
		
		return cal; 
	}
}
