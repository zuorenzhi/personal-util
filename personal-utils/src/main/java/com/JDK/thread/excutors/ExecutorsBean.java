package com.JDK.thread.excutors;

import java.util.concurrent.Executors;

/**
 * Description: 描述 <br/>
 * Created on: 2017/6/28 19:17 <br/>
 *
 * @author: <a href="mailto: zuorenzhi@gomeholdings.com">zuorenzhi</a><br/>
 * Copyright (c) 2017年 国美融通科技 消费服务
 */


public class ExecutorsBean {

    public static void main(String[] args) {

        Executors.newCachedThreadPool();

        Executors.newSingleThreadExecutor();
        Executors.newFixedThreadPool(4);
        Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        Executors.newScheduledThreadPool(5);

        //可用的线程数，默认几核cpu是几个线程
        System.out.println(Runtime.getRuntime().availableProcessors());
//你 好   哈   你       你
        if (5 > 1) {
            System.out.println();
            System.out.println();
        }
        int age = 16;


    }
}
