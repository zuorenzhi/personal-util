package com.aop;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Description : [类描述]
 * Created on : 2018年01月24日
 * author : [左仁智]
 * version : 1.0
 * Copyright (c) 2018 国美金控-美借
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringAopConfig.class)
@Slf4j
public class SpringAopTest {

    @Resource
    private ProductService productService;

    @Test
    public void add() {
        productService.add(1);
    }

    @Test
    public void getNum() {
        Long num = productService.getNum(10);
        System.out.println("今天是个好日子，哈哈");
        log.info("【SpringAopTest-->getNum】 出参是 [num={}]",num);
    }
}
