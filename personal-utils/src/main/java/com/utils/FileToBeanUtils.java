package com.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Description: 基于file文件生成javaBean
 * Created on: 2017-08-08 17:24
 * @author: zuorenzhi
 * Copyright (c) 2017年 国美融通科技
 */

public class FileToBeanUtils {

    @Test
    public void writeToFile(){

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name","zhangsan");
        jsonObject.put("age",20);

        try {
            FileUtils.copyInputStreamToFile(new ByteArrayInputStream(jsonObject.toString().getBytes("utf-8")),new File("d:/writeToFile.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void readToBean(){
        File file = new File("d:/writeToFile.txt");
        StringBuffer sb = new StringBuffer();
        try {
            List<String> lines = FileUtils.readLines(file, "utf-8");
            System.out.println(lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
