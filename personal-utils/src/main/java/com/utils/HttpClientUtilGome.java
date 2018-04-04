package com.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
* Description : [http工具]
* Created on : 2017-11-03 14:00
* author : [左仁智]
* version : 1.0
* Copyright (c) 2017 国美金控-美借
*/
@Slf4j
public class HttpClientUtilGome {

    private static final String application_x_www_form_urlencoded = "application/x-www-form-urlencoded";
    private static final String application_json =  "application/json";
    private static final String text_plain =  "text/plain";
    private static final String application_xml =  "application/xml";
    private static final String text_html =  "text/html";

    private static String UTF8 = "UTF-8";
    private static final CloseableHttpClient httpClient = createHttpClient();

    /**
     * 通过连接池获取HttpClient
     */
    private static CloseableHttpClient createHttpClient() {
        RequestConfig config = RequestConfig.custom()
                .setSocketTimeout(10000)
                .setConnectTimeout(5000)
                .setConnectionRequestTimeout(30000)
                .build();
        return HttpClients.custom().setDefaultRequestConfig(config)
                .setMaxConnTotal(200)
                .setMaxConnPerRoute(20)
                .build();
    }


    private static CloseableHttpClient getHttpClient() {
        return httpClient;
    }

    /**
     * Discription: [post方式,调用restful服务.]
     * Created on: 2017-11-03 14:01
     * param :
     * return :
     * author : [左仁智]
     */
    public static String httpPostRestRequest(String url, String json) {
        HttpPost httpPost = new HttpPost(url);
        StringEntity entity = new StringEntity(json, UTF8);
        entity.setContentType(application_x_www_form_urlencoded);
        httpPost.setEntity(entity);
        return getResult(httpPost);
    }

    /**
     * Discription: [POST方式,JSON参数调用服务.]
     * Created on: 2017-11-03 14:01
     * param : [url, json]
     * return : java.lang.String
     * author : [左仁智]
     */
    public static String httpPostJsonRequest(String url, String json) {
        HttpPost httpPost = new HttpPost(url);
        StringEntity entity = new StringEntity(json, UTF8);
        entity.setContentType(application_json);
        httpPost.setEntity(entity);
        return getResult(httpPost);
    }

    /**
     * Discription: [处理Http请求]
     * todo: 此处需要注意 try(){}catch(){} ,jdk 1.7 后有，try的部分，使用后自动关闭流,或相关类对象
     * url:https://www.cnblogs.com/zhoujl-5071/p/6006976.html
     * Created on: 2017-12-11 20:53
     * author : [左仁智]
     */
    private static String getResult(HttpRequestBase request) {
        CloseableHttpClient httpClient = getHttpClient();
        try (CloseableHttpResponse response = httpClient.execute(request);) {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                return EntityUtils.toString(entity);
            }
        } catch (Exception e) {
            log.error("【HttpClientUtil-->getResult】 调用异常",e);
        }
        return null;
    }

}

