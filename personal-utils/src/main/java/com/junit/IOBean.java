package com.junit;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

public class IOBean {

	@Test
	public void testByte() throws IOException {
		String str = "123412";
		InputStream ins = new ByteArrayInputStream(str.getBytes("UTF-8"));
		FileUtils.copyInputStreamToFile(ins, new File("E:\\Test\\2.txt"));
	}

	@Test
	public void test() throws IOException{
		String path = "d:/home"+File.separator+"official_website_html"+File.separator+"kk.txt";
		File file = new File(path);
		if(!file.getParentFile().exists()){
			file.mkdirs();
		}
		if(!file.exists()){
			file.createNewFile();
		}
		System.out.println(file.getAbsolutePath());
		System.out.println(File.pathSeparator);
		System.out.println(File.separator);
	}
}
