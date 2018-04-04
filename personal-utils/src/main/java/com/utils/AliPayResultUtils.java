package com.utils;

import com.alibaba.fastjson.JSONObject;

/**
 * Description: 支付宝还款方式返回参数转换工具类
 * Created on: 2017-08-09 15:56
 * @author: zuorenzhi
 * Copyright (c) 2017年 国美融通科技
 */
public class AliPayResultUtils {

    /**
     * 处理返回参数的头文件参数
     * @param jsonObject
     * @return JSONObject
     */
    public static JSONObject getCommonHeader(JSONObject jsonObject){

        JSONObject serviceHeader = jsonObject.getJSONObject("SERVICE_HEADER");
        JSONObject servResponse = serviceHeader.getJSONObject("SERV_RESPONSE");

        JSONObject headerResult = new JSONObject();
        JSONObject responseResult = new JSONObject();
        responseResult.put("status",servResponse.getString("STATUS"));
        responseResult.put("code",servResponse.getString("CODE"));
        responseResult.put("desc",servResponse.getString("DESC"));

        headerResult.put("versionId",serviceHeader.getString("VERSION_ID"));
        headerResult.put("serviceId",serviceHeader.getString("SERVICE_ID"));
        headerResult.put("org",serviceHeader.getString("ORG"));
        headerResult.put("servicesn",serviceHeader.getString("SERVICESN"));
        headerResult.put("channelId",serviceHeader.getString("SERVICESN"));
        headerResult.put("subTerminalType",serviceHeader.getString("SUB_TERMINAL_TYPE"));
        headerResult.put("opId",serviceHeader.getString("OP_ID"));
        headerResult.put("requestTime",serviceHeader.getString("REQUEST_TIME"));
        headerResult.put("mac",serviceHeader.getString("MAC"));
        headerResult.put("servResponse",responseResult);

        return headerResult;
    }

    /**
     * 处理payday返回参数的Body文件参数
     * @param jsonObject
     * @return JSONObject
     */
    public static JSONObject getPayDayBody(JSONObject jsonObject){

        JSONObject serviceBody = jsonObject.getJSONObject("SERVICE_BODY");
        JSONObject response = serviceBody.getJSONObject("RESPONSE");

        JSONObject bodyResult = new JSONObject();
        JSONObject responseResult = new JSONObject();

        responseResult.put("contrNbr",response.getString("CONTR_NBR"));
        responseResult.put("amount",response.getString("AMOUNT"));
        responseResult.put("orderStatus",response.getString("ORDER_STATUS"));
        responseResult.put("orderTime",response.getString("ORDER_TIME"));
        responseResult.put("transNo",response.getString("TRANS_NO"));
        responseResult.put("signContent",response.getString("SIGN_CONTENT"));

        bodyResult.put("response",responseResult);

        return bodyResult;
    }

    /**
     * 处理prepare返回参数的Body文件参数
     * @param jsonObject
     * @return JSONObject
     */
    public static JSONObject getPrepareBody(JSONObject jsonObject){

        JSONObject serviceBody = jsonObject.getJSONObject("SERVICE_BODY");
        JSONObject response = serviceBody.getJSONObject("RESPONSE");

        JSONObject bodyResult = new JSONObject();
        JSONObject responseResult = new JSONObject();

        responseResult.put("contrNbr",response.getString("CONTR_NBR"));
        responseResult.put("amount",response.getString("AMOUNT"));
        responseResult.put("paymentStatus",response.getString("PAYMENT_STATUS"));
        responseResult.put("transNo",response.getString("TRANS_NO"));
        responseResult.put("signContent",response.getString("SIGN_CONTENT"));

        bodyResult.put("response",responseResult);

        return bodyResult;
    }

    /**
     * 处理delay返回参数的Body文件参数
     * @param jsonObject
     * @return JSONObject
     */
    public static JSONObject getDelayBody(JSONObject jsonObject){

        JSONObject serviceBody = jsonObject.getJSONObject("SERVICE_BODY");
        JSONObject response = serviceBody.getJSONObject("RESPONSE");

        JSONObject bodyResult = new JSONObject();
        JSONObject responseResult = new JSONObject();

        responseResult.put("contrNbr",response.getString("CONTR_NBR"));
        responseResult.put("prepayPrinAmt",response.getString("PREPAY_PRIN_AMT"));
        responseResult.put("totalCutAmt",response.getString("TOTAL_CUT_AMT"));
        responseResult.put("transNo",response.getString("TRANS_NO"));
        responseResult.put("signContent",response.getString("SIGN_CONTENT"));

        bodyResult.put("response",responseResult);

        return bodyResult;
    }
}
