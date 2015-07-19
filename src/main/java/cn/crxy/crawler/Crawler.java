package cn.crxy.crawler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.crxy.crawler.download.Downloadable;
import cn.crxy.crawler.download.HttpClientDownload;
import cn.crxy.crawler.duplicatable.Duplicatable;
import cn.crxy.crawler.duplicatable.HashSetDuplicatable;
import cn.crxy.crawler.process.JdProcess;
import cn.crxy.crawler.process.Processable;
import cn.crxy.crawler.repository.MemoryRepository;
import cn.crxy.crawler.repository.Repository;
import cn.crxy.crawler.store.ConsoleStore;
import cn.crxy.crawler.store.FileStore;
import cn.crxy.crawler.store.Storable;
import cn.crxy.crawler.utils.SleepUtil;

public class Crawler {
	Logger logger = LoggerFactory.getLogger(getClass());
	Downloadable downloadable = new HttpClientDownload();
	Processable processable;
	Storable storable = new ConsoleStore();
	Repository repository = new MemoryRepository();
	Duplicatable duplicatable = new HashSetDuplicatable();

	/**
	 * 启动爬虫 
	 */
	public void start() {
		check();
		while (!Thread.currentThread().isInterrupted()) {
			//取出目标url
			String url = repository.poll();
			
			//TODO 如果为空，则等待
			
			//下载
			Page page = download(url);
			//解析
			this.process(page);
			//把目标url放到队列中
			for (String nextUrl : page.getTargetUrls()) {
				if (duplicatable.is(nextUrl)) {
					continue;
				}
				duplicatable.add(nextUrl);
				if (nextUrl.startsWith("http://list.jd.com/")) {
					repository.addHigh(nextUrl);
				}else {
					repository.add(nextUrl);
				}
			}
			//导出
			this.store(page);
			SleepUtil.sleep(1000);
		}
	}
	
	/**
	 * 检查运行环境是否齐备
	 */
	private void check() {
		String message = "请设置Processable";
		if (processable==null) {
			logger.error(message);
			throw new RuntimeException(message);
		}
		logger.info("=======================================================");
		logger.info("downloadable是{}",getDownloadable().getClass().getName());
		logger.info("processable是{}",getProcessable().getClass().getName());
		logger.info("storable是{}",getStorable().getClass().getName());
		logger.info("repository是{}",getRepository().getClass().getName());
		logger.info("duplicatable是{}",getDuplicatable().getClass().getName());
		logger.info("=======================================================");
	}

	/* (non-Javadoc)
	 * @see cn.crxy.crawler.Processable#process(cn.crxy.crawler.Page)
	 */
	public void process(Page page) {
		this.processable.process(page);
	}

	/* (non-Javadoc)
	 * @see cn.crxy.crawler.Storable#store(cn.crxy.crawler.Page)
	 */
	public void store(Page page) {
		// TODO Auto-generated method stub
		this.storable.store(page);
	}

	public Downloadable getDownloadable() {
		return downloadable;
	}

	public void setDownloadable(Downloadable downloadable) {
		this.downloadable = downloadable;
	}

	public Page download(String url) {
		// TODO Auto-generated method stub
		return this.downloadable.download(url);
	}

	public Processable getProcessable() {
		return processable;
	}

	public Crawler setProcessable(Processable processable) {
		this.processable = processable;
		return this;
	}

	public Storable getStorable() {
		return storable;
	}

	public Crawler setStorable(Storable storable) {
		this.storable = storable;
		return this;
	}

	public Crawler setSeedUrl(String url) {
		this.repository.add(url);
		return this;
	}

	public Repository getRepository() {
		return repository;
	}

	public void setRepository(Repository repository) {
		this.repository = repository;
	}

	public Duplicatable getDuplicatable() {
		return duplicatable;
	}

	public void setDuplicatable(Duplicatable duplicatable) {
		this.duplicatable = duplicatable;
	}
	
	public static void main(String[] args) {
		String url = "http://www.jd.com";
		if (args.length == 1) {
			url = args[0];
		}
		Crawler crawler = new Crawler();
		crawler
		.setProcessable(new JdProcess())
		.setStorable(new FileStore("E:\\tmp"))
		.setSeedUrl(url)
		.start();
	}
}
