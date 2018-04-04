package com.utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
* Description: [柯莱特http工具类]<br/>
* Created on 2017/6/7 20:42 <br/>
* @author  <a href="mailto: zuorenzhi@camelotchina.com">zuorenzhi</a><br/>
* @version 1.0</br>
* Copyright (c) 2017年 北京柯莱特科技有限公司 交付部
*/
public class HttpClientUtilCamelot {
//	private static final Logger logger = Log.get(HttpClientUtil.class);
	
	public static String postMethod(String url,Map<String,String> params){
		PostMethod methodPost = new PostMethod(url);
		methodPost.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"utf-8"); 
		// 构造提交的参数
		if (params!=null && params.size()>0) {
			Iterator<Entry<String, String>> paramsIterator = params.entrySet().iterator();
			while(paramsIterator.hasNext()) {
				Entry<String, String> entry = paramsIterator.next();
				String key = entry.getKey();
				String value = entry.getValue();
				
				methodPost.addParameter(key,value);
			}
		}
		String respPost = null;
		HttpClient  httpClient = new HttpClient();
		// 向服务器发送HTTP Post请求
		try {
			httpClient.executeMethod(methodPost);
			byte[] result = methodPost.getResponseBody();
			respPost = new String(result,"UTF-8");
		} catch (Exception e) {
//			Log.error(logger, e, "HttpClientUtil-postMethod");
			e.printStackTrace();
		} finally {
			// 关闭连接
			methodPost.releaseConnection();
		}
		return respPost;
	}


	/**
	 * Discription:  get方式
	 * Created on: 2017/7/14 13:23
	 * @param:
	 * @return:
	 * @author: zuorenzhi
	 */
	public static String sendGet(String urlStr){
//		System.out.println(urlStr);
		URL url = null;
		HttpURLConnection conn = null;
		StringBuffer sb = new StringBuffer();
		try {
			url = new URL(urlStr);
			conn = (HttpURLConnection) url.openConnection();
			conn.setUseCaches(false);
			conn.setRequestProperty("Content-Type", "text/plain");
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Charsert", "UTF-8");
			//设置这两个参数是防止程序超时无响应的问题
			conn.setConnectTimeout(30000);
			conn.setReadTimeout(30000);
			BufferedReader bufferReader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			String aa = "";
			while ((aa = bufferReader.readLine()) != null) {
				sb.append(aa).append("\r\n");
			}
		} catch (Throwable e) {
//			log.info("访问链接get错误--"+urlStr);
//			log.error(e.getMessage(),e);
		} finally {
			if (conn != null)
				conn.disconnect();
		}
		return sb.toString();
	}


	public static void main(String[] args) {
//http://localhost:8081/meijie/h5/v1/version/versionManage?jsonData={"clientType":"Android","currentVersion":"1.0.1.0"}
		String url = "http://localhost:8081/meijie/h5/v1/version/versionManage";
		HashMap<String, String> hashMap = new HashMap<>();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("clientType", "Android");
		jsonObject.put("currentVersion", "1.0.1.0");
		hashMap.put("jsonData", JSON.toJSONString(jsonObject));
		String result = postMethod(url, hashMap);
		System.out.println(result);
	}

}
