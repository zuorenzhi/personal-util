package com.algorithm.jdk;

/**
 * Description : [类描述]
 * Created on : 2017年08月11日
 * author : [左仁智]
 * version : 1.0
 * Copyright (c) 2017 国美金控-美借
 */

public class HashMapTable {

    /**
     * The maximum capacity, used if a higher value is implicitly specified
     * by either of the constructors with arguments.
     * MUST be a power of two <= 1<<30.
     */
    static final int MAXIMUM_CAPACITY = 1 << 30;

    /**
     * The load factor used when none specified in constructor.
     */
    static final float DEFAULT_LOAD_FACTOR = 0.75f;

    /**
     * Returns a power of two size for the given target capacity.
     * [返回 2的n次方 > 给定参数 的最小值]
     */
    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    public static void main(String[] args) {
        System.out.println(tableSizeFor(10));//16  ;  (2<<3) < 10 < (2<<4)
        System.out.println(tableSizeFor(17));//32  ;  (2<<4) < 17 < (2<<5)
        System.out.println(1<<4);//16
        System.out.println(1<<4 == 2 <<3);// 16
    }
}
