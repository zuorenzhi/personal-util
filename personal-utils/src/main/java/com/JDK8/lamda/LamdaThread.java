package com.JDK8.lamda;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * Description : [jdk 1.8 lamda表达式练习]
 * Created on : 2017年08月29日
 * author : [左仁智]
 * version : 1.0
 * Copyright (c) 2017 国美金控-美借
 */

public class LamdaThread {

    /**
     * Discription: [替换匿名表达式Runnable]
     * Created on: 2017-08-29 17:43
     * param : []
     * return : void
     * author : [左仁智]
     */
    @Test
    public void thread() {
        // Java 8之前：
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Before Java8, too much code for too little to do");
            }
        }).start();
        //Java 8方式：
        new Thread(() -> System.out.println("In Java8, Lambda expression rocks !!")).start();
    }

    @Test
    public void foreach(){
        // Java 8之前：
        List<String> features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
        for (String feature : features) {
            System.out.println(feature);
        }
        // Java 8之后：
        features.forEach(n -> System.out.println(n));

        // 使用Java 8的方法引用更方便，方法引用由::双冒号操作符标示，
        // 看起来像C++的作用域解析运算符
        features.forEach(System.out::println);
    }

    @Test
    public void predicate(){

        List<String> languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");
/*        List<String> languages = new ArrayList<String>();
        languages.add("Java");*/

        System.out.println("Languages which starts with J :");
//        filter(languages, (str)->str.startsWith("J"));

        System.out.println("Languages which ends with a ");
//        filter(languages, (str)->str.endsWith("a"));

        System.out.println("Print all languages :");
        filter(languages, (str)->true);

        System.out.println("Print no language : ");
        filter(languages, (str)->false);

        System.out.println("Print language whose length greater than 4:");
//        filter(languages, (str)->str.length() > 4);
    }

    public void filter(List<String> names, Predicate condition) {
        for(String name: names)  {
            if(condition.test(name)) {
                System.out.println(name + " ");
            }
        }
    }
}
