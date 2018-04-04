package com.utils.context;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.Test;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public class ConfigUtils {
	
	/**
	 * 自项目的根目录获取文件路径
	 * @throws IOException
	 */
	@Test
	public void getKey1() throws IOException{
		Properties properties = new Properties();
		InputStream resourceAsStream = ConfigUtils.class.getClassLoader().getResourceAsStream("system.properties");
		properties.load(resourceAsStream);
		String property = properties.getProperty("redis.host");
		System.out.println(property);
	}
	/**
	 * 自项目的根目录获取文件路径[全路径]
	 * @throws IOException
	 */
	@Test
	public void getKey2() throws IOException{
		Properties properties = new Properties();
		InputStream resourceAsStream = ConfigUtils.class.getClassLoader().getResourceAsStream("com/zuo/conf/config.properties");
		properties.load(resourceAsStream);
		String property = properties.getProperty("zuo.name");
		System.out.println(property);
	}
	/**
	 * 当前类路径下(同一个包下)
	 * @throws IOException
	 */
	@Test
	public void getKey3() throws IOException{
		Properties properties = new Properties();
		InputStream resourceAsStream = ConfigUtils.class.getResourceAsStream("config1.properties");
		properties.load(resourceAsStream);
		String property = properties.getProperty("zuo.name");
		System.out.println(property);
	}
	/**
	 * classLoader总是全路径
	 * @throws IOException
	 */
	@Test
	public void getKey4() throws IOException{
		Properties properties = new Properties();
		ResourceLoader resourceLoader = new DefaultResourceLoader();
		ClassLoader classLoader = resourceLoader.getClassLoader();
		InputStream resourceAsStream = classLoader.getResourceAsStream("com/zuo/soft/note/config1.properties");
		properties.load(resourceAsStream);
		String property = properties.getProperty("zuo.name");
		System.out.println(property);
	}
	/**
	 * getResource也是获取全路径
	 * @throws IOException
	 */
	@Test
	public void getKey5() throws IOException{
		Properties properties = new Properties();
		ResourceLoader resourceLoader = new DefaultResourceLoader();
		Resource resource = resourceLoader.getResource("com/zuo/soft/note/config1.properties");
		InputStream inputStream = resource.getInputStream();
		properties.load(inputStream);
		String property = properties.getProperty("zuo.name");
		System.out.println(property);
	}
	/**
	 * spring的工具类
	 * @throws IOException
	 */
	@Test
	public void getKey6() throws IOException{
		Properties loadAllProperties = PropertiesLoaderUtils.loadAllProperties("com/zuo/soft/note/config1.properties");
		String property = loadAllProperties.getProperty("zuo.name");
		System.out.println(property);
	}
	/**
	 * 用文件对象获取流,路径基于项目路径[注意不要遗漏src]
	 * @throws IOException
	 */
	@Test
	public void getKey7() throws IOException{
		FileInputStream inputStream = new FileInputStream(new File("src/com/zuo/soft/note/config1.properties"));
//		InputStream inputStream1 = new FileInputStream("src/com/zuo/soft/note/config1.properties");
		Properties properties = new Properties();
		properties.load(inputStream);
		String property = properties.getProperty("zuo.name");
		System.out.println(property);
	}

}
