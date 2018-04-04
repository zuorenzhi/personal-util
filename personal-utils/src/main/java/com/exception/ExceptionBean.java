package com.exception;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * Description : [Exception测试]
 * Created on : 2018年01月31日
 * author : [左仁智]
 * version : 1.0
 * Copyright (c) 2018 国美金控-美借
 */
@Slf4j
public class ExceptionBean {


    @Test
    public void run(){
        log.info("【ExceptionBean-->run】 入参是 []");
        throw new RunException("run");
    }

    @Test
    public void ex(){
        log.info("【ExceptionBean-->ex】 入参是 []");
        try {
            throw new P2pException("ex");
        } catch (P2pException e) {
            log.error("【ExceptionBean-->ex】 调用异常 []",e);
        }
    }

    @Test
    public void exthrows() throws P2pException {
        log.info("【ExceptionBean-->exthrows】 入参是 []");
        throw new P2pException("exthrows");
    }
}
