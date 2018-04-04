package com.json;

import lombok.Getter;
import lombok.Setter;

/**
* Description : [常量返回数据封装，参数全为大写格式]
* Created on : 2017-11-06 11:28
* author : [左仁智]
* version : 1.0
* Copyright (c) 2017 国美金控-美借
*/
@Setter
@Getter
public class SunlineResponse<T> {
   private Service<T> SERVICE;

   @Setter
   @Getter
   public static class Service<T>{
       private ServiceHeader SERVICE_HEADER;
       private ServiceBody<T> SERVICE_BODY;

       @Setter
       @Getter
       public static class ServiceHeader{
           private String SERVICE_ID;
           private String ORG;
           //服务接入渠道编号
           private String CHANNEL_ID;
           //对接系统编号
           private String ACQ_ID;
           //终端类型
           private String SUB_TERMINAL_TYPE;
           private String SERVICESN;
           //操作员号
           private String OP_ID;
           private String REQUEST_TIME;
           private String VERSION_ID;
           //报文验证码(预留)
           private String MAC;
           private ServResponse SERV_RESPONSE;

           @Setter
           @Getter
           public static class ServResponse{
               private String STATUS;
               private String CODE;
               private String DESC;
           }
       }

       @Setter
       @Getter
       public static class ServiceBody<T>{
           private T RESPONSE;
       }

   }
}
