package com.algorithm.sort;

import java.util.Arrays;

import org.junit.Test;

/**
 * 对于嵌套循环，计数时需要清晰：外环跑一步，内环跑一圈/一轮
 */

/**
 * <p>Description: [排序算法]</p>
 * Created on 2017年1月10日
 * @author  <a href="mailto: zuorenzhi@clt.com">左仁智</a>
 * @version 1.0 
 * Copyright (c) 2017 北京柯莱特科技有限公司 交付部
 */
public class Xuanze {


	/**
	 * <p>Discription:[交换数字]</p>
	 * Created on 2017年1月10日
	 * @param intArr
	 * @param i
	 * @param j
	 * @author:[左仁智]
	 */
	private void exchange(int[] intArr, int i, int j) {
		int temp = intArr[i];
		intArr[i] = intArr[j];
		intArr[j] = temp;
	}
	
	@Test
	public void testSort(){
		int arr [] = new int []{5,2,6,3,8,1,9,88,23,11};//10个元素
		System.out.printf("初始数组 %s%n",Arrays.toString(arr));
		System.out.println(Arrays.toString(xuanzeSort(arr)));
	}
	
	private int [] xuanzeSort(int [] intArr){
		int i,j,min,count = 0;
		for (i = 0; i < intArr.length; i++) {
			min = i;
			for (j = i+1; j < intArr.length; j++) {
				count++;
				if(intArr[min] > intArr[j]){ 
					min = j;
				}
			}
			if(min != i){
				//把本轮最小的值，赋值给本轮第一个元素
				exchange(intArr, i, min);
			}
			System.out.printf("第%d轮循环后的数组%s%n",i+1,Arrays.toString(intArr));
		}
		System.out.printf("选择排序算法%d个元数比较次数--%d次%n",intArr.length,count);
		return intArr;
	}
	
}
