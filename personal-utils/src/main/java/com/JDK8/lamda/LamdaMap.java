package com.JDK8.lamda;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Description : [类描述]
 * Created on : 2017年12月27日
 * author : [左仁智]
 * version : 1.0
 * Copyright (c) 2017 国美金控-美借
 */

public class LamdaMap {

    @Test
    public void test(){
        System.out.println(soutListMap());
        System.out.println(soutListMapI());
    }

    public List<Map<String, String>> soutListMap() {

        ArrayList<Integer> integers = new ArrayList<>();
        Collections.addAll(integers,1,2,3,4,5,6);

        return integers.stream().map(item -> {
            Map<String, String> map = new HashMap<>();
            map.put("num", item.toString());
            return map;
        }).collect(Collectors.toList());
    }
    public List<Map<String, String>> soutListMapI() {

        ArrayList<Integer> integers = new ArrayList<>();
        Collections.addAll(integers,1,2,3,4,5,6);

        List<Map<String, String>> num = integers.stream().map(item -> {
            Map<String, String> map = new HashMap<>();
            map.put("num", item.toString());
            return map;
        }).collect(Collectors.toList());
        return num;
    }
}
