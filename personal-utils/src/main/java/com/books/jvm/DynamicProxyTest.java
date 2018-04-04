package com.books.jvm;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Description: 代理类测试
 * @see 《深入java虚拟机page283》
 * Created on: 2017-07-09 18:33
 * @author: zuorenzhi
 * Copyright (c) 2017年 国美融通科技
 */
@Slf4j
public class DynamicProxyTest {

    interface IHello{
        void sayHello();
    }

    static  class  Hello implements  IHello{
        @Override
        public void sayHello() {
            log.info("【Hello-->sayHello】 入参是 []");
        }
    }

    static  class  DynamicProxy implements InvocationHandler{
        Object originalObj;
        Object bind(Object originalObj){
            log.info("【DynamicProxy-->bind】 入参是 [originalObj]");
            this.originalObj = originalObj;
            //this:传入到代理类的参数是当前代理类对象本身的引用
            return Proxy.newProxyInstance(originalObj.getClass().getClassLoader(), originalObj.getClass().getInterfaces(), this);
        }
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            log.info("【DynamicProxy-->invoke】 入参是 [proxy, method, args]");
            return method.invoke(originalObj,args);
        }
    }

    public static void main(String[] args) {
        //构建代理类
        DynamicProxy dynamicProxy = new DynamicProxy();
        //要传入代理类的实现类
        Hello hello1 = new Hello();
        //调用代理类方法，给代理类的内部属性参数赋值
        Object bind = dynamicProxy.bind(hello1);
        //如果此处用 Hello则不会通过，代理类bind方法中会调用到传入被代理类的接口
        if (bind instanceof IHello) {
            IHello hello = (IHello) bind;
            hello.sayHello();
        }
//        IHello hello = (IHello)new DynamicProxy().bind(new Hello());
//        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
    }

}
