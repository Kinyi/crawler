package cn.crxy.crawler.duplicatable;

import java.nio.charset.Charset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

/**
 * 布隆过滤器(bloom filter)是通过时间换空间的方式判重的。
 * 通过增加位数、增加哈希函数个数来提高准确率
 * @author martin
 *
 */
public class BloomFilterDuplicatable implements Duplicatable {
	Logger logger = LoggerFactory.getLogger(getClass());
	BloomFilter<String> bloomFilter = BloomFilter.create(Funnels.stringFunnel(Charset.forName("UTF-8")), 99999, 0.001);
	

	public boolean is(String target) {
		return bloomFilter.mightContain(target);
	}

	public void add(String target) {
		bloomFilter.put(target);
	}

}
