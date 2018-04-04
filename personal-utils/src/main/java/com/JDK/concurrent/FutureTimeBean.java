package com.JDK.concurrent;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.*;


/**
 * Description : [A方法中-异步调用B方法，A返回值后，B依旧会执行完毕]
 * Created on : 2017年11月25日
 * author : [左仁智]
 * version : 1.0
 * Copyright (c) 2017 国美金控-美借
 */
@Slf4j
public class FutureTimeBean {

    @Test
    public void test() throws InterruptedException, ExecutionException {

        log.info("【FutureTimeBean-->file2Bean】 开始 =={}",Thread.currentThread().getId());
        log.info("【FutureTimeBean-->file2Bean】 getNum = {}",getNum());
        log.info("【FutureTimeBean-->file2Bean】 结束 =={}",Thread.currentThread().getId());

    }


    public Integer getNum() throws ExecutionException, InterruptedException {
        log.info("【FutureTimeBean-->getNum】 入参是 [{}]",Thread.currentThread().getId());
        innerMethodII();
        return 13;

    }

    private void innerMethod() throws InterruptedException {

        log.info("【FutureTimeBean-->innerMethod】 开始,{}",Thread.currentThread().getId());

        Thread.sleep(2000);
        ExecutorService threadPool = Executors.newFixedThreadPool(5);

        threadPool.submit(new Runnable() {
            @Override
            public void run() {
                log.info("【FutureTimeBean-->run】 执行异步方法结束,{}",Thread.currentThread().getId());
            }
        });

    }

    private void innerMethodII() throws ExecutionException, InterruptedException {

        log.info("【FutureTimeBean-->innerMethodII】 开始=={}",Thread.currentThread().getId());


        ExecutorService threadPool = Executors.newFixedThreadPool(4);

        Future<String> future = threadPool.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.info("【FutureTimeBean-->call】 id={} ",Thread.currentThread().getId());
                return "file2Bean-name";
            }
        });
        Thread.sleep(20000);
        boolean flag = true;
        while(flag){
            //异步任务完成并且未被取消，则获取返回的结果
            if(future.isDone() && !future.isCancelled()){
                log.info("【FutureTimeBean-->innerMethod】 获取异步任务执行结果 :{},id={}",future.get(),Thread.currentThread().getId());
                flag = false;
            }
        }
    }
}
