package com.gof.observer;

import com.gof.observer.notice.BossImpl;
import com.gof.observer.observer.NbaObserver;
import com.gof.observer.notice.ReceptionImpl;
import com.gof.observer.observer.StockObserver;

public class Client {

	public static void main(String[] args) {
		
		bossNotify();
		receptionNotify();
	}

	private static void bossNotify() {
		BossImpl huhansan = new BossImpl();
		
		StockObserver stockObserver = new StockObserver("张三", huhansan);
		
		NbaObserver nbaObserver = new NbaObserver("李四", huhansan);
		
		huhansan.add(stockObserver);
		huhansan.add(nbaObserver);
		
		huhansan.set("我胡汉三回来了!");
			
		huhansan.notifyMes();
	}
	
	private static void receptionNotify() {
		ReceptionImpl receptionImpl = new ReceptionImpl();
		
		StockObserver stockObserver = new StockObserver("张三", receptionImpl);
		
		NbaObserver nbaObserver = new NbaObserver("李四", receptionImpl);
		
		receptionImpl.add(stockObserver);
		receptionImpl.add(nbaObserver);
		
		receptionImpl.set("老板回来了!");
		
		receptionImpl.notifyMes();
	}
	
}
