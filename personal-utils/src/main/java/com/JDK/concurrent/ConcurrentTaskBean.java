package com.JDK.concurrent;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.concurrent.*;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * <p>Description: [java.util.concurrent工具包测试]</p>
 * Created on 2017年1月12日
 *
 * @author <a href="mailto: zuorenzhi@clt.com">左仁智</a>
 * @version 1.0
 *          Copyright (c) 2017 北京柯莱特科技有限公司 交付部
 */
@Slf4j
public class ConcurrentTaskBean {

    /**
     * <p>Discription:[单个任务执行,有返回结果]</p>
     * Created on 2017年1月12日
     *
     * @author:[左仁智]
     */
    @Test
    public void newSingleThreadExecutor() {
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        //此处String可以替换--接口定义为泛型
        CompletionService<String> pool = new ExecutorCompletionService<String>(singleThreadExecutor);
        try {
            pool.submit(new CallBackBean(0));
            String result = pool.take().get();
            log.info("【TestConcurrentTask-->newSingleThreadExecutor】 执行结果= [{}]",result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        singleThreadExecutor.shutdown();
    }

    /**
     * <p>Discription:[多个任务执行,有返回结果]</p>
     * Created on 2017年1月12日
     * @author:[左仁智]
     */
    @Test
    public void newFixedThreadPool() {
        int threadNum = 5;
        int maxNum = 10;
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(threadNum);
        CompletionService<String> pool = new ExecutorCompletionService<String>(fixedThreadPool);
        try {
            for (int i = 0; i < maxNum; i++) {
                Future<String> future = pool.submit(new CallBackBean(i));
                String s = future.get();
                log.info("【TestConcurrentTask-->newFixedThreadPool】 执行结果 [i={},s={}]",i,s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        fixedThreadPool.shutdown();
    }

    /**
     * https://www.oschina.net/question/234345_45865
     * <p>Discription:[来自网上的demo任务执行]</p>
     * Created on 2017年1月12日
     *
     * @author:[左仁智]
     */
    @Test
    public void newFixedThreadPoolFromNet() {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 20; i++) {
            executor.execute(new RunImpl(i));
//            executor.submit(new RunImpl(i));
        }
        executor.shutdown();
        try {
            //如果主线程不等待，任务执行不完就结束了
            executor.awaitTermination(100, TimeUnit.SECONDS);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * @see  http://blog.csdn.net/qq_31390937/article/details/72353739;http://blog.csdn.net/u010425776/article/details/54580710
     * Discription:批量获取多线程执行结果，合并/组合多线程执行结果【国美金融面试】
     * [CompletionService:将执行器和队列注入，‘任务’完成处理器,take出结果，若无就阻塞]
     * Created on: 2017/7/5 13:45
     * @param:
     * @author: zuorenzhi
     */
    @Test
    public void batchThreadPoolResult() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        //初始化的队列数量，超过则会阻塞
        final BlockingQueue<Future<Integer>> queue = new LinkedBlockingDeque<Future<Integer>>(10);
        //实例化CompletionService
        final CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(executorService, queue);
        // 提交10个任务
        for (int i = 0; i < 100; i++) {
            completionService.submit(new Callable<Integer>() {
                public Integer call() throws InterruptedException {
                    int sleepTime = new Random().nextInt(100);
                    Thread.sleep(sleepTime);
                    System.out.println("线程" + Thread.currentThread().getId() + "睡了" + sleepTime + "ms");
                    return Integer.valueOf(Thread.currentThread().getId()+"");
                }
            });
        }
        ArrayList<Object> list = new ArrayList<>();
        // 输出结果
        for (int i = 0; i < 9; i++) {
            //todo 获取包含返回结果的future对象（若整个阻塞队列中还没有一条线程返回结果，那么调用take将会被阻塞，当然你可以调用poll，不会被阻塞，若没有结果会返回null，poll和take返回正确的结果后会将该结果从队列中删除）
            Future<Integer> future = completionService.take();
            // 从future中取出执行结果，这里存储的future已经拥有执行结果，get不会被阻塞
            Integer result = future.get();
            list.add(result);
        }
        System.out.println(list.size());
        System.out.println(list);
    }


    /**
    * Description: 自定义回调的Bean
    * Created on 2017/7/5 13:11
    * @author  zuorenzhi
    * @version 1.0
    * Copyright (c) 2017年 国美融通科技 消费金融
    */
    static class CallBackBean implements Callable<String> {
        private int code;
        public CallBackBean(int code) {
            this.code = code;
        }
        @Override
        public String call() throws Exception {
            log.info("【CallBackBean-->call】 入参是 [{}]",code);
            return "执行第" + code + "次";
        }
    }

    /**
     * https://www.oschina.net/question/234345_45865
     * <p>Description: [来自网上的demo类]</p>
     * Created on 2017年1月12日
     * @author <a href="mailto: zuorenzhi@clt.com">左仁智</a>
     * @version 1.0
     * Copyright (c) 2017 北京柯莱特科技有限公司 交付部
     */
    static class RunImpl implements Runnable {
        public RunImpl(int data) {
            this.data = data;
        }
        @Override
        public void run() {
            log.info("【RunImpl-->run】 入参是 [{}]",data);
        }
        private int data;
    }
}






