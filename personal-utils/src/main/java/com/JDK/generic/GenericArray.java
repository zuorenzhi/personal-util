package com.JDK.generic;

import java.util.Arrays;

import org.junit.Test;

/**
* Description : [泛型数组操作]
* Created on : 2018-03-06 15:07
* author : [左仁智]
* version : 1.0
* Copyright (c) 2018 国美金控-美借
*/
public class GenericArray {


	@Test
	public void swapT(){
		Integer[] swap = swap(new Integer[]{1,2,3,4,5}, 0, 1);
		System.out.println(Arrays.toString(swap));
	}
	@Test
	public void reverseT(){
		Integer[] swap = reverse(new Integer[]{1,2,23,3,5});
		System.out.println(Arrays.toString(swap));
	}
	/**
	 * Discription: [交换数组中指定index的值]
	 * Created on: 2018-03-06 15:04
	 * author : [左仁智]
	 */
	private  <T> T[] swap(T array[],int pos1,int pos2){
		T t = array[pos1];
		array[pos1] = array[pos2];
		array[pos2] = t;
		return array;
	}

	/**
	 * Discription: [数组倒序输出]
	 * Created on: 2018-03-06 15:06
	 * author : [左仁智]
	 */
	private  <T> T[] reverse(T[] arr){
		int start = 0;
		int end = arr.length-1;
		while(start < end){
			T temp = arr[start];
			arr[start] = arr[end];
			arr[end] = temp;
			
			start++;
			end--;
		}
		return arr;
	}
	
	
}
