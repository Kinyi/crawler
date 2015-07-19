package cn.crxy.crawler.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExp {
	public static String parse(String regexp,String url){
		Pattern pattern = Pattern.compile(regexp);
		Matcher matcher = pattern.matcher(url);
		if (matcher.find()) {
			return matcher.group();
		}else {
			return null;
		}
	}
}
