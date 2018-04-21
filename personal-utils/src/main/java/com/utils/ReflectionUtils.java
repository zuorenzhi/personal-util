package com.utils;

import com.alibaba.fastjson.JSON;
import com.dto.DailyNumDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * <p>Description: [反射工具类]<br>[操作对象属性getter-setter;调用对象setter-getter]</p>
 * Created on 2017年3月3日
 *
 * @author <a href="mailto: zuorenzhi@clt.com">左仁智</a>
 * @version 1.0
 * Copyright (c) 2017 北京柯莱特科技有限公司 交付部
 */
@Slf4j
public class ReflectionUtils {

    /**
     * <p>Discription:[获取对象obj的filedName值]</p>
     * Created on 2017年3月8日
     *
     * @param obj
     * @param filedName
     * @return
     * @author:[zuorenzhi]
     */
    public static Object getFiledValue(Object obj, String filedName) {
        if (null == obj || StringUtils.isBlank(filedName)) {
            return null;
        }
        try {
            Field field = obj.getClass().getDeclaredField(filedName);
            if (filedName.equals(field.getName())) {
                field.setAccessible(true);
                return field.get(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * <p>Discription:[给对象属性赋值]</p>
     * Created on 2017年3月8日
     *
     * @param obj
     * @param filedName
     * @param objectValue
     * @author:[zuorenzhi]
     */
    public static void setFiledValue(Object obj, String filedName, Object objectValue) {
        if (null == obj || StringUtils.isBlank(filedName)) {
            return;
        }
        try {
            Field field = obj.getClass().getDeclaredField(filedName);
            if (filedName.equals(field.getName())) {
                field.setAccessible(true);
                field.set(obj, objectValue);
                ;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * <p>Discription:[执行obj的methodName方法]</p>
     * Created on 2017年3月8日
     *
     * @param obj
     * @param methodName
     * @return
     * @author:[zuorenzhi]
     */
    public static Object invokeGetMethod(Object obj, String methodName) {
        if (null == obj || StringUtils.isBlank(methodName)) {
            return null;
        }
        try {
            Method method = obj.getClass().getMethod(methodName);
            return method.invoke(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * <p>Discription:[执行obj的methodName方法]</p>
     * Created on 2017年3月8日
     *
     * @param obj
     * @param methodName
     * @param objValue
     * @notice execute setMethod  if has parameter,param is nessary
     * @author:[zuorenzhi]
     */
    public static void invokeSetMethod(Object obj, String methodName, Object objValue) {
        if (null == obj || StringUtils.isBlank(methodName)) {
            return;
        }
        try {
            Method method = obj.getClass().getMethod(methodName, Integer.class);
            method.invoke(obj, objValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * <p>字符串排序</p>
     * Created on: 2018-04-18 16:51
     * @param
     * @return
     * @author renzhi.zuo
     */
    @SuppressWarnings("unchecked")
    private static void sortListByFieldName(List list, final String sortFiledName) {
        Collections.sort(list, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                String str0 = ReflectionUtils.getFiledValue(o1, sortFiledName).toString();
                String str1 = ReflectionUtils.getFiledValue(o2, sortFiledName).toString();
                return str0.compareTo(str1);
            }
        });
    }


    public void test(){
        List<DailyNumDTO> list = new ArrayList<>();
        list.add(DailyNumDTO.getInstance("2018-04-18", 22));
        list.add(DailyNumDTO.getInstance("2017-04-16", 22));
        list.add(DailyNumDTO.getInstance("2018-04-15", 22));
        list.add(DailyNumDTO.getInstance("2015-03-21", 22));
        list.add(DailyNumDTO.getInstance("2018-03-21", 22));
        list.add(DailyNumDTO.getInstance("2018-04-17", 22));

        log.info("【ReflectionUtils-->main】 入参是 [list={}]", JSON.toJSONString(list));
        sortListByFieldName(list,"day");
        log.info("【ReflectionUtils-->main】 入参是 [list={}]", JSON.toJSONString(list));

        //todo treeMap 自动按key 排序:前提是key自动满足可排序条件
//        TreeMap<String, Integer> treeMap = new TreeMap<>();
//        for (DailyNumDTO dailyNumDTO : list) {
//            treeMap.put(dailyNumDTO.getDay(), dailyNumDTO.getNum());
//        }
//        for (Map.Entry<String, Integer> entry : treeMap.entrySet()) {
//            System.out.println(entry.getKey() + "=" + entry.getValue());
//        }
//        System.out.println("====================");
//        Iterator<Map.Entry<String, Integer>> iterator = treeMap.entrySet().iterator();
//        while (iterator.hasNext()) {
//            Map.Entry<String, Integer> entry = iterator.next();
//            System.out.println(entry.getKey() + "=" + entry.getValue());
//        }

//		System.out.println("2018-04-18".compareTo("2018-04-18"));
//		System.out.println("2018-04-18".compareTo("2018-04-19"));
//		System.out.println("2018-04-18".compareTo("2018-04-17"));

    }

    public static void main(String[] args) {
        Double d = 5*3600D;

    }

}




