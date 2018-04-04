package com.queue.java4;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Description: 描述 <br/>
 * Created on: 2017/6/19 10:52 <br/>
 *
 * @author: <a href="mailto: zuorenzhi@hahaha.com">zuorenzhi</a><br/>
 * @version: 1.0 <br/>
 * Copyright (c) 2017年 北京柯莱特科技有限公司 交付部
 */


public class Toaster implements Runnable {
    private ToastQueue toastQueue;
    private int count = 0;
    private Random random = new Random(47);

    public Toaster(ToastQueue toastQueue) {
        this.toastQueue = toastQueue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(100+random.nextInt(500));
                //make toast
                Toast toast = new Toast(count++);
                System.out.println(toast);
                System.out.println("生产 = " + toast);
                toastQueue.put(toast);//放入队列
            }
        } catch (InterruptedException e) {
            System.out.println("Toaster interrupted");
        }
        System.out.println("Toaster off");
    }
}
