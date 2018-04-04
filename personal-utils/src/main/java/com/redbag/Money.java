package com.redbag;

import java.util.Random;

/**
 * Description : [JAVA版的微信红包算法]
 * @see http://blog.csdn.net/baidu_21578557/article/details/51307013
 * Created on : 2017年09月25日
 * author : [左仁智]
 * version : 1.0
 * Copyright (c) 2017 国美金控-美借
 */

public class Money {

    // remainMoney 剩余的钱
    public static double remainMoney;
    // remainNum剩余的红包数量
    public static int remainNum;

    public static double getRandomMoney(Money myMoney) {
        // remainNum 剩余的红包数量
        // remainMoney 剩余的钱
        // 随机，额度在0.01和(剩余平均值*2)之间
        double min = 0.01;
        double max = myMoney.remainMoney / myMoney.remainNum * 2;
        //最后一个红包
        if (myMoney.remainNum == 1) {
            myMoney.remainNum--;
            return (double) Math.round(myMoney.remainMoney * 100) / 100;
        }
        Random r = new Random();
        double money = r.nextDouble() * max;
        money = money <= min ? 0.01 : money;
        money = Math.floor(money * 100) / 100;
        myMoney.remainNum--;
        myMoney.remainMoney -= money;

        return money;
    }

    public static void main(String[] args) {
        Money m = new Money();
        m.remainNum = 5;
        m.remainMoney = 50;

        int count = m.remainNum;
        for (int i = 1; i <= count; i++) {
            System.out.println("第" + i + "次获取的红包为" + getRandomMoney(m) + "元");
        }

    }
}
