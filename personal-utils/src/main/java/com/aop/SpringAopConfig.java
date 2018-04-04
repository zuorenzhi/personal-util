package com.aop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Description : [类描述]
 * Created on : 2018年01月24日
 * author : [左仁智]
 * version : 1.0
 * Copyright (c) 2018 国美金控-美借
 */

@ComponentScan(basePackages = "com.aop")
@EnableAspectJAutoProxy
public class SpringAopConfig {
}
