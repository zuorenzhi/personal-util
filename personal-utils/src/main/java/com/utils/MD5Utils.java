package com.utils;

import java.security.MessageDigest;

/**
 * <p>Description: [MD5加密]</p>
 * Created on 2017年4月16日
 * @author  <a href="mailto: zuorenzhi@camelotchina.com">左仁智</a>
 * @version 1.0 
 * Copyright (c) 2017 北京柯莱特科技有限公司 交付部
 */
public class MD5Utils {
    public final static String MD5(String s) {
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};       

        try {
            byte[] btInput = s.getBytes();
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            mdInst.update(btInput);
            byte[] md = mdInst.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
    	String md5 = MD5Utils.MD5("'GET&/v3/cities&Thu, 25 Jul 2013 09:56:11 GMT&0&'" + MD5Utils.MD5("demo_secret"));
//    	System.out.println(md5);/57D8EEF24D2B4A0E5B3C61E50C7E4537
        System.out.println(MD5Utils.MD5("'demo_secret'"));
    }
}