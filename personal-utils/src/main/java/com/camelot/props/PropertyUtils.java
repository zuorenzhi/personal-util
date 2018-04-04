package com.camelot.props;

import java.util.Properties;

public class PropertyUtils {
	
	private static Properties props = null;//存储env.properties文件里所有配置项

	public static void init(Properties properties) {
		props = properties;
	}

	public static String getProperty(String key){
		return props.getProperty(key);
	}
	
}
