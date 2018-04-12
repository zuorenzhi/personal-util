package com.junit;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * java
 * Created on : 2018年04月11日
 *
 * @author : [左仁智]
 * @see https://www.cnblogs.com/xuemaxiongfeng/p/3236591.html
 */

/**
 * 正则表达式中常见通配符:

 对于单字符串比较而言,使用正则表达式没有什么优势.
 Regex的真正强大之处在于体现在包括字符类和量词(*,+,?)的更复杂的模式上.
 字符类包括:
 \d  数字
 \D  非数字
 \w  单字字符(0-9,A-Z,a-z)
 \W 非单字字符
 \s  空白(空格符,换行符,回车符,制表符)
 \S 非空白
 []  由方括号内的一个字符列表创建的自定义字符类
 .   匹配任何单个字符
 下面的字符将用于控制将一个子模式应用到匹配次数的过程.
 ?  重复前面的子模式0次到一次
 *  重复前面的子模式0次或多次
 + 重复前面的子模式一次到多次
 */
@Slf4j
public class PatternBean {

    private static final Pattern pattern = Pattern.compile("a*b");

    public static void main(String[] args) {
        Matcher m = pattern.matcher("aaaaab");
        boolean b = m.matches();
        System.out.println(b);
    }

    /**实例一:*/
    @Test
    public void test1() {
        //正则式是最简单的能准确匹配一个给定String的模式,模式与要匹配的文本是等价的.
        //静态的Pattern.matches方法用于比较一个String是否匹配一个给定模式.例程如下:
        boolean result = Pattern.matches("java", "java");
        log.info("【PatternBean-->testI】 入参是 [result={}]", result);
    }

    /**实例二:*/
    @Test
    public void test2() {
        String[] dataArr = {"moon", "mon", "moon", "mono"};
        for (String str : dataArr) {
            String patternStr = "m(o+)n";
            boolean result = Pattern.matches(patternStr, str);
            if (result) {
                System.out.println("字符串" + str + "匹配模式" + patternStr + "成功");
            } else {
                System.out.println("字符串" + str + "匹配模式" + patternStr + "失败");
            }
        }
//        模式是”m(o+)n”,它表示mn中间的o可以重复一次或多次,因此moon,mon,mooon能匹配成功,而mono在n后多了一个o,和模式匹配不上.
//        注:+表示一次或多次;?表示0次或一次;*表示0次或多次.
    }

    /**实例三:*/
    @Test
    public void test3() {
        String[] dataArr = {"ban", "ben", "bin", "bon", "bun", "byn", "baen"};
        for (String str : dataArr) {
            String patternStr = "b[aeiou]n";
            boolean result = Pattern.matches(patternStr, str);
            if (result) {
                System.out.println("字符串" + str + "匹配模式" + patternStr + "成功");
            } else {
                System.out.println("字符串" + str + "匹配模式" + patternStr + "失败");
            }
        }
//    注:方括号中只允许的单个字符,模式“b[aeiou]n”指定,只有以b开头,n结尾,中间是a,e,i,o,u中任意一个的才能匹配上,
// 所以数组的前五个可以匹配,后两个元素无法匹配.
//    方括号[]表示只有其中指定的字符才能匹配.
    }


    /**实例4:*/
    @Test
    public void test4() {
        String[] dataArr = {"been", "bean", "boon", "buin", "bynn"};
        for (String str : dataArr) {
            String patternStr = "b(ee|ea|oo)n";
            boolean result = Pattern.matches(patternStr, str);
            if (result) {
                System.out.println("字符串" + str + "匹配模式" + patternStr + "成功");
            } else {
                System.out.println("字符串" + str + "匹配模式" + patternStr + "失败");
            }
        }

//        如果需要匹配多个字符,那么[]就不能用上了,这里我们可以用()加上|来代替,()表示一组,|表示或的关系,模式b(ee|ea|oo)n就能匹配been,bean,boon等.
//                因此前三个能匹配上,而后两个不能.
    }


    @Test
    public void test5() {
        String[] dataArr = {"1", "10", "101", "1010", "100+"};
        for (String str : dataArr) {
            String patternStr = "\\d+";
            boolean result = Pattern.matches(patternStr, str);
            if (result) {
                System.out.println("字符串" + str + "匹配模式" + patternStr + "成功");
            } else {
                System.out.println("字符串" + str + "匹配模式" + patternStr + "失败");
            }
        }
//    注:从前面可以知道,\d表示的是数字,而+表示一次或多次,所以模式\d+就表示一位或多位数字.
//            因此前四个能匹配上,最后一个因为+号是非数字字符而匹配不上.
    }


    @Test
    public void test6() {
        String[] dataArr = {"a100", "b20", "c30", "df10000", "gh0t"};
        for (String str : dataArr) {
            String patternStr = "\\w+\\d+";
            boolean result = Pattern.matches(patternStr, str);
            if (result) {
                System.out.println("字符串" + str + "匹配模式" + patternStr + "成功");
            } else {
                System.out.println("字符串" + str + "匹配模式" + patternStr + "失败");
            }
        }

//    模式\w+\d+表示的是以多个单字字符开头,多个数字结尾的字符串,因此前四个能匹配上,最后一个因为数字后还含有单字字符而不能匹配.
    }

