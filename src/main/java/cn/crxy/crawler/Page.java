package cn.crxy.crawler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.crxy.crawler.utils.JSONUtils;

public class Page {
	private String url;
	private String rawHtml;
	private Map<String , String> values = new HashMap<String, String>();
	private List<String> targetUrls = new ArrayList<String>();

	public String getRawHtml() {
		return rawHtml;
	}

	public void setRawHtml(String rawHtml) {
		this.rawHtml = rawHtml;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void addField(String key, String value) {
		values.put(key, value);
	}

	public Map<String, String> getValues() {
		return values;
	}

	public void setValues(Map<String, String> values) {
		this.values = values;
	}
	
	public String valuesToJSON(){
		return JSONUtils.parseMap(getValues());
	}

	public void addTargetUrl(String nextUrl) {
		this.targetUrls.add(nextUrl);
	}

	public List<String> getTargetUrls() {
		return targetUrls;
	}

	public void setTargetUrls(List<String> targetUrls) {
		this.targetUrls = targetUrls;
	}
	
}
