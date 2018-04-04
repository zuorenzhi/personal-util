package com.algorithm.classic;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;

/**
 * Description : [古典问题：有一对兔子，从出生后第3个月起每个月都生一对兔子...]
 * url:http://blog.csdn.net/u013372519/article/details/38175953
 * Created on : 2018年01月29日
 * author : [左仁智]
 * version : 1.0
 * Copyright (c) 2018 国美金控-美借
 */
@Slf4j
public class Rabbit {

    public static void main(String[] args) {
        Rabbit rabbit = new Rabbit();
//        System.out.println(rabbit.sum(-2));
        for (int i = 1; i < 20; i++) {
            log.info("【Rabbit-->main】 入参是 [sum({})={}]",i,rabbit.sum(i));
        }
    }


    // 1,1,2,3,5,8,13,21.........
    private int sum(int month) {
        if (month <= 0) {
            throw new RuntimeException("参数必须为正整数!");
        }
        if (month == 1 || month == 2) {
            return 1;
        }
        return sum(month - 1) + sum(month - 2);
    }

    private int sumI(int month) {
        Assert.assertTrue(month > 0);
        if (month == 1 || month == 2) {
            return 1;
        }
        return sum(month - 1) + sum(month - 2);
    }


}
