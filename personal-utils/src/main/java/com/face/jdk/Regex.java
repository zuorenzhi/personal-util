package com.face.jdk;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class Regex {

	@Test
	public void testNumber(){
		 String regex = "第[0-9]*条";
		   String str = "第9条,数据错误,错误信息,第jjj哦条哦条我的条件如何？第221条xx";
		   Pattern pat = Pattern.compile(regex);  
		   Matcher matcher = pat.matcher(str);     
		   while (matcher.find()) { 
		     String temp = str.substring(matcher.start(),matcher.end());
		     str = str.replaceAll(temp, temp.substring(0,temp.lastIndexOf("条"))+"行");
		   }     
		   System.out.println(str);
	}
	@Test
	public void test(){
		String regex = "第[0-9]*条";
		String str = "第9条,数据错误,错误信息,第jjj哦条哦条我的条件如何？第221条xx";
		String s = getStr(str, regex, "垂直");
		System.out.println(s);
	}
	/**
	 * 对strStr字符串用replaceStr替换regexStr
	 * @param srcStr 元字符串
	 * @param regexStr 匹配的表达式
	 * @param replaceStr 用于替换的表达式
	 * @return
	 */
	public String getStr(String srcStr,String regexStr,String replaceStr){
		Pattern pat = Pattern.compile(regexStr);  
		Matcher matcher = pat.matcher(srcStr);     
		while (matcher.find()) { 
			String temp = srcStr.substring(matcher.start(),matcher.end());
			srcStr = srcStr.replaceAll(temp, replaceStr);
		}     
		return srcStr;
	}
}
