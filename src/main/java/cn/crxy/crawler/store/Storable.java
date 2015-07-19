package cn.crxy.crawler.store;

import cn.crxy.crawler.Page;

public interface Storable {

	/**
	 * 存储下载的数据
	 * @param page
	 */
	public abstract void store(Page page);

}