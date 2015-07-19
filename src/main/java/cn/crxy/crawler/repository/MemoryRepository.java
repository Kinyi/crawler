package cn.crxy.crawler.repository;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 存储url的仓库，一个网页有很多的url存进来，每次读取一个url来进行解析
 */
public class MemoryRepository implements Repository {
	Logger logger = LoggerFactory.getLogger(getClass());
	//高优先级
	Queue<String> highPriority = new ConcurrentLinkedQueue<String>();
	//低优先级
	Queue<String> lowPriority = new ConcurrentLinkedQueue<String>();

	public String poll() {
		if (!highPriority.isEmpty()) {
			return highPriority.poll();
		}
		if (!lowPriority.isEmpty()) {
			return lowPriority.poll();
		}
		return null;
	}

	public void addHigh(String nextUrl) {
		highPriority.add(nextUrl);
	}
	
	public void add(String nextUrl){
		lowPriority.add(nextUrl);
	}

}
