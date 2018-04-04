package com.JDK.collections;

import java.util.TreeMap;

/**
 * Description: 描述 <br/>
 * Created on: 2017/6/1 10:40 <br/>
 *
 * @author: <a href="mailto: zuorenzhi@hahaha.com">zuorenzhi</a><br/>
 * @version: 1.0 <br/>
 * Copyright (c) 2017年 北京柯莱特科技有限公司 交付部
 */


public class TestTreeMap {

    public static void main(String[] args) {
        TreeMap<String, Double> treeMap = new TreeMap<String, Double>();
        treeMap.put("ccc" , 89.0);
        treeMap.put("aaa" , 80.0);
        treeMap.put("zzz" , 80.0);
        treeMap.put("bbb" , 89.0);
        System.out.println(treeMap);


    }
}
