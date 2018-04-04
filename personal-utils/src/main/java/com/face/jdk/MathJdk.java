package com.face.jdk;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * Description : [java.lang.Math部分方法整理]
 * Created on : 2018年02月11日
 * author : [左仁智]
 * version : 1.0
 * Copyright (c) 2018 国美金控-美借
 */
@Slf4j
public class MathJdk {

    /**
     * 绝对值
     */
    @Test
    public void abs() {
        log.info("【MathBeanJdk-->绝对值】 [{}]", Math.abs(100));
        log.info("【MathBeanJdk-->绝对值】 [{}]", Math.abs(-1000.30));
    }

    /**
     * 立方根
     */
    @Test
    public void cbrt() {
        log.info("【MathBeanJdk-->立方根】 [{}]", Math.cbrt(1000));
        log.info("【MathBeanJdk-->立方根】 [{}]", Math.cbrt(-27));
    }

    /**
     * 返回最大的（最接近正无穷大）double 值，该值小于等于参数，并等于某个整数。
     */
    @Test
    public void floor() {
        log.info("【MathBeanJdk-->floor】 [{}]", Math.floor(1000.25));
        log.info("【MathBeanJdk-->floor】 [{}]", Math.floor(-27.03));
    }

    /**
     * 返回两个 double 值中较大的一个
     */
    @Test
    public void max() {
        log.info("【MathBeanJdk-->max】 [{}]", Math.max(12, 13));
        log.info("【MathBeanJdk-->max】 [{}]", Math.max(-12, -13));
    }

    /**
     * 返回第一个参数的第二个参数次幂的值
     */
    @Test
    public void pow() {
        log.info("【MathBeanJdk-->pow】 入参是 [{}]", Math.pow(2, 3));//8.0
    }

    /**
     * 返回带正号的 double 值，该值大于等于 0.0 且小于 1.0
     */
    @Test
    public void random() {
        int i = 0;
        while (i < 20) {
            log.info("【MathBeanJdk-->random】 入参是 [{}]", Math.random());
            i++;
        }
    }

    @Test
    public void sqrt() {
        log.info("【MathJdk-->sqrt】 入参是 [{}]", Math.sqrt(100));
    }
}
