package com.devalla.grocerieskart.util;

public class KeyGenerationUtil {
	
	public static String getAlphaNumbericRandom() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        int numberOfCodes = 0;
        String code = "";
        while (numberOfCodes < 8) {
            char c = chars.charAt((int) (Math.random() * chars.length()));
            code += c;
            numberOfCodes++;
        }
        return code;
    }
	
	public static String getNumbericRandom() {
        String chars = "0123456789";
        int numberOfCodes = 0;
        String code = "";
        while (numberOfCodes < 4) {
            char c = chars.charAt((int) (Math.random() * chars.length()));
            code += c;
            numberOfCodes++;
        }
        return code;
    }
	
	public static String getTransactionNumber() {
        String chars = "0123456789";
        int numberOfCodes = 0;
        String code = "";
        while (numberOfCodes < 8) {
            char c = chars.charAt((int) (Math.random() * chars.length()));
            code += c;
            numberOfCodes++;
        }
        return code;
    }
	
	public static String getPasswordRandom() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        int numberOfCodes = 0;
        String code = "";
        while (numberOfCodes < 6) {
            char c = chars.charAt((int) (Math.random() * chars.length()));
            code += c;
            numberOfCodes++;
        }
        return code;
    }

}
