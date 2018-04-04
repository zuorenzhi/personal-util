package com.JDK.concurrent.google.guava;

import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description : [漏桶算法和令牌桶算法]
 * url : http://blog.csdn.net/scorpio3k/article/details/53103239
 * guava的RateLimiter使用的是令牌方式
 * Created on : 2018年01月31日
 * author : [左仁智]
 * version : 1.0
 * Copyright (c) 2018 国美金控-美借
 */
@Slf4j
public class RateLimiterBean {

    //速率是每秒两个许可
    final RateLimiter rateLimiter = RateLimiter.create(2.0);

    private void exec(List<Task> tasks, Executor executor) {
        for (Runnable task : tasks) {
            rateLimiter.acquire();//也许需要等待
            executor.execute(task);
        }
    }

    @Test
    public void test() {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        ArrayList tasks = new ArrayList<>();
        Collections.addAll(tasks, new Task(1), new Task(2),new Task(3),new Task(4),
                new Task(5),new Task(6),new Task(7),new Task(8),new Task(9));
        //调用方法
        exec(tasks,threadPool);
    }

    static class Task implements Runnable {
        int num ;

        public Task(int num) {
            this.num = num;
        }

        @Override
        public void run() {
            log.info("【Task-->run】 入参是 {}",num);
        }
    }
}