    @Test
    public void test7() {
        //todo
        String str = "薪水,职位 姓名;年龄 性别";
        String[] dataArr = str.split("\\[,\\s;]");
        for (String strTmp : dataArr) {
            System.out.println(strTmp);
        }
//    String类的split函数支持正则表达式,上例中模式能匹配”,”,单个空格,”;”中的一个,split函数能把它们中任意一个当作分隔符,将一个字符串劈分成字符串数组.
    }


    @Test
    public void test8() {
        String str = "2007年12月11日";
        Pattern p = Pattern.compile("[年月日]");
        String[] dataArr = p.split(str);
        for (String strTmp : dataArr) {
            System.out.println(strTmp);
        }
//    Pattern是一个正则表达式经编译后的表现模式 ,它的split方法能有效劈分字符串.
//            注意其和String.split()使用上的不同.
    }

    @Test
    public void test9() {
        String str = "10元 1000人民币 10000元 100000RMB";
//        str = str.replaceAll("(\\d+)(元|人民币|RMB)", "￥");//yuanlai
        str = str.replaceAll("(元|人民币|RMB)", "￥");
        System.out.println(str);

//    上例中,模式“(\d+)(元|人民币|RMB)”按括号分成了两组,第一组\d+匹配单个或多个数字,
// 第二组匹配元,人民币,RMB中的任意一个,替换部分表示第一个组匹配的部分不变,其余组替换成￥.
//
//            替换后的str为￥10 ￥1000 ￥10000 ￥100000
    }

    @Test
    public void test10() {
        Pattern p = Pattern.compile("m(o+)n", Pattern.CASE_INSENSITIVE);
        // 用Pattern类的matcher()方法生成一个Matcher对象
        Matcher m = p.matcher("moon mooon Mon mooooon Mooon");
        StringBuffer sb = new StringBuffer();

        // 使用find()方法查找第一个匹配的对象
        boolean result = m.find();
// 使用循环找出模式匹配的内容替换之,再将内容加到sb里
        while (result) {
            m.appendReplacement(sb, "moon");
            result = m.find();
        }
// 最后调用appendTail()方法将最后一次匹配后的剩余字符串加到sb里；
        m.appendTail(sb);
        System.out.println("替换后内容是" + sb.toString());
    }

    @Test
    public void test11() {
//    除了用+表示一次或多次,*表示0次或多次,?表示0次或一次外,还可以用{}来指定精确指定出现的次数,X{2,5}表示X最少出现2次,
// 最多出现5次;X{2,}表示X最少出现2次,多则不限;X{5}表示X只精确的出现5次.例程:
        String[] dataArr = {"google", "gooogle", "gooooogle", "goooooogle", "ggle"};

        for (String str : dataArr) {
            String patternStr = "g(o{2,5})gle";
            boolean result = Pattern.matches(patternStr, str);
            if (result) {
                System.out.println("字符串" + str + "匹配模式" + patternStr + "成功");
            } else {
                System.out.println("字符串" + str + "匹配模式" + patternStr + "失败");
            }
        }
    }

    @Test
    public void test12() {

//    -表示从..到…,如[a-e]等同于[abcde]
        String[] dataArr = {"Tan", "Tbn", "Tcn", "Ton", "Twn"};

        for (String str : dataArr) {
            String regex = "T[a-c]n";

            boolean result = Pattern.matches(regex, str);
            if (result) {
                System.out.println("字符串" + str + "匹配模式" + regex + "成功");
            } else {
                System.out.println("字符串" + str + "匹配模式" + regex + "失败");
            }
        }
    }

    @Test
    public void test13() {
//        实例十三:不区分大小写匹配.正则表达式默认都是区分大小写的,使用了Pattern.CASE_INSENSITIVE则不对大小写进行区分.

        String patternStr = "ab";
        Pattern pattern = Pattern.compile(patternStr, Pattern.CASE_INSENSITIVE);
        String[] dataArr = {"ab", "Ab", "AB"};
        for (String str : dataArr) {
            Matcher matcher = pattern.matcher(str);
            if (matcher.find()) {
                System.out.println("字符串" + str + "匹配模式" + patternStr + "成功");
            }
        }
    }

    @Test
    public void test14() {
//    实例十四:使用正则表达式劈分字符串.注意这里要把复杂的模式写在前面,否则简单模式会先匹配上.
        String input = "职务=GM 薪水=50000 , 姓名=职业经理人 ; 性别=男  年龄=45 ";
        String patternStr = "(\\s*,\\s*)|(\\s*;\\s*)|(\\s+)";
        Pattern pattern = Pattern.compile(patternStr);

        String[] dataArr = pattern.split(input);
        for (String str : dataArr) {
            System.out.println(str);
        }
    }

    @Test
    public void test15() {
//    实例十五:解析正则表达式中的文字,对应第一个小括号括起来的group1.

//        String regex="<(\w+)>(\w+)</>";//old
        String regex = "<(\\w+)>(\\w+)<\\w+\\/>";
        Pattern pattern = Pattern.compile(regex);

        String input = "<name>Bill</name><salary>50000</salary><title>GM</title>";
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            System.out.println(matcher.group(2));
        }
    }

    @Test
    public void test16() {
//    实例十六:将单词数字混合的字符串的单词部分大写.
        String regex = "([a-zA-Z]+[0-9]+)";
        Pattern pattern = Pattern.compile(regex);
        String input = "age45 salary500000 50000 title";
        Matcher matcher = pattern.matcher(input);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            String replacement = matcher.group(1).toUpperCase();
            matcher.appendReplacement(sb, replacement);
        }
        matcher.appendTail(sb);
        System.out.println("替换完的字串为" + sb.toString());
    }


}
