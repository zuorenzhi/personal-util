package com.JDK.thread.impl;

/**
 * 继承Thread 多线程
 * @author zuorz
 *
 */
public class ExtendsThread extends Thread {

	public ExtendsThread(String str) {
		// TODO Auto-generated constructor stub
		super(str);
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for (int i = 0; i < 20; i++) {
			System.out.println(i+" "+ getName());
			try {
				sleep((int)(Math.random()*1000));
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		System.out.println("done! "+ getName());
	}
	
	public static void main(String[] args) {
		new ExtendsThread("Thread1").start();
		new ExtendsThread("Thread2").start();
	}
}
