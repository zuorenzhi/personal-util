package com.queue.java4;

/**
 * Description: 描述 <br/>
 * Created on: 2017/6/19 10:52 <br/>
 *
 * @author: <a href="mailto: zuorenzhi@hahaha.com">zuorenzhi</a><br/>
 * @version: 1.0 <br/>
 * Copyright (c) 2017年 北京柯莱特科技有限公司 交付部
 */


public class Butterer implements Runnable {
    private ToastQueue toastQueue;
    private ToastQueue buttererQueue;

    public Butterer(ToastQueue toastQueue, ToastQueue buttererQueue) {
        this.toastQueue = toastQueue;
        this.buttererQueue = buttererQueue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                Toast toast = toastQueue.take();
                toast.butter();
                System.out.println(toast);
                buttererQueue.put(toast);//放入队列
            }
        } catch (InterruptedException e) {
            System.out.println("Butterer interrupted");
        }
        System.out.println("Butterer off");
    }
}
