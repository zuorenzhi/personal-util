package com.algorithm.sort;

import java.util.Arrays;

import org.junit.Test;

/**
 * 对于嵌套循环，计数时需要清晰：外环跑一步，内环跑一圈/一轮
 */

/**
 * <p>Description: [排序算法]</p>
 * Created on 2017年1月10日
 * @author  <a href="mailto: zuorenzhi@camelotchina.com">左仁智</a>
 * @version 1.0 
 * Copyright (c) 2017 北京柯莱特科技有限公司 交付部
 */
public class Quick {

	@Test
	public void testSort(){
		int arr [] = new int []{5,2,6,3,8,1,9,88,23,11};//10个元素
		System.out.printf("--------初始数组 %s%n",Arrays.toString(arr));
		quick(arr);
		System.out.printf("--------快速排序后数组 %s排序次数count=%d次%n",Arrays.toString(arr),count);
	}
	
	/**
	 * <p>Discription:[快速排序]</p>
	 * 参考地址 http://www.cnblogs.com/liuling/p/2013-7-24-01.html
	 * Created on 2017年1月11日
	 * @param a
	 * @return
	 * @author:[左仁智]
	 */
	private void quick(int [] a){
		//此处可以根据数组长度选取不同的排序算法
       if(a.length > 0){
    	   quickSort(a,0,a.length-1);
       }
	}

	int count = 0;
	private void quickSort(int[] a, int lowIndex, int highIndex) {
		count ++;
		if(lowIndex < highIndex){//次数不判断会数组越界，栈溢出
			int middle = getMiddle(a,lowIndex,highIndex);
			quickSort(a, lowIndex, middle-1);
			quickSort(a, middle+1, highIndex);
		}
	}

	private int getMiddle(int[] a,int lowIndex, int highIndex) {
		int beginArr [] = Arrays.copyOf(a, a.length);
		int beginLowIndex = lowIndex;
		int beginHighIndex = highIndex;
		int temp = a[lowIndex];
		while (lowIndex < highIndex) {
			while(lowIndex < highIndex && a[highIndex] >= temp){
				highIndex --;
			}
			a[lowIndex] = a[highIndex];//若a[highIndex] < temp
			while(lowIndex < highIndex && a[lowIndex] <= temp){
				lowIndex ++;
			}
			a[highIndex] = a[lowIndex];
		}
		a[lowIndex] = temp;
		System.out.printf("开始%s%n结束%s%n:%d在%d到%d的middle=%d%n",Arrays.toString(beginArr),Arrays.toString(a),
				temp,beginLowIndex,beginHighIndex,lowIndex);
		return lowIndex;
	}
	
	
	/***********************************************优化**************************************************************
	 * 1优化选取轴 三数取中,9数取中[分三次抽样,每次抽取三个数,将各数的中间值再比较，选出中间值，用该值作为选取轴,尽量使得原数组对半分]
	 * 2优化不必要的交换 exchange
	 * 3 优化小数组时的排序方案,根据arr.length不大于某数值[资料显示7合适，也有显示50合适]判断,可用基本插入，冒泡之类的
	 * 4优化递归操作
	 * */
	
	//优化--optimize
	private void quickSortBetter(int[] a, int lowIndex, int highIndex) {
		count ++;
		//每次排序左边部分，结果相同，但因采用迭代而不是递归的方法可以缩减堆枝深度，从而提高了整体性能。
		while(lowIndex < highIndex){	//次数不判断会数组越界，栈溢出
			int middle = getMiddle(a,lowIndex,highIndex);
			quickSort(a, lowIndex, middle-1);
			lowIndex = middle + 1;
		}
	}
	
}
