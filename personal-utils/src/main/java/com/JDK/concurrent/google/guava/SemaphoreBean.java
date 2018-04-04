package com.JDK.concurrent.google.guava;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Description : [java.util.concurrent.Semaphore ,使用Semphore进行并发流控]
 * url: http://blog.csdn.net/scorpio3k/article/details/53103239
 * Created on : 2018年01月31日
 * author : [左仁智]
 * version : 1.0
 * Copyright (c) 2018 国美金控-美借
 */
@Slf4j
public class SemaphoreBean {

    public static void main(String[] args) {

        ExecutorService threadPool = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(5);
        for (int i = 0; i < 20; i++) {
            final int no = i;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        log.info("【SemaphoreBean-->run】 执行序列号是 [{}]", no);
                        semaphore.acquire();//获取许可
                        Thread.sleep((long) (Math.random() * 10000));
                        semaphore.release();//释放许可
                        log.info("【SemaphoreBean-->run】 availablePermits = [{}]", semaphore.availablePermits());
                    } catch (InterruptedException e) {
                        log.error("【SemaphoreBean-->run】 调用异常 []", e);
                    }
                }
            };
            threadPool.execute(runnable);
        }
        threadPool.shutdown();
    }

}
