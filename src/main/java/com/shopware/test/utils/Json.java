package com.shopware.test.utils;

import org.json.JSONObject;

public class Json {
	
	public static JSONObject converTo(String jsonConvert)  {
		JSONObject json = new JSONObject(jsonConvert);
		return json;
	}

}
