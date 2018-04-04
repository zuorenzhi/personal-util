package com.queue;

import org.junit.Test;

import java.util.PriorityQueue;

/**
 * Description : [队列测试类]
 * Created on : 2017年11月21日
 * author : [左仁智]
 * version : 1.0
 * Copyright (c) 2017 国美金控-美借
 */

public class QueueBean {

    @Test
    public void poll() {
        PriorityQueue<Long> priorityQueue = new PriorityQueue<Long>();
        priorityQueue.add(1L);
        priorityQueue.add(3L);
        priorityQueue.add(2L);
        priorityQueue.add(5L);
        System.out.println(priorityQueue);
        System.out.println(priorityQueue.poll().toString());
        System.out.println(priorityQueue);
    }
}
