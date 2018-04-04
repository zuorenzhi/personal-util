package com.JDK.ASCII;

import org.junit.Test;

/**
 * Description : [类描述]
 * Created on : 2017年09月04日
 * author : [左仁智]
 * version : 1.0
 * Copyright (c) 2017 国美金控-美借
 */

public class CharOut {

    /**
     * Discription: [ASCII码数字与符号对应关系]
     * Created on: 2017-09-04 11:13
     * param :
     * return :
     * author : [左仁智]
     */
    @Test
    public void test(){
        for (int i = 0; i < 255 ; i++) {
            System.out.printf("数字%s==符号%s%n",i,(char)i);
        }
    }

}
