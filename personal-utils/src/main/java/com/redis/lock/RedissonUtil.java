package com.redis.lock;

import org.redisson.RedissonClient;
import org.redisson.core.*;

import java.util.concurrent.TimeUnit;

/**
 * Description : [redis分布式锁的实现Redisson支持的方法]
 * url: http://blog.csdn.net/l1028386804/article/details/73523810
 * Created on : 2018年02月07日
 * author : [左仁智]
 * version : 1.0
 * Copyright (c) 2018 国美金控-美借
 */

public class RedissonUtil {


    public void testReentrantLock(RedissonClient redisson){
        RLock lock = redisson.getLock("anyLock");
        try{
            // 1. 最常见的使用方法
            //lock.lock();
            // 2. 支持过期解锁功能,10秒钟以后自动解锁, 无需调用unlock方法手动解锁
            //lock.lock(10, TimeUnit.SECONDS);
            // 3. 尝试加锁，最多等待3秒，上锁以后10秒自动解锁
            boolean res = lock.tryLock(3, 10, TimeUnit.SECONDS);
            if(res){ //成功
                // do your business
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     * Discription: [
     *
     * 可重入锁Reentrant Lock
     公平锁Fair Lock
     联锁MultiLock
     红锁RedLock
     读写锁ReadWriteLock
     信号量Semaphore
     可过期性信号量PermitExpirableSemaphore
     闭锁CountDownLatch
     转载请注明出处：http://blog.csdn.net/l1028386804/article/details/73523810
     * ]
     * Created on: 2018-02-07 16:52
     * author : [左仁智]
     */
    public void lock(RedissonClient redisson){
        //1
        RLock lock = redisson.getLock("anyLock");
        //2
        RLock fairLock = redisson.getFairLock("");
        //3 联锁
        RedissonMultiLock multiLock = new RedissonMultiLock(lock,fairLock);
        //4 红锁
        RedissonRedLock redLock = new RedissonRedLock(lock,fairLock);
        //5 读写锁
        RReadWriteLock readWriteLock = redisson.getReadWriteLock("anyLock");
        // 6
        RSemaphore semaphore = redisson.getSemaphore("");
        //7
        //RPermitExpirableSemaphore w = redisson.getPermitExpirableSemaphore("mySemaphore");
        //8
        RCountDownLatch countDownLatch = redisson.getCountDownLatch("");
        try{

        } finally {
            lock.unlock();
        }
    }



}
