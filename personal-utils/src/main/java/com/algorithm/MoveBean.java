package com.algorithm;

import org.junit.Test;

import java.util.UUID;

/**
* Description : [位运算,位移]
* Created on : 2018-01-29 14:34
* author : [左仁智]
* version : 1.0
* Copyright (c) 2018 国美金控-美借
*/
public class MoveBean {

	@Test
	public void bitMove() {
		System.out.println(2<<2);
		System.out.println(8>>2);
		System.out.println(4<<1);
	}
	

	/**
	 * 3&1=1
	这还要从宇宙的起源开始讲起
	......
	然后
	......
	最后，
	5 = 0000 0000 0000 0000 0000 0000 0000 0101
	3 = 0000 0000 0000 0000 0000 0000 0000 0011
	1 = 0000 0000 0000 0000 0000 0000 0000 0001
	&：都是1，结果才是1
	^：一个1一个0，结果是1 (异域中同位如果值相同（都是0或者都是1）则为0，不同（一个是0，一个是1）为1)
	|：只要有1，结果是1
	 */
	@Test
	public void bitOperation(){
		System.out.println(5|3);//7
		System.out.println(1|3);//3
		System.out.println(1^3);//2
		System.out.println(1^0);//1
		System.out.println(1&3);//1
	}
	

}
