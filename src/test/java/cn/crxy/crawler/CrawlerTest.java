package cn.crxy.crawler;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import cn.crxy.crawler.download.HttpClientDownload;
import cn.crxy.crawler.duplicatable.BloomFilterDuplicatable;
import cn.crxy.crawler.duplicatable.HashSetDuplicatable;
import cn.crxy.crawler.process.JdProcess;
import cn.crxy.crawler.repository.MemoryRepository;
import cn.crxy.crawler.store.ConsoleStore;
import cn.crxy.crawler.utils.HtmlXParser;

public class CrawlerTest {
//	String url = "http://item.jd.com/1106216586.html";
	String url = "http://www.jd.com";
	Crawler crawler;
	
	@Before  //每个测试方法执行之前，before都要先执行
	public void before(){
		this.crawler = new Crawler(); //保证每个测试方法都有一个新的Crawler对象，不受污染
		this.crawler.setDownloadable(new HttpClientDownload());
		this.crawler.setProcessable(new JdProcess());
		this.crawler.setStorable(new ConsoleStore());
		this.crawler.setRepository(new MemoryRepository());
		this.crawler.setDuplicatable(new BloomFilterDuplicatable());
		this.crawler.setSeedUrl(url);
	}
	
	@Test 
	public void test(){
		try {
			this.crawler.start();
			Assert.assertTrue(true);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	
	@Test @Ignore
	public void testDownload(){
		Page page = this.crawler.download(url);
		Assert.assertNotNull(page);
		
		String rawHtml = page.getRawHtml();
		Assert.assertNotNull(rawHtml);
		
//		System.out.println(page.getRawHtml());
	}
	
	@Test @Ignore
	public void testProcess(){
		Page page = this.crawler.download(url);
		this.crawler.process(page);
		int size = page.getValues().keySet().size();
		Assert.assertTrue(size > 0);
	}
	
	@Test @Ignore
	public void testHtmlXParser(){
		Page page = this.crawler.download(url);
		/*HtmlXParser htmlXParser = new HtmlXParser(page.getRawHtml());
		String result = htmlXParser.select("//div[@class='breadcrumb']//a").text();*/
		String name = (new HtmlXParser(page.getRawHtml())).select("//div[@id='name']").text();
		System.out.println(name);
		Assert.assertNotNull(name);
	}
	
	@Test @Ignore
	public void testStore(){
		Page page = this.crawler.download(url);
		this.crawler.process(page);
		this.crawler.store(page);
		Assert.assertTrue(true);
	}
	
	@Test @Ignore
	public void testFindAlla(){
		Page page = this.crawler.download(url);
		String as = (new HtmlXParser(page.getRawHtml())).select("//a").getAttributeByName("href");
		System.out.println(as);
	}
}
