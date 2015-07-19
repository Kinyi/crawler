package cn.crxy.crawler.download;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.crxy.crawler.Page;

public class HttpClientDownload implements Downloadable {
	Logger logger = LoggerFactory.getLogger(getClass());

	/* (non-Javadoc)
	 * @see cn.crxy.crawler.Downloadable#download(java.lang.String)
	 */
	public Page download(String url) {
		Page page = new Page();
		page.setUrl(url);
		
		page.setRawHtml(execute(url));
		return page;
	}

	public String execute(String url) {
		String rawHtml = null;
		//构造HttpClient
		HttpClientBuilder builder = HttpClients.custom();
		builder.setUserAgent("Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:0.9.4)");
		CloseableHttpClient httpClient = builder.build();
		
		HttpGet httpGet = new HttpGet(url);
		CloseableHttpResponse response = null;
		long start = System.currentTimeMillis();
		try {
			//处理response
			response = httpClient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			if (entity!=null) {
				rawHtml = EntityUtils.toString(entity);
				EntityUtils.consume(entity);
			}
			logger.info("解析{}成功，耗时{}毫秒",url,(System.currentTimeMillis()-start));
		}catch (Exception e) {
			e.printStackTrace();
			logger.info("解析{}失败，耗时{}毫秒",url,(System.currentTimeMillis()-start));
//			throw new RuntimeException(e);
		}finally{
			try {
				if (response != null) {
					response.close();
				}
				if (httpClient != null) {
					httpClient.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
//				throw new RuntimeException(e);
			}
		}
		return rawHtml;
	}

}
