package com.JDK.concurrent;

import org.apache.commons.lang3.concurrent.ConcurrentUtils;

import java.util.concurrent.*;

/**
* Description: java.util.concurrent包的学习
* Created on 2017/7/4 12:51
* @author  zuorenzhi
* @version 1.0
* Copyright (c) 2017年 国美融通科技 消费金融
*/
public class ConcurrentBean {

    /**
     * Discription:  并发集合的创建方式
     * Created on: 2017/7/4 12:47
     * @author: zuorenzhi
     */
    public void concurrentCollections() {
        ConcurrentHashMap<Object, Object> concurrentHashMap = new ConcurrentHashMap<>();
        ConcurrentLinkedQueue<Object> concurrentLinkedQueue = new ConcurrentLinkedQueue<>();
//        ConcurrentMap<Object, Object> concurrentMap = new ConcurrentMap<String,Integer>();
        ConcurrentSkipListMap<Object, Object> concurrentSkipListMap = new ConcurrentSkipListMap<>();
        ConcurrentSkipListSet<Object> concurrentSkipListSet = new ConcurrentSkipListSet<>();

        Future<Integer> integerFuture = ConcurrentUtils.constantFuture(new Integer(1));
        try {
            integerFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    /** 
     * Discription:  Executors几种创建线程池的方式
     * Created on: 2017/7/4 11:54
     * @author: zuorenzhi
     */
    public void threadPoolCategories(){
        //固定线程池
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);
        //缓存线程池(理论无上限，实际坑多)
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        //单个线程池
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        //单个线程池
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        //构造执行器
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
    }

}
