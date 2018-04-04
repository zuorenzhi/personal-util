package com.JDK.concurrent;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description : [Java并发包中CountDownLatch的工作原理、使用示例,先后顺序]
 * url:https://www.cnblogs.com/nullzx/p/5272807.html
 * Created on : 2018年01月22日
 * author : [左仁智]
 * version : 1.0
 * Copyright (c) 2018 国美金控-美借
 */

public class CountDownLatchBean {
    //todo 此处初始值依赖于，调用 countDownLatch.countDown() 方法的实现类的使用个数
    //todo 要把非await的任务都执行完毕，state=0，await的任务才会接着执行
    private CountDownLatch countDownLatch = new CountDownLatch(3);
    private Random random = new Random();

    class FirstTask implements Runnable{
        private String id;

        public FirstTask(String id){
            this.id = id;
        }

        @Override
        public void run(){
            System.out.println("Thread "+ id + " is start");
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread "+ id + " is over");
            countDownLatch.countDown();
        }
    }

    class SecondTask implements Runnable{
        private String id;

        public SecondTask(String id){
            this.id = id;
        }

        @Override
        public void run(){
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("----------Thread "+ id + " is start");
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("----------Thread "+ id + " is over");
        }
    }

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newCachedThreadPool();
        CountDownLatchBean countDownLatchBean = new CountDownLatchBean();
        executorService.submit(countDownLatchBean.new SecondTask("c"));
        executorService.submit(countDownLatchBean.new SecondTask("d"));
        executorService.submit(countDownLatchBean.new SecondTask("e"));
        executorService.submit(countDownLatchBean.new FirstTask("a"));
        executorService.submit(countDownLatchBean.new FirstTask("b"));
        executorService.submit(countDownLatchBean.new FirstTask("aa"));
        executorService.shutdown();
    }
}
