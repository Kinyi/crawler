package cn.crxy.crawler.duplicatable;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HashSetDuplicatable implements Duplicatable {
	Logger logger = LoggerFactory.getLogger(getClass());
	Set<String> duplicatable = new HashSet<String>();

	public boolean is(String target) {
		return duplicatable.contains(target);
	}

	public void add(String target) {
		duplicatable.add(target);
		logger.info("{}的当前大小是{}",getClass().getName(),duplicatable.size());
	}

}
