package cn.crxy.crawler;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class SpiderTest {

	public static void main(String[] args) {
		String url = "http://list.jd.com/list.html?cat=9987,653,655";
		
		String result = null;
		HttpClientBuilder builder = HttpClients.custom();
		CloseableHttpClient client = builder.build();
		HttpGet request = new HttpGet(url);
		try {
			CloseableHttpResponse response = client.execute(request);
			HttpEntity entity = response.getEntity();
			result = EntityUtils.toString(entity);
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}