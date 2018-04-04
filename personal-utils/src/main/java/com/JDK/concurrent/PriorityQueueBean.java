package com.JDK.concurrent;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/***
 * PriorityQueue有序队列
 */
public class PriorityQueueBean {

    public static void main(String args[]) {
        int initialCapacity = 11;//初始化size
        Queue<Bean> priorityQueue = new PriorityQueue<Bean>(initialCapacity, new Comparator<Bean>() {
            @Override
            public int compare(Bean o1, Bean o2) {
                return Integer.valueOf(o1.getPopulation()).compareTo(Integer.valueOf(o2.getPopulation()));
            }
        });
        Bean t1 = new Bean("t1", 1);
        Bean t3 = new Bean("t3", 3);
        Bean t2 = new Bean("t2", 2);
        Bean t4 = new Bean("t4", 0);
        Bean t5 = new Bean("t5", 9);

        priorityQueue.add(t1);
        priorityQueue.add(t3);
        priorityQueue.add(t2);
        priorityQueue.add(t4);
        priorityQueue.add(t5);
        System.out.println(priorityQueue.toString());
        System.out.println(priorityQueue.poll().toString());
    }

    @Getter
    @Setter
    @AllArgsConstructor
    static class Bean {
        private String name;
        private int population;

        @Override
        public String toString() {
            return JSON.toJSONString(this);
        }
    }

}



