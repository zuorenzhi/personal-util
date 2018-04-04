package com.JDK.thread.threadlocal;

public class ThreadLocalInteger {

	private static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>(){
		protected Integer initialValue() {
			return 10;
		};
	};
	
	public static Integer getValue(){
		return threadLocal.get();
	}
	
	public static void setValue(Integer value){
		threadLocal.set(value);
	}
	/**
	 * ThreadLocal的静态内部类 ThreadLocalMap
	 * Thread 的变量 threadLocals 类型为  ThreadLocal.ThreadLocalMap
	 * 1.ThreadLocal存数据：存到当前线程的threadLocals，即ThreadLocal.ThreadLocalMap.Entry。其中以ThreadLocal自身为key,value为值
	 * 2.ThreadLocal取数据：取当前线程的threadLocals，即ThreadLocal.ThreadLocalMap.Entry.value。其中ThreadLocal自身为key
	 */
	public static void main(String[] args) {
		System.out.println(threadLocal.get());
		threadLocal.set(99);
		System.out.println(threadLocal.get());
	}
	
}
