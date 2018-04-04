package com.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import lombok.extern.slf4j.Slf4j;

/**
 * Description: 长亮返回参数转换工具类
 * Created on: 2017-08-09 15:56
 * @author: zuorenzhi
 * Copyright (c) 2017年 国美融通科技
 */
@Slf4j
public class SunlineResultUtils {



    /*******************************  SunlineResponse 方式 组装返回 ******************************/

    /**
     * Discription: [原数据返回常量header中servresponse]
     * Created on: 2017-11-06 11:54
     * param : [json :常量返回字符串]
     * return : SunlineResponse.Service.ServiceHeader.ServResponse
     * author : [左仁智]
     */
    public static  SunlineResponse.Service.ServiceHeader.ServResponse getServResponse(String json) {
        TypeReference<SunlineResponse> type = new TypeReference<SunlineResponse>() {};
        SunlineResponse sunlineResponse = JSON.parseObject(json, type);
        return sunlineResponse.getSERVICE().getSERVICE_HEADER().getSERV_RESPONSE();
    }

    /**
     * Discription: [原数据返回常量serviceBody中 response ，对应转换的VO]
     * Created on: 2017-11-06 11:56
     * param : [json :常量返回字符串]
     * return : T
     * author : [左仁智]
     */
    public static <T>  T getResponse(Class<T> clazz, String json) {
        JSONObject jsonObject = JSONObject.parseObject(json);
        String response = jsonObject.getJSONObject("SERVICE").getJSONObject("SERVICE_BODY").getJSONObject("RESPONSE").toJSONString();
        T sunlineResponse = JSON.parseObject(response,clazz);
        return sunlineResponse;
    }

    /***
     * 最原始--左仁智【未测试】
     * @param json
     * @param <T>
     * @return
     */
    @Deprecated
    public static <T>  T getResponse(String json) {
        TypeReference<SunlineResponse<T>> type = new TypeReference<SunlineResponse<T>>() {};
        SunlineResponse<T> sunlineResponse = JSON.parseObject(json, type);
        return sunlineResponse.getSERVICE().getSERVICE_BODY().getRESPONSE();
    }

    public static void main(String[] args) {

        String str = "{\n" +
                "    SERVICE: {\n" +
                "        SERVICE_HEADER: {\n" +
                "            SERVICE_ID: \"TFCMJPreRepayByTerms\", \n" +
                "            ORG: \"000000000001\", \n" +
                "            CHANNEL_ID: \"BANK\", \n" +
                "            SUB_TERMINAL_TYPE: \"SDK\", \n" +
                "            REQUEST_TIME: \"20171124165250\", \n" +
                "            VERSION_ID: \"01\", \n" +
                "            SERV_RESPONSE: {\n" +
                "                STATUS: \"F\", \n" +
                "                CODE: \"1046\", \n" +
                "                DESC: \"已有1笔受理中代扣交易\"\n" +
                "            }\n" +
                "        }, \n" +
                "        SERVICE_BODY: {\n" +
                "            RESPONSE: {\n" +
                "                PAYMENT_STATUS: \"E\", \n" +
                "                ORDER_STATUS: \"E\", \n" +
                "                ORDER_TIME: \"2017-11-24 16:52:50\"\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}";

        SunlineResultUtils.getResponse(JSONObject.class, str);

        SunlineResultUtils.getServResponse("");
    }
}
