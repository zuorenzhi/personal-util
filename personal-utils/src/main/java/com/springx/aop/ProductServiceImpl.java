package com.springx.aop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Description : [类描述]
 * Created on : 2018年01月24日
 * author : [左仁智]
 * version : 1.0
 * Copyright (c) 2018 国美金控-美借
 */

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    @Override
    public void add(int id) {
        log.info("【ProductServiceImpl-->add】 入参是 [id={}]",id);
    }

    @Override
    public Long getNum(int id) {
        log.info("【ProductServiceImpl-->getNum】 入参是 [id={}]",id);
        return 100L;
    }
}
