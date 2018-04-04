package com.aop;

/**
 * Description : [类描述]
 * Created on : 2018年01月24日
 * author : [左仁智]
 * version : 1.0
 * Copyright (c) 2018 国美金控-美借
 */

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * Description : [统一日志aop]
 * url: http://moon-walker.iteye.com/blog/2381532
 * Created on : 2018-01-25 10:32
 * author : [左仁智]
 * version : 1.0
 * Copyright (c) 2018 国美金控-美借
 */
@Component  //标记为一个
@Aspect //标记为切面
@Slf4j
public class LogAop {

    //定义切点 方便复用
    @Pointcut("execution(* com.aop.*.*(..))")
    public void logAll() {
    }

    //定义切点 方便复用
    @Pointcut("execution(* com.aop.ProductServiceImpl.add(..))")
    public void log() {
    }

    @Pointcut("execution(* com.aop.ProductServiceImpl.getNum(..))")
    public void getNum() {
    }

    //前置通知
    @Before("log()")
    public void beforeLog(JoinPoint jp) {
        Signature signature = jp.getSignature();
        log.info("【LogAop-->beforeLog】 [getDeclaringTypeName={},getName={},getDeclaringType={},getModifiers={}]", signature.getDeclaringTypeName(), signature.getName(), signature.getDeclaringType(), signature.getModifiers());
        log.info(signature.getDeclaringTypeName() + "类的" + signature.getName() + "方法Before日志");
    }

    //环绕通知
    @Around("log()")
    public void aroundLog(ProceedingJoinPoint jp) {
        try {
            log.info(jp.getSignature().getDeclaringTypeName() + "类的" + jp.getSignature().getName() + "方法Around通知开始");
            jp.proceed();
            log.info(jp.getSignature().getDeclaringTypeName() + "方法Around通知结束");
        } catch (Throwable throwable) {
            Object[] args = jp.getArgs();

            System.out.println("参数列表值为：");
            for (Object one : args) {
                log.error(one.toString());
            }
            log.error(jp.getSignature().getDeclaringTypeName() + "类的" + jp.getSignature().getName() + "调用异常", throwable);

        }

    }

    //后置通知
    @After("log()")
    public void after(JoinPoint jp) {
        log.info(jp.getSignature().getDeclaringTypeName() + "类的" + jp.getSignature().getName() + "方法after日志");
    }

    //返回通知
    @AfterReturning(pointcut = "log()")
    public void afterReturnLog(JoinPoint jp) {
        log.info(jp.getSignature().getDeclaringTypeName() + "类的" + jp.getSignature().getName() + "方法afterReturnLog日志");
    }

    //返回通知
    @AfterReturning(pointcut = "getNum()", returning = "result")
    public void afterReturn(JoinPoint jp, Long result) {
        log.info("【LogAop-->afterReturn】 入参是 [jp, result={}]", result);
    }

    //异常通知
    @AfterThrowing("log()")
    public void afterError(JoinPoint jp) {
        log.info(jp.getSignature().getDeclaringTypeName() + "类的" + jp.getSignature().getName() + "方法AfterThrowing日志");
    }
}

