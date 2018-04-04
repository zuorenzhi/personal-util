package com.framework;

import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.RecoveryCallback;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

import java.util.Collections;
import java.util.Random;

/**
 * Description : [spring-retry]
 * url:https://www.cnblogs.com/zgghb/p/7828069.html
 * Created on : 2017年12月07日
 * author : [左仁智]
 * version : 1.0
 * Copyright (c) 2017 国美金控-美借
 */
@Slf4j
public class SpringRetry {

    public static void main(String[] args) {
        testSpringRetry("张三丰");
    }

    public static void testSpringRetry(final String userId) {

        RetryTemplate retryTemplate = getRetryTemplate();

        // 通过RetryCallback 重试回调实例包装正常逻辑逻辑，第一次执行和重试执行执行的都是这段逻辑
        final RetryCallback<Object, Exception> retryCallback = getRetryCallback(userId);

        // 通过RecoveryCallback 重试流程正常结束或者达到重试上限后的退出恢复操作实例
        final RecoveryCallback<Object> recoveryCallback = getRecoveryCallback(userId);

        try {
            retryTemplate.execute(retryCallback, recoveryCallback);// 由retryTemplate 执行execute方法开始逻辑执行
        } catch (Exception e) {
            log.error("【SpringRetry-->testSpringRetry】 调用异常-发券错误异常 [userId={}]",userId,e);
        }
    }

    private static RecoveryCallback<Object> getRecoveryCallback(String userId) {
        return new RecoveryCallback<Object>() {
                public Object recover(RetryContext context) throws Exception {
                    log.info("正在重试发券::::::::::::"+userId);
                    return null;
                }
            };
    }

    private static RetryCallback<Object, Exception> getRetryCallback(String userId) {
        return new RetryCallback<Object, Exception>() {
                //RetryContext 重试操作上下文约定，统一spring-try包装
                public Object doWithRetry(RetryContext context) throws Exception {
                    if (!getRandom(userId)) {
                        throw new RuntimeException(userId+" 本次调用失败");//这个点特别注意，重试的根源通过Exception返回
                    }
                    return true;
                }
            };
    }

    private static RetryTemplate getRetryTemplate() {
        // 构建重试模板实例
        RetryTemplate retryTemplate = new RetryTemplate();
        // 设置重试策略，主要设置重试次数
        SimpleRetryPolicy policy = new SimpleRetryPolicy(10, Collections.<Class<? extends Throwable>, Boolean>singletonMap(Exception.class, true));
        // 设置重试回退操作策略，主要设置重试间隔时间
        FixedBackOffPolicy fixedBackOffPolicy = new FixedBackOffPolicy();
        fixedBackOffPolicy.setBackOffPeriod(100);
        retryTemplate.setRetryPolicy(policy);
        retryTemplate.setBackOffPolicy(fixedBackOffPolicy);
        return retryTemplate;
    }


    private static Boolean getRandom(String userId) {
        Random random = new Random();
        int a = random.nextInt(10);
        log.info("【SpringRetry-->getRandom】 入参是 [userId={}所获取的随机数,a={}]",userId,a);
        if (a == 8) {
            return true;
        } else {
            return false;
        }
    }
}