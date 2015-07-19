package cn.crxy.crawler.utils;

public class SleepUtil {
	public static void sleep(long millis){
		try {
			Thread.currentThread().sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
