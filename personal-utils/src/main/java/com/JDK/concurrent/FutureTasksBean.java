package com.JDK.concurrent;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.*;

/**
 * Description : [多任务异步完成后，组合各任务结果]
 * Created on : 2017年11月25日
 * author : [左仁智]
 * version : 1.0
 * Copyright (c) 2017 国美金控-美借
 */

@Slf4j
public class FutureTasksBean {

    @Test
    public void test() throws ExecutionException, InterruptedException {

        ExecutorService threadPool = Executors.newFixedThreadPool(5);

        FutureTask<Integer> IntegerTask = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Thread.sleep(1000);
                return 10;
            }
        });

        FutureTask<Long> longFutureTask = new FutureTask<>(new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                Thread.sleep(2000);
                return 20L;
            }
        });

        FutureTask<String> stringFutureTask = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(3000);
                return "zuorenzhi";
            }
        });

        threadPool.execute(IntegerTask);

        Future<JSONObject> futureJson = threadPool.submit(new Callable<JSONObject>() {
            @Override
            public JSONObject call() throws Exception {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("name", "张三");
                jsonObject.put("sex", "男");
                return jsonObject;
            }
        });


        threadPool.execute(longFutureTask);
        threadPool.execute(stringFutureTask);

        boolean flag = true;
        JSONObject jsonObject = new JSONObject();
        while (flag) {

            if (IntegerTask.isDone() && !IntegerTask.isCancelled()
                    && longFutureTask.isDone() && !longFutureTask.isCancelled()
                    && stringFutureTask.isDone() && !stringFutureTask.isCancelled()
                    && futureJson.isDone() && !futureJson.isCancelled()) {
                jsonObject.put("integer",IntegerTask.get());
                jsonObject.put("long",longFutureTask.get());
                jsonObject.put("string",stringFutureTask.get());
                jsonObject.put("json",futureJson.get());
                flag = false;
            }
        }

        log.info("【FutureTasksBean-->file2Bean】 出参是 jsonObj = {}",jsonObject.toJSONString());

    }

}
