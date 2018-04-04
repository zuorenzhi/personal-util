package com.queue.java4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Description: 描述 <br/>
 * Created on: 2017/6/19 12:01 <br/>
 *
 * @author: <a href="mailto: zuorenzhi@hahaha.com">zuorenzhi</a><br/>
 * @version: 1.0 <br/>
 * Copyright (c) 2017年 北京柯莱特科技有限公司 交付部
 */


public class ToastOMatic {

    public static void main(String[] args) throws InterruptedException {
        ToastQueue dryQueue = new ToastQueue();
        ToastQueue buttererQueue = new ToastQueue();
        ToastQueue finishedQueue = new ToastQueue();


        ExecutorService threadPool = Executors.newCachedThreadPool();
        threadPool.execute(new Toaster(dryQueue));
        threadPool.execute(new Butterer(dryQueue,buttererQueue));
        threadPool.execute(new Jammer(buttererQueue,finishedQueue));
        threadPool.execute(new Eater(finishedQueue));

        TimeUnit.SECONDS.sleep(5);
        threadPool.shutdown();
    }
}
