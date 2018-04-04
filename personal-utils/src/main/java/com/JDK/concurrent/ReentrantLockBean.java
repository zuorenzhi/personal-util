package com.JDK.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Description : [java 显式锁ReentrantLock使用详解之条件对象(2)]
 * url: http://blog.csdn.net/u011784767/article/details/51656697
 * Created on : 2017年12月12日
 * author : [左仁智]
 * version : 1.0
 * Copyright (c) 2017 国美金控-美借
 */

public class ReentrantLockBean {

    private ReentrantLock myLock = new ReentrantLock();

    private Condition condition = myLock.newCondition();

    private List<Integer> listBuffer = new ArrayList<Integer>();

    private volatile boolean runFlag = true;

    /**
     * 生产者 生产数据
     */
    public void produce() {
        int i = 0;
        while(runFlag) {
            myLock.lock();
            try {
                // 生产者检查容器中是否有数据，如果容器中有数据则生产者等待
                // 如果容器中没有数据则生产数据放入容器中并通知消费者
                if (listBuffer.size() > 0) {
                    try {
                        // 调用await()方法，生产者线程阻塞并释放锁，之后进入该条件的等待集中
                        // 直到消费者调用signalAll()方法之后，生产者线程解除阻塞并重新竞争锁
                        // 生产者线程获得锁之后，重新开始从被阻塞的地方继续执行程序
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println(Thread.currentThread().getName() + " add Integer");
                    listBuffer.add(i++);
                    // 生产者线程调用signalAll()方法，通知消费者线程容器中有数据
                    condition.signalAll();
                }
            } finally {
                myLock.unlock();
            }
        }
    }

    /**
     * 消费者 读取数据
     */
    public void consume() {
        while(runFlag) {
            myLock.lock();
            try {
                // 消费者检查容器中是否有数据，如果没有数据消费者等待
                // 如果容器中有数据则读取数据，读完之后通知生产者
                if (listBuffer.size() == 0) {
                    try {
                        // 同生产者线程一样，消费者线程调用await()方法阻塞并进入该条件等待集中
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println(Thread.currentThread().getName() + " get Integer");
                    long beginTime = 0;
                    System.out.println(listBuffer.remove(0));
                    beginTime = System.currentTimeMillis();
                    while(System.currentTimeMillis() - beginTime < 100) {}
                    // 消费者线程调用signalAll()方法，通知生产者生产数据
                    condition.signalAll();
                }
            } finally {
                myLock.unlock();
            }
        }
    }



    public boolean isRunFlag() {
        return runFlag;
    }

    public void setRunFlag(boolean runFlag) {
        this.runFlag = runFlag;
    }

    public static void main(String[] args) {
        final ReentrantLockBean test = new ReentrantLockBean();

        Thread produce = new Thread(new Runnable() {
            public void run() {
                test.produce();
            }
        },"A");

        Thread consume = new Thread(new Runnable() {
            public void run() {
                test.consume();
            }
        },"B");

        produce.start();
        consume.start();

        try {
            //主线程等待,让生产者和消费者跑一会
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        test.setRunFlag(false);
    }

}
