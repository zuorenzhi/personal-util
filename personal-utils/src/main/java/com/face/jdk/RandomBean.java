package com.face.jdk;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Random;

/**
 * Description : [java.util.Random]
 * Created on : 2018年02月11日
 * author : [左仁智]
 * version : 1.0
 * Copyright (c) 2018 国美金控-美借
 */
@Slf4j
public class RandomBean {

    /**返回一个伪随机数，它是取自此随机数生成器序列的、在 0（包括）和指定值（不包括）之间均匀分布的 int 值*/
    @Test
    public void nextInt() {
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            log.info("【RandomBean-->test】 入参是 [{}]", random.nextInt(10) + 1);
        }
    }

    @Test
    public void nextDouble(){
        Random random = new Random();
        System.out.println(random.nextDouble());
    }
}
