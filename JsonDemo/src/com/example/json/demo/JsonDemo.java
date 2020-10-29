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
		
		JSONObject jo = new JSONObject();
		jo.put("xxx", "xxx");
		jo.put("zzz", getkIdsJSONArray("233,3425,2,3"));
		System.out.println(jo);
		
		String zzz = jo.getString("zzz");
		System.out.println(zzz);
		JSONArray ja = JSONArray.fromObject(zzz);
		System.out.println(ja);
	}

	public static JSONArray getkIdsJSONArray(String kIds) {
		JSONArray ja = new JSONArray();
		if (kIds == null || kIds.isEmpty()) return ja;
		String[] ss = kIds.split(",");
		for (String s : ss) {
			if (s == null) continue;
		    s = s.trim();
		    if (s.isEmpty()) continue;
		    ja.add(Integer.parseInt(s));
		}
		return ja;
	}
}
