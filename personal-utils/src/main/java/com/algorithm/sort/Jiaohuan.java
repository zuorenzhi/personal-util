package com.algorithm.sort;

import org.testng.annotations.Test;

import java.util.Arrays;


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
public class Jiaohuan {

	/**
	 * 得名是，竖着看时，较小的数字升到上面来，形如冒泡
	 * 此方法是不标准的冒泡方法，因其不满足两两比较,它更应该是最最简单的交换排序而已
	 * <p>Discription:[最最简单的交换排序--可升降序]</p>
	 * Created on 2017年1月10日
	 * @author:[左仁智]
	 * 比较次数是 i * j == length * length
	 */
	private int [] simplestExchangeSort(int [] intArr){
		int count = 0;
		for (int i = 0; i < intArr.length; i++) {
			for (int j = 0; j < intArr.length; j++) {
				count ++;
				if(intArr[i] > intArr[j]){
					exchange(intArr, i, j);
				}
			}
		}
		System.out.printf("最最简单交换排序算法%d个元数比较次数--%d次%n",intArr.length,count);
		return intArr;
	}

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
		int arr1 [] = new int []{5,2,6,3,8,1,9,88,23,11};//10个元素
		int arr2 [] = new int []{23, 88, 11, 9, 8, 6, 5, 3, 2, 1};//10个元素
		System.out.println(Arrays.toString(simplestExchangeSort(arr)));
		System.out.println(Arrays.toString(maopaoSort(arr1)));
		System.out.println(Arrays.toString(maopaoSortV1(arr2)));
	}
	
	/**
	 * <p>Discription:[冒泡排序--可升降序]</p>
	 * Created on 2017年1月10日
	 * @author:[左仁智]
	 * 比较次数是 (n-1)! 即 n-1的阶乘
	 */
	private int [] maopaoSort(int [] intArr){
		int count = 0;
		for (int i = 0; i < intArr.length; i++) {
			for (int j = intArr.length -1; j > i; j--) {
				count++;
				//第一轮比到 index=0 共比较 len-1次，第二轮比到index=1共比较len-2次
				if(intArr[j] > intArr[j-1]){ 
					exchange(intArr, i, j);
				}
			}
		}
		System.out.printf("冒泡排序算法%d个元数比较次数--%d次%n",intArr.length,count);
		return intArr;
	}
	/**
	 * <p>Discription:[冒泡优化]</p>
	 * 如果第 i 轮 已经一个数都没有交换，那么i+1轮和以后的轮次就不用执行了，如：98765都没有交换,则8765自然也没有交换
	 * 多余的比较没有意义，浪费性能
	 * Created on 2017年1月10日
	 * @param intArr
	 * @return
	 * @author:[左仁智]
	 */
	private int [] maopaoSortV1(int [] intArr){
		int count = 0;
		boolean flag = true;
		for (int i = 0; i < intArr.length && flag; i++) {
			flag = false;
			for (int j = i+1; j < intArr.length; j++) {
				count++;
				//第一轮 1:0开始选出最小的放在最后 内环 n-1次
				//第二轮 2:1开始选出最小的放在最后 内环 n-1 - 2 + 1 = n-2次
				//第一轮比到 index=0 共比较 len-1次，第二轮比到index=1共比较len-2次
				if(intArr[j] > intArr[j-1]){ 
					exchange(intArr, i, j);
					flag = true;
				}
			}
		}
		System.out.printf("冒泡排序算法%d个元数比较次数--%d次%n",intArr.length,count);
		return intArr;
	}
	
}
