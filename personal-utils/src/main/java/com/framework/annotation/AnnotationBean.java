package com.framework.annotation;

import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

/**
 * Description : [自定义注解类]
 * url:http://blog.csdn.net/rock_ray/article/details/22334467
 * Created on : 2017年12月07日
 * author : [左仁智]
 * version : 1.0
 * Copyright (c) 2017 国美金控-美借
 */

@Retention(RetentionPolicy.RUNTIME) //设置元注解的值为RUNTIME
@Target(ElementType.TYPE)
        //表示@MyAnnotation注解可以修饰类、接口、枚举类。
@interface BeanAnnotation //自定义注解
{

}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface MethodAnnotation //自定义注解
{
    String color();
}

@BeanAnnotation
@Slf4j
public class AnnotationBean {

    public static void main(String[] args) {
        //类
        if (AnnotationBean.class.isAnnotationPresent(BeanAnnotation.class)) {
            BeanAnnotation myAnnotation = AnnotationBean.class.getAnnotation(BeanAnnotation.class);
            if (null != myAnnotation) {
                System.out.println("执行业务逻辑-class");
            }
            System.out.println(myAnnotation);
        }
        //方法
        Class<AnnotationBean> beanClass = AnnotationBean.class;
        try {
            Method testA = beanClass.getMethod("testA");//无回参
            MethodAnnotation methodAnnotation = testA.getAnnotation(MethodAnnotation.class);
            if (null != methodAnnotation) {
                System.out.println("执行业务逻辑-method");
                log.info("【AnnotationBean-->main】 入参是 [color={}]",methodAnnotation.color());
            }
            testA = beanClass.getMethod("testB",String.class);//无回参
            methodAnnotation = testA.getAnnotation(MethodAnnotation.class);
            if (null != methodAnnotation) {
                System.out.println("执行业务逻辑-method");
                log.info("【AnnotationBean-->main】 入参是 [color={}]",methodAnnotation.color());
            }
        } catch (NoSuchMethodException e) {
            log.error("【AnnotationBean-->main】 调用异常 [args]",e);
        }
    }


    @MethodAnnotation(color = "red")
    public void testA() {

    }

    @MethodAnnotation(color = "blue")
    public void testB(String name) {
        System.out.println(name);
    }

}
