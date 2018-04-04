package com.json;

import java.util.*;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.junit.Test;

/**
 * Description: fastJson测试api
 * Created on 2017/7/17 14:31
 *
 * @author zuorenzhi
 *         Copyright (c) 2017年 国美融通科技
 */
public class FastJsonApi {

    @Test
    public void test(){

        JSONObject jsonObject = new JSONObject();
        System.out.println(jsonObject);
        doJson(jsonObject);
        System.out.println(jsonObject);
    }

    private void doJson(JSONObject jsonObject) {
        jsonObject.put("name",123);
        jsonObject.put("name2",1234);
    }


    @Test
    public void getNone(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name","张三");
        String age = jsonObject.getString("age");
        System.out.printf("age=%s%n",age);
        JSONObject color = jsonObject.getJSONObject("color");
//        JSONObject color = jsonObject.getJSONObject("color").getJSONObject("haha");
        JSONObject aliResponse = null;
        try {
            aliResponse = jsonObject.getJSONObject("service").getJSONObject("serviceBody").getJSONObject("response");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(aliResponse);
    }


    @Test
    public void parseObjectMap() {
        Person person = new Person();
        person.setId("12");
        person.setAge(23);
        person.setName("张三");

        String jsonStr = JSON.toJSONString(person);
        System.out.println(jsonStr);

        Map hashMap = JSON.parseObject(jsonStr, HashMap.class);
        System.out.println(hashMap);
    }

    @Test
    public void objArr2JSONArray() {
        Object[] objArr = new Object[2];
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("action", "test");
        objArr[0] = jsonObject;
        objArr[1] = "{\"name\":\"hello\"}";
        System.out.println(Arrays.toString(objArr));
        JSONArray parseArray = JSON.parseArray(Arrays.toString(objArr));
        System.out.println(parseArray);
    }

    @Test
    public void parseObjectClass() {
        System.out.println("javabean转化示例开始----------");
        Person person = new Person("1", "fastjson", 1);

        //这里将javabean转化成json字符串
        String jsonString = JSON.toJSONString(person);
        System.out.println(jsonString);
        //这里将json字符串转化成javabean对象,
        person = JSON.parseObject(jsonString, Person.class);
        System.out.println(person.toString());

        System.out.println("javabean转化示例结束----------");
    }

    @Test
    public void listArray() {
        System.out.println("List<javabean>转化示例开始----------");

        Person person1 = new Person("1", "fastjson1", 1);
        Person person2 = new Person("2", "fastjson2", 2);
        List<Person> personList = new ArrayList<Person>();
        personList.add(person1);
        personList.add(person2);
        String jsonString = JSON.toJSONString(personList);
        System.out.println("json字符串:" + jsonString);

        //解析json字符串
        List<Person> persons2 = JSON.parseArray(jsonString, Person.class);
        //输出解析后的person对象，也可以通过调试模式查看persons2的结构
        System.out.println("person1对象：" + persons2.get(0).toString());
        System.out.println("person2对象：" + persons2.get(1).toString());

        System.out.println("List<javabean>转化示例结束----------");
    }

    @Test
    public void parseObject() {
        System.out.println("List<String>转化示例开始----------");
        List<String> list = new ArrayList<String>();
        list.add("fastjson1");
        list.add("fastjson2");
        list.add("fastjson3");
        String jsonString = JSON.toJSONString(list);
        System.out.println("json字符串:" + jsonString);

        //解析json字符串
        List<String> list2 = JSON.parseObject(jsonString, new TypeReference<List<String>>() {
        });
        System.out.println(list2.get(0) + "::" + list2.get(1) + "::" + list2.get(2));
        System.out.println("List<String>转化示例结束----------");

    }

    @Test
    public void typeReference() {
        System.out.println(" List<Map<String,Object>>转化示例开始 ----------");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("key1", 1);
        map2.put("key2", 2);
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        list.add(map);
        list.add(map2);
        String jsonString = JSON.toJSONString(list);
        System.out.println("json字符串:" + jsonString);
        //解析json字符串
        List<Map<String, Object>> list2 = JSON.parseObject(jsonString, new TypeReference<List<Map<String, Object>>>() { });

        System.out.println("map的key1值" + list2.get(0).get("key1"));
        System.out.println("map的key2值" + list2.get(0).get("key2"));
        System.out.println("ma2p的key1值" + list2.get(1).get("key1"));
        System.out.println("map2的key2值" + list2.get(1).get("key2"));
    }

    @Test
    public void typeReferenceObject() {
        Person person = new Person("NO222", "zhangsan", 55);
        //解析json字符串
        Person personResult = JSON.parseObject(JSON.toJSONString(person), new TypeReference<Person>() {});
        System.out.println(personResult);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
   static class Person {
        private String id;
        private String name;
        private int age;

        @Override
        public String toString() {
//			return "Person [age=" + age + ", id=" + id + ", name=" + name + "]";
            return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
        }
    }
}

  
