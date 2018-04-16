package com.utils;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>Description: [求一数字的位数]</p>
 * Created on 2016年12月27日
 * @author  <a href="mailto: zuorenzhi@clt.com">左仁智</a>
 * @version 1.0 
 * Copyright (c) 2016 北京柯莱特科技有限公司 交付部
 */
@Slf4j
public class NumberUtils {

	final static int[] sizeTable = { 9, 99, 999, 9999, 99999, 999999, 9999999,
			99999999, 999999999, Integer.MAX_VALUE };

	static int sizeOfInt(int x) {
		for (int i = 0; ; i++) {
			if (x <= sizeTable[i]) {
				return i + 1;
			}
		}
	}

	public static void main(String[] args) {
		System.out.println(sizeOfInt(1000));
		System.out.println(sizeOfInt(100));
		System.out.println(sizeOfInt(12345));
	}






}
