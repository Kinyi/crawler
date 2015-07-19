package cn.crxy.crawler.store;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.http.client.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.crxy.crawler.Page;

public class FileStore implements Storable {
	Logger logger = LoggerFactory.getLogger(getClass());
	private String basePath;
	private int flag = 1;
	
	public FileStore(){
		this(".");
	}
	
	public FileStore(String basepath){
		this(basepath, 1);
	}
	
	public FileStore(String basepath , int flag){
		this.basePath = basepath;
		this.flag = flag;
		checkExist();
	}

	private void checkExist() {
		File dir = new File(basePath);
		if (!dir.exists()) {
			dir.mkdirs();
		}else{
			if (!dir.isDirectory()) {
				dir.delete();
				dir.mkdirs();
			}
		}
	}

	public void store(Page page) {
		String valuesToJSON = page.valuesToJSON();
		
		try {
			FileUtils.write(new File(basePath+File.separator+FileName()), valuesToJSON);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	private String FileName() {
		switch (flag) {
		case 1:
			return DateUtils.formatDate(new Date(), "yyyy年MM月dd日HH时mm分ss秒SSS");
		default:
			return null;
		}
	}

}
