package com.gof.single.sync;

/**
 * <p>Description: [单例双重锁]</p>
 * 当两个线程(或以上)都走过步骤1，线程1赋值，线程2不检查会创建出超过一个的对象,违反单例原则
 * Created on 2016年12月27日
 * @author  <a href="mailto: zuorenzhi@clt.com">左仁智</a>
 * @version 1.0 
 * Copyright (c) 2016 北京柯莱特科技有限公司 交付部
 */
public class Singleton {

	private static Singleton uniqueInstance = null;
	
	private static Object [] objects = new Object[0];

	public static Singleton getInstance() {

		if (uniqueInstance == null) {//1
			synchronized (objects) {
				if (uniqueInstance == null) {
					uniqueInstance = new Singleton();
				}
			}
		}
		return uniqueInstance;
	}

	// Other methods...
}
