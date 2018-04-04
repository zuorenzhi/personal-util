package com.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import org.junit.Test;

/**
 * Description: fastJson测试注解 jsonField
 * Created on 2017/7/17 14:32
 *
 * @author zuorenzhi
 *         Copyright (c) 2017年 国美融通科技
 */
public class FastJsonField {

    /**
     * <p>Discription:[测试被JSONField注解的字段在json转换中如何传递]</p>
     * Created on 2016-9-20
     *
     * @author:[左仁智]
     */
    @Test
    public void jSONField() {
        Person person = new Person();
        person.setAge(10);
        person.setName2("dog");
        person.setAppPlatform("IOS");
        System.out.println(person);

        String str = "{\"age\":10,\"app_platform\":\"IOS\",\"name\":\"dog\"}";
        Person ss = JSON.parseObject(str, Person.class);
        System.out.println(ss);
        System.out.println(ss.getAppPlatform());
    }

    @Test
    public void refrenceJson() {
        String str = "{\"age\":10,\"app_platform\":\"IOS\",\"name\":\"dog\"}";
        Person ss = JSON.parseObject(str, new TypeReference<Person>(){});
        System.out.println(ss);
        System.out.println(ss.getAppPlatform());
        System.out.println(ss.getName2());
    }

    /**成员内部内实例化方式*/

    @Setter
    @Getter
    static class Person {
        int age;
        @JSONField(name = "name")
        String name2;
        @JSONField(name="app_platform")
        String appPlatform;

        public String toString() {
            return JSON.toJSONString(this);
        }
    }


}


