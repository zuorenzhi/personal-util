package com.JDK.thread.impl;



public class ImplRunnable implements Runnable {
	Thread runnableThread = null;
	public ImplRunnable() {
		runnableThread = new Thread(this,"Thread");
		runnableThread.start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for (int i = 0; i < 20; i++) {
			System.out.println(i+"次"+runnableThread.getName());
			try {
				Thread.sleep((int)(Math.random()*1000));
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		System.out.println("Done !"+runnableThread.getName());
	}

	public static void main(String[] args) {
		new ImplRunnable();

		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("--------thread");
			}
		}).start();
	}
}
