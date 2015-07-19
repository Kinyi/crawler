package cn.crxy.crawler.process;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.crxy.crawler.Page;
import cn.crxy.crawler.utils.HtmlXParser;
import cn.crxy.crawler.utils.JSONUtils;
import cn.crxy.crawler.utils.RegExp;

public class JdProcess implements Processable {
	Logger logger = LoggerFactory.getLogger(getClass());

	public void process(Page page) {
		String rawHtml = page.getRawHtml();
		if (rawHtml == null) {
			return;
		}
		String url = page.getUrl();
		//处理产品明细
		if (url.startsWith("http://item.jd.com/")) {
			parseProduct(page, rawHtml, url);
		}
		
		String as = (new HtmlXParser(page.getRawHtml())).select("//a").getAttributeByName("href");
		if (as!=null) {
			for (String nextUrl : as.split(",")) {
				if (nextUrl.startsWith("javascript:")) {
					continue;
				}
				page.addTargetUrl(nextUrl);
			}
		}
	}

	private void parseProduct(Page page, String rawHtml, String url) {
		//解析分类
		String style = new HtmlXParser(rawHtml).select("//div[@class='breadcrumb']//a").text();
		page.addField("style",style);
		
		//解析名称
		String name = (new HtmlXParser(rawHtml)).select("//div[@id='name']").text();
		page.addField("name",name);
		
		//请求价格
		String skuId = RegExp.parse("\\d+", url);
		String priceUrl = "http://p.3.cn/prices/get?skuId=J_"+skuId;
		
		String priceJSON = JSONUtils.parseFromUrl(priceUrl);
//			String priceJSON = (new HttpClientDownload()).execute(priceUrl);  //也可以使用这种方式得到priceJSON
		String price = JSONUtils.parseJSONArrayIndex0(priceJSON, "p");
		page.addField("price", price);
//		System.out.println("价格"+price);
	}

}
