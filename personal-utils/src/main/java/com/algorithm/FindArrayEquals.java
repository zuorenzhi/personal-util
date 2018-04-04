package com.algorithm;

import lombok.extern.slf4j.Slf4j;

/**
 * Description : [算法]
 * Created on : 2018年02月05日
 * author : [左仁智]
 * version : 1.0
 * Copyright (c) 2018 国美金控-美借
 */
@Slf4j
public class FindArrayEquals {

    public static void main(String[] args) {
        System.out.println("111");
        int arr[] = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
        invoke(arr);
    }

    private static void invoke(int[] arr) {
        int i = 0;
        int j = arr.length - 1;
        int n = 12;
        while (i < j) {
            if (arr[i] + arr[j] < n) {
                i++;
            } else if (arr[i] + arr[j] > n) {
                j--;
            } else {
                i++;
                j--;
                log.info("【FindArrayEquals-->invoke】 入参是 arr[i]={},arr[j]={}", arr[i], arr[j]);
            }
        }
    }


}
