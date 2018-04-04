package com.face.jdk;

/**
 * Description: 描述 <br/>
 * Created on: 2017/6/27 17:00 <br/>
 * @author: <a href="mailto: zuorenzhi@gomeholdings.com">zuorenzhi</a><br/>
 * @version: 1.0 <br/>
 * Copyright (c) 2017年 融通科技 消费服务
 */

public class Extend {
    public static void testInstanceof(Object x) {
        System.out.println("x instanceof Parent:  " + (x instanceof Parent));
        System.out.println("x instanceof Child:  " + (x instanceof Child));
        System.out.println("x getClass Parent:  " + (x.getClass() == Parent.class));
        System.out.println("x getClass Child:  " + (x.getClass() == Child.class));
    }

    public static void main(String[] args) {
        testInstanceof(new Parent());
        System.out.println("---------------------------");
        testInstanceof(new Child());
    }

    static class Parent {

    }

    static class Child extends Parent {

    }
}

