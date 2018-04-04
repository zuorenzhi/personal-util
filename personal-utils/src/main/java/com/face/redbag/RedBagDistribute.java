package com.face.redbag;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
* Description : [红包分配]
* Created on : 2018-02-11 11:48
 * url:https://www.cnblogs.com/canglong/p/canglong001.html?utm_source=itdadao&utm_medium=referral
* author : [左仁智]
* version : 1.0
* Copyright (c) 2018 国美金控-美借
*/
@Slf4j
public class RedBagDistribute {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            List<BigDecimal> moneys = distributeMoney(BigDecimal.valueOf(10), 6);
            if (moneys != null) {
                BigDecimal b = BigDecimal.ZERO;
                for (BigDecimal bigDecimal : moneys) {
                    System.out.print(bigDecimal + "元    ");
                    b = b.add(bigDecimal);
                }
                System.out.print("   总额：" + b+"元 ");
                System.out.println();
            }
        }
    }

    /**
     * 计算每人获得红包金额;最小每人0.01元
     * @param totalYuan 红包总额
     * @param number 人数
     * @return
     */
    public static List<BigDecimal> distributeMoney(BigDecimal totalYuan, int number) {
        if (totalYuan.doubleValue() < number * 0.01) {
            return null;
        }
        Random random = new Random();
        // 金钱，按分计算 10块等于 1000分
        int totalFen = totalYuan.multiply(BigDecimal.valueOf(100)).intValue();
        // 随机数总额
        double count = 0;
        // 每人获得随机点数
        double[] arrRandom = new double[number];
        // 每人获得钱数
        List<BigDecimal> arrMoney = new ArrayList<BigDecimal>(number);
        // 循环人数 随机点
        for (int i = 0; i < arrRandom.length; i++) {
            int r = random.nextInt(number * 99) + 1;
            count += r;
            arrRandom[i] = r;
        }
        // 计算每人拆红包获得金额
        int c = 0;
        for (int i = 0; i < arrRandom.length; i++) {
            //todo 精髓之处:划权重，比 0~99之间随机落数，划区间分更合理，更随机。
            // 每人获得随机数相加 计算每人占百分比
            Double x = arrRandom[i] / count;
            // 每人通过百分比获得金额
            int item = (int) Math.floor(x * totalFen);
            log.info("【RedBagDistribute-->distributeMoney】 入参是 [x={},floor={},m={}]",x,Math.floor(x * totalFen),item);
            // 如果获得 0 金额，则设置最小值 1分钱
            if (item == 0) {
                item = 1;
            }
            // 计算获得总额
            c += item;
            // 如果不是最后一个人则正常计算
            if (i < arrRandom.length - 1) {
                arrMoney.add(new BigDecimal(item).divide(new BigDecimal(100)));
            } else {
                // 如果是最后一个人，则把剩余的钱数给最后一个人
                arrMoney.add(new BigDecimal(totalFen - c + item).divide(new BigDecimal(100)));
            }
        }
        // 随机打乱每人获得金额
        Collections.shuffle(arrMoney);
        return arrMoney;
    }
}
