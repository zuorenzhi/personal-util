package com.JDK.extend;

import lombok.Getter;
import lombok.Setter;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * Description : [类描述]
 * Created on : 2017年11月13日
 * author : [左仁智]
 * version : 1.0
 * Copyright (c) 2017 国美金控-美借
 */

public class ExtendsJava {

    /**
     * 格式化操作-保留两位小数
     * @param doubleVal
     * @return
     */
    private Double formatDouble(Double doubleVal){
        BigDecimal bigDecimal = new BigDecimal(doubleVal);
        bigDecimal.setScale(2,BigDecimal.ROUND_HALF_UP);
        return bigDecimal.doubleValue();
    }

    public static void main(String[] args) {


        BigDecimal bigDecimal = new BigDecimal(555.5999999999999999999D);
        System.out.println(bigDecimal.doubleValue());
//        bigDecimal = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
        System.out.println(bigDecimal.toString());
        System.out.println(String.valueOf(bigDecimal));
        System.out.println(Double.valueOf("555.6000000000000227373675443232059478759765625"));
    }

    @Test
    public void test(){
        SubClass subClass = new SubClass();
        System.out.println(subClass.name);
        subClass.setName("wangwu");
        subClass.test();
    }
}




@Setter
@Getter
class  Parent{
    public String name = "父类zhangsan";

    public void test() {
        System.out.println("Parent=="+name);
    }
}

class  SubClass extends Parent{
    public String name = "子类lisi";

    @Override
    public void test() {
        System.out.println("SubClass=调用父类="+super.name);
        System.out.println("SubClass==调用子类"+this.name);
    }
}
