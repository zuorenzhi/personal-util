package com.junit;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.Collator;
import java.util.*;

@Slf4j
public class StringBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(StringBean.class);


    @Test
    public void testFor(){
        log.info("【StringBean-->testFor】 入参是 [1]");
        boolean flag = 5 > 3;
        while (true) {
            if (flag) {
                log.info("【StringBean-->testFor】 入参是 [2]");
                break;
            }
            log.info("【StringBean-->testFor】 入参是 [3]");
        }
        log.info("【StringBean-->testFor】 入参是 [4]");
    }

    @Test
    public void valueOf(){
        Object obj = null;
        System.out.println(String.valueOf(new Integer(22)));
        System.out.println(String.valueOf(obj));//
        System.out.println(String.valueOf(null));//不行
    }


    @Test
    public void link(){
        int max = 36;
        StringBuffer sb = new StringBuffer();
        for (int i = 1; i <= max; i++) {
            sb.append(i);
            if (i < max)
                sb.append(",");
        }
        System.out.println(sb.toString());
        System.out.println(sb.toString().length());
    }


    /** 
     * Discription: [方法功能中文描述]
     * url: http://tool.oschina.net/apidocs/apidoc?api=jdk-zh
     * Created on: 2017-12-01 20:01 
     * param :  
     * return :  
     * author : [左仁智] 
     */
    @Test
    public void formatApi(){
        Calendar c = Calendar.getInstance();
        String s = String.format("Duke's Birthday: %1$tm %1$te,%1$tY", c);//年月日
        System.out.println(s);
    }

    @Test
    public void format(){
        System.out.println(String.format("%.2f",23.45531D));
        System.out.println(String.format("%.2f",22.0));
        System.out.println(String.format("%.2f",22.20));
        System.out.println(String.format("%.2f",23.415D));
        System.out.println(String.format("%.2f",23.414D));
        System.out.println(String.format("%.2f",23D));
        System.out.println(String.format("%.2f",0.12345f));
        System.out.println(String.format("%.2f",23.55599999D));
        System.out.println(String.format("%.2f",23.000000002D));
        System.out.println(String.format("%.2f",new BigDecimal(23.555599999D)));
    }


    @Test
    public void str2Set() {
        String str = "addbbcc";
        char[] charArray = str.toCharArray();
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];
            list.add(String.valueOf(c));
        }
        System.out.println(list);
        HashSet<String> set = new HashSet<>(list);
        System.out.println(set);
    }

    @Test
    public void replace() {
        String str = "/app/h5/44444444000";
//		str.replaceAll("/app", "").replaceAll("/h5", "");
        System.out.println(str.replaceAll("/app", "").replaceAll("/h5", ""));
    }

    @Test
    public void testClass() {
        String str = "";
        System.out.println(str.getClass() == String.class);
        System.out.println(str instanceof String);

    }

    @Test
    public void compareTo() {

        String version1 = "v1.0.0";
        String version2 = "v2.3";
        String version3 = "v1.0";

        System.out.println(version1.compareTo(version2));
        System.out.println(version1.compareTo(version3));
    }

    @Test
    public void treeSet() {
        //entity实现or匿名内部类[不干扰实体类]
        TreeSet<Object> treeSet = new TreeSet<Object>(new Comparator<Object>() {
            @Override
            public int compare(Object o1, Object o2) {
                return Integer.valueOf(o1.toString()).compareTo(Integer.valueOf(o2.toString()));
            }

        });
        treeSet.add("333");
        treeSet.add("23");
        treeSet.add("999");
        treeSet.add("71");
        System.out.println(treeSet);
        System.out.println(treeSet.first());
        System.out.println(treeSet.last());
    }

    @Test
    @SuppressWarnings("unchecked")
    public void comparator() {
//		Comparator cmp = Collator.getInstance(java.util.Locale.CHINA);
        Comparator cmp = Collator.getInstance();
        int compare = cmp.compare("英国", "中国");//-1
//		int compare = cmp.compare("中国", "英国");//1
        System.out.println(compare);
        String arr[] = new String[]{"中国", "英国", "太极"};
//		String arr1[] = new String[4];
//		String arr2[] = {};
        Arrays.sort(arr, cmp);
        System.out.println(Arrays.toString(arr));
        System.out.println(arr.getClass());//class [Ljava.lang.String;
        System.out.println(String[].class);//class [Ljava.lang.String;
        System.out.println(Arrays.class);
    }

    @Test
    public void uuid() throws UnsupportedEncodingException {
        System.out.println(UUID.randomUUID());
        System.out.println(UUID.nameUUIDFromBytes("zuoyou".getBytes("utf-8")));
    }

    @Test
    public void getUUID() {
        System.out.println(UUID.randomUUID());
        String str = UUID.randomUUID().toString().replaceAll("-", "");
        System.out.println(str);
    }

    /**看注释，传入beginIndex-endIndex*/
    @Test
    public void substring() {
        String str = "/t/cms/www/default/alone/file2Bean.html";
        System.out.println(str.substring(0,str.lastIndexOf("/")+1));
        System.out.println(str.substring(str.lastIndexOf("/")+1,str.lastIndexOf(".")));
    }

    @Test
    public void split() {
        String str = "a,b,c,,";
        String[] split = str.split(",");
        log.info("【StringBean-->test】 size={},arr={}", split.length, Arrays.toString(split));
    }

    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(10000000);


    }
}

