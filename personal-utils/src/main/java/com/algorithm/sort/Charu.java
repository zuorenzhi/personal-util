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
public class Charu {

	@Test
	public void testSort(){
		int arr [] = new int []{5,2,6,3,8,1,9,88,23,11};//10个元素
		int arr1 [] = new int []{5,2,6,3,8,1,9,88,23,11};//10个元素
		System.out.printf("初始数组 %s%n",Arrays.toString(arr));
		System.out.println(Arrays.toString(chaRuSort(arr)));
		System.out.println(Arrays.toString(zhijiechaRuSort(arr1)));
	}
	
	private int [] chaRuSort(int [] intArr){
		//初始比较该是第二(index=1)和第一个(index=0)比，第一个数默认为有序的队列
		for (int i = 1; i < intArr.length; i++) {
			int temp = intArr[i];
			int j;
			//自index=i-1(包含) 开始遍历 index=i 这条数据之前所有存在的数据
			for (j = i-1; j >= 0 ; j--) {
				if(intArr[j] > temp){ 
					intArr[j+1] = intArr[j];
				}else {
					System.out.printf("break跳出且当前j值=%d%n",j);
					break;//break的目的是阻止 j--,前面某个值已经比temp小了，他前面的值更小;此时还j--会错误标记合适的index值
				}
			}
			intArr[j+1] = temp;//内循环体最后有j-- 操作,此处 j+1 相当于回到 intArr[j]处,即后移之后空闲出来的位置
			System.out.printf("第%d轮循环后的数组%s%n",i,Arrays.toString(intArr));
		}
		return intArr;
	}
	/**
	 * <p>Discription:[直接插入排序]</p>
	 * 参考地址 http://www.cnblogs.com/liuling/p/2013-7-24-01.html
	 * Created on 2017年1月11日
	 * @param a
	 * @return
	 * @author:[左仁智]
	 */
	private int [] zhijiechaRuSort(int [] a){
        for (int i = 1; i < a.length; i++) {
            int temp = a[i];//待插入元素
            int j;
            for (j = i-1; j>=0; j--) {
                if(a[j]>temp){
                    a[j+1] = a[j];//将大于temp的往后移动一位
                }else{
                    break;
                }
            }
            a[j+1] = temp;
        }
        return a;
	}
	
}
