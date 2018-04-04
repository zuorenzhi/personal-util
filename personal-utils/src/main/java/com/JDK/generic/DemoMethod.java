package com.JDK.generic;

/**
 * Description : [泛型方法]
 * Created on : 2017年10月30日
 * author : [左仁智]
 * version : 1.0
 * Copyright (c) 2017 国美金控-美借
 */

public class DemoMethod {

    <T> T f (T t) {
        System.out.println(t.getClass().getName());
        return t;
    }

    public static void main(String[] args) {
        DemoMethod demo = new DemoMethod();
        demo.f(12);
        demo.f("123");
        demo.f(12D);
    }
}
