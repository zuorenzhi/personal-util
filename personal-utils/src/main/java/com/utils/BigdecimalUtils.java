package com.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * 大数值计算工具类
 * Created on : 2018年04月16日
 *
 * @author : [左仁智]
 */
@Slf4j
public class BigdecimalUtils {

    public static void main(String[] args) {


        System.out.println(1f/3);
        System.out.println(1001D/16);
        System.out.println(1/3D);
        System.out.println(2/7D);
        System.out.println(2f/7);
        System.out.println(retainTwoPoint(0.3333333333333333D));
        System.out.println(retainTwoPoint(0.2857142857142857));


    }

    /**
     * Discription: [将字符串金额转换为0.00格式字符串]
     * Created on: 2017-12-03 14:13
     * param :
     * return :
     * author : [左仁智]
     */
    public static String parseAmount(String bigdecimalStr){
        String pattern = "0.00";
        if (StringUtils.isBlank(bigdecimalStr)) {
            return "0.00";
        }
        try {
            return new DecimalFormat(pattern).format(Double.parseDouble(bigdecimalStr));
        } catch (NumberFormatException e) {
            log.error("【BigdecimalUtils-->parseAmount】 调用异常 入参={}",bigdecimalStr);
        }
        return "0.00";
    }

    public static String retainTwoPoint(double doubleValue){
        String pattern = "0.00";
        try {
            return new DecimalFormat(pattern).format(doubleValue);
        } catch (NumberFormatException e) {
            log.error("【BigdecimalUtils-->parseAmount】 调用异常 入参={}",doubleValue);
        }
        return "0.00";
    }




}
