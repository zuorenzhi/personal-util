package com.junit;

import org.junit.Test;

import java.util.Random;

/**
 * Description : [类描述]
 * Created on : 2017年12月26日
 * author : [左仁智]
 * version : 1.0
 * Copyright (c) 2017 国美金控-美借
 */

public class TestBean {

    @Test
    public void test(){

        System.out.println(System.identityHashCode("2"));
        System.out.println(System.identityHashCode("abc"));
//        System.out.println("000"+new Random().nextInt(100000));
//        System.out.println("abc123456"+ new Random().nextInt(100000));
        int i = new Random().nextInt(100000);
        System.out.println(i);
    }
}
