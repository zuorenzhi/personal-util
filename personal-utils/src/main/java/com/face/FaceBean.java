package com.face;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;

/**
 * Description : [面试,程序题目]
 * Created on : 2018年02月07日
 * author : [左仁智]
 * version : 1.0
 * Copyright (c) 2018 国美金控-美借
 */
@Slf4j
public class FaceBean {

    /**
     * Discription: [给定数字反转显示,忽略有效数字前的0-思路2(面试官给的思路?)]
     * 实现一个int数值的反转,例如394变为493,3200变为23
     * Created on: 2017-08-14 16:03
     * author : [左仁智]
     */
    @Test
    public void charReverse() {
        int num = 135000;//12357
        char[] charArray = String.valueOf(num).toCharArray();
        System.out.println(Arrays.toString(charArray));
        String temp = "";
        for (int i = charArray.length-1; i >= 0 ; i--) {
            temp += charArray[i];
        }
        System.out.println(temp);
        System.out.println(Integer.valueOf(temp));
    }

    /**
     * Discription: [给定数字反转显示,忽略有效数字前的0-思路1(自己的思路)]
     * Created on: 2017-08-14 16:00
     * author : [左仁智]
     */
    @Test
    public void intReverse() {
        int num = 135000;//12357
        String str = String.valueOf(num);
        char[] charArray = str.toCharArray();
        ArrayList<Integer> listNum = new ArrayList<>();
        for (char c : charArray) {
            listNum.add(Integer.valueOf(c+""));
        }
        log.info(listNum.toString());
        Collections.reverse(listNum);
        log.info(listNum.toString());

        String temp = "";
        for (Integer integer : listNum) {
            temp += integer.toString();
        }
        System.out.println(temp);
        System.out.println(Integer.valueOf(temp));
    }

    /**
     * Discription: [先有一个数组，先变小后变大 如[4,3,1,5,6],设计一种算法找到数组内最小项]
     * Created on: 2018-02-08 13:40
     * author : [左仁智]
     */
    @Test
    public void minOfArrayI(){
        List<Integer> list = Arrays.asList(4, 3, 1, 5, 6);
        System.out.println(list);
        Collections.sort(list);
        System.out.println(list);
        System.out.println(list.get(0));
    }

    @Test
    public void minOfArrayII(){
        List<Integer> list = Arrays.asList(9, 8, 7, 6, 5, 4, 3, 1, 4, 5, 6);
        int i = 0;//因为同时和左右比较
        int length = list.size() - 1;
        while (i < length) {
            if (i > 0 ) {
//                if (list.get(i) < list.get(i+1) && list.get(i) < list.get(i - 1)) {
//                    log.info("【FaceBean-->minOfArrayII】 [i={},value={}]",i,list.get(i));
//                    break;
//                }
                //上面拦住了，i+1 <= length
                if (list.get(i) < list.get(i+1) ) {
                    log.info("【FaceBean-->minOfArrayII】 [i={},value={}]",i,list.get(i));
                    break;
                }
            }
            i++;
        }
    }

    /**最小和最大*/
    @Test
    public void minOfArrayIII() {
        List<Integer> list = Arrays.asList(9, 8, 7, 6, 5, 4, 3, 1, 4, 5, 6);
        int min = list.get(0);
        int max = list.get(0);
        int index = 0;
        int indexMax = 0;
        for (int i = 1; i < list.size() - 1; i++) {
            if (list.get(i) < min) {
                min = list.get(i);
                index = i;
            }
            if (list.get(i) > max) {
                max = list.get(i);
                indexMax = i;
            }
        }
        log.info("【FaceBean-->minOfArrayIII】 入参是 [min={},index={}]", min, index);
        log.info("【FaceBean-->minOfArrayIII】 入参是 [max={},indexMax={}]", max, indexMax);


    }


}
