package com.junit;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Description : [类描述]
 * Created on : 2017年11月21日
 * author : [左仁智]
 * version : 1.0
 * Copyright (c) 2017 国美金控-美借
 */

public class EncoderBean {

    @Test
    public void test() throws UnsupportedEncodingException {
        String str ="sssssssssss";
        String encode = URLEncoder.encode(str, "utf-8");
        System.out.println(encode);

    }
}
