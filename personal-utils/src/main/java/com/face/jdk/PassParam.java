package com.face.jdk;

import org.junit.Test;

/**
 * <p>Description: [java 传值和传引用]</p>
 * Created on 2017年4月27日
 * @author  <a href="mailto: zuorenzhi@camelotchina.com">zuorenzhi</a>
 * @version 1.0 
 * Copyright (c) 2017 camelot jiaofubu
 */
public class PassParam {
	
	/**
	 * <p>Discription:[传基本数据类型===>传值]</p>
	 * Created on 2017年4月27日
	 * @author:[zuorenzhi]
	 */
	@Test
	public void testInteger(){
		int i = 10;
		System.out.printf("--before,i的值---%s%n",i);
		addNum(i);
		System.out.printf("--after,i的值---%s%n",i);
	}

	//进入方法后，可以认为该变量名是另一个崭新的变量名
	private void addNum(int i) {
		i += 100;
		System.out.printf("addNum中i的值---%s%n",i);
	}

	@Test
	public void testString(){
		String str = "hello";
		System.out.printf("--before,i的值---%s%n",str);
		addStr(str);
		System.out.printf("--after,i的值---%s%n",str);
	}
	
	private void addStr(String str) {
		str += 100;
		System.out.printf("addStr中i的值---%s%n",str);
		str ="java";
		System.out.printf("addStr中i的值---%s%n",str);
	}
}
