package com.utils;

import java.util.Arrays;
import java.util.Random;
import java.util.UUID;

/**
 * <p>Description: [随机数生成器]</p>
 * Created on 2016年5月10日
 * @author  <a href="mailto: liuxiangping86@clt.com">刘香平</a>
 * @version 1.0 
 * Copyright (c) 2016 北京柯莱特科技有限公司 交付部
 */
public class RandomUtils {
	
    public static final String ALLCHAR    = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    
    public static final String LETTERCHAR = "abcdefghijkllmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    
    public static final String NUMBERCHAR = "0123456789";


    /**
     * <p>Discription:[生成制定范围内的随机数]</p>
     * Created on 2016年5月10日
     * @param scopeMin
     * @param scoeMax
     * @return
     * @author:[刘香平]
     */
    public static int randomScope(int scopeMin,int scoeMax){
        Random random = new Random();
        //2;10--%9
        return (random.nextInt(scoeMax)%(scoeMax-scopeMin+1) + scopeMin);
//        return (random.nextInt(scoeMax-scopeMin+1) + scopeMin);
    }
    
    /**
     * <p>Discription:[返回固定长度的数字]</p>
     * Created on 2016年5月10日
     * @param length
     * @return
     * @author:[刘香平]
     */
    public static String randomNum(int length) {
        StringBuffer sb     = new StringBuffer();
        Random       random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(NUMBERCHAR.charAt(random.nextInt(NUMBERCHAR.length())));
        }
        return sb.toString();
    }

    /**
     * <p>Discription:[返回一个定长的随机字符串(只包含大小写字母、数字)]</p>
     * Created on 2016年5月10日
     * @param length
     * @return
     * @author:[刘香平]
     */
    public static String randomStr(int length) {
        StringBuffer sb     = new StringBuffer();
        Random       random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(ALLCHAR.charAt(random.nextInt(ALLCHAR.length())));
        }
        return sb.toString();
    }

    /**
     * <p>Discription:[返回一个定长的随机纯字母字符串(只包含大小写字母)]</p>
     * Created on 2016年5月10日
     * @param length
     * @return
     * @author:[刘香平]
     */
    public static String randomLetter(int length) {
        StringBuffer sb     = new StringBuffer();
        Random       random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(LETTERCHAR.charAt(random.nextInt(LETTERCHAR.length())));
        }
        return sb.toString();
    }

    /**
     * <p>Discription:[生成一个定长的纯0字符串]</p>
     * Created on 2016年5月10日
     * @param length
     * @return
     * @author:[刘香平]
     */
    public static String randomZero(int length) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            sb.append('0');
        }
        return sb.toString();
    }

    /**
     * <p>Discription:[根据数字生成一个定长的字符串，长度不够前面补0]</p>
     * Created on 2016年5月10日
     * @param num
     * @param fixdlenth
     * @return
     * @author:[刘香平]
     */
    public static String randomZeroStr(long num, int fixdlenth) {
        StringBuffer sb     = new StringBuffer();
        String strNum = String.valueOf(num);
        if (fixdlenth - strNum.length() >= 0) {
            sb.append(randomZero(fixdlenth - strNum.length()));
        } else {
            throw new RuntimeException("将数字" + num + "转化为长度为" + fixdlenth + "的字符串发生异常！");
        }
        sb.append(strNum);
        return sb.toString();
    }

    /**
     * <p>Discription:[根据数字生成一个定长的字符串，长度不够前面补0]</p>
     * Created on 2016年5月10日
     * @param num
     * @param fixdlenth
     * @return
     * @author:[刘香平]
     */
    public static String randomZeroStr(int num, int fixdlenth) {
        StringBuffer sb     = new StringBuffer();
        String  strNum = String.valueOf(num);
        if (fixdlenth - strNum.length() >= 0) {
            sb.append(randomZero(fixdlenth - strNum.length()));
        } else {
            throw new RuntimeException("将数字" + num + "转化为长度为" + fixdlenth + "的字符串发生异常！");
        }
        sb.append(strNum);
        return sb.toString();
    }

    /**
     * <p>Discription:[每次生成的len位数都不相同]</p>
     * Created on 2016年5月10日
     * @param param
     * @param len
     * @return
     * @author:[刘香平]
     */
    public static int randomNumLen(int[] param, int len) {
    	if(len > param.length){
    		throw new RuntimeException("数组越界："+len+">"+param.length);
    	}
    	System.out.println("----begin---"+Arrays.toString(param));
        Random rand = new Random();
        for (int i = param.length; i > 1; i--) {
            int index = rand.nextInt(i);
            int tmp = param[index];
            param[index] = param[i - 1];
            param[i - 1] = tmp;
        }
        System.out.println("----end---"+Arrays.toString(param));
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < len; i++) {
        	sb.append(param[i]);
        }
        return Integer.parseInt(sb.toString());
    }

    /**
     * <p>Discription:[返回一个小写的UUID]</p>
     * Created on 2016年5月10日
     * @return
     * @author:[刘香平]
     */
    public static String uuid(){
        UUID uuid = UUID.randomUUID();
        String s = uuid.toString();
        //return s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24);
        return s.toString().replace("-", "");
    }
    /**
     * <p>Discription:[返回一个大写的UUID]</p>
     * Created on 2016年5月10日
     * @return
     * @author:[刘香平]
     */
    public static String UUID(){
        UUID uuid = UUID.randomUUID();
        String s = uuid.toString();
        String temp =s.toString().replace("-", "");
        return temp.toUpperCase();
    }
    
    public static void main(String[] args) {
    	System.out.println(uuid());
    	System.out.println(UUID());
    	System.out.println(randomScope(2, 10));
    	System.out.println("randomLetter:"+randomLetter(10));
    	System.out.println(randomNum(10));
    	System.out.println("randomStr:"+randomStr(10));
    	System.out.println(randomZeroStr(123, 5));
    	System.out.println(randomZeroStr(20L, 10));
    	System.out.println(randomNumLen(new int[]{1,2,3,4,5,6,7,8,9},9));
    	StringBuffer sb = new StringBuffer("12");//super(str.length() + 16);
    	System.out.println(sb.capacity());//容量
	}
}