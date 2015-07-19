package cn.crxy.crawler.duplicatable;

public interface Duplicatable {

	boolean is(String nextUrl);

	void add(String nextUrl);

}
