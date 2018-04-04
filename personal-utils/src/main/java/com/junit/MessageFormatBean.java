package com.junit;

import org.junit.Test;

import java.text.MessageFormat;

/**
 * Description : [java.text.MessageFormat]
 * Created on : 2018年02月02日
 * author : [左仁智]
 * version : 1.0
 * Copyright (c) 2018 国美金控-美借
 */

public class MessageFormatBean {

    public static void main(String[] args) {
        String pattern = "{0}-{1}-{2}";
        MessageFormat messageFormat = new MessageFormat(pattern);
        String format = messageFormat.format(new Object[]{"zhangsan", "lisi", "wangwu"});
        System.out.println(format);
    }

    @Test
    public void test(){
        String pattern = "{0}-{1}-{2}";
        String format = MessageFormat.format(pattern,new Object[]{"zhangsan", "lisi", "wangwu"});
        System.out.println(format);
    }
}