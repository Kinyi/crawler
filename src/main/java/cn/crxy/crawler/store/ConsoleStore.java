package cn.crxy.crawler.store;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.crxy.crawler.Page;

public class ConsoleStore implements Storable {
	Logger logger = LoggerFactory.getLogger(getClass());

	public void store(Page page) {
//		System.out.println(page.valuesToJSON());
		System.out.println("url="+page.getUrl()+" price="+page.getValues().get("price"));
	}

}
