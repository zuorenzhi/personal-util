package com.JDK.collections;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * Description : [HashMap学习]
 * url:http://geek.csdn.net/news/detail/246700
 * desc:默认的负载因子大小为0.75
 * 当一个map填满了75%的bucket时候，和其它集合类(如ArrayList等)一样，
 * 将会创建原来HashMap大小的两倍的bucket数组，来重新调整map的大小，
 * 并将原来的对象放入新的bucket数组中
 * Created on : 2017-12-05 14:29
 * author : [左仁智]
 * version : 1.0
 * Copyright (c) 2017 国美金控-美借
 */
@Slf4j
public class HashMapBean {

    public static void main(String[] args) {

    }

    /**
     * <p>Discription:[从map中获取不存在的key]</p>
     * Created on 2017年2月3日
     * @author:[左仁智]
     */
    @Test
    public void getNullKey() {
        HashMap<String, BigDecimal> hashMap = new HashMap<String, BigDecimal>();
        hashMap.put("zz", new BigDecimal(0.01).setScale(2, BigDecimal.ROUND_HALF_UP));
        System.out.printf("**********%s", hashMap.get("z2z"));
    }

    @Test
    public void constructor() {
        HashMap<String, Object> map = new HashMap<String, Object>() {
            {
                put("name", "张三");
                put("age", 22);
                put("sex", "male");
            }
        };
        log.info("【HashMapBean-->test】 创建对象时，同时初始化部分参数 constructor={}", JSON.toJSONString(map));
        map.put("height", 172.02D);
        map.put("job", "seller");
        log.info("【HashMapBean-->test】 创建对象时，同时初始化部分参数 constructor={}", JSON.toJSONString(map));
    }

    /**
     * 王鹏,同内容的hashmap的equals比较
     */
    @Test
    public void test() {
        Map<String, String> map = new HashMap<String, String>();
        System.out.println(map.size());
        map.put("key", "value");
        Map<String, String> map1 = new HashMap<String, String>();
        map1.put("key", "value");
        System.out.println(map.equals(map1));
    }
}
