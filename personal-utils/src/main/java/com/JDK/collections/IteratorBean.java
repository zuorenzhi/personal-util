package com.JDK.collections;

import org.junit.Test;

import java.util.*;

/**
 * Description : [类描述]
 * Created on : 2017年12月05日
 * author : [左仁智]
 * version : 1.0
 * Copyright (c) 2017 国美金控-美借
 */

public class IteratorBean {

    @Test
    public void iteratorList(){
        List<Integer> integers = new ArrayList<>();
        Collections.addAll(integers,1,2,3,4,5,6);
        System.out.println(integers);

        Iterator<Integer> iterator = integers.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    @Test
    public void iteratorMap(){
        HashMap<String, Integer> map = new HashMap<>();
        map.put("sex",1);
        map.put("age",12);
        map.put("height",180);
        System.out.println(map);

        Iterator<Map.Entry<String, Integer>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> entry = iterator.next();
            System.out.println(entry.getKey()+"="+entry.getValue());
        }
    }

    /**
     * http://blog.csdn.net/superxlcr/article/details/51534428
     * */
    @Test
    public void iteratorListError() {

        List<Integer> list = new ArrayList<>();
        Collections.addAll(list, 1, 2, 3, 4, 5, 6);

        for (int i = 0; i < list.size(); i++) {
            // index and number
            System.out.print(i + "的值是" + list.get(i));
            if (list.get(i) % 2 == 0) {
                list.remove(list.get(i));
                System.out.print(" delete");
                i--; // 索引改变!
                System.out.print("  "+list.size());
            }
            System.out.println();
        }
        System.out.println(list);

        /*for (Iterator<Integer> iterator = integers.iterator(); iterator.hasNext(); ) {
            Integer next = iterator.next();
            if (next % 2 == 0) {
                System.out.println(next);
                integers.remove(next);
            }
        }*/
    }
}
