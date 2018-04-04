package com.gof.observer.observer;

import com.gof.observer.notice.SubjectService;

public class StockObserver extends Observer {

	public StockObserver(String name, SubjectService subjectService) {
		super(name, subjectService);
	}

	@Override
	public void update() {
		System.out.printf("%s,关闭股票行情，继续工作,%s%n",this.subjectService.get(),this.name);
	}

}
