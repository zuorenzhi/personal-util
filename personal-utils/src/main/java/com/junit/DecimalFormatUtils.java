package com.junit;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.text.DecimalFormat;

/**
 * Description : [java格式化数字、货币、金钱]
 * url:https://www.cnblogs.com/hq233/p/6539107.html
 * url:https://www.cnblogs.com/xujingyang/p/6527780.html
 * Created on : 2017年12月03日
 * author : [左仁智]
 * version : 1.0
 * Copyright (c) 2017 国美金控-美借
 */
@Slf4j
public class DecimalFormatUtils {

    public static void main(String[] args) {
        /**I*/
        double pi = 3.1415927;//圆周率
        //取一位整数
        System.out.println(new DecimalFormat("0").format(pi));//3
        //取一位整数和两位小数
        System.out.println(new DecimalFormat("0.00").format(pi));//3.14
        //取两位整数和三位小数，整数不足部分以0填补。
        System.out.println(new DecimalFormat("00.000").format(pi));//03.142
        //取所有整数部分
        System.out.println(new DecimalFormat("#").format(pi));//3
        //以百分比方式计数，并取两位小数
        System.out.println(new DecimalFormat("#.##%").format(pi));//314.16%
        System.out.println(new DecimalFormat("#.##").format(pi));//3.14

        log.info("【DecimalFormatUtils-->main】 II");
        /**II*/
        long c = 299792458;//光速
        //显示为科学计数法，并取五位小数
        System.out.println(new DecimalFormat("#.#####E0").format(c));//2.99792E8
        //显示为两位整数的科学计数法，并取四位小数
        System.out.println(new DecimalFormat("00.####E0").format(c));//29.9792E7
        //每三位以逗号进行分隔。
        System.out.println(new DecimalFormat(",###").format(c));//299,792,458
        //将格式嵌入文本
        System.out.println(new DecimalFormat("光速大小为每秒,###米").format(c)); //光速大小为每秒299,792,458米

        log.info("【DecimalFormatUtils-->main】 III");
        /**II*/
        //第一种:比如网上交易系统，数字保留4位小数：
        double d = 554545.4545454;
        System.out.println(new DecimalFormat("##.####").format(d));//554545.4545

        //第二种:比如网上交易系统，金钱数字保留4位小数且以“￥”开头：
        System.out.println(new DecimalFormat("Y##.####").format(d));// $554545.4545
        System.out.println(new DecimalFormat("$##.####").format(d));//Y554545.4545
        //第三种:比如网上交易系统，金钱数字保留4位小数且三位三位的隔开：
        System.out.println(new DecimalFormat("#,###.####").format(d));//554,545.4545
    }

    /**
     * Discription: [强制2位小数]
     * Created on: 2017-12-04 10:35
     * param :
     * return :
     * author : [左仁智]
     */
    @Test
    public void test() {
        System.out.println(new DecimalFormat("0.00").format(23D));
        System.out.println(new DecimalFormat("0.00").format(23.1D));
        System.out.println(new DecimalFormat("0.00").format(23.0F));
        System.out.println(new DecimalFormat("0.00").format(23.3f));
    }

}
