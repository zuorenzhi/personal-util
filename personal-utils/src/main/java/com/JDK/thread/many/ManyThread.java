package com.JDK.thread.many;

import org.junit.Test;

public class ManyThread {
	@Override
	public String toString() {
		return super.toString();
	}

	public static Thread[] getThreads() {
		Thread[] threads = new Thread[10];
		for (int i = 0; i < threads.length; i++) {
			// final int num = i;
			final Integer num = new Integer(i);
			threads[i] = new Thread(new Runnable() {
				@Override
				public void run() {
					int j = 2;
					while(j-- > 0){
						System.out.println("thread index:" + num+ ";id:"+ Thread.currentThread().getId());
					}
				}
			});
		}
		return threads;
	}

	/**
	 * java 并发实现原理： 是否可以利用多线程，实现10个并发执行
	 */
	@Test
	public void many() {
		for (Thread thread : getThreads()) {
			thread.start();
		}
	}

}
