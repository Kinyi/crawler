package cn.crxy.crawler.download;

import cn.crxy.crawler.Page;

public interface Downloadable {

	/**
	 * 爬虫下载数据
	 * @param url
	 * @return 
	 */
	public abstract Page download(String url);

}