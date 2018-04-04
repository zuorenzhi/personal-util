package com.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * <p>Description: [一些常用的安全相关的操作方法]</p>
 * Created on 2016年8月31日
 * @author  <a href="mailto: liuxiangping86@camelotchina.com">刘香平</a>
 * @version 1.0 
 * Copyright (c) 2016 北京柯莱特科技有限公司 交付部
 */
public class SecUtils {

    public static MessageDigest MD5 = null;

    static {
        try {
            MD5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ne) {
            ne.printStackTrace();
        }
    }

    public static String FileMD5(File file) {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            byte[] buffer = new byte[8192];
            int length;
            while ((length = fileInputStream.read(buffer)) != -1) {
                MD5.update(buffer, 0, length);
            }
            return new BigInteger(1, MD5.digest()).toString(16);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (fileInputStream != null)
                    fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * <p>Discription:[字符串加密函数MD5实现]</p>
     * Created on 2016年8月31日
     * @param password
     * @return
     * @author:[刘香平]
     */
    public static String md5(String password) {
        try {
            MD5.update(password.getBytes());
            String pwd = new BigInteger(1, MD5.digest()).toString(16);
            return pwd;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return password;
    }
    
    public static void main(String[] args) {
		
    	String aaa = SecUtils.md5("GET&/v3/cities&Thu, 25 Jul 2013 09:56:11 GMT&0&" + md5("demo_secret"));
    	System.out.println(aaa);
    	System.out.println("819d54cbec983f54587b15647e6a41a8");
    	String s = SecUtils.md5("GET&/v3/rate_limit_status&Thu, 01 Sep 2016 12:15:46 GMT&0&da1bf7582f00768b50ba2e32832b6161");
    	
	}
}
