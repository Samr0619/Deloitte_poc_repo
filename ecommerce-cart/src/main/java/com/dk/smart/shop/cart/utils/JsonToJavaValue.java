package com.dk.smart.shop.cart.utils;


public class JsonToJavaValue {

	/**
	 * This methods converts any string which contains "_" to Java style(camelCase)
	 * EX: "no_of_products" to "noOfProducts"
	 * 
	 * @param jsonPropertyName
	 * 
	 * @return String
	 * 
	 */
	public static String convertToJavaStyle(String jsonPropertyName) {
		
		StringBuilder javaPropertyName = new StringBuilder();

		boolean capitalizeNext = false;

		for (char c : jsonPropertyName.toCharArray()) {
			if (c == '_') {
				capitalizeNext = true;
			} else {
				if (capitalizeNext) {
					javaPropertyName.append(Character.toUpperCase(c));
					capitalizeNext = false;
				} else {
					javaPropertyName.append(c);
				}
			}
		}
		return javaPropertyName.toString();
	}
}