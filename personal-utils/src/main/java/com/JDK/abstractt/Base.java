package com.JDK.abstractt;

/**
 * Description : [抽象父类，定义抽象方法，和普通方法]
 * Created on : 2017年10月31日
 * author : [左仁智]
 * version : 1.0
 * Copyright (c) 2017 国美金控-美借
 */

public abstract class Base {

    abstract Integer createInteger();
    abstract Long createLong();

    protected Long sum() {
        return createLong() + createInteger();
    }
}
