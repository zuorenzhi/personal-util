package com.JDK.thread.volatilej;

import org.junit.Test;

/**
 * Description : [类描述]
 * Created on : 2017年08月31日
 * author : [左仁智]
 * version : 1.0
 * Copyright (c) 2017 国美金控-美借
 */

public class TestVolatile {

    public volatile boolean flag = false;


    @Test
    public void test() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            while (!flag) {
                doSomethingI();
            }
            while (flag){
                doSomethingII();
            }
        }

//        Thread.sleep(1000);
    }

    private void doSomethingI() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("doSomethingI");
            }
        }).start();
        flag = true;
    }

    private void doSomethingII() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("doSomethingII");
            }
        }).start();
        flag = false;
    }


}
