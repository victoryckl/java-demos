package com.example.json.demo2;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonDemo2 {

	public static void main(String[] args) throws JSONException {
		JSONArray jArray = new JSONArray("[{\"name\":\"zhangsan\",\"age\":\"25\"},{\"name\":\"lisi\",\"age\":\"27\"}]");
		System.out.println(jArray);
		
		JSONObject json = new JSONObject("{\"name\":\"zhangsan\",\"age\":\"25\"}");
		System.out.println(json);
	}
}
