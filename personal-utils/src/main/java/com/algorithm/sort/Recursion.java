package com.algorithm.sort;

import org.junit.Test;

/**
 * <p>Description: [斐波那契递归函数]</p>
 * Created on 2017年1月4日
 * @author  <a href="mailto: zuorenzhi@camelotchina.com">左仁智</a>
 * @version 1.0 
 * Copyright (c) 2017 北京柯莱特科技有限公司 交付部
 */
public class Recursion {

	public int fn(int n){
		System.out.printf("入参-%s%n",n);
		if(n < 2){
			return n <= 0 ? 0: 1;
		}
		return fn(n-1) + fn(n-2);
	}
	
	/**
	 *  fn(5)=fn(4)+fn(3)
	=fn(3)+fn(2)+fn(3)
	=fn(2)+fn(1)+fn(2)+fn(3)
	=fn(1)+fn(0)+fn(1)+fn(2)+fn(3)
	=fn(1)+fn(0)+fn(1)+fn(1)+fn(0)+fn(3)
	=fn(1)+fn(0)+fn(1)+fn(1)+fn(0)+fn(2)+fn(1)
	=fn(1)+fn(0)+fn(1)+fn(1)+fn(0)+fn(1)+fn(0)+fn(1)
	1 0 1 1 0 1 0 1
	 * <p>Discription:[方法功能中文描述]</p>
	 * Created on 2017年1月4日
	 * @author:[左仁智]
	 */
	@Test
	public void test(){
		int n = 10;
		System.out.printf("fn("+n+")=%d%n",fn(n));
	}
}
