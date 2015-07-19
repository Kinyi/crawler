package cn.crxy.crawler.process;

import cn.crxy.crawler.Page;

public interface Processable {

	/**
	 * 解析下载的页面
	 * @param page
	 */
	public abstract void process(Page page);

}