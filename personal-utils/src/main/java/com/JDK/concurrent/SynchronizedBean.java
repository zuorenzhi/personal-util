package com.JDK.concurrent;

import lombok.extern.slf4j.Slf4j;

/**
 * Description : [类描述]
 * Created on : 2018年02月06日
 * author : [左仁智]
 * version : 1.0
 * Copyright (c) 2018 国美金控-美借
 */
@Slf4j
public class SynchronizedBean {

    private String name ;

    {
        log.info("【SynchronizedBean-->instance initializer】 ");
    }

    public SynchronizedBean(String name) {
        this.name = name;
        {
            log.info("【SynchronizedBean-->SynchronizedBean】");
        }
    }

    public static void main(String[] args) {
        SynchronizedBean synchronizedBean = new SynchronizedBean("zhagnsan");
    }
}
