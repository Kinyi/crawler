package cn.crxy.crawler.repository;

public interface Repository {

	String poll();

	void add(String url);
	
	void addHigh(String url);

}
