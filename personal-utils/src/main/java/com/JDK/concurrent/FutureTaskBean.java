package com.JDK.concurrent;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Description : [类描述]
 * Created on : 2017年11月23日
 * author : [左仁智]
 * version : 1.0
 * Copyright (c) 2017 国美金控-美借
 */

@Slf4j
public class FutureTaskBean {


    @Test
    public void testI(){
        ExecutorService threadPool = Executors.newFixedThreadPool(5);

        log.info("【FutureTaskBean-->getNum】 开始 [time={}]",System.currentTimeMillis());
        FutureTask<List<Integer>> task = new FutureTask<List<Integer>>(new Callable<List<Integer>>() {
            @Override
            public List<Integer> call() throws Exception {
                return getList(new ArrayList<>());
            }
        });
        threadPool.execute(task);//execute
        boolean flag = true;

        List<Integer> list = null;

        while (flag) {
            if (task.isDone() && !task.isCancelled()) {
                try {
                    list = task.get();
                    log.info("【FutureTaskBean-->main】  [list={}]",list);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                flag = false;
            }
        }
        log.info("【FutureTaskBean-->getNum】 结束 [time={}]",System.currentTimeMillis());

        if (!threadPool.isShutdown()) {
            threadPool.shutdown();
        }
    }

    private List getList(final List<Integer> list) {

        log.info("【FutureTaskBean-->getList】 开始 [time={}]",System.currentTimeMillis());
        for (int i = 0; i <10 ; i++) {
            list.add(i);
            try {
                Thread.sleep(500);//休眠2s
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        log.info("【FutureTaskBean-->getList】 结束 [time={}]",System.currentTimeMillis());
        return list;
    }

}


