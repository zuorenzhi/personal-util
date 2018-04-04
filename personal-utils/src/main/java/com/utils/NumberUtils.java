package com.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.text.DecimalFormat;

/**
 * <p>Description: [求一数字的位数]</p>
 * Created on 2016年12月27日
 * @author  <a href="mailto: zuorenzhi@camelotchina.com">左仁智</a>
 * @version 1.0 
 * Copyright (c) 2016 北京柯莱特科技有限公司 交付部
 */
@Slf4j
public class NumberUtils {

	final static int[] sizeTable = { 9, 99, 999, 9999, 99999, 999999, 9999999,
			99999999, 999999999, Integer.MAX_VALUE };

	static int sizeOfInt(int x) {
		for (int i = 0;; i++)
			if (x <= sizeTable[i])
				return i + 1;
	}

	public static void main(String[] args) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i <1000 ; i++) {
					log.info("thread={},i={},result={}",Thread.currentThread().getId(),i,parseAmount("23.9999"));
				}
			}
		}).start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i <1000 ; i++) {
					log.info("thread={},i={},result={}",Thread.currentThread().getId(),i,parseAmount("23.9999"));
				}
			}
		}).start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i <1000 ; i++) {
					log.info("thread={},i={},result={}",Thread.currentThread().getId(),i,parseAmount("23.9999"));
				}
			}
		}).start();

	}

	@Test
	public void parseDouble(){
//		System.out.println(Double.parseDouble("23.99999"));
//		System.out.println(new DecimalFormat("0.00").format(Double.parseDouble("23.99999")));
//		for (int i = 0; i <100 ; i++) {
//			System.out.println(parseAmount("23.9999"));
//		}

	}

	/**
	 * Discription: [将字符串金额转换为0.00格式字符串]
	 * Created on: 2017-12-03 14:13
	 * param :
	 * return :
	 * author : [左仁智]
	 */
	public static String parseAmount(String bigdecimalStr){
		String pattern = "0.00";
		if (StringUtils.isBlank(bigdecimalStr)) {
			return "0.00";
		}
		try {
			return new DecimalFormat(pattern).format(Double.parseDouble(bigdecimalStr));
		} catch (NumberFormatException e) {
			log.error("【BigdecimalUtils-->parseAmount】 调用异常 入参={}",bigdecimalStr);
		}
		return "0.00";
	}



}
