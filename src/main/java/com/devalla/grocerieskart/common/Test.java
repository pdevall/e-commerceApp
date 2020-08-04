package com.devalla.grocerieskart.common;

import org.apache.commons.codec.binary.Base64;

public class Test {

	public static void main(String[] args) {
		
				
				String passString = new String(Base64.decodeBase64("Z2thcHBkZXYxMjM=".getBytes()));
				System.out.print(passString);

	}

}
