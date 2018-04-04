package com.queue.java4;

/**
 * Description: 描述 <br/>
 * Created on: 2017/6/19 10:52 <br/>
 *
 * @author: <a href="mailto: zuorenzhi@hahaha.com">zuorenzhi</a><br/>
 * @version: 1.0 <br/>
 * Copyright (c) 2017年 北京柯莱特科技有限公司 交付部
 */


public class Eater implements Runnable {
    private ToastQueue finishedQueue;
    private int count = 0;

    public Eater(ToastQueue finishedQueue) {
        this.finishedQueue = finishedQueue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                Toast toast = finishedQueue.take();
                if (toast.getId() != count++ ||!toast.getStatus().equals(Toast.Status.JAMMED)) {
                    System.out.println(" >>> error");
                    System.exit(1);
                }
                System.out.println("chomp ! " + toast);
            }
        } catch (InterruptedException e) {
            System.out.println("Eater interrupted");
        }
        System.out.println("Eater off");
    }
}
