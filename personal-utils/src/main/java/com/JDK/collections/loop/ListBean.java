package com.JDK.collections.loop;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import javax.xml.bind.SchemaOutputResolver;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Description : [list循环删除元素]
 * 循环删除会有问题
 * Created on : 2018年02月01日
 * author : [左仁智]
 * version : 1.0
 * Copyright (c) 2018 国美金控-美借
 */
@Slf4j
public class ListBean {


    /**
     * Discription: [倒着删除list，剩下的index不会发生偏移]
     * Created on: 2018-02-01 18:01
     * author : [左仁智]
     */
    @Test
    public void testI() {
        ArrayList<Integer> list = new ArrayList<>();
        Collections.addAll(list, 2, 4, 6, 8, 10);

        System.out.println(list);
        for (int i = list.size() - 1; i >= 0; i--) {
            list.remove(i);
        }
        System.out.println(list);
    }

    /**
     * Discription: [正序删除元素，每次删除index以及list.size()都会发生变化]
     * Created on: 2018-02-01 18:02
     * author : [左仁智]
     */
    @Test
    public void testII() {
        ArrayList<Integer> list = new ArrayList<>();
        Collections.addAll(list, 2, 4, 6, 8, 10);
        System.out.println(list);
        for (int i = 0; i < list.size(); i++) {
            log.warn("【ListBean-->main】 循环 [i={},list.size()={}]", i, list.size());
            Integer remove = list.remove(i);
            log.info("【ListBean-->main】 入参是 [index={},element={}]", i, remove);
        }
        System.out.println(list);
        System.out.println(list.size());
    }

    /**
     * 处于末位时，删除出错
     */
    @Test
    public void testIII() {
        List<String> a = new ArrayList<String>();
        a.add("1");
        a.add("2");
        a.add("3");
        a.add("4");
        for (String temp : a) {
            if ("3".equals(temp)) {
                a.remove(temp);
            }
        }

        System.out.println(a);
        System.out.println(a.size());
    }
    /**
     * Discription: [iterator方式遍历删除元素]
     * Created on: 2018-02-01 18:43
     * author : [左仁智]
     */
    @Test
    public void iterator() {
        ArrayList<Integer> list = new ArrayList<>();
        Collections.addAll(list, 2, 4, 6, 8, 10);

        System.out.println(list);
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            //如果没有取出来，直接调用remove方法....那么会报错
            //因为其会调用ArrayList的remove方法
            iterator.next();
            iterator.remove();
        }
        System.out.println(list);
    }

    @Test
    public void initalCapacity(){
        ArrayList<String> list = new ArrayList<>(2);
        list.add("A");
        list.add("b");
        list.add("C");
        System.out.println(list.size());
    }
}