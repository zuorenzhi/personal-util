package com.JDK.generic;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Description : [通配符，上界，下界]
 * url:https://www.zhihu.com/question/20400700
 * <? extends T>和<? super T>是Java泛型中的“通配符（Wildcards）和 边界（Bounds）”的概念。
 * Created on : 2018年03月06日
 * author : [左仁智]
 * version : 1.0
 * Copyright (c) 2018 国美金控-美借
 */
public class ExtendSuper {

    @Test
    public void extendsT(){
        //上界：上界为 Fruit
        List<? extends Fruit> listFruit = new ArrayList<Apple>();
        //不能存入任何元素
//        listFruit.add(Fruit);//Error
//        listFruit.add(Apple);//Error

        //读取出来的东西只能存放在Fruit或它的基类里。
        Fruit fruit = listFruit.get(0);
        Object obj = listFruit.get(1);
//        Apple apple = listFruit.get(2);//Error

    }

    @Test
    public void superT(){
        List<? super Fruit> listFruit = new ArrayList<Fruit>();

        //存入元素正常
        listFruit.add(new Fruit());
        listFruit.add(new Apple());
        listFruit.add(new RedApple());
        listFruit.add(new Banana());

        //读取出来的东西只能存放在Object类里。
        Object object = listFruit.get(0);
//        Fruit fruit = listFruit.get(1);//Error
//        Apple apple = listFruit.get(2);//Error
    }



}

class Food { }

class Fruit extends Food { }

class Apple extends Fruit { }
class Banana extends Fruit { }

class RedApple extends Apple {}
class GreenApple extends Apple {}


