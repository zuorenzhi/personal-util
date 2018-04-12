package com.face.jdk;

import org.junit.Test;

import java.util.List;

/**
* <p>Description: [ 数组]</p>
* Created on 2017/5/16
* @author  <a href="mailto: zuorenzhi@clt.com">zuorenzhi</a>
* @version 1.0
* Copyright (c) 2017/5/1614:47 camelot jiaofubu
*/
public class ArrayBean {

	/** 
	* @Author zuorenzhi 
	* @Date:2017/5/16 14:50
	*/
	@Test
	public void asList(){
		String str = "0,2,5";						
		String[] strArr = str.split(",");
		//数组对象
		System.out.println(strArr);
		//toString
		System.out.println(java.util.Arrays.toString(strArr));
		//转List
		List<String> list = java.util.Arrays.asList(strArr);
		System.out.println(list);
		//转数组对象
		System.out.println(list.toArray());
	}
	
	@Test
	public void twoLength(){
	String arr [] = new String [5];
	System.out.println(arr.length);
	arr[0]="a";
	arr[1]="a";
	System.out.println(arr.length);
	System.out.println(arr);
	System.out.println(java.util.Arrays.toString(arr));
	}

}
