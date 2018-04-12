package com.gof.single.holder;

/**
 * @see http://www.cnblogs.com/chenpi/p/5168886.html
 * <p>Description: [描述该类概要功能介绍]</p>
 * Created on 2017年4月18日
 * @author  <a href="mailto: zuorenzhi@clt.com">左仁智</a>
 * @version 1.0 
 * Copyright (c) 2017 北京柯莱特科技有限公司 交付部
 */
public class MyPrinter {

	private static class MyPrinterHolder {
        private static MyPrinter instance = new MyPrinter();
    }
    private MyPrinter(){
        System.out.println("implements3: created a MyPrint instance.");
    }
    public static  MyPrinter getInsatnce(){
        return MyPrinterHolder.instance;
    } 
    
    public static void testPrint(){
        System.out.println("hello!");
    }
    
    public void print(String str){
        System.out.println(str);
    }

    public static void main(String[] args) {
    	
	  MyPrinter.testPrint();
    	
	  //显示调用的时候才会走构造方法
      MyPrinter p = MyPrinter.getInsatnce();
      p.print("hello world.");
      
  }
    
}
