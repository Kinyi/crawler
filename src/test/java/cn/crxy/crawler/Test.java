package cn.crxy.crawler;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

import org.apache.http.client.utils.DateUtils;

public class Test {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		/*
		 * StringBuffer sb = new StringBuffer(); String tmp = "abc";
		 * sb.append(tmp.trim()); System.out.println(sb);
		 */

		/*
		 * String date = DateUtils.formatDate(new Date(),
		 * "yyyy-MM-dd HH:mm:ss"); System.out.println(date);
		 * 
		 * long timestamp = System.currentTimeMillis(); Date dat =
		 * DateUtils.parseDate(String.valueOf(timestamp));
		 * System.out.println(timestamp);
		 */

		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,DateFormat.LONG);
		String dateStr = "2013-6-10 03:48:06";
		// 解析字符串到日期对象的 parse 方法.
		Date date2 = dateFormat.parse(dateStr);
		System.out.println(date2);

	}

}
