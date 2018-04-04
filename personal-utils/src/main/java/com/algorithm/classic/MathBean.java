package com.algorithm.classic;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * Description : [JAVA经典算法面试15题及答案]
 * url: [ http://blog.csdn.net/u013372519/article/details/38175953 ]
 * Created on : 2018年01月29日
 * author : [左仁智]
 * version : 1.0
 * Copyright (c) 2018 国美金控-美借
 */
@Slf4j
public class MathBean {

    /**
     * 【程序10】 题目：输出9*9口诀。
     * 1.程序分析：分行与列考虑，共9行9列，i控制行，j控制列。
     * 不去重99乘法表，矩形
     */
    @Test
    public void nineI() {
        for (int i = 1; i <= 9; i++) {
//            log.info("【Mathbean-->nineI】 第{}行 ",i);
            for (int j = 1; j <= i; j++) {
                System.out.print(i + "*" + j + "=" + i * j + "\t");
            }
            System.out.println();
        }
    }

    /**
     * 去重99乘法表，下三角
     */
    @Test
    public void nineII() {
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(i + "*" + j + "=" + i * j + "\t");
            }
            System.out.println();
        }
    }

    /**
     * Discription: [【程序7】 题目：一球从100米高度自由落下，每次落地后反跳回原高度的一半；
     * 再落下，求它在 第10次落地时，共经过多少米？第10次反弹多高？]
     * Created on: 2018-01-29 17:14
     * param :
     * return :
     * author : [左仁智]
     */
    @Test
    public void oneHundredMeter() {
        double s = 0;
        double t = 100;
        for (int i = 1; i <= 10; i++) {
            s += t;
            t = t / 2;
        }
        System.out.println(s);
        System.out.println(t);
    }

    /**
     * Discription: [【程序8】 题目：有1、2、3、4个数字，能组成多少个互不相同且无重复数字的三位数？都是多少？
     * 程序分析：可填在百位、十位、个位的数字都是1、2、3、4。组成所有的排列后再去 掉不满足条件的排列。]
     * Created on: 2018-01-29 17:16
     * param :
     * return :
     * author : [左仁智]
     */
    @Test
    public void chongfu() {
        int i = 0;
        int j = 0;
        int k = 0;
        int t = 0;
        for (i = 1; i <= 4; i++)
            for (j = 1; j <= 4; j++)
                for (k = 1; k <= 4; k++)
                    if (i != j && j != k && i != k) {
                        t += 1;
                        System.out.println(i * 100 + j * 10 + k);
                    }
        System.out.println(t);
    }

    /** 
     * Discription: [【程序11】 题目：猴子吃桃问题：猴子第一天摘下若干个桃子，当即吃了一半，
     * 还不瘾，又多吃了一个 第二天早上又将剩下的桃子吃掉一半，又多吃了一个。
     * 以后每天早上都吃了前一天剩下 的一半零一个。到第10天早上想再吃时，见只剩下一个桃子了。
     * 求第一天共摘了多少。
     程序分析：采取逆向思维的方法，从后往前推断。]
     total(9) - (total(9)/2 + 1) = 1
     total(9) = 2*(1+1)
     total(9) = 2*(1+total(10))
     * Created on: 2018-01-29 17:22
     * author : [左仁智]
     */
    public int total(int day) {
        if (day == 10) {
            return 1;
        } else {
            return (total(day + 1) + 1) * 2;
        }
    }

    @Test
    public void testTotal(){
        //测试猴子吃桃
        System.out.println(total(1));
        System.out.println(total(7));
    }



}

