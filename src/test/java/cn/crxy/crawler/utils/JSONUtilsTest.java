package cn.crxy.crawler.utils;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class JSONUtilsTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		Map<String, String> values = new HashMap<String, String>();
		values.put("name", "martin");
		values.put("age", "21");
		String json = JSONUtils.parseMap(values);
		System.out.println(json);
	}

}
