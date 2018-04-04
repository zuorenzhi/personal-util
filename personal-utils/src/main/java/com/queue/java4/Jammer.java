package com.queue.java4;

/**
 * Description: 描述 <br/>
 * Created on: 2017/6/19 10:52 <br/>
 *
 * @author: <a href="mailto: zuorenzhi@hahaha.com">zuorenzhi</a><br/>
 * @version: 1.0 <br/>
 * Copyright (c) 2017年 北京柯莱特科技有限公司 交付部
 */


public class Jammer implements Runnable {
    private ToastQueue buttererQueue;
    private ToastQueue finishedQueue;

    public Jammer(ToastQueue finishedQueue, ToastQueue buttererQueue) {
        this.finishedQueue = finishedQueue;
        this.buttererQueue = buttererQueue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                Toast toast = buttererQueue.take();
                toast.jam();
                System.out.println(toast);
                finishedQueue.put(toast);//放入队列
            }
        } catch (InterruptedException e) {
            System.out.println("Jammer interrupted");
        }
        System.out.println("Jammer off");
    }
}
