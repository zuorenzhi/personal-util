package com.JDK.abstractt;

import org.junit.Test;

/**
 * Description : [子类，继承父类，实现父类抽象方法，自有方法并
 * 调用父类非抽象方法，最终会调用到子类具体实现类
 * eg: 暂时理解为：super.m()，相当于少写一些代码，作用等效于copy，代码投影]
 * Created on : 2017年10月31日
 * author : [左仁智]
 * version : 1.0
 * Copyright (c) 2017 国美金控-美借
 */

public class Sub extends  Base {

    @Override
    Integer createInteger() {
        return 12;
    }

    @Override
    Long createLong() {
        return 12L;
    }

    @Test
    public void subSum() {
        Long sum = super.sum();
        System.out.println(sum);
    }


}
