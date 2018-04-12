package com.utils;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

/**
 * <p>Description: [ishugui]</p>
 * Created on 2017年5月5日
 * @author  <a href="mailto: zuorenzhi@clt.com">zuorenzhi</a>
 * @version 1.0 
 * Copyright (c) 2017 camelot jiaofubu
 */
public class DZSignUtils {


	public static String sign(Map<String, String> parameterMap) {
		if(parameterMap == null){
			return null;
		}
		Map<String, String> filterParameterMap = filterParameter(parameterMap,"");
		String linkParameterStr = linkFileName(filterParameterMap);
		//return DZMD5.sign(linkParameterStr, KEY, "UTF-8");
		return MD5Utils.MD5(linkParameterStr);
	}

	/**remove key=sign 
	 * @param parameterMap
	 */
	public static Map<String, String> filterParameter(Map<String, String> parameterMap,String removeKey) {
		Map<String, String> resultMap = new HashMap<String, String>();
		if(parameterMap != null){
			for (String key : parameterMap.keySet()) {
				if(StringUtils.isNotBlank(removeKey) && removeKey.equals(key)){
					continue;
				}
				resultMap.put(key, parameterMap.get(key));
			}
		}
		return resultMap;
	}

	/**
	 * 将map的key与value排序用&拼接成字符串
	 * @param parameterMap
	 * @return key1=value1&key2=value2&key3=value3.....
	 */
	public static String linkFileName(Map<String, String> parameterMap) {
		if (parameterMap == null || parameterMap.size() < 1) {
			return null;
		}

		List<String> keyList = new ArrayList<String>(parameterMap.keySet());
		Collections.sort(keyList);

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < keyList.size(); i++) {
			String key = keyList.get(i);
			String keyValue = parameterMap.get(key);
			sb.append(key).append("=").append(keyValue);
			if (i < keyList.size() - 1) {
				sb.append("&");
			}
		}
		return sb.toString();
	}
	/**
	 * @param parameterMap
	 * @return key1value1key2value2key3value3.....
	 */
	public static String linkFileNameNoConnector(Map<String, String> parameterMap) {
		if (parameterMap == null || parameterMap.size() < 1) {
			return null;
		}
		
		List<String> keyList = new ArrayList<String>(parameterMap.keySet());
		Collections.sort(keyList);
		
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < keyList.size(); i++) {
			String key = keyList.get(i);
			String keyValue = parameterMap.get(key);
			sb.append(key).append(keyValue);
		}
		return sb.toString();
	}

	public static boolean verify(Map<String, String> parameterMap, String sign) {
		String localSign = sign(parameterMap);
		if (localSign != null) {
			return localSign.equals(sign);
		}
		return false;
	}

	@Test
	public void testSignZH() {
		String secret = "ur3bqcril30sz71r";//纵横密钥
		StringBuffer sb = new StringBuffer();
		sb.append(secret);
		Map<String, String> parameterMap = new HashMap<String, String>();
		parameterMap.put("api_key", "2tlzkkqmkt");//百度/纵横 api_key
		parameterMap.put("method", "chapter.count");
//		parameterMap.put("method", "chapter.list");
//		parameterMap.put("method", "chapter.first");
//		parameterMap.put("book_id", "206808");
		parameterMap.put("book_id", "249726");
//		parameterMap.put("pageSize", "10");
//		parameterMap.put("cursor", "3747305");
//		parameterMap.put("cursor", "4916682");
		sb.append(DZSignUtils.linkFileNameNoConnector(parameterMap));
		sb.append(secret);
		System.out.println(sb.toString());
		String sig = MD5Utils.MD5(sb.toString()).toUpperCase();
		System.out.println(sig);
	}
	@Test
	public void testSignZHSign() {
		StringBuffer sb = new StringBuffer();
		sb.append("ddadfaffdasfedf");
		Map<String, String> parameterMap = new HashMap<String, String>();
		parameterMap.put("api_key", "ec9e57913c5b42b282ab7b743559e1b0");
		parameterMap.put("method", "book.info.basic");
		parameterMap.put("book_id", "123209");
		sb.append(linkFileNameNoConnector(parameterMap));
		sb.append("ddadfaffdasfedf");
		System.out.println(sb.toString());
		String md5 = MD5Utils.MD5(sb.toString());
		System.out.println(md5.toUpperCase());
		
		System.out.println(System.getProperty("java.version" ));
        System.out.println(Charset.defaultCharset());
	}
}
