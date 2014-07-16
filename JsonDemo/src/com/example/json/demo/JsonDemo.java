package com.example.json.demo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonDemo {

	public static void main(String[] args) {
		JSONArray jArray = new JSONArray();
		
		JSONObject json = new JSONObject();
		json.put("name", "zhangsan");
		json.put("age", "25");
		jArray.add(json);
		
		JSONObject json1 = new JSONObject();
		json1.put("name", "lisi");
		json1.put("age", "27");
		jArray.add(json1);
		
		System.out.println(jArray);
	}

}
