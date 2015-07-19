package cn.crxy.crawler.utils;

import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;

import org.htmlcleaner.HtmlCleaner;
import org.json.JSONArray;
import org.json.JSONObject;

public class JSONUtils {
	public static String parseFromUrl(String url){
		try {
			return (new HtmlCleaner()).clean(new URL(url)).getText().toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String parseJSONArrayIndex0(String jsonString,String  key){
		JSONArray array = new JSONArray(jsonString);
		JSONObject obj = (JSONObject)array.get(0);
		return obj.getString(key);
	}
	
	public static String parseMap(Map<String , String> values){
		JSONObject jsonObject = new JSONObject();
		for (Entry<String , String> entry : values.entrySet()) {
			jsonObject.put(entry.getKey(), entry.getValue());
		}
		return jsonObject.toString();
	}
}
