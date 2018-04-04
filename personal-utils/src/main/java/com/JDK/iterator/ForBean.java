package com.JDK.iterator;

import org.junit.Test;

/**
 * Description : [类描述]
 * Created on : 2017年08月22日
 * author : [左仁智]
 * version : 1.0
 * Copyright (c) 2017 国美金控-美借
 */

public class ForBean {

    @Test
    public void stop(){
        for (int i = 0; i <10 ; i++) {
            if(i%2 == 0)
                continue;
            if(i == 5)
                break;
            System.out.println(i);
        }
    }

    @Test
    public void testReturn(){
        int num = getNum();
        System.out.println(num);
    }

    private int getNum() {
        for (int i = 0; i < 5; i++) {
            if(i == 4)
                return 8;
            System.out.println(i);
        }
        return 0;
    }

    @Test
    public void elseIf(){

        int i = 5;
        int j = 7;
        if (i > 4) {
            System.out.println(i);
        } else if (j > 0) {
            System.out.println(j);
        }
        testI();
        System.out.println("999");
    }

    private void testI(){
        System.out.println("22");
        return;
    }

}
